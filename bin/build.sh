#!/usr/bin/bash

# application build script #
echo "#=========================================================================#"
echo "# starting to build the artifacts for parking lot application             #"
echo "#=========================================================================#"


if [ "$EUID" -ne 0 ]
  then echo "Please run as root"
  exit
fi

appDir="/opt/muks/parking_lot"
logsDir="$appDir/logs"
binDir="$appDir/bin"
libDir="$appDir/lib"

echo "# Generating application directory structure..."
echo "
App base dir: $appDir
Logs dir: $logsDir
Bin dir: $binDir
"


# one step back-dir
cd ..
pwd=`pwd`
echo "Pwd: $pwd"


buildCmd="mvn clean install"
buildLog="build.log"
finalBuildCmd="$buildCmd > $buildLog 2>&1"
echo "Executing build command: $finalBuildCmd"