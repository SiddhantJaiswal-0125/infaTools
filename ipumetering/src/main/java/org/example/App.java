package org.example;

import Services.AppStart;

import java.io.Console;
import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {


    public static void main(String[] args) throws InterruptedException, IOException {



//        Scanner sc = new Scanner(System.in);
//        String date = sc.next();
//
//       boolean checker =  dateChecker(date);
//        System.out.println(checker);

        Console console = System.console();

        if (console == null) {
            System.err.println("Console not available.");
        }
        AppStart.startProject();
    }
}
