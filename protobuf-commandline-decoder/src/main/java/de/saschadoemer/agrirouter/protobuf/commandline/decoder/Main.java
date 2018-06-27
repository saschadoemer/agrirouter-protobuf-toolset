package de.saschadoemer.agrirouter.protobuf.commandline.decoder;

import de.saschadoemer.agrirouter.protobuf.commandline.decoder.handler.PasteInputHandler;

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
            printInputOptions();
            int choice = readInputOption();
            switch (choice) {
                case 0:
                    exit();
                    doNotLeaveApplication = false;
                    break;
                case 1:
                    PasteInputHandler pasteInputHandler = new PasteInputHandler();
                    pasteInputHandler.handle();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid option selected.");
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
        System.out.println("[1] Paste input.");
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

    private static void exit() {
        System.out.println("************************************************************************************");
        System.out.println("*");
        System.out.println("* AGRIROUTER TOOLSET / Thank you for using the decoder.");
        System.out.println("* AGRIROUTER TOOLSET / Feel free to buy us a coffee [buymeacoff.ee/ks0iWGZlR] ;-)");
        System.out.println("*");
        System.out.println("************************************************************************************");
    }

}
