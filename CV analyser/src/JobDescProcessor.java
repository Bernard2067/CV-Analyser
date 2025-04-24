import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JobDescProcessor {
    public static Map<String, Object> ExtractRequirements(String JobDescText) {
        Map<String, Object> JobData = new HashMap<>();

        //Locating the skills the job requires
        Matcher SkillsMatcher = Pattern.compile("(?i)(skills required|desired skills|requirements|must have skills|preferred skills|should have|must have)[:\\n](.*?)(\\n\\n|$)", Pattern.DOTALL).matcher(JobDescText);
        if  (SkillsMatcher.find()) {
            String[] skills = SkillsMatcher.group(2).split("[,\\n]");
            JobData.put("Skills needed: ", Arrays.stream(skills).map(String::trim).toArray(String[]::new));
        }

        //Locating the qualifications the job requires
        Matcher QualMatcher = Pattern.compile("(?i)(qualifications|education required|qualifications needed)[:\\n](.*?)(\\n\\n|$)", Pattern.DOTALL).matcher(JobDescText);
        if  (QualMatcher.find()) {
            String[] qualifications = QualMatcher.group(2).split("[,\\n]");
            JobData.put("Qualifications needed: ", Arrays.stream(qualifications).map(String::trim).toArray(String[]::new));
        }

        //Returning the information found in the job description
        return JobData;
    }
}
