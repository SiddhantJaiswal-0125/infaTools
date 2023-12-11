package Services.SummaryReport;

import Services.*;
import org.example.Modals.FileStructure;
import org.example.Modals.InitiatorTaskResponse;
import org.example.Modals.LoginResponse;
import org.example.Modals.StatusCheckerResponse;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class InvokeSumaryReport {
    static Logger logger = new Logger();

    public static void invokeExportSummaryJob(LoginResponse currentSession) throws IOException, InterruptedException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Please the Start Date and End Date in the Format \" YYYY-MM-DDTHH:MM:SSZ \"");
        System.out.println("Note : YYYY-MM-DDTHH:MM:SSZ  this format is important, example : 2022-08-12T00:00:00Z " );
        System.out.println();
        System.out.println("Please enter Start Date ");
        String startDate = sc.next();
        System.out.println("Please enter End Date ");
        String endDate = sc.next();


        InitiatorTaskResponse response = jobLevelMeteringInitiator(currentSession, startDate, endDate);
        StatusCheckerResponse statusCheckerResponse =  JobStatusChecker.checkJobStatus(currentSession,response);
        while(statusCheckerResponse!=null && statusCheckerResponse.getStatus().equalsIgnoreCase("SUCCESS")==false)
        {
            logger.debugLogger("Recheck the status after 5 Seconds");
            Thread.sleep(5000);

            logger.debugLogger("STATUS CHECKING AGAIN");
            statusCheckerResponse   = JobStatusChecker.checkJobStatus(currentSession,response);

        }
        logger.debugLogger("Invoke Export Summary  status response "+ statusCheckerResponse.getStatus());

        logger.debugLogger("DOWNLOADING FILE ");
        List<FileStructure> unzippedFiles =  FileDownloader.downloadFile(currentSession, response,
                statusCheckerResponse, Utilities.parentDirectory+"/CompleteExport/");

















    }
    static InitiatorTaskResponse jobLevelMeteringInitiator(LoginResponse currentSession, String startDate, String endDate) throws InterruptedException, IOException {
        RestTemplate restTemplate =  new RestTemplate();

        System.out.println("THIS IS CALLED ");

        if(currentSession == null)
        {
            logger.errorLogger("Session is Invalid");

            AppStart.startProject();

        }
        try
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            headers.set("INFA-SESSION-ID", currentSession.getIcSessionId());

            String requestBody = "{\"startDate\": \""+startDate+"\", " +
                    " \"endDate\": \""+endDate+"\", " +
                    "\"callbackUrl\": \"https://MyExportJobStatus.com\"}";

            HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
            ResponseEntity<InitiatorTaskResponse> responseEntity =   restTemplate.exchange(
                    currentSession.getServerUrl()+ Utilities.exportAllData, HttpMethod.POST,entity, InitiatorTaskResponse.class);


            InitiatorTaskResponse response = responseEntity.getBody();
            System.out.println("JOB ID : "+response.getJobId());
//            System.out.println(responseEntity);
            return response;





        }
        catch (RestClientException ex)
        {
            logger.errorLogger(ex.getMessage());
            return null;
        }
}

    }
