package Services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.example.Modals.CDIReportStructure;
import org.example.Modals.FileStructure;


import java.io.*;
import java.time.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.Scanner;

public class CSV_Manipulator {
    static Logger logger = new Logger();
    public static List<CDIReportStructure> readCSV(FileStructure fileStructure) throws FileNotFoundException {


        List<CDIReportStructure> report = new ArrayList<>();


        try {
            Reader reader = new FileReader(fileStructure.getFilePath());
            CsvToBean<CDIReportStructure> csvToBean = new CsvToBeanBuilder<CDIReportStructure>(reader)
                    .withType(CDIReportStructure.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

                    report = csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }


        addExecutionTime(report);

        return report;
    }



    public static List<CDIReportStructure> addExecutionTime(List<CDIReportStructure> report)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");




        for(CDIReportStructure rep : report) {

            String time1 = rep.getStartTime();
            String time2 = rep.getEndTime();

            LocalDateTime dateTime1 = LocalDateTime.parse(time1, formatter);
            LocalDateTime dateTime2 = LocalDateTime.parse(time2, formatter);

            Duration duration = Duration.between(dateTime1, dateTime2);




            long millis =duration.toMillis() /* your duration in milliseconds here */;

            long totalSeconds = millis / 1000; // convert duration from milliseconds to seconds

            long hours = totalSeconds / 3600; // calculate hours
            totalSeconds %= 3600; // update the remaining total seconds

            long minutes = totalSeconds / 60; // calculate minutes
            totalSeconds %= 60; // update the remaining total seconds





            String jobDuration = "Job ran for " + (duration.toDays() > 0 ? (duration.toDays() + " Days ") : "") +
                    (hours > 0 ? (hours + " Hours ") : "") +
                    (minutes > 0 ? (minutes + " Minutes ") : "") +
                    (totalSeconds > 0 ? (totalSeconds + " Seconds ") : "") ;

            System.out.println(jobDuration);

//
            rep.setExecutionTime(jobDuration);


        }



        return report;
    }
}
