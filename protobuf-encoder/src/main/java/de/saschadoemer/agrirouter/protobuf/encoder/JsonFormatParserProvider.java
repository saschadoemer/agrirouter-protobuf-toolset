package de.saschadoemer.agrirouter.protobuf.encoder;

import com.google.protobuf.util.JsonFormat;

public interface JsonFormatParserProvider extends TypeRegistryProvider {

    default JsonFormat.Parser createParser() {
        return JsonFormat.parser().usingTypeRegistry(this.createTypeRegistry());
    }

}
