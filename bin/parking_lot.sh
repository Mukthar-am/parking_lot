#!/usr/bin/env bash

# Start script for parking lot application #
#echo "# Starting up parking lot application now..."

cd ..
pwd=`pwd`
libDir="$pwd/lib"

# Check for the user interaction with the applicatin.
if [ $# -eq 0 ]
then
    echo "No arguments supplied"
    appStartCmd="java -cp $libDir/parking_lot-1.0.jar org.muks.parking.manager.AppManager"
    $appStartCmd

else
    fileInput=$1    # as first argument is the input file-name, expected to be found at the same path
    #echo "# Parsing input file $fileInput"

    appStartCmd="java -cp $libDir/parking_lot-1.0.jar org.muks.parking.manager.AppManager $fileInput"
    $appStartCmd

fi



