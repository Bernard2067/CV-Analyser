import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CvAnalyser {
    public static Map<String, Object> AnalyseCV(String CvText) {
        Map<String, Object> CvData = new HashMap<>();

        //Locating the applicant's location to be able to see where they are located
        Matcher AddressMatcher = Pattern.compile("(?i)(address|home address|primary residence)[:\\n](.*?)(\\n\\n|$)", Pattern.DOTALL).matcher(CvText);
        if  (AddressMatcher.find()) {
            CvData.put("Address: ", AddressMatcher.group(2).trim());
        }

        //locating the email of the applicant using regex
        Matcher EmailMatcher = Pattern.compile("[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,3}").matcher(CvText);
        if (EmailMatcher.find()) {
            //then puts it in CvData
            CvData.put("Email: ", EmailMatcher.group());
        }

        //Looking for the "skills" section in the CV
        Matcher SkillsMatcher = Pattern.compile("(?i)(skills|key skills|techical skills|necessary skills)[:\\n](.*?)(\\n\\n|$)", Pattern.DOTALL).matcher(CvText);
        if (SkillsMatcher.find()) {
            String[] skills = SkillsMatcher.group(2).split("[,\\n]");
            CvData.put("Skills: ", Arrays.stream(skills).map(String::trim).toArray(String[]::new));
        }

        //Looking for the "qualifications" or "education section to add to CvData
        Matcher QualMatcher = Pattern.compile("(?i)(qualifications|education|certificates)[:\\n](.*?)(\\n\\n|$)", Pattern.DOTALL).matcher(CvText);
        if (QualMatcher.find()) {
            CvData.put("Qualifications: ", QualMatcher.group(2).trim());
        }

        //then return all the found information
        return CvData;
    }

}
