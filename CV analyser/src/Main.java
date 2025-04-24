import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws SQLException {
        DialogueManager.Welcome();

        try {
            String CvText = FileManager.readFile(DialogueManager.ResumeFPath);
            String JobDescText = FileManager.readFile(DialogueManager.JobDescFPath);

            Map<String, Object> CvData = CvAnalyser.AnalyseCV(CvText);
            Map<String, Object> JobDescData = JobDescProcessor.ExtractRequirements(JobDescText);

            System.out.println("CV Email: " + CvData.get("Email: "));

            String[] RawCvSkills = (String[]) CvData.getOrDefault("Skills: ", new  String[]{});
            String[] RawJobSkills = (String[]) JobDescData.getOrDefault("Skills needed: ", new  String[]{});

            String[] CvSkills = CleanSkills(RawCvSkills);
            String[] JobSkills = CleanSkills(RawJobSkills);

            double score = CalculateMatchScore(CvSkills, JobSkills);
            System.out.printf("The given CV matches the job description by: %.2f%%\n", score);

            DatabaseManager.InitialiseDatabase();
            String email = (String) CvData.get("Email: ");
            DatabaseManager.SaveAnalysisResult(email, score);

        } catch(IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static double CalculateMatchScore(String[] CvSkills, String[] JobSkills) {
        Set<String> CvSet = new HashSet<>(Arrays.asList(CvSkills));
        Set<String> JobDescSet = new HashSet<>(Arrays.asList(JobSkills));

        int MatchCount = 0;
        for (String skill : JobDescSet) {
            if  (CvSet.contains(skill)) {
                MatchCount++;
            }
        }
        //System.out.println("CV Skills: " + CvSet);
        //System.out.println("Job Skills Required: " + JobDescSet);
        System.out.println("Matched: " + MatchCount + "/" + JobDescSet.size());


        return JobDescSet.isEmpty() ? 0 : (MatchCount * 100) / JobDescSet.size();
    }

    public static String[] CleanSkills(String[] skills) {
        return Arrays.stream(skills)
                .filter(s -> s != null && !s.isBlank())
                .map(String::toLowerCase)
                .toArray(String[]::new);
    }


}

