#!/usr/bin/env bash

# application build script #

echo "# starting to build the artefacts for parking lot application"

# one step back-dir
cd ..
pwd=`pwd`
echo "Pwd: $pwd"

`mvn clean install`
