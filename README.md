# agrirouter-protobuf-toolset

## Build status

* ![Build status for master](https://travis-ci.com/saschadoemer/masterjigs.svg?token=fGmM42PoYQ9sywqUX4Rz&branch=master) | `master`
* ![Build status for develop](https://travis-ci.com/saschadoemer/agrirouter-protobuf-toolset.svg?branch=develop) | `develop`

## Quality status

* [Code Quality](https://app.codacy.com/project/cf4thqgxcnxaovouxtnv/agrirouter-protobuf-toolset/dashboard)

## Project description

### The agrirouter?

The agrirouter is a universal data exchange platform for farmers and agricultural contractors that makes it possible to connect machinery and agricultural software, regardless of vendor or manufacturer. Agrirouter does not save data; it transfers data.

As a universal data exchange platform, agrirouter fills a gap on the way to Farming 4.0. Its underlying concept unites cross-vendor and discrimination-free data transfer. You retain full control over your data. Even data exchange with service providers (e.g. agricultural contractors) and other partners is uncomplicated: Data are very rapidly transferred via the online connection, and if you wish, is intelligently connected to other datasets.

### The current project you're looking at?

This project contains a simple toolset for working with the agrirouter protobuf files to ensure easy development, testing and debugging. At this point the project will include:

* ...a command line client to encode, decode and validate Protobuf using the given types the AR supports.
* ...a web service implementation which can be run as a Spring Boot JAR with the same functionality as the command line client.
* ...a rich client interface with the same functionality as the command line client.

### How to support the project?

If you like the work we are doing, feel free to [buy us a coffee](buymeacoff.ee/ks0iWGZlR).

### Development setup

* Clone the repository using SSH, see [gist for further information](https://gist.github.com/developius/c81f021eb5c5916013dc) how to setup SSH.
* Import Maven project into IDE of your choice.
* Generate sources using `mvn clean package`.

