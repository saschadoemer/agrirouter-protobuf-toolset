package de.saschadoemer.arts.client.commandline.handler;

import com.dke.data.agrirouter.api.dto.onboard.OnboardingResponse;
import com.dke.data.agrirouter.api.service.messaging.SendMessageService;
import com.dke.data.agrirouter.api.service.parameters.SendMessageParameters;
import com.dke.data.agrirouter.impl.common.MessageIdService;
import com.dke.data.agrirouter.impl.messaging.rest.SendMessageServiceImpl;

import java.util.Collections;

public class SendCommandHandler extends InputHandler {

    @Override
    public void handle() {
        try {
            String encodedMessage = readInput("Please paste your message:");
            String onboardingResponseAsJson = readInput("Please paste your onboarding response:");

            OnboardingResponse onboardingResponse = parseOnboardingResponse(onboardingResponseAsJson);
            SendMessageService sendMessageService = new SendMessageServiceImpl();
            SendMessageParameters parameters = new SendMessageParameters();
            parameters.setApplicationMessageId(MessageIdService.generateMessageId());
            parameters.setOnboardingResponse(onboardingResponse);
            parameters.setEncodedMessages(Collections.singletonList(encodedMessage));
            parameters.setSequenceNumber(getSeqNo());
            
            sendMessageService.send(parameters);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

}
