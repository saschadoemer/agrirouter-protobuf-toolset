package de.saschadoemer.arts.client.commandline;

import de.saschadoemer.arts.client.commandline.handler.DecodeMessageHandler;
import de.saschadoemer.arts.client.commandline.handler.EncodeCapabilitiesHandler;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Main entry of the application.
 */
public class Main {

    public static void main(String[] args) {
        printWelcomeTitle();
        boolean doNotLeaveApplication = true;
        while (doNotLeaveApplication) {
            try {

                printInputOptions();
                int choice = readInputOption();
                switch (choice) {
                    case 0:
                        exit();
                        doNotLeaveApplication = false;
                        break;
                    case 1:
                        DecodeMessageHandler decodeMessageHandler = new DecodeMessageHandler();
                        decodeMessageHandler.handle();
                        break;
                    case 2:
                        EncodeCapabilitiesHandler encodeCapabilitiesHandler = new EncodeCapabilitiesHandler();
                        encodeCapabilitiesHandler.handle();
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid option selected.");
                }
            } catch (Exception e) {
                printSomethingWentWrongInfo();
            }
        }
    }

    private static void printWelcomeTitle() {
        System.out.println("************************************************************************************");
        System.out.println("*");
        System.out.println("* AGRIROUTER TOOLSET / Decoding protobuf messages from the AR.");
        System.out.println("*");
        System.out.println("************************************************************************************");
    }

    private static void printInputOptions() {
        System.out.println("[1] Paste input to decode.");
        System.out.println("[2] Encode capabilities message.");
        System.out.println("[0] Quit.");
    }

    private static int readInputOption() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            choice = scanner.nextInt();
        }
        while (!Arrays.asList(0, 1, 2).contains(choice));
        return choice;
    }

    private static void printSomethingWentWrongInfo() {
        System.out.println("************************************************************************************");
        System.out.println("*");
        System.out.println("* AGRIROUTER TOOLSET / Something went wrong, please file an issue and support us to solve the error.");
        System.out.println("* AGRIROUTER TOOLSET / You'll find the issue tracker right here >>> https://github.com/DKE-Data/agrirouter-api-protobuf-definitions/issues.");
        System.out.println("*");
        System.out.println("************************************************************************************");
    }

    private static void exit() {
        System.out.println("************************************************************************************");
        System.out.println("*");
        System.out.println("* AGRIROUTER TOOLSET / Thank you for using the decoder.");
        System.out.println("* AGRIROUTER TOOLSET / Feel free to buy us a coffee [https://buymeacoff.ee/ks0iWGZlR] ;-)");
        System.out.println("*");
        System.out.println("************************************************************************************");
    }

}
