package Services.SummaryReport;

import Services.*;
import org.example.Modals.*;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class InvokeMeterLevelReport {


    static Scanner sc = new Scanner(System.in);

    public static void invokeMeterLevelReport(LoginResponse currentSession) throws IOException, InterruptedException {



//        logger.debugLogger("Invoking API to download Service Level Metering Data ");

        String selectedService = selectService();
        if(selectedService == null || selectedService.length() ==0)
        {
            invokeMeterLevelReport(currentSession);
            return;
        }

        System.out.println("Please the Start Date and End Date in the Format \" YYYY-MM-DDTHH:MM:SSZ \"");
        System.out.println("Note : YYYY-MM-DDTHH:MM:SSZ  this format is important, example : 2022-08-12T00:00:00Z " );
        System.out.println();
        System.out.println("Please enter Start Date ");
        String startDate = sc.next();
        System.out.println("Please enter End Date ");
        String endDate = sc.next();

        InitiatorTaskResponse taskInitiatorResponse = InitiateTask.jobLevelMeteringInitiator(currentSession, selectedService, startDate, endDate);
        if(taskInitiatorResponse == null || taskInitiatorResponse.getJobId() == null)
        {
            taskInitiatorResponse = InitiateTask.jobLevelMeteringInitiator(currentSession, selectedService, startDate, endDate);

            if( taskInitiatorResponse == null || taskInitiatorResponse.getJobId() == null)
            {
                System.out.println("SOME ISSUE -- RETRY AFTER SOME TIME");
                System.exit(-1);
            }
        }


        StatusCheckerResponse statusCheckerResponse =  JobStatusChecker.checkJobStatus(currentSession,taskInitiatorResponse);
        System.out.println("Checking Job Status " );
        while(statusCheckerResponse!=null && statusCheckerResponse.getStatus().equalsIgnoreCase("SUCCESS")==false)
        {
//            logger.debugLogger("Recheck the status after 5 Seconds");
//            System.out.println("Recheck the status after 5 Seconds");
            Thread.sleep(5000);
            statusCheckerResponse   = JobStatusChecker.checkJobStatus(currentSession,taskInitiatorResponse);

        }


//        logger.debugLogger(statusCheckerResponse.getStatus());
//
//        logger.debugLogger("DOWNLOADING FILE ");
        List<FileStructure> unzippedFiles =  FileDownloader.downloadFile(currentSession, taskInitiatorResponse, statusCheckerResponse, Utilities.parentDirectory);



        if(selectedService.equalsIgnoreCase(Utilities.Data_Integration))
        {
            createCSVforCDIReport(unzippedFiles);


            return;
        }

    }
    static String selectService()
    {

        System.out.println("Please choose the service for which you would like to download data.");
        System.out.println("1. Data_Integration");
        System.out.println("2. Data_Integration_with_Advanced_Serverless");
        System.out.println("3. Data_Integration_Elastic");
        System.out.println("4. Data_Integration_Elastic_with_Advanced_Serverless");
        System.out.println("5. Application_Integration");
        System.out.println("6. Application_Integration_with_Advanced_Serverless");
        System.out.println("7.Mass_Ingestion_Streaming");
        System.out.println("8. Mass_Ingestion_Database");
        System.out.println("9. Mass_Ingestion_Files");
        System.out.println("10. Mass_Ingestion_Application");
        System.out.println("11. Advanced_Pushdown_Optimization");
        System.out.println("12. Data_Integration_CDC");
        System.out.println("13. Mass_Ingestion_Database_CDC");
        System.out.println("14. Mass_Ingestion_Application_CDC");
        System.out.println("15. Integration_Hub");

        int choice = sc.nextInt();
        if(choice>15 || choice<0)
        {
            selectService();
            return null;

        }

        switch (choice)
        {
            case 1: return Utilities.Data_Integration;
            case 2: return Utilities.Data_Integration_with_Advanced_Serverless;
            case 3: return Utilities.Data_Integration_Elastic;
            case 4: return Utilities.Data_Integration_Elastic_with_Advanced_Serverless;
            case 5: return Utilities.Application_Integration;
            case 6: return Utilities.Application_Integration_with_Advanced_Serverless;
            case 7: return Utilities.Mass_Ingestion_Streaming;
            case 8: return Utilities.Mass_Ingestion_Database;
            case 9: return Utilities.Mass_Ingestion_Files;
            case 10: return Utilities.Mass_Ingestion_Application;
            case 11: return Utilities.Advanced_Pushdown_Optimization;
            case 12: return Utilities.Data_Integration_CDC;
            case 13: return Utilities.Mass_Ingestion_Database_CDC;
            case 14: return Utilities.Mass_Ingestion_Application_CDC;
            case 15: return Utilities.Integration_Hub;

        }

        return "";
    }

    static void createCSVforCDIReport(List<FileStructure> unzippedFiles) throws IOException {
        List<CDIReportStructure> report =   CSV_Manipulator.readCSV(unzippedFiles.get(0));

        report = CSV_Manipulator.addExecutionTime(report);

        CSV_Manipulator.CSVcreator(report);

    }

}
