package de.saschadoemer.arts.client.commandline.handler;

import de.saschadoemer.arts.client.commandline.helper.ErrorPrinter;
import de.saschadoemer.arts.client.commandline.helper.ResultPrinter;

public class EncodeCapabilitiesHandler extends InputHandler {

    private final ErrorPrinter errorPrinter;
    private final ResultPrinter resultPrinter;

    public EncodeCapabilitiesHandler() {
        this.errorPrinter = new ErrorPrinter();
        this.resultPrinter = new ResultPrinter();
    }

    public void handle() {
        String capabilitiesAsCsv = this.readInput("Please enter the capabilities as CSV");
        String[] capabilities = capabilitiesAsCsv.split(",");

    }

}
