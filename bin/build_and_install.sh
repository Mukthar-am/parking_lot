#!/usr/bin/bash

# application build script #
echo "#=========================================================================#"
echo "# Application build manager                                               #"
echo "#=========================================================================#"

# Check if the execution user has root previlidges
USAGE="Usage: sh build_and_install.sh /opt/parking_lot\n"

if [ $# -eq 0 ]
then
    echo "Please provide the app installation path.
    $USAGE"
    exit
fi

appDir=$1
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
buildLog="$pwd/build.log"
finalBuildCmd=$buildCmd" > "$buildLog" 2>&1"
echo "Executing build command: $finalBuildCmd"

$buildCmd

echo "# Printing contents of target build directory for reference usage."
fileListing="ls -l target"
$fileListing

# Coping bin and lib files at the app install path
cpBinFiles="cp -rf bin/parking_lot.sh $binDir"
cpAppLib="cp -rf target/parking_lot-1.0.jar $libDir"
$cpBinFiles
$cpAppLib


# Change bin files permissions to executable so that ./ is a valid execution method as per doc
changePermissions="chmod -R a+x $binDir"
$changePermissions

# listing of parent level dir at the end.
fileListingAppPath="ls -l $libDir"
$fileListingAppPath

fileListingBinPath="ls -l $binDir"
$fileListingBinPath

commandInputFile="file_inputs.txt"
commandFileInputs="cp -rf bin/$commandInputFile $binDir"
$commandFileInputs