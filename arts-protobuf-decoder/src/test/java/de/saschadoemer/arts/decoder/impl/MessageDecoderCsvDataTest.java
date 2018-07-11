package de.saschadoemer.arts.decoder.impl;

import de.saschadoemer.arts.decoder.Decoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Optional;

class MessageDecoderCsvDataTest {

    @ParameterizedTest
    @CsvFileSource(resources = "messages.csv")
    void givenRealLifeData_Decode_ShouldNotFailForAnyOfTheMessages(String message) {
        Decoder decoder = new MessageDecoder();
        Optional<String> decodedMessageOuterClassAsJson = decoder.safeDecode(message);
        Assertions.assertTrue(decodedMessageOuterClassAsJson.isPresent(), String.format("There should be a result for message [%s].",message));
    }

}
