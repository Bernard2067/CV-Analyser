//Importing necessary libraries/functions needed for txt file handling
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
    public static String readFile(String FilePath) throws IOException {
        //Using a try system to convert the FilePath string into an object to then be used as a file path
        try {
            //taking all the content in the file and making it into a readable form
            return new String(Files.readAllBytes(Paths.get(FilePath)));
        } catch (IOException e) {
            System.out.println("Error reading file " + FilePath + "\n");
            System.out.print("Details: \n" + e.getMessage() + "\n");
            return "";
        }
    }
}
