package de.saschadoemer.arts.client.commandline;

import de.saschadoemer.arts.client.commandline.handler.*;

import java.util.Scanner;

/**
 * Main entry of the application.
 */
public class Main {

    public static void main(String[] args) {
        printWelcomeTitle();
        while (true) {
            try {
                printInputOptions();
                int choice = readInputOption();
                switch (choice) {
                    case 0:
                        exit();
                        break;
                    case 1:
                        DecodeMessageHandler decodeMessageHandler = new DecodeMessageHandler();
                        decodeMessageHandler.handle();
                        break;
                    case 2:
                        DecodeResponseHandler decodeResponseHandler = new DecodeResponseHandler();
                        decodeResponseHandler.handle();
                        break;
                    case 3:
                        EncodeCapabilitiesHandler encodeCapabilitiesHandler = new EncodeCapabilitiesHandler();
                        encodeCapabilitiesHandler.handle();
                        break;
                    case 4:
                        SendCommandHandler sendCommandHandler = new SendCommandHandler();
                        sendCommandHandler.handle();
                        break;
                    case 5:
                        FetchMessageResponseHandler fetchMessageResponseHandler = new FetchMessageResponseHandler();
                        fetchMessageResponseHandler.handle();
                        break;
                    default:
                        System.exit(0);
                }
            } catch (Exception e) {
                printSomethingWentWrongInfo();
            }
        }
    }

    private static void printWelcomeTitle() {
        System.out.println("************************************************************************************");
        System.out.println("*");
        System.out.println("* AGRIROUTER TOOLSET / Helping developers to access the AR!");
        System.out.println("* AGRIROUTER TOOLSET / Feel free to buy us a coffee [https://buymeacoff.ee/ks0iWGZlR] ;-)");
        System.out.println("*");
        System.out.println("************************************************************************************");
    }

    private static void printInputOptions() {
        System.out.println("************************************************************************************");
        System.out.println("*");
        System.out.println("* [1] Decode Base64 encoded message.");
        System.out.println("* [2] Decode Base64 encoded response.");
        System.out.println("* [3] Encode capabilities message.");
        System.out.println("* [4] Send command to the AR (message without dedicated recipients).");
        System.out.println("* [5] Fetch messages from outbox.");
        System.out.println("*");
        System.out.println("************************************************************************************");
    }

    private static int readInputOption() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        choice = scanner.nextInt();
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
