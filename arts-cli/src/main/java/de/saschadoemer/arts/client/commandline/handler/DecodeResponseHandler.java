package de.saschadoemer.arts.client.commandline.handler;

import agrirouter.commons.MessageOuterClass;
import agrirouter.response.payload.account.Endpoints;
import com.dke.data.agrirouter.api.dto.encoding.DecodeMessageResponse;
import com.dke.data.agrirouter.api.service.messaging.encoding.DecodeMessageService;
import com.dke.data.agrirouter.impl.messaging.encoding.DecodeMessageServiceImpl;
import de.saschadoemer.arts.client.commandline.decoder.ContentDecoder;
import de.saschadoemer.arts.client.commandline.decoder.impl.ListEndpointsResponseDecoder;
import de.saschadoemer.arts.client.commandline.decoder.impl.MessagesResponseDecoder;
import de.saschadoemer.arts.client.commandline.decoder.impl.UndefinedContentDecoder;
import de.saschadoemer.arts.client.commandline.helper.ErrorPrinter;
import de.saschadoemer.arts.client.commandline.helper.ResultPrinter;

import java.util.Optional;

public class DecodeResponseHandler extends InputHandler {

    private final ErrorPrinter errorPrinter;
    private final ResultPrinter resultPrinter;

    public DecodeResponseHandler() {
        this.errorPrinter = new ErrorPrinter();
        this.resultPrinter = new ResultPrinter();
    }

    @Override
    public void handle() {
        String base64EncodedValue = this.readInput("Please paste the BASE64 encoded message.");
        DecodeMessageService decodeMessageService = new DecodeMessageServiceImpl();
        DecodeMessageResponse decodedMessage = decodeMessageService.decode(base64EncodedValue);
        resultPrinter.print(String.format("This is the envelope of the message. The type of the message is '%s'", decodedMessage.getResponsePayloadWrapper().getDetails().getTypeUrl()));
        this.resultPrinter.print(decodedMessage.getResponseEnvelope());
        resultPrinter.print("This is the content of the message.");
        Optional<Object> decode = this.getDecoder(decodedMessage.getResponsePayloadWrapper().getDetails().getTypeUrl()).decode(decodedMessage.getResponsePayloadWrapper().getDetails());
        if (decode.isPresent()) {
            resultPrinter.print(decode.get());
        } else {
            errorPrinter.print("Could not decode message content.");
        }

    }

    private ContentDecoder getDecoder(String typeUrl) {
        if (typeUrl.contains(Endpoints.ListEndpointsResponse.getDescriptor().getName())) {
            return new ListEndpointsResponseDecoder();
        }
        if (typeUrl.contains(MessageOuterClass.Messages.getDescriptor().getName())) {
            return new MessagesResponseDecoder();
        }
        return new UndefinedContentDecoder();
    }


}
