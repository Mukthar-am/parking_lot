#!/bin/bash

# Check if the execution user has root previlidges
USAGE="Usage: $0 <input-file-having-commands>
Eg: $0 file_inputs.txt"
if [ "$1" = "-help" ]
then
    echo ""
    echo "$USAGE"
    echo ""
    exit;
fi


#=== BUILD SECTION ===
echo "#=========================================================================#"
echo "# Application build manager                                               #"
echo "#=========================================================================#"

CWD=`pwd`
LIBDIR="$CWD/target"


#=== BUILD SECTION ===
buildCmd="mvn clean install"
buildLog="$pwd/build.log"
finalBuildCmd=$buildCmd" > "$buildLog" 2>&1"
echo "Executing build command: $finalBuildCmd"
$buildCmd




#=== EXECUTION SECTION ===
if [ $# -eq 0 ]
then
    appStartCmd="java -cp $LIBDIR/parking_lot-1.0.jar org.muks.parking.manager.AppManager"
    $appStartCmd

else
    fileInput="$CWD/$1"    # as first argument is the input file-name, expected to be found at the same path

    appStartCmd="java -cp $LIBDIR/parking_lot-1.0.jar org.muks.parking.manager.AppManager $fileInput"
    $appStartCmd

fi
