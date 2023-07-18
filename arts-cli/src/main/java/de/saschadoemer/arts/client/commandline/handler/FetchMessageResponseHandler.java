package de.saschadoemer.arts.client.commandline.handler;

import com.dke.data.agrirouter.api.dto.messaging.FetchMessageResponse;
import com.dke.data.agrirouter.api.dto.onboard.OnboardingResponse;
import com.dke.data.agrirouter.api.service.messaging.http.FetchMessageService;
import com.dke.data.agrirouter.impl.messaging.rest.FetchMessageServiceImpl;
import com.dke.data.agrirouter.impl.messaging.rest.MessageFetcher;

import java.util.List;
import java.util.Optional;

public class FetchMessageResponseHandler extends InputHandler {

    @Override
    public void handle() {
        String onboardingResponseAsJson = readInput("Please paste your onboarding response:");
        OnboardingResponse onboardingResponse = parseOnboardingResponse(onboardingResponseAsJson);
        FetchMessageService fetchMessageService = new FetchMessageServiceImpl();
        Optional<List<FetchMessageResponse>> optionalFetchMessageResponses = fetchMessageService.fetch(onboardingResponse, MessageFetcher.MAX_TRIES_BEFORE_FAILURE, MessageFetcher.DEFAULT_INTERVAL);
        if (optionalFetchMessageResponses.isPresent()) {
            List<FetchMessageResponse> fetchMessageResponses = optionalFetchMessageResponses.get();
            for (FetchMessageResponse fetchMessageResponse : fetchMessageResponses) {
                resultPrinter.print(fetchMessageResponse);
            }
        }
    }
}
