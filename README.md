# parking_lot
Parking lot, vehicle parking management system.

## Dev/Installation Environmental requirements
JDK-8 (Dev and application run environment)

maven-3 (Build environment)


## Getting started / installation instructions
1. Check out/clone the project locally.
2. Navigate to bin director for automated build and installation, where the build_and_install.sh script is.

            cd parking_lot/bin
3. Run build and installation.

            sh build_and_installation.sh <app-install-path>

    Boom, you have your application installed @ local path as mentioned above.

## Running application
1. Running tests

        mvn clean install

    (Will run the tests first and upon 40% test pass, application jar build takes place. By default, parking_lot-1.0.jar will be available under target directory)

2. Running application

    Two ways of running application as per the instruction in requirement doc.
    #### 2.1 Running along with commands in file input
        cd <application-installed-path>/bin
        ./parking_lot.sh file_inputs.txt (while file_inputs.txt will be found under same bin dir for ease)

    #### 2.2 Running application with user interation
                cd <application-installed-path>/bin
                ./parking_lot.sh

    Henceforth, the execution is as per the instructions in the requirement doc.




