package Services;

import org.example.App;
import org.example.Modals.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AppStart {
    public static boolean startProject() throws InterruptedException, IOException {
        Logger logger = new Logger();
        Scanner sc = new Scanner(System.in);


        String UserPODURL;

        logger.debugLogger("Login Started");
        logger.debugLogger("Please select your POD ");
        System.out.println("1. USW1_AWS_POD1");
        System.out.println("2. USE2_AWS_POD2");
        System.out.println("3. USW3_AWS_POD3");
        System.out.println("4. USE4_AWS_POD4");
        System.out.println("5. USW5_AWS_POD5");
        System.out.println("6. USE6_AWS_POD6");
        System.out.println("7. USW1_1_Azure_POD7");
        System.out.println("8. USW3_1_Azure_POD8");
        System.out.println("9. USW1_2_GCP_POD9");
        System.out.println("10. CAC1_AWS_POD10");
        System.out.println("11. CAC2_AZURE_POD21");
        System.out.println("12. USE1_ORACLE_POD22");
        System.out.println("13. APSE1_AWS_POD14");
        System.out.println("14. APSE2_AZURE_POD15");
        System.out.println("15. APNE1_AZURE_POD16");
        System.out.println("16. APNE2_AWS_POD19");


        System.out.println("17. APAUC1_AZURE_POD17");
        System.out.println("18. EMW1_AWS_POD11");

        System.out.println("19. EMC1_AZURE_POD13");
        System.out.println("20. UK1_AWS_POD13");
        System.out.println("21. EMSE1_AZURE_POD18");
        System.out.println("22. EMW2_GCP_POD20");

        System.out.println();
        System.out.println();
        System.out.println("Please enter the number corresponding to your POD  or enter -1 if you want to exit.");
        int podNum = sc.nextInt();

        if(podNum<1 || podNum>22)
        {
            logger.errorLogger("Please enter a valid POD number");
            System.exit(-1);
        }

        UserPODURL = LoginAPI.getPodURL(podNum) + "ma/api/v2/user/login";

        logger.debugLogger("POD URL SELECTED IS : "+UserPODURL);




        logger.debugLogger("LOGIN CALL");


        String username, password;

        logger.debugLogger("Please Enter your username:");
        username = sc.next();

        logger.debugLogger("Please enter your password for the username : "+username);

        password = sc.next();

        LoginResponse userSession = LoginAPI.loginCall(UserPODURL, username, password);

        if(userSession==null)
        {
          boolean started =  AppStart.startProject();
          if(started == false)
          {
              logger.errorLogger("Sorry Unable to start for now, please try after sometime");
              System.exit(-1);
          }

        }
        logger.debugLogger("User has successfully logged in");

       InitiatorTaskResponse taskInitiatorResponse = InitiateTask.jobLevelMeteringInitiator(userSession);
       if(taskInitiatorResponse == null || taskInitiatorResponse.getJobId() == null)
       {
           taskInitiatorResponse = InitiateTask.jobLevelMeteringInitiator(userSession);

           if( taskInitiatorResponse == null || taskInitiatorResponse.getJobId() == null)
           {
               System.out.println("SOME ISSUE -- RETRY AFTER SOME TIME");
               System.exit(-1);
           }
       }


        StatusCheckerResponse statusCheckerResponse =  JobStatusChecker.checkJobStatus(userSession,taskInitiatorResponse);
        while(statusCheckerResponse!=null && statusCheckerResponse.getStatus().equalsIgnoreCase("SUCCESS")==false)
        {
            logger.debugLogger("Recheck the status after 5 Seconds");
            Thread.sleep(5000);

            logger.debugLogger("STATUS CHECKING AGAIN");
            statusCheckerResponse   = JobStatusChecker.checkJobStatus(userSession,taskInitiatorResponse);

        }


        logger.debugLogger(statusCheckerResponse.getStatus());

        logger.debugLogger("DOWNLOADING FILE ");
        List<FileStructure> unzippedFiles =  FileDownloader.downloadFile(userSession, taskInitiatorResponse, statusCheckerResponse);


        //FOR NOW WE WILL TRY FOR CDI ONLY
      List<CDIReportStructure> report =   CSV_Manipulator.readCSV(unzippedFiles.get(0));




//      List<CSVobject_writer> csvwriterlist = CSV_Manipulator.copyReportToCSVwriter(report);
//      csvwriterlist = CSV_Manipulator.addExecutionTime(csvwriterlist);



        logger.debugLogger("After Adding Execution Time");


        report = CSV_Manipulator.addExecutionTime(report);
        logger.debugLogger("creating CSV file");
        CSV_Manipulator.CSVcreator(report);


        return true;
    }
}
