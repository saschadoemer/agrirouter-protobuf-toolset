package de.saschadoemer.arts.encoder;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import de.saschadoemer.arts.api.exceptions.CouldNotEncodeProtobufException;

import java.util.Optional;

public interface Encoder<T extends Message> extends JsonFormatParserProvider {

    /**
     * Creating protobuf representation.
     *
     * @return JSON
     */
    Optional<T> encode(String json);

    default Optional<T> encode(String json, T.Builder builder) {
        try {
            this.createParser().merge(json, builder);
            return (Optional<T>) Optional.ofNullable(builder.build());
        } catch (InvalidProtocolBufferException e) {
            throw new CouldNotEncodeProtobufException(json, e);
        }
    }


}