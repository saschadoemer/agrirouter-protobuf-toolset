package de.saschadoemer.arts.decoder.impl;

import de.saschadoemer.arts.decoder.Decoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class CapabilitiesMessageDecoderTest {

    @Test
    void givenValidInput_DecodeMessage_ShouldReturnFormattedJson() {
        String validInput = "RgokMDIxZDhhMTEtY2RhYS00NzM1LWFjMTItOGYxN2Y2YWU0M2RmEAEaEGRrZTpjYXBhYmlsaXRpZXNCCgjpl6/ZBRDAhD2dAQqaAQo7YWdyaXJvdXRlci5yZXF1ZXN0Ln" +
                "BheWxvYWQuZW5kcG9pbnQuQ2FwYWJpbGl0eVNwZWNpZmljYXRpb24SWwoNCglka2U6b3RoZXIQAhIkOTgwN2MyZTMtMjkwNS00MTlmLWEwZDAtYmE2ZWU1ODZlOTQ0GiRmZTdiODkxNi1i" +
                "ZjI5LTRlYWEtYTVkZC02MjlhMzhhYTIyMGE=";
        String expectedOutcome = "{\n" +
                "  \"type\": \"ACK\",\n" +
                "  \"applicationMessageId\": \"dke:capabilities\"\n" +
                "}{\n" +
                "  \"details\": {\n" +
                "    \"@type\": \"types.agrirouter/agrirouter.request.payload.endpoint.CapabilitySpecification\",\n" +
                "    \"capabilities\": [{\n" +
                "      \"technicalMessageType\": \"dke:other\",\n" +
                "      \"direction\": \"SEND_RECEIVE\"\n" +
                "    }],\n" +
                "    \"appCertificationId\": \"9807c2e3-2905-419f-a0d0-ba6ee586e944\",\n" +
                "    \"appCertificationVersionId\": \"fe7b8916-bf29-4eaa-a5dd-629a38aa220a\"\n" +
                "  }\n" +
                "}";

        Decoder decoder = new MessageDecoder();
        Optional<String> decodedMessageOuterClassAsJson = decoder.safeDecode(validInput);

        Assertions.assertTrue(decodedMessageOuterClassAsJson.isPresent(), "There should be a result.");
        Assertions.assertEquals(expectedOutcome, decodedMessageOuterClassAsJson.get(), "The outcome should match the expected outcome.");
    }

}