package de.saschadoemer.arts.client.commandline.handler;

import agrirouter.cloud.registration.CloudVirtualizedAppRegistration;
import agrirouter.feed.request.FeedRequests;
import agrirouter.request.Request;
import agrirouter.request.payload.account.Endpoints;
import agrirouter.request.payload.endpoint.Capabilities;
import agrirouter.request.payload.endpoint.SubscriptionOuterClass;
import de.saschadoemer.arts.client.commandline.decoder.ContentDecoder;
import de.saschadoemer.arts.client.commandline.decoder.impl.request.*;
import de.saschadoemer.arts.client.commandline.decoder.impl.response.UndefinedContentDecoder;
import de.saschadoemer.arts.client.commandline.helper.ErrorPrinter;
import de.saschadoemer.arts.client.commandline.helper.ResultPrinter;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.Optional;

public class DecodeMessageHandler extends InputHandler {

    private final ErrorPrinter errorPrinter;
    private final ResultPrinter resultPrinter;

    public DecodeMessageHandler() {
        errorPrinter = new ErrorPrinter();
        resultPrinter = new ResultPrinter();
    }

    @Override
    public void handle() {
        try {
            String base64EncodedValue = readInput("Please paste the BASE64 encoded message.");
            DecodeRequestResponse decodedRequest = decode(base64EncodedValue);
            resultPrinter.print("This is the envelope of the message.");
            resultPrinter.print(decodedRequest.requestEnvelope);
            resultPrinter.print("This is the content of the message.");
            Optional<Object> decode = getDecoder(decodedRequest.requestPayloadWrapper.getDetails().getTypeUrl()).decode(decodedRequest.requestPayloadWrapper.getDetails());
            if (decode.isPresent()) {
                resultPrinter.print(decode.get());
            } else {
                errorPrinter.print("Could not decode message content.");
            }
        } catch (Exception ignored) {
            errorPrinter.print("Could not decode message content.");
        }
    }

    private ContentDecoder getDecoder(String typeUrl) {
        if (typeUrl.contains(Capabilities.getDescriptor().getName())) {
            return new CapabilityRequestDecoder();
        }
        if (typeUrl.contains(SubscriptionOuterClass.Subscription.getDescriptor().getName())) {
            return new SubscriptionRequestDecoder();
        }
        if (typeUrl.contains(FeedRequests.MessageQuery.getDescriptor().getName())) {
            return new MessageQueryRequestDecoder();
        }
        if (typeUrl.contains(FeedRequests.MessageConfirm.getDescriptor().getName())) {
            return new MessageConfirmRequestDecoder();
        }
        if (typeUrl.contains(FeedRequests.MessageDelete.getDescriptor().getName())) {
            return new MessageDeleteRequestDecoder();
        }
        if (typeUrl.contains(Endpoints.ListEndpointsQuery.getDescriptor().getName())) {
            return new ListEndpointsQueryRequestDecoder();
        }
        if (typeUrl.contains(CloudVirtualizedAppRegistration.OnboardingRequest.getDescriptor().getName())) {
            return new OnboardingRequestDecoder();
        }
        if (typeUrl.contains(CloudVirtualizedAppRegistration.OffboardingRequest.getDescriptor().getName())) {
            return new OffboardingRequestDecoder();
        }

        return new UndefinedContentDecoder();
    }

    public DecodeRequestResponse decode(String message) throws Exception {
        byte[] decodedBytes = Base64.getDecoder().decode(message);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedBytes);
        Request.RequestEnvelope requestEnvelope = Request.RequestEnvelope.parseDelimitedFrom(inputStream);
        Request.RequestPayloadWrapper requestPayloadWrapper = Request.RequestPayloadWrapper.parseDelimitedFrom(inputStream);
        return new DecodeRequestResponse(requestEnvelope, requestPayloadWrapper);
    }

    private static class DecodeRequestResponse {
        private final Request.RequestEnvelope requestEnvelope;
        private final Request.RequestPayloadWrapper requestPayloadWrapper;

        public DecodeRequestResponse(Request.RequestEnvelope requestEnvelope, Request.RequestPayloadWrapper requestPayloadWrapper) {
            this.requestEnvelope = requestEnvelope;
            this.requestPayloadWrapper = requestPayloadWrapper;
        }
    }
}
