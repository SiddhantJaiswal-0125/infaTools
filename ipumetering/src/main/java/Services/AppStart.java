package Services;

import org.example.App;
import org.example.Modals.InitiatorTaskResponse;
import org.example.Modals.LoginResponse;
import org.example.Modals.StatusCheckerResponse;

import java.io.IOException;

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

            Thread.sleep(10000);
            logger.debugLogger("STATUS CHECKING AGAIN");
            statusCheckerResponse   = JobStatusChecker.checkJobStatus(userSession,taskInitiatorResponse);
        }


        logger.debugLogger(statusCheckerResponse.getStatus());

        logger.debugLogger("DOWNLOADING FILE ");
        FileDownloader.downloadFile(userSession, taskInitiatorResponse, statusCheckerResponse);
        return true;
    }
}
