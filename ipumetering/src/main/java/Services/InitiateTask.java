package Services;

import org.example.Modals.InitiatorTaskResponse;
import org.example.Modals.LoginResponse;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class InitiateTask {
//    static Logger logger = new Logger();
    public static InitiatorTaskResponse jobLevelMeteringInitiator(LoginResponse currentSession, String meteringID,String startDate, String endDate) throws InterruptedException, IOException {
        RestTemplate restTemplate =  new RestTemplate();

        if(currentSession == null)
        {
//            logger.errorLogger("Session is Invalid");

            System.err.println("Session-Token is Invalid");
            AppStart.startProject();

        }
        try
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            headers.set("INFA-SESSION-ID", currentSession.getIcSessionId());


            String requestBody = "{\"startDate\": \""+startDate+"\", " +
                    " \"endDate\": \""+endDate+"\", " +
                    " \"allMeters\": \"FALSE\", " +
                    " \"meterId\":\""+meteringID+"\",  "+
                    "\"callbackUrl\": \"https://MyExportJobStatus.com\"}";

            HttpEntity <String> entity = new HttpEntity<String>(requestBody, headers);
            ResponseEntity <InitiatorTaskResponse> responseEntity =   restTemplate.exchange(
                   currentSession.getServerUrl()+ Utilities.joblevelMeteringInitiatorURL, HttpMethod.POST,entity, InitiatorTaskResponse.class);




                InitiatorTaskResponse response = responseEntity.getBody();

                System.out.println("The Job has been initiated, Job Id: " + response.getJobId());
                return response;





        }

        catch (RestClientException ex)
        {

            System.err.println("Please check the error log message for more information.");
//       logger.errorLogger("Please check the error log message for more information.");
//            logger.errorLogger(ex.getMessage());
            System.err.println(ex.getMessage());
            return null;
        }
    }

}
