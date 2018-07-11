# ARTS - The AgriRouter ToolSet

[![Build status for develop](https://travis-ci.com/saschadoemer/agrirouter-protobuf-toolset.svg?branch=develop)](https://travis-ci.com/saschadoemer/agrirouter-protobuf-toolset)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3f2f2fb0c18b47b983e2144ff3ebd95c)](https://www.codacy.com/app/cf4thqgxcnxaovouxtnv/agrirouter-protobuf-toolset?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=saschadoemer/agrirouter-protobuf-toolset&amp;utm_campaign=Badge_Grade)

# Current version

Release | [Snapshot](https://oss.sonatype.org/content/repositories/snapshots/de/saschadoemer/)

## Project description

### The agrirouter?

The agrirouter is a universal data exchange platform for farmers and agricultural contractors that makes it possible to connect machinery and agricultural software, regardless of vendor or manufacturer. Agrirouter does not save data; it transfers data.

As a universal data exchange platform, agrirouter fills a gap on the way to Farming 4.0. Its underlying concept unites cross-vendor and discrimination-free data transfer. You retain full control over your data. Even data exchange with service providers (e.g. agricultural contractors) and other partners is uncomplicated: Data are very rapidly transferred via the online connection, and if you wish, is intelligently connected to other datasets.

### The current project you're looking at?

This project contains a simple toolset for working with the agrirouter to ensure easy development, testing and debugging. At this point the project will include sub projects with different approaches.

#### Command line client

You can run the client by downloading the latest RELEASE | [SNAPSHOT](https://oss.sonatype.org/content/repositories/snapshots/de/saschadoemer/agrirouter/protobuf-client-commandline) and 
execute `java -jar protobuf-client-commandline-X.X.X.jar` for the latest release and `java -jar protobuf-client-commandline-X.X.X-SNAPSHOT.jar` for the latest snapshot.

_Requirements_

Currently the client requires a Java 9 runtime environment to run properly. No further needs.

#### Rich client interface 

You can run the client by downloading the latest RELEASE | [SNAPSHOT](https://oss.sonatype.org/content/repositories/snapshots/de/saschadoemer/agrirouter/protobuf-client-javafx) and 
execute `java -jar protobuf-client-javafx-X.X.X.jar` for the latest release and `java -jar protobuf-client-javafx-X.X.X-SNAPSHOT.jar` for the latest snapshot.

_Requirements_

Currently the client requires a Java 9 runtime environment to run properly. No further needs. 

#### Web service implementation

-- TBD --

### Missing something? Something is broken?

Feel free to file a feature request or a bug using the issue tracker.

### How to support the project?

If you like the work we are doing, feel free to [buy us a coffee](buymeacoff.ee/ks0iWGZlR). We're open for any kind of contribution, feel free to file issues, feature requests or anything else.

# Development

## Custom maven settings

To run code coverage add the following snippet to your `.m2/settings.xml`.

```xml    
<pluginGroups>
    <pluginGroup>org.openclover</pluginGroup>
</pluginGroups>
```

This enables the much short usage of `mvn clean clover:setup test clover:aggregate clover:clover` to generate the coverage reports. 