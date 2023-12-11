package Services;

import Services.SummaryReport.InvokeMeterLevelReport;
import Services.SummaryReport.InvokeSumaryReport;
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

        int podNum = LoginAPI.getPodNum();

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
            System.out.println("  Please enter the number corresponding to your POD  or enter -1 if you want to exit.");
            option = sc.nextInt();

            System.out.println("OPTION IS "+option);


            if (option == -1) {
                logger.errorLogger("Please enter a valid Option Number");
                System.exit(-1);

            }
            if(option ==1 || option == 2)
                break;
        }


        if(option == 1)
        {
            logger.debugLogger("Invoking Export All data ");

            InvokeSumaryReport.invokeExportSummaryJob(userSession);
            /*Create export job*/

            System.exit(-1);
        }


        else {

            InvokeMeterLevelReport.invokeMeterLevelReport(userSession);

            /*MeterSpecific Task*/
//
//            InitiatorTaskResponse taskInitiatorResponse = InitiateTask.jobLevelMeteringInitiator(userSession);
//            if(taskInitiatorResponse == null || taskInitiatorResponse.getJobId() == null)
//            {
//                taskInitiatorResponse = InitiateTask.jobLevelMeteringInitiator(userSession,);
//
//                if( taskInitiatorResponse == null || taskInitiatorResponse.getJobId() == null)
//                {
//                    System.out.println("SOME ISSUE -- RETRY AFTER SOME TIME");
//                    System.exit(-1);
//                }
//            }
//
//
//            StatusCheckerResponse statusCheckerResponse =  JobStatusChecker.checkJobStatus(userSession,taskInitiatorResponse);
//            while(statusCheckerResponse!=null && statusCheckerResponse.getStatus().equalsIgnoreCase("SUCCESS")==false)
//            {
//                logger.debugLogger("Recheck the status after 5 Seconds");
//                Thread.sleep(5000);
//
//                logger.debugLogger("STATUS CHECKING AGAIN");
//                statusCheckerResponse   = JobStatusChecker.checkJobStatus(userSession,taskInitiatorResponse);
//
//            }
//
//
//            logger.debugLogger(statusCheckerResponse.getStatus());
//
//            logger.debugLogger("DOWNLOADING FILE ");
//            List<FileStructure> unzippedFiles =  FileDownloader.downloadFile(userSession, taskInitiatorResponse, statusCheckerResponse, Utilities.parentDirectory);


            //FOR NOW WE WILL TRY FOR CDI ONLY
//            List<CDIReportStructure> report =   CSV_Manipulator.readCSV(unzippedFiles.get(0));
//
//
//
//
////      List<CSVobject_writer> csvwriterlist = CSV_Manipulator.copyReportToCSVwriter(report);
////      csvwriterlist = CSV_Manipulator.addExecutionTime(csvwriterlist);
//
//            logger.debugLogger("After Adding Execution Time");
//
//
//            report = CSV_Manipulator.addExecutionTime(report);
//            logger.debugLogger("creating CSV file");
//            CSV_Manipulator.CSVcreator(report);

        }



        return true;
    }
}
