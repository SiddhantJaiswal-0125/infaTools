package Services;

public class Logger {
   public  void debugLogger(String msg)
    {
        System.out.println("--- [DEBUG]----- "+msg+" -----------");
    }
    public void errorLogger(String errMsg)
    {
        System.err.println("--- [ERROR] ----- "+errMsg+" ----------");
    }
}
