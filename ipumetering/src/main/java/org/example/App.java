package org.example;

import Services.Logger;
import Services.LoginAPI;

/**
 * Hello world!
 *
 */
public class App {

   
    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.debugLogger("LOGIN CALL");
        LoginAPI.loginCall();
        logger.debugLogger("User has successfully logged in");
    }
}
