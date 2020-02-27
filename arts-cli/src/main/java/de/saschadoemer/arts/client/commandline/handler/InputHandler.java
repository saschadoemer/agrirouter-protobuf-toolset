package de.saschadoemer.arts.client.commandline.handler;

import com.dke.data.agrirouter.api.dto.onboard.OnboardingResponse;
import com.google.gson.Gson;
import de.saschadoemer.arts.client.commandline.helper.ErrorPrinter;
import de.saschadoemer.arts.client.commandline.helper.ResultPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public abstract class InputHandler {

    private static int seqNo = 1;

    protected final ErrorPrinter errorPrinter;
    protected final ResultPrinter resultPrinter;
    protected final Logger logger = LogManager.getLogger(this.getClass());

    public InputHandler() {
        this.errorPrinter = new ErrorPrinter();
        this.resultPrinter = new ResultPrinter();
    }

    protected String readInput(String message) {
        System.out.println("* " + message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    protected void handleException(Exception e) {
        this.logger.error(e);
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
