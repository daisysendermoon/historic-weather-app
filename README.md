# historic-weather

Application URL if you run the application locally:

http://localhost:8080/historic-weather/home
or
http://localhost:8080/historic-weather/

This is a Springboot application with a H2 in memory DB initialized every time you start the application.

# run application on linux/mac/windows10:

java -jar historic-weather-0.0.1-SNAPSHOT.jar

But please make sure you run the command with jdk 1.8. If you use java 9 or higher, you will be hit by JAXBException noClassDefFoundError.
https://stackoverflow.com/questions/43574426/java-how-to-resolve-java-lang-noclassdeffounderror-javax-xml-bind-jaxbexceptio

# compile and test:

Use intellij to open the build.gradle file and project will be created. In there, you can use Build panel to build and go to test package of the project to run test class.

# client and server side validation rule

I am validating if both start_date and end_date is null. Plus the date input type itself do the format check.
If start_date is empty and end_date is not empty, I will search from 1900-01-01 to end_date.
If start_date is not empty and end_date is empty, I will search from start_date to 2100-01-01.
The start_date is inclusive, the end_date is exclusive in terms of search time span.

# notes:

You will find I have to use a temp table in schema.sql. That is because there is an inconsistency when CSVREAD function get called across mac and windows system.
In windows, the function will treat the first line of csv file as data and caused an error: COLUMN does not exists...
