# TechChallenge

Summary: 
=================================
This project has built a client which communicates with open weather map API called One call API. Main task of this client is to predict weather and temperature of Sydney for upcoming 7 days from the current date.
This project also has one TestNG class which tests this client.

Tools and Technology used:
=================================
Java, Rest assured library,TestNG, Maven,Log4j API
API used: One call API  https://openweathermap.org/api/one-call-api

Problem statement:
=================================
Find the number of days in Sydney where the temperature is predicated to be above 20 degrees (at the time of calling the API) in the next 7 days (from the current days date), or whichever period the free subscription will allow.
Also find how many days it is predicted to be sunny in the same time period.

Instructions to execute the code
=================================
1: Clone the repository <<repo name>>
2: Navigate to root directory and fire the command mvn clean install
3:Or the other way is once you have the code mentioned in above git repository in your editor then right click the testng.xml and select Run As TestNG Suite
Note:testng.xml is present at the root directory level. This is a main configuration file in TestNG

Test cases executed:
=================================
1: validateResponseCodeTest() : This verifies the status code of API execution.
2: validateLatitudeLongitudeTest(): This verifies values of latitude and longitude from API response.
3: validateWeatherDetailsTest(): This test focusses on parsing the response of API execution and prints the result for the requirement mentioned in ‘Problem statement’ section
Test case result artifacts:
After executing the code, refer below files for more information.
1:<<Root Dir>>/Result.log : This contains the detailed report for the requirement mentioned in ‘Problem statement’ section
2:<<Root Dir>>/target/request.log: Contains logs related to request
3:<<Root Dir>>/target/response.log: Contains logs related to response
4:<<Root Dir>>/test-output/index.html : Contains results of all the test cases

Project structure:
=================================
<<Root Dir>>src\main\java\com\qa\base\TestBase.java : This is a super class of all classes and it loads the configuration file(config.properties)
<<Root Dir>>\src\main\java\com\qa\utils\Utility.java : This contains various utility methods for parsing the response and printing the result.
<<Root Dir>>\src\main\java\com\qa\WeatherClient\WeatherAPIClient.java
This is a weather api client code.
<<Root Dir>>src\test\java\com\qa\test\WeatherAPITest.java : This is a testNG class which tests the weather api client
<<Root Dir>>\src\main\resources\config.properties: This is a property file containing various parameters for request.
<<Root Dir>>\src\main\resources\log4j.properties : This is a configuration file for logging mechanism.
<<Root Dir>>\testng.xml : This is a testNG configuration file
<<Root Dir>>\pom.xml : This file contains all the maven dependencies.

Sample output
Below is the sample output which shows the number of days in Sydney where the temperature is predicated to be above 20 degrees in next 7 days from the current date and find how days it is predicted to be sunny in the same time period.
Note here current date is 22-04-2021. Sydney is specified using its latitude and longitude values in the request.
Output:
####################################################################
Printing below details for location with latitude= 33.8688 and longitude = 151.2093

Day and Predicted Temperature of all upcoming 7 days where current date is :22-04-2021
Day :24-04-2021(1619226000), Temperature : 14.07 degrees
Day :25-04-2021(1619312400), Temperature : 13.56 degrees
Day :29-04-2021(1619658000), Temperature : 14.87 degrees
Day :27-04-2021(1619485200), Temperature : 11.95 degrees
Day :26-04-2021(1619398800), Temperature : 15.92 degrees
Day :23-04-2021(1619139600), Temperature : 11.9 degrees
Day :28-04-2021(1619571600), Temperature : 13.77 degrees

####################################################################

There is no single day in next 7 days where temperature is predicated to be above 20 degrees

####################################################################
Day and Predicted Weather of all upcoming 7 days where current date is :22-04-2021
Day :24-04-2021(1619226000), Weather : Rain
Day :25-04-2021(1619312400), Weather : Clouds
Day :29-04-2021(1619658000), Weather : Clouds
Day :27-04-2021(1619485200), Weather : Clear
Day :26-04-2021(1619398800), Weather : Rain
Day :23-04-2021(1619139600), Weather : Rain
Day :28-04-2021(1619571600), Weather : Clouds

####################################################################

Number of Sunny Days are : 1
Those days and their weather are::
Day :27-04-2021(1619485200), Weather : Clear
####################################################################

Understanding the output
=================================
There are 4 sections of the output
Section 1: It contains Day and Predicted Temperature of all upcoming 7 days from the current date.
Section 2: Prints numbers of days where temperature is predicted to be above 20 degrees.
Section 3: Prints Day and Predicted weather of all upcoming 7 days from the current date.
Section 4: Prints number of sunny days

Note: A day which has weather predicted as Clear is treated as a sunny day.
From the response there is a parameter called daily.weather.main which treats a day either sunny/rainy/cloudy etc . 
For various weather parameters refer https://openweathermap.org/api/one-call-api
For more information on various weather conditions, refer https://openweathermap.org/weather-conditions#Weather-Condition-Codes-2 



