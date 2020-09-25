package de.saschadoemer.arts.client.commandline.handler;

import agrirouter.request.Request;
import agrirouter.request.payload.endpoint.Capabilities;
import agrirouter.request.payload.endpoint.Capabilities.CapabilitySpecification.PushNotification;
import com.dke.data.agrirouter.api.enums.TechnicalMessageType;
import com.dke.data.agrirouter.api.service.messaging.encoding.EncodeMessageService;
import com.dke.data.agrirouter.api.service.parameters.MessageHeaderParameters;
import com.dke.data.agrirouter.api.service.parameters.PayloadParameters;
import com.dke.data.agrirouter.impl.common.MessageIdService;
import com.dke.data.agrirouter.impl.messaging.encoding.EncodeMessageServiceImpl;
import de.saschadoemer.arts.client.commandline.helper.ErrorPrinter;
import de.saschadoemer.arts.client.commandline.helper.ResultPrinter;

import java.util.ArrayList;
import java.util.List;

public class EncodeCapabilitiesHandler extends InputHandler {

    private final ErrorPrinter errorPrinter;
    private final ResultPrinter resultPrinter;

    public EncodeCapabilitiesHandler() {
        errorPrinter = new ErrorPrinter();
        resultPrinter = new ResultPrinter();
    }

    @Override
    public void handle() {
        try {
            String appCertificationId = readInput("Enter the application ID:");
            String appCertificationVersionId = readInput("Enter the application certification version ID:");
            String enablePushNotifications = readInput("Enter 'true' if you want to enable push notification:");
            String sendingCapabilitiesAsCsv = readInput("Enter the capabilities to send as CSV:");
            String receivingCapabilitiesAsCsv = readInput("Enter the capabilities to receive as CSV:");

            List<Capabilities.CapabilitySpecification.Capability> capabilities = new ArrayList<>();

            String[] sendingCapabilities = sendingCapabilitiesAsCsv.split(",");
            for (String technicalMessageType : sendingCapabilities) {
                Capabilities.CapabilitySpecification.Capability.Builder capability = Capabilities.CapabilitySpecification.Capability.newBuilder();
                capability.setDirection(Capabilities.CapabilitySpecification.Direction.SEND);
                capability.setTechnicalMessageType(technicalMessageType.trim());
                capabilities.add(capability.build());
            }
            String[] receivingCapabilities = receivingCapabilitiesAsCsv.split(",");
            for (String technicalMessageType : receivingCapabilities) {
                Capabilities.CapabilitySpecification.Capability.Builder capability = Capabilities.CapabilitySpecification.Capability.newBuilder();
                capability.setDirection(Capabilities.CapabilitySpecification.Direction.RECEIVE);
                capability.setTechnicalMessageType(technicalMessageType.trim());
                capabilities.add(capability.build());
            }

            if (!capabilities.isEmpty()) {
                EncodeMessageService encodeMessageService = new EncodeMessageServiceImpl();
                MessageHeaderParameters messageHeaderParameters = new MessageHeaderParameters();
                messageHeaderParameters.setTechnicalMessageType(TechnicalMessageType.DKE_CAPABILITIES);
                messageHeaderParameters.setApplicationMessageSeqNo(getSeqNo());
                messageHeaderParameters.setApplicationMessageId(MessageIdService.generateMessageId());
                messageHeaderParameters.setMode(Request.RequestEnvelope.Mode.DIRECT);

                Capabilities.CapabilitySpecification.Builder builder = Capabilities.CapabilitySpecification.newBuilder();
                builder.setAppCertificationId(appCertificationId);
                builder.setAppCertificationVersionId(appCertificationVersionId);
                builder.setEnablePushNotifications(Boolean.parseBoolean(enablePushNotifications) ? PushNotification.ENABLED : PushNotification.DISABLED);
                builder.addAllCapabilities(capabilities);

                PayloadParameters payloadParameters = new PayloadParameters();
                payloadParameters.setTypeUrl(Capabilities.CapabilitySpecification.getDescriptor().getFullName());
                payloadParameters.setValue(builder.build().toByteString());

                String encodedMessage = encodeMessageService.encode(messageHeaderParameters, payloadParameters);

                resultPrinter.print(encodedMessage);
            } else {
                errorPrinter.print("Missing capabilities, please provide capabilities to set.");
            }

        } catch (Exception e) {
            handleException(e);
        }
    }

}
