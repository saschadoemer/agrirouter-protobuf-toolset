package de.saschadoemer.arts.client.commandline.handler;

import com.dke.data.agrirouter.api.dto.encoding.DecodeMessageResponse;
import com.dke.data.agrirouter.api.service.messaging.encoding.DecodeMessageService;
import com.dke.data.agrirouter.impl.messaging.encoding.DecodeMessageServiceImpl;
import de.saschadoemer.arts.client.commandline.helper.ErrorPrinter;
import de.saschadoemer.arts.client.commandline.helper.ResultPrinter;

public class DecodeMessageHandler extends InputHandler {

    private final ErrorPrinter errorPrinter;
    private final ResultPrinter resultPrinter;

    public DecodeMessageHandler() {
        this.errorPrinter = new ErrorPrinter();
        this.resultPrinter = new ResultPrinter();
    }

    public void handle() {
        String base64EncodedValue = this.readInput("Please paste the BASE64 encoded message.");
        DecodeMessageService decodeMessageService = new DecodeMessageServiceImpl();
        DecodeMessageResponse decodedMessage = decodeMessageService.decode(base64EncodedValue);
        this.resultPrinter.print(decodedMessage);
    }

}
