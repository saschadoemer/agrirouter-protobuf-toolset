package de.saschadoemer.agrirouter.protobuf.decoder;

import com.google.protobuf.util.JsonFormat;

public interface JsonFormatPrinterProvider extends TypeRegistryProvider {

    default JsonFormat.Printer createPrinter() {
        return JsonFormat.printer().usingTypeRegistry(this.createTypeRegistry());
    }

}