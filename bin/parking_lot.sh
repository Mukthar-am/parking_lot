#!/usr/bin/env bash

# Start script for parking lot application #
#echo "# Starting up parking lot application now..."

# Check for the user interaction with the applicatin.
if [ $# -eq 0 ]
then
    echo "No arguments supplied"
    appStartCmd="java -cp /opt/muks/parking_lot/lib/parking_lot-1.0.jar org.muks.parking.manager.AppManager"
    $appStartCmd

else
    fileInput=$1    # as first argument is the input file-name, expected to be found at the same path
    #echo "# Parsing input file $fileInput"

    appStartCmd="java -cp /opt/muks/parking_lot/lib/parking_lot-1.0.jar org.muks.parking.manager.AppManager"
    $appStartCmd

    sleep 3

    while read line; do
        system.in $line
        sleep 3
    done < $fileInput

fi



