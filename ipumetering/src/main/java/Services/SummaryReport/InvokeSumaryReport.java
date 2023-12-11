package Services.SummaryReport;

import org.example.Modals.LoginResponse;

import java.util.Scanner;

public class InvokeSumaryReport {
    public static void invokeExportSummaryJon(LoginResponse currentSession)
    {

        Scanner sc = new Scanner(System.in);

        System.out.println("Please the Start Date and End Date in the Format \" YYYY-MM-DDTHH:MM:SSZ \"");
        System.out.println("Note : YYYY-MM-DDTHH:MM:SSZ  this format is important, example : 2022-08-12T00:00:00Z " );
        System.out.println();
        System.out.println("Please enter Start Date ");
        String startDate = sc.next();
        System.out.println("Please enter End Date ");
        String endDate = sc.next();
















    }
}
