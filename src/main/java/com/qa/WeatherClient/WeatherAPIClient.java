package com.qa.WeatherClient;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;

import com.qa.base.TestBase;
import com.qa.utils.Utility;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * 
 * @author AjitRaskar WeatherAPIClient acts as client responsible for executing
 *         the weather API using Get method. It also parses and prints the
 *         response
 *
 */
public class WeatherAPIClient extends TestBase {

	public static Response response;
	public static JsonPath jsonPath;

	/**
	 * This method is responsible for executing the weather api using GET method
	 * 
	 * @return response in raw format
	 */

	public static Response executeWeatherAPI() {
		intialization();

		try {
			response = given().spec(Utility.generateRequestSpecification()).when()
					.get(properties.getProperty("onecalluri")).then().extract().response();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;

	}

	/**
	 * This methods converts raw response in Json format
	 * 
	 * @param rawResponse :Raw response generated when GET method is executed in
	 *                    executeWeatherAPI()
	 * @return response in Json format
	 */
	public static JsonPath getJsonResponse(Response rawResponse) {

		jsonPath = Utility.convertToJsonResponse(rawResponse);
		return jsonPath;
	}

	/**
	 * This method fetches latitude from Json response
	 * 
	 * @param jsonResponse Response in Json format
	 * @return latitude from Json response
	 */
	public static String getLatitude(JsonPath jsonResponse) {

		String actualLatitude = Utility.getValueFromJsonResponse(jsonResponse, "lat");
		return actualLatitude;

	}

	/**
	 * This method fetches longitude from Json response
	 * 
	 * @param jsonResponse Response in Json format
	 * @return longitude from Json response
	 */
	public static String getLongitude(JsonPath jsonResponse) {

		String actualLongitude = Utility.getValueFromJsonResponse(jsonResponse, "lon");
		return actualLongitude;
	}

	
	/**
	 * This method fetches response code from raw response
	 * 
	 * @param rawResponse Response in raw format
	 * @return status code of API execution
	 */
	public static int getResponseCode(Response rawResponse) {

		int statusCode = rawResponse.getStatusCode();
		return statusCode;
	}

	/**
	 * This method parses the Json response
	 * 
	 * @param jsonResponse: Response in Json format
	 */
	public static void performParsing(JsonPath jsonResponse) {

		Utility.parseTheResponse(jsonResponse);

	}

	/**
	 * This method prints day and its temperature for all upcoming 7 days from
	 * current date
	 */
	public static void printDayAndTemperatureOfAllDays() {

		Utility.displayDayAndTempOfAllDays();

	}

	/**
	 * This method prints day and its temperature of those days where temperature is
	 * predicated to be above 20 degrees / or whatever predicted temperature is
	 * configured in config.properties.
	 */

	public static void printDayAndTemperatureOfSelectedDays() {

		Utility.displayDayAndTempOfSelectedDays();
	}

	/**
	 * This method prints day and its weather type for all upcoming 7 days from
	 * current date.
	 */
	public static void printDayAndWeatherOfAllDays() {

		Utility.displayDayAndWeatherOfAllDays();
	}

	/**
	 * This method prints day and it's weather type of those days where weather type
	 * is predicated to be clear/sunny or whatever predicted weather type is
	 * configured in config.properties. It also prints number of days which will
	 * have predicted weather type configured in config.properties
	 */
	public static void printNumberofWeatherDays() {
		Utility.displayDayAndWeatherOfSelectedDays();

	}

}
