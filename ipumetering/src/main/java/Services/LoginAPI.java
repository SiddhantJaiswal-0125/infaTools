package Services;

import org.example.Modals.LoginResponse;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class LoginAPI {


    static  int getPodNum()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Started");
//        logger.debugLogger("Please select your POD ");
        System.out.println("Please select your POD ");
        System.out.println("1. NA WEST 1 : "+Utilities.USW1_AWS_POD1_LoginURL);
        System.out.println("2. NA EAST 2 : "+Utilities.USE2_AWS_POD2_LoginURL);
        System.out.println("3. US WEST 3 : "+Utilities.USW3_AWS_POD3_LoginURL);
        System.out.println("4. US EAST 4 : "+Utilities.USE4_AWS_POD4_LoginURL);
        System.out.println("5. US WEST 5 : "+Utilities.USW5_AWS_POD5_LoginURL);
        System.out.println("6. US EAST 6 : "+Utilities.USE6_AWS_POD6_LoginURL );
        System.out.println("7. US WEST 1 AZURE   : "+Utilities.USW1_1_Azure_POD7_LoginURL);
        System.out.println("8. US WEST 3 AZURE   : "+Utilities.USW3_1_Azure_POD8_LoginURL);
        System.out.println("9. US WEST 1 GCP     : "+Utilities.USW1_2_GCP_POD9_LoginURL);
        System.out.println("10. CANADA CENTRAL 1 : "+Utilities.CAC1_AWS_POD10_LoginURL);
        System.out.println("11. CANADA CENTRAL 1 AZURE : "+Utilities.CAC2_AZURE_POD21_LoginURL);
        System.out.println("12. US EAST 1 OCI  : "+Utilities.USE1_ORACLE_POD22_LoginURL );
        System.out.println("13. AP SOUTHEAST 1 : "+Utilities.APSE1_AWS_POD14_LoginURL);
        System.out.println("14. AP SOUTHEAST AZURE : "+Utilities.APSE2_AZURE_POD15_LoginURL);
        System.out.println("15. AP NORTHEAST 1 AZURE : "+Utilities.APNE1_AZURE_POD16_LoginURL);
        System.out.println("16. AP NORTHEAST 2 : "+Utilities.APNE2_AWS_POD19_LoginURL);


        System.out.println("17. AUSTRALIA AZURE : "+Utilities.APAUC1_AZURE_POD17_LoginURL);
        System.out.println("18. EM WEST 1 : "+Utilities.EMW1_AWS_POD11_LoginURL);

        System.out.println("19. EM CENTRAL 1 AZURE : "+Utilities.EMC1_AZURE_POD12_LoginURL);
        System.out.println("20. UK : "+Utilities.UK1_AWS_POD13_LoginURL);
        System.out.println("21. EM SOUTHEAST 1 AZURE : "+Utilities.EMSE1_AZURE_POD18_LoginURL);
        System.out.println("22. EM WEST 2 GCP : "+Utilities.EMW2_GCP_POD20_LoginURL);

        System.out.println();
        System.out.println();
        System.out.println("Please enter the number corresponding to your POD  or enter -1 if you want to exit.");
        int podNum = sc.nextInt();

        return podNum;
    }

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

//
//            System.out.println("Session URL : "+sessionDetails.getServerUrl());

            return sessionDetails;



        }

        catch (RestClientException ex)
        {
            System.err.println(ex.getMessage());
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
