#!/usr/bin/env bash
if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    openssl aes-256-cbc -K $encrypted_f4089c555473_key -iv $encrypted_f4089c555473_iv -in codesigning.asc.enc -out codesigning.asc -d
fi