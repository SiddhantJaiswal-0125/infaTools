package Services;

import org.example.Modals.LoginResponse;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class LoginAPI {

   static Logger logger = new Logger();
   public static LoginResponse loginCall(String podUrl, String username, String password)
    {
        RestTemplate restTemplate =  new RestTemplate();
        try
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
//            String requestBody1 = "{\"type\": \"login\",  \"username\": \"repro_user\", \"password\": \"infa@1234\"}";

            String requestBody = "{\"type\": \"login\",  \"username\": \""+username+"\", \"password\": \""+password+"\"}";

            HttpEntity <String> entity = new HttpEntity<String>(requestBody, headers);
            ResponseEntity <LoginResponse> responseEntity =   restTemplate.exchange(
                            podUrl, HttpMethod.POST,entity, LoginResponse.class);


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

    public static String getPodURL(int num)
    {
        String podURL = "";

        switch (num){
            case 1:
                return Utilities.USW1_AWS_POD1_LoginURL;
            case 2:
                return Utilities.USE2_AWS_POD2_LoginURL;
            case 3:
                return Utilities.USW3_AWS_POD3_LoginURL;
            case 4:
                return Utilities.USE4_AWS_POD4_LoginURL;
            case 5:
                return Utilities.USW5_AWS_POD5_LoginURL;
            case 6:
                return Utilities.USE6_AWS_POD6_LoginURL;
            case 7:
                return Utilities.USW1_1_Azure_POD7_LoginURL;
            case 8:
                return Utilities.USW3_1_Azure_POD8_LoginURL;
            case 9:
                return Utilities.USW1_2_GCP_POD9_LoginURL;
            case 10:
                return Utilities.CAC1_AWS_POD10_LoginURL;
            case 11:
                return Utilities.CAC2_AZURE_POD21_LoginURL;
            case 12:
                return Utilities.USE1_ORACLE_POD22_LoginURL;
            case 13:
                return Utilities.APSE1_AWS_POD14_LoginURL;
            case 14:
                return Utilities.APSE2_AZURE_POD15_LoginURL;
            case 15:
                return Utilities.APNE1_AZURE_POD16_LoginURL;
            case 16:
                return Utilities.APNE2_AWS_POD19_LoginURL;
            case 17:
                return Utilities.APAUC1_AZURE_POD17_LoginURL;



            case 18:
                return Utilities.EMW1_AWS_POD11_LoginURL;
            case 19:
                return Utilities.EMC1_AZURE_POD12_LoginURL;
            case 20:
                return Utilities.UK1_AWS_POD13_LoginURL;
            case 21:
                return Utilities.EMSE1_AZURE_POD18_LoginURL;
            case 22:
                return Utilities.EMW2_GCP_POD20_LoginURL;






        }


        return  podURL;
    }
}
