package Services;

import org.example.Modals.LoginResponse;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class LoginAPI {

   static Logger logger = new Logger();
   public static LoginResponse loginCall()
    {
        RestTemplate restTemplate =  new RestTemplate();
        try
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String requestBody = "{\"type\": \"login\",  \"username\": \"repro_user\", \"password\": \"infa@1234\"}";
            HttpEntity <String> entity = new HttpEntity<String>(requestBody, headers);
            ResponseEntity <LoginResponse> responseEntity =   restTemplate.exchange(
                            Utilities.loginUrl_dmUS, HttpMethod.POST,entity, LoginResponse.class);


            LoginResponse sessionDetails = responseEntity.getBody();


            System.out.println("Session URL : "+sessionDetails.getServerUrl());

            return sessionDetails;



        }

        catch (RestClientException ex)
        {
            logger.errorLogger(ex.getMessage());
        }
        return null;
    }
}