package com.qa.test;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.WeatherClient.WeatherAPIClient;
import com.qa.base.TestBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * 
 * @author AjitRaskar This is a TestNG class which tests the weather
 *         client(WeatherAPIClient.java) and prints the result as per the
 *         requirement.
 *
 */
public class WeatherAPITest extends TestBase {

	public static Response rawResponse;
	public static JsonPath jsonResponse;
	public static Logger log;

	/**
	 * This method initializes the Logger class for logging mechanism
	 */
	@BeforeTest
	public void setup() {
		log = Logger.getLogger(WeatherAPITest.class);

	}

	/**
	 * A test method which validates response code from the response
	 */
	@Test(priority = 1)
	public void validateResponseCodeTest() {

		rawResponse = WeatherAPIClient.executeWeatherAPI(); // Weather client executes the weather api
		jsonResponse = WeatherAPIClient.getJsonResponse(rawResponse);
		int statusCode = WeatherAPIClient.getResponseCode(rawResponse);
		Assert.assertEquals(statusCode, STATUS_CODE_200, "API Execution failed.Status code is not 200"); // Assert status code
																											
																											
		log.info("Response details are as below :");
		log.info("Status code = " + statusCode);
	}

	/**
	 * A test method which validates latitude and longitude from response
	 */
	@Test(priority = 2, dependsOnMethods = "validateResponseCodeTest")
	public void validateLatitudeLongitudeTest() {

		SoftAssert softAssert = new SoftAssert();
		String actualLatitude = WeatherAPIClient.getLatitude(jsonResponse);
		softAssert.assertEquals(actualLatitude, properties.getProperty("latitude"),
				"API response showing incorrect latitude"); // Assert latitude
		String actualLongitude = WeatherAPIClient.getLongitude(jsonResponse);
		softAssert.assertEquals(actualLongitude, properties.getProperty("longitude"),
				"API response showing incorrect longitude");// Assert longitude
		softAssert.assertAll(); // Assert the result of soft assertion
		log.info("Latitude =  " + actualLatitude);
		log.info("Longitude = " + actualLongitude);
	}

	/**
	 * A test method which parses the Json response and prints the output as per the
	 * requirement.
	 */
	@Test(priority = 3, dependsOnMethods = "validateResponseCodeTest")
	public void validateWeatherDetailsTest() {
		WeatherAPIClient.performParsing(jsonResponse); // Weather api client parses the Json resonse.
		WeatherAPIClient.printDayAndTemperatureOfAllDays();// Prints day and temperature of all upcoming 7 days
		WeatherAPIClient.printDayAndTemperatureOfSelectedDays();// Prints day and temperature of selected days
		WeatherAPIClient.printDayAndWeatherOfAllDays();// Prints day and weather type of all upcoming 7 days
		WeatherAPIClient.printNumberofWeatherDays(); // Weather api client prints the number of sunny days

	}
	@AfterTest
	public void cleaup()
	{
		System.out.println("Please refer Result.log for the result of execution");
		
	}

}
