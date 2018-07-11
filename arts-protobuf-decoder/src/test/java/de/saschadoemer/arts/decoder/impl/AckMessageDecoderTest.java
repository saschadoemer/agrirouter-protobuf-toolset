package de.saschadoemer.arts.decoder.impl;

import de.saschadoemer.arts.decoder.Decoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class AckMessageDecoderTest {

    @Test
    void givenValidInput_DecodeMessage_ShouldReturnFormattedJsbon() {
        String validInput = "XwjJARABGiQ1NjE3OGRlOC1kMmE1LTRjOTctYjZjYy1lZjA5ZWYxZGZjNGUiJDg2M2NkNjI0LWY1NzMtNDdlMC1h" +
                "NGFiLTdjZGM5NDU3ZTI0ZSoMCPGXr9kFEIDupuwCNgo0CjB0eXBlcy5hZ3Jpcm91dGVyLmNvbS9hZ3Jpcm91dGVyLmNvbW1vbnMuTWVzc2FnZXMSAA==";
        String expectedOutcome = "{\n" +
                "  \"responseCode\": 201,\n" +
                "  \"type\": \"ACK\",\n" +
                "  \"applicationMessageId\": \"56178de8-d2a5-4c97-b6cc-ef09ef1dfc4e\",\n" +
                "  \"messageId\": \"863cd624-f573-47e0-a4ab-7cdc9457e24e\",\n" +
                "  \"timestamp\": \"2018-06-21T16:01:53.764Z\"\n" +
                "}{\n" +
                "  \"details\": {\n" +
                "    \"@type\": \"types.agrirouter.com/agrirouter.commons.Messages\"\n" +
                "  }\n" +
                "}";

        Decoder decoder = new MessageDecoder();
        Optional<String> decodedMessageOuterClassAsJson = decoder.safeDecode(validInput);

        Assertions.assertTrue(decodedMessageOuterClassAsJson.isPresent(), "There should be a result.");
        Assertions.assertEquals(expectedOutcome, decodedMessageOuterClassAsJson.get(), "The outcome should match the expected outcome.");
    }

}