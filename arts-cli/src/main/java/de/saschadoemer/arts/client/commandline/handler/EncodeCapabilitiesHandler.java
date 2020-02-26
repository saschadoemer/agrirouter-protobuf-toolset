package de.saschadoemer.arts.client.commandline.handler;

import agrirouter.request.Request;
import agrirouter.request.payload.endpoint.Capabilities;
import agrirouter.request.payload.endpoint.Capabilities.CapabilitySpecification.PushNotification;
import com.dke.data.agrirouter.api.enums.TechnicalMessageType;
import com.dke.data.agrirouter.api.factories.impl.CapabilitiesMessageContentFactory;
import com.dke.data.agrirouter.api.factories.impl.parameters.CapabilitiesMessageParameters;
import com.dke.data.agrirouter.api.service.messaging.encoding.EncodeMessageService;
import com.dke.data.agrirouter.api.service.parameters.MessageHeaderParameters;
import com.dke.data.agrirouter.api.service.parameters.PayloadParameters;
import com.dke.data.agrirouter.impl.common.MessageIdService;
import com.dke.data.agrirouter.impl.messaging.encoding.EncodeMessageServiceImpl;
import com.google.protobuf.ByteString;
import de.saschadoemer.arts.client.commandline.helper.ErrorPrinter;
import de.saschadoemer.arts.client.commandline.helper.ResultPrinter;

import java.util.ArrayList;

public class EncodeCapabilitiesHandler extends InputHandler {

    private final ErrorPrinter errorPrinter;
    private final ResultPrinter resultPrinter;

    public EncodeCapabilitiesHandler() {
        this.errorPrinter = new ErrorPrinter();
        this.resultPrinter = new ResultPrinter();
    }

    public void handle() {
        try {
            String appCertificationId = this.readInput("Enter the application ID:");
            String appCertificationVersionId = this.readInput("Enter the application certification version ID:");
            String enablePushNotifications = this.readInput("Enter 'true' if you want to enable push notification:");
            String sendingCapabilitiesAsCsv = this.readInput("Enter the capabilities to send as CSV:");
            String receivingCapabilitiesAsCsv = this.readInput("Enter the capabilities to receive as CSV:");

            CapabilitiesMessageContentFactory capabilitiesMessageContentFactory = new CapabilitiesMessageContentFactory();
            CapabilitiesMessageParameters parameters = new CapabilitiesMessageParameters();
            parameters.setAppCertificationId(appCertificationId);
            parameters.setAppCertificationVersionId(appCertificationVersionId);
            parameters.setEnablePushNotifications(Boolean.parseBoolean(enablePushNotifications) ? PushNotification.ENABLED : PushNotification.DISABLED);
            parameters.setCapabilities(new ArrayList<>(2));

            String[] sendingCapabilities = sendingCapabilitiesAsCsv.split(",");
            for (String technicalMessageType : sendingCapabilities) {
                Capabilities.CapabilitySpecification.Capability.Builder capability = Capabilities.CapabilitySpecification.Capability.newBuilder();
                capability.setDirection(Capabilities.CapabilitySpecification.Direction.SEND);
                capability.setTechnicalMessageType(technicalMessageType.trim());
                parameters.getCapabilities().add(capability.build());
            }
            String[] receivingCapabilities = receivingCapabilitiesAsCsv.split(",");
            for (String technicalMessageType : receivingCapabilities) {
                Capabilities.CapabilitySpecification.Capability.Builder capability = Capabilities.CapabilitySpecification.Capability.newBuilder();
                capability.setDirection(Capabilities.CapabilitySpecification.Direction.RECEIVE);
                capability.setTechnicalMessageType(technicalMessageType.trim());
                parameters.getCapabilities().add(capability.build());
            }

            ByteString message = capabilitiesMessageContentFactory.message(parameters);

            EncodeMessageService encodeMessageService = new EncodeMessageServiceImpl();
            MessageHeaderParameters messageHeaderParameters = new MessageHeaderParameters();
            messageHeaderParameters.setTechnicalMessageType(TechnicalMessageType.DKE_CAPABILITIES);
            messageHeaderParameters.setApplicationMessageSeqNo(this.getSeqNo());
            messageHeaderParameters.setApplicationMessageId(MessageIdService.generateMessageId());
            messageHeaderParameters.setMode(Request.RequestEnvelope.Mode.DIRECT);
            PayloadParameters payloadParameters = new PayloadParameters();
            payloadParameters.setValue(message);
            String encodedMessage = encodeMessageService.encode(messageHeaderParameters, payloadParameters);

            this.resultPrinter.print(encodedMessage);

        } catch (Exception e) {
            this.handleException(e);
        }
    }

}
