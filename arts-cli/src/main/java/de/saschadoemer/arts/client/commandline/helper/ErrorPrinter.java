package de.saschadoemer.arts.client.commandline.helper;

public class ErrorPrinter {

    public void print(String error){
        System.out.println("************************************************************************************");
        System.out.println(String.format("* ERROR - %s",error));
        System.out.println("* ERROR - Could not decode the message. Please check the input.");
        System.out.println("************************************************************************************");
    }

}
