package de.saschadoemer.agrirouter.protobuf.decoder;

import agrirouter.commons.MessageOuterClass;
import com.google.protobuf.util.JsonFormat;

public interface TypeRegistryProvider {

    default JsonFormat.TypeRegistry createTypeRegistry(){
        return JsonFormat.TypeRegistry.newBuilder().add(MessageOuterClass.Messages.getDescriptor()).build();
    }
}
