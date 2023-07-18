package de.saschadoemer.arts.client.commandline.handler;

import com.dke.data.agrirouter.api.dto.onboard.OnboardingResponse;
import com.google.gson.Gson;
import de.saschadoemer.arts.client.commandline.helper.ErrorPrinter;
import de.saschadoemer.arts.client.commandline.helper.ResultPrinter;

import java.util.Scanner;

public abstract class InputHandler {

    private static int seqNo = 1;

    protected final ErrorPrinter errorPrinter;
    protected final ResultPrinter resultPrinter;

    public InputHandler() {
        errorPrinter = new ErrorPrinter();
        resultPrinter = new ResultPrinter();
    }

    protected String readInput(String message) {
        System.out.println("* " + message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    protected void handleException(Exception e) {
        e.printStackTrace(System.out);
    }

    public int getSeqNo() {
        return seqNo++;
    }

    public abstract void handle();

    protected OnboardingResponse parseOnboardingResponse(String onboardingResponseAsJson) {
        Gson gson = new Gson();
        return gson.fromJson(onboardingResponseAsJson, OnboardingResponse.class);
    }
}
