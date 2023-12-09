package Services;

import org.example.App;
import org.example.Modals.LoginResponse;

public class AppStart {
    public static boolean startProject(){
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


        return true;
    }
}
