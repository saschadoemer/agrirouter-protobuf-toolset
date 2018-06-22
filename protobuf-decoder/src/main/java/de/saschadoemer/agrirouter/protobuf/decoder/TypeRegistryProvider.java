package de.saschadoemer.agrirouter.protobuf.decoder;

import agrirouter.commons.MessageOuterClass;
import agrirouter.request.payload.endpoint.Capabilities;
import com.google.protobuf.util.JsonFormat;

public interface TypeRegistryProvider {

    default JsonFormat.TypeRegistry createTypeRegistry(){
        return JsonFormat.TypeRegistry.newBuilder()
                .add(MessageOuterClass.Messages.getDescriptor())
                .add(Capabilities.CapabilitySpecification.getDescriptor())
                .build();
    }
}
