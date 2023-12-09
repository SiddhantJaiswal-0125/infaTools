package Services;

import org.example.Modals.LoginResponse;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class InitiateTask {
    static Logger logger = new Logger();
    static void jobLevelMeteringInitiator(LoginResponse currentSession)
    {
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

            String requestBody = "{\"type\": \"login\",  \"username\": \"repro_user\", \"password\": \"infa@1234\"}";
            HttpEntity <String> entity = new HttpEntity<String>(requestBody, headers);
            ResponseEntity <LoginResponse> responseEntity =   restTemplate.exchange(
                    Utilities.loginUrl_dmUS, HttpMethod.POST,entity, LoginResponse.class);


            LoginResponse sessionDetails = responseEntity.getBody();


            System.out.println("Session URL : "+sessionDetails.getServerUrl());





        }

        catch (RestClientException ex)
        {
            logger.errorLogger(ex.getMessage());
        }



    }

}
