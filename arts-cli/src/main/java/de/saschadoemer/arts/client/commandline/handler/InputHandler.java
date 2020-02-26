package de.saschadoemer.arts.client.commandline.handler;

import java.util.Scanner;

public abstract class InputHandler {

    protected String readInput(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
