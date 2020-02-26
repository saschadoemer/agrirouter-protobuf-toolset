package de.saschadoemer.arts.client.commandline.handler;

import com.dke.data.agrirouter.api.env.Environment;
import com.dke.data.agrirouter.api.env.QA;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public abstract class InputHandler {

    protected final Logger logger = LogManager.getLogger(this.getClass());
    private static int seqNo = 1;

    protected String readInput(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    protected Environment getEnv() {
        return new QA() {
            @Override
            public String getAgrirouterLoginUsername() {
                throw new NotImplementedException("Not supported.");
            }

            @Override
            public String getAgrirouterLoginPassword() {
                throw new NotImplementedException("Not supported.");
            }
        };
    }

    protected void handleException(Exception e) {
        this.logger.error(e);
    }

    public int getSeqNo() {
        return seqNo++;
    }
}
