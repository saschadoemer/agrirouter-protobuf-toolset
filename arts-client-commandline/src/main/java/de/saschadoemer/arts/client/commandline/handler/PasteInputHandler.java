package de.saschadoemer.arts.client.commandline.handler;

import de.saschadoemer.arts.client.commandline.helper.ErrorPrinter;
import de.saschadoemer.arts.client.commandline.helper.ResultPrinter;
import de.saschadoemer.arts.decoder.Decoder;
import de.saschadoemer.arts.decoder.impl.MessageDecoder;

import java.util.Optional;
import java.util.Scanner;

public class PasteInputHandler {

    private final ErrorPrinter errorPrinter;
    private final ResultPrinter resultPrinter;

    public PasteInputHandler() {
        this.errorPrinter = new ErrorPrinter();
        this.resultPrinter = new ResultPrinter();
    }

    public void handle() {
        String base64EncodedValue = this.readInput();
        Decoder decoder = new MessageDecoder();
        Optional<String> decodedMessage = decoder.safeDecode(base64EncodedValue);
        if (decodedMessage.isPresent()) {
            this.resultPrinter.print(decodedMessage.get());
        } else {
            errorPrinter.print(String.format("Could not decode '%s'.1", base64EncodedValue));
        }
    }

    private String readInput() {
        System.out.println("Please paste the BASE64 encoded message.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
