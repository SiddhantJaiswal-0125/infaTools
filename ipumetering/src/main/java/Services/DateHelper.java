package Services;

public class DateHelper {
   public static boolean dateChecker(String calender)
    {
        String ar [] = calender.split("-");

        if(ar.length!=3)
            return  false;

        int year = getDate(ar[0]);
        if(year<2023)
        {
            System.out.println("Please enter a valid year");
            System.out.println("Date should in the format of YYYY-MM-DD, example 2023-11-02");

            return false;
        }
        int month = getDate(ar[1]);
        if(month < 1 || month >12)
        {
            System.out.println("Please enter a valid month");
            System.out.println("Date should in the format of YYYY-MM-DD, example 2023-11-02");

            return false;
        }

        int date = getDate(ar[2]);


        if(ar.length != 2 || date > 31 || date < 1 )
        {
            System.out.println("Please enter a valid date");
            System.out.println("Date should in the format of YYYY-MM-DD, example 2023-11-02");
            return false;
        }





        System.out.println(ar[0] + "\t"+ar[1]+"\t"+ar[2]);

        return true;
    }

     static int getDate(String date)
    {
        try {
            int data = Integer.parseInt(date);
            return data;

        }
        catch (Exception e)
        {
            System.out.println("Expection : "+e.getMessage() );
            return  -1;
        }
    }
}
