## Nasa Images & Video Library

####`Steps to run the program :`

    1.  Its a maven project, make sure you do a maven clean install from your project before running the test cases
    2.  There are 2 XML runner located at /NasaImages/src/test/resources
        Run any of the XML runner depending on your need. 
        One of them are for API & another for Web


####`Project Structure :`

    /NasaImages/main/java --    is having all the project related common informations
    /NasaImages/test/java --    is carrying all the test related informations
    


`This project is planned to design on POM with PageFactory Pattarn`
`/NasaImages/src/test/java/pages     -- will have all the page classes
/NasaImages/src/test/java/executor  -- will have all the test executors`

####`Other Class & resources detail :`

* /NasaImages/src/main/java/base/ExtentTestManager.java : 
    Extent Reporting Setup Related informations
* /NasaImages/src/main/java/base/SetupFactory.java : 
    Driver initialization & other required setup for reporting
* /NasaImages/src/main/resources/driver : 
    required drivers for both win & mac for chrome & mozilla
* /NasaImages/src/main/resources/log4j.properties : 
    log4j proparties
* /NasaImages/report-config.xml : 
    config file ror extent report
* /NasaImages/src/test/resources : 
    xml runner for TestNG