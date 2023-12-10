package Services;

import org.example.App;
import org.example.Modals.*;

import java.io.IOException;
import java.util.List;

public class AppStart {
    public static boolean startProject() throws InterruptedException, IOException {
        Logger logger = new Logger();
        logger.debugLogger("LOGIN CALL");
        LoginResponse userSession = LoginAPI.loginCall();
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


      report = CSV_Manipulator.addExecutionTime(report);
        logger.debugLogger("After Adding Execution Time");



        logger.debugLogger("creating CSV file");
        CSV_Manipulator.CSVcreator(report);


        return true;
    }
}
