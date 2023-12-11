package Services;

import org.example.Modals.InitiatorTaskResponse;
import org.example.Modals.LoginResponse;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class InitiateTask {
    static Logger logger = new Logger();
    public static InitiatorTaskResponse jobLevelMeteringInitiator(LoginResponse currentSession, String meteringID) throws InterruptedException, IOException {
        RestTemplate restTemplate =  new RestTemplate();
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

            String requestBody = "{\"startDate\": \"2023-11-01T00:00:00Z\", " +
                    " \"endDate\": \"2023-12-01T00:00:00Z\", " +
                    " \"allMeters\": \"FALSE\", " +
                    " \"meterId\":\""+meteringID+"\",  "+

                    "\"callbackUrl\": \"https://MyExportJobStatus.com\"}";

            HttpEntity <String> entity = new HttpEntity<String>(requestBody, headers);
            ResponseEntity <InitiatorTaskResponse> responseEntity =   restTemplate.exchange(
                   currentSession.getServerUrl()+ Utilities.joblevelMeteringInitiatorURL, HttpMethod.POST,entity, InitiatorTaskResponse.class);


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
