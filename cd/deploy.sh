#!/usr/bin/env bash
#if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
#    mvn deploy -P sign,build-extras --settings cd/mavensettings.xml
#fi
#
#if [ "$TRAVIS_BRANCH" = 'develop' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
#    mvn deploy -P build-extras --settings cd/mavensettings.xml
#fi

mvn deploy -P -X build-extras --settings cd/mavensettings.xml
