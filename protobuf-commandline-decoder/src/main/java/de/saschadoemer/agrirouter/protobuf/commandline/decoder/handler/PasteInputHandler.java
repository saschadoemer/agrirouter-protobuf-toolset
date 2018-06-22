package de.saschadoemer.agrirouter.protobuf.commandline.decoder.handler;

import de.saschadoemer.agrirouter.protobuf.commandline.decoder.helper.ErrorPrinter;
import de.saschadoemer.agrirouter.protobuf.commandline.decoder.helper.ResultPrinter;
import de.saschadoemer.agrirouter.protobuf.decoder.Decoder;
import de.saschadoemer.agrirouter.protobuf.decoder.impl.MessagesDecoder;

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
        Decoder decoder = new MessagesDecoder();
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
