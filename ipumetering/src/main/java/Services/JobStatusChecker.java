package Services;

import org.apache.juli.logging.Log;
import org.example.Modals.InitiatorTaskResponse;
import org.example.Modals.LoginResponse;
import org.example.Modals.StatusCheckerResponse;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class JobStatusChecker
{



    public static StatusCheckerResponse checkJobStatus(LoginResponse currentSession, InitiatorTaskResponse initiatorTaskResponse) throws InterruptedException, IOException {
        RestTemplate restTemplate =  new RestTemplate();
        if(currentSession == null)
        {
            System.err.println("Session-Token is Invalid");
            AppStart.startProject();
        }
        try
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            headers.set("INFA-SESSION-ID", currentSession.getIcSessionId());

            String requestBody = null;

            HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
            ResponseEntity<StatusCheckerResponse> statusEntity =   restTemplate.exchange(
                    currentSession.getServerUrl()+ Utilities.exportMeteringJobStatusURL+initiatorTaskResponse.getJobId(), HttpMethod.GET,entity, StatusCheckerResponse.class);


            StatusCheckerResponse response = statusEntity.getBody();

            System.out.println("Job Current Status : " +response.getStatus());





            return response;





        }

        catch (RestClientException ex)
        {
            System.err.println(ex.getMessage());
            return null;
        }
    }



}
