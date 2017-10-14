# parking_lot
Parking lot, vehicle parking management system.

## Dev/Installation Environmental requirements
JDK-8 (Dev and application run environment)

maven-3 (Build environment)


## Getting started / installation instructions
### Build and execution made simple
Check out the project and run the below command.

Command to find help

    ./parking_lot.sh -help


Command to find help

    ./parking_lot.sh file_inputs.txt


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

## Controlling test execution and percentage test coverage
Test execution direct via maven build is controlled by ***maven-surefire-plugin*** and test coverage is controlled by ***jacoco-maven-plugin*** as showed below, a snapshot of the project's pom.xml. Adjust <minimum\>your-value-here-0.0-To-1.0</minimum\>



    <configuration>
        <rules>
            <rule implementation="org.jacoco.maven.RuleConfiguration">
                <element>BUNDLE</element>
                <limits>
                    <limit implementation="org.jacoco.report.check.Limit">
                        <counter>INSTRUCTION</counter>
                        <value>COVEREDRATIO</value>
                        <minimum>0.1</minimum>
                    </limit>
                </limits>
            </rule>
        </rules>
    </configuration>





