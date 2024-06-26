package Services;

import Services.SummaryReport.InvokeMeterLevelReport;
import Services.SummaryReport.InvokeSumaryReport;
import org.example.App;
import org.example.Modals.*;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AppStart {
    public static boolean startProject() throws InterruptedException, IOException {
//        Logger logger = new Logger();
        Scanner sc = new Scanner(System.in);



        String UserPODURL;

        int podNum = LoginAPI.getPodNum();
        Console console = System.console();


        if (podNum == -1) {
//                logger.errorLogger("Please enter a valid Option Number");
            System.out.println("Thanks,Bye!");
            System.exit(-1);

        }
        if(podNum<1 || podNum>22)
        {
//            logger.errorLogger("Please enter a valid POD number");
            System.err.println("Please enter a valid POD Number");
            System.out.println();
            System.out.println();
         startProject();
        }



        UserPODURL = LoginAPI.getPodURL(podNum) + "ma/api/v2/user/login";

//        logger.debugLogger("Selected POD URl is : "+LoginAPI.getPodURL(podNum));
        System.out.println("Selected POD URl is : "+LoginAPI.getPodURL(podNum));


//
//        logger.debugLogger("LOGIN CALL");


        String username, password;

//        logger.debugLogger("Please Enter your username:");
        System.out.println("Please Enter your username:");
        username = sc.next();

//        logger.debugLogger("Please enter your password for the username : "+username)/;
        System.out.println("Please enter your password for the username : "+username);
        if(console!=null) {
            char[] passwordArray = console.readPassword("");
            password = new String(passwordArray);
        }
        else
            password =sc.next();

        System.out.println("Please wait, we logging in.");
        LoginResponse userSession = LoginAPI.loginCall(UserPODURL, username, password);

        if(userSession==null)
        {
          boolean started =  AppStart.startProject();
          if(started == false)
          {

              System.err.println("Sorry, there was some issue, please try again.\n If the issue still persists please check after some time");
              System.out.println();
              System.out.println();
              AppStart.startProject();

          }

        }



        int option  = -1;

        while(true) {
            System.out.println("Options :");
            System.out.println("1. Export Summary IPU Usage");
            System.out.println("With this option : You can run a job to export summary IPU usage data for the parent organization and its linked organizations such as additional production organizations,\n" +
                    " sub-organizations, and sandbox organizations for a specified date range.");
            System.out.println();
            System.out.println();
            System.out.println("2. Export Job Level IPU Usage for a Particular Service");
            System.out.println("With this option : For certain meters, you can run a job to export job-level details for a particular service and meter for a specified date range.");
            System.out.println();
            System.out.println();
            System.out.println("Please input the desired option or enter -1 to exit");
            option = sc.nextInt();


            if(option ==1 || option == 2)
                break;

            if (option == -1) {
//                logger.errorLogger("Please enter a valid Option Number");
                System.out.println("Thanks,Bye!");
                System.exit(-1);

            }
            else
            {
                System.err.println("Please enter a valid option");
                System.out.println();
                System.out.println();
            }


        }


        if(option == 1)
        {


            InvokeSumaryReport.invokeExportSummaryJob(userSession);
            System.out.println();
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.println();
            System.out.println("Please enter the option to continue : ");
            System.out.println("1. Export Job Level IPU Usage for a Particular Service ");
            System.out.println();
            System.out.println();
            System.out.println("Enter -1 to exit ");
            int input = sc.nextInt();
            if(input == 1){
                InvokeMeterLevelReport.invokeMeterLevelReport(userSession);
                System.out.println("Files Downloaded at path : "+Utilities.parentDirectory);

                System.out.println("Thanks, Bye!");
                System.exit(0);
            }
            else if (input == -1)
            {
                System.out.println("Thanks, Bye!");


                System.exit(0);
            }
            else {
                System.err.println("Enter a valid option");
                System.out.println();
                System.out.println();
            }
        }

        else {
            InvokeMeterLevelReport.invokeMeterLevelReport(userSession);
            System.out.println("Files Downloaded at path : "+Utilities.parentDirectory);




            System.out.println();
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.println();


            System.out.println("Please enter the option to continue : ");
            System.out.println("1. Export Summary IPU Usage");
            System.out.println("With this option : You can run a job to export summary IPU usage data for the parent organization and its linked organizations such as additional production organizations,\n" +
                    " sub-organizations, and sandbox organizations for a specified date range.");
            System.out.println();
            System.out.println();
            System.out.println("Enter -1 to exit ");
            int input = sc.nextInt();
            if(input == 1){
                InvokeSumaryReport.invokeExportSummaryJob(userSession);
                System.out.println("Thanks, Bye!");
                System.exit(0);
            }
            else if (input == -1)
            {
                System.out.println("Thanks, Bye!");
                System.exit(0);
            }
            else
                System.err.println("Enter a valid option");


        }



        return true;
    }

}
