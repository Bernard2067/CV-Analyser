import java.util.Map;
import java.util.Scanner;
import java.sql.SQLException;

public class DialogueManager {
    //Publicly declare the file paths of the Job Description and the Resume that were input ealier
    public static String JobDescFPath;
    public static String ResumeFPath;

    public static void Welcome() throws SQLException {
        System.out.println("\n----------Welcome to the CV Analyser----------");

        String intro = "";
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter which option you would like to do:\n[1] Enter a new CV Job Description Comparison \n[2] View the Database\n[3] Exit");
        intro = input.nextLine();
        if (intro.equalsIgnoreCase("1")) {
            DialogueManager.RunFilePathInput();
        }

        else if (intro.equalsIgnoreCase("2")) {
            DatabaseManager.ViewAll();
        }

        else if (intro.equalsIgnoreCase("3")) {
            System.exit(1);
        }

        while (!intro.equalsIgnoreCase("1") && !intro.equalsIgnoreCase("2") && !intro.equalsIgnoreCase("3")) {
            System.out.println("Please enter a valid option");
            intro = input.nextLine();
        }
    }

    public static void RunFilePathInput() throws SQLException {

        String ResumeFPConfirm = ("N");

        //While loop for user confirmation
        while  (ResumeFPConfirm.toUpperCase().equals("Y")) {
            System.out.print("Thank you for confirming.");
            break;
        }
        while  (ResumeFPConfirm.toUpperCase().equals("N")) {
            Scanner FileLine = new Scanner(System.in);
            System.out.print("Please enter the file path of your CV file: \n ");

            //Scanning the user input for the file path
            ResumeFPath = FileLine.nextLine();
            System.out.print("File path you entered is: \n" + ResumeFPath + "\n");

            //Scanning the user input to confirm if they entered the correct file path
            Scanner Confirmation = new Scanner(System.in);
            System.out.print("Please confirm that this is the right file path Y/N: \n ");
            ResumeFPConfirm = Confirmation.nextLine();
        }


        String JobDescFPConfirm = ("N");

        while  (JobDescFPConfirm.toUpperCase().equals("Y")) {
            System.out.print("Thank you for confirming.");
            break;
        }

        while  (JobDescFPConfirm.toUpperCase().equals("N")) {
            //Scanning the user inputs to get the file path for the job description
            Scanner FileLine2 = new Scanner(System.in);
            System.out.print("Please enter the file path of the job description they are applying for: \n");
            JobDescFPath = FileLine2.nextLine();

            //Scanning the user input to confirm it is the correct file path
            Scanner Confirmation2 = new Scanner(System.in);
            System.out.print("Please confirm that this is the right file path Y/N: \n ");
            JobDescFPConfirm = Confirmation2.nextLine();
        }
        DialogueManager.Welcome();
    }

    public static void ViewDatabase() throws SQLException {
        DatabaseManager.ViewAll();
    }
}
