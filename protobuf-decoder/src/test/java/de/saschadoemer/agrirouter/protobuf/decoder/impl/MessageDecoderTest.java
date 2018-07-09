package de.saschadoemer.agrirouter.protobuf.decoder.impl;

import de.saschadoemer.agrirouter.protobuf.decoder.Decoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class MessageDecoderTest {

    @Test
    void givenValidInputDecodeMessageOutClassShouldReturnFormattedJson() {
        String validInput = "XwjMARACGiQ3YzA2OTZiMy0yODgwLTQxOTUtYjBjNy01NzUxOGI3MWQ5NzYiJDUxOTJiMWJhLWU4OTQtNGIwZC04NGEx" +
                "LWVkYjU2NWUwYzRkNCoMCPy5n9kFEIDxlaEBeAp2CjB0eXBlcy5hZ3Jpcm91dGVyLmNvbS9hZ3Jpcm91dGVyLmNvbW1vbnMu" +
                "TWVzc2FnZXMSQgpACjJObyBkYXRhIGlzIGN1cnJlbnRseSBhdmFpbGFibGUgZm9yIHJlcXVlc3Rl" +
                "ZCBxdWVyeRIKVkFMXzAwMDIwOA==";
        String expectedOutcome = "{\n" +
                "  \"responseCode\": 204,\n" +
                "  \"type\": \"ACK_WITH_MESSAGES\",\n" +
                "  \"applicationMessageId\": \"7c0696b3-2880-4195-b0c7-57518b71d976\",\n" +
                "  \"messageId\": \"5192b1ba-e894-4b0d-84a1-edb565e0c4d4\",\n" +
                "  \"timestamp\": \"2018-06-18T16:25:32.338Z\"\n" +
                "}{\n" +
                "  \"details\": {\n" +
                "    \"@type\": \"types.agrirouter.com/agrirouter.commons.Messages\",\n" +
                "    \"messages\": [{\n" +
                "      \"message\": \"No data is currently available for requested query\",\n" +
                "      \"messageCode\": \"VAL_000208\"\n" +
                "    }]\n" +
                "  }\n" +
                "}";

        Decoder decoder = new MessageDecoder();
        Optional<String> decodedMessageOuterClassAsJson = decoder.safeDecode(validInput);

        Assertions.assertTrue(decodedMessageOuterClassAsJson.isPresent(), "There should be a result.");
        Assertions.assertEquals(expectedOutcome, decodedMessageOuterClassAsJson.get(), "The outcome should match the expected outcome.");
    }

}