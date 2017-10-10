#!/usr/bin/bash

# application build script #
echo "#=========================================================================#"
echo "# Application build manager                                               #"
echo "#=========================================================================#"

## Check if the execution user has root previlidges
#if [ "$EUID" -ne 0 ]
#  then echo "Please run as root"
#  exit
#fi

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
`mkdir -p $logsDir`
`mkdir -p $binDir`
`mkdir -p $libDir`

# one step back-dir
cd ..
pwd=`pwd`
echo "Currently @ $pwd"


buildCmd="mvn clean install"
buildLog="build.log"
finalBuildCmd=$buildCmd." > ".$buildLog." 2>&1"
echo "Executing build command: $finalBuildCmd"

$buildCmd

echo "# Printing contents of target build directory for reference usage."
fileListing="ls -l target"
$fileListing

cpBinFiles="cp -rf bin/parking_lot.sh $binDir"
cpAppLib="cp -rf target/parking_lot-1.0.jar $libDir"

$cpBinFiles
$cpAppLib