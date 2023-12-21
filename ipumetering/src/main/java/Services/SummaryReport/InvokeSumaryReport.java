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
//    static Logger logger = new Logger();

    public static void invokeExportSummaryJob(LoginResponse currentSession) throws IOException, InterruptedException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Please the Start Date and End Date in the Format \" YYYY-MM-DD \"");
        System.out.println("Note : YYYY-MM-DD  this format is important, example : 2022-08-12" );
        System.out.println();
        System.out.println("Please enter Start Date ");
        String startDate = sc.next();

        boolean validDate = DateHelper.dateChecker(startDate);
        if(validDate == false)
        {
            invokeExportSummaryJob(currentSession);
            return;
        }
        startDate =  startDate+"T00:00:00Z";
        System.out.println("Please enter End Date ");
        String endDate = sc.next();
        validDate = DateHelper.dateChecker(endDate);

        endDate = endDate+"T00:00:00Z";
if(validDate == false)
{
    invokeExportSummaryJob(currentSession);
    return;


}

        InitiatorTaskResponse response = jobLevelMeteringInitiator(currentSession, startDate, endDate);
        StatusCheckerResponse statusCheckerResponse =  JobStatusChecker.checkJobStatus(currentSession,response);
        System.out.println("Please wait:");
        while(statusCheckerResponse!=null && statusCheckerResponse.getStatus().equalsIgnoreCase("SUCCESS")==false)
        {
            Thread.sleep(10000);

//            logger.debugLogger("STATUS CHECKING AGAIN");
            statusCheckerResponse   = JobStatusChecker.checkJobStatus(currentSession,response);

        }
//        logger.debugLogger("Invoke Export Summary  status response "+ statusCheckerResponse.getStatus());

//        logger.debugLogger("DOWNLOADING FILE ");
        List<FileStructure> unzippedFiles =  FileDownloader.downloadFile(currentSession, response,
                statusCheckerResponse, Utilities.parentDirectory+"/CompleteExport/");

















    }
    static InitiatorTaskResponse jobLevelMeteringInitiator(LoginResponse currentSession, String startDate, String endDate) throws InterruptedException, IOException {
        RestTemplate restTemplate =  new RestTemplate();



        if(currentSession == null)
        {
//            logger.errorLogger("Session is Invalid");
            System.err.println("Session - Token is Invalid");

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
            System.out.println("Job has been initiated, Job Id : "+response.getJobId());

            return response;





        }
        catch (RestClientException ex)
        {
            System.err.println(ex.getMessage());
            return null;
        }
}

    }
