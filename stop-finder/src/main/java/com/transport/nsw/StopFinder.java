package com.transport.nsw;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class StopFinder {
	static Logger LOG = Logger.getLogger(StopFinder.class);

	/**
	 * Scenario: A stop providing multiple transport options can be located 
	 * Given
	 * 		Phileas is looking for a stop 
	 * When 
	 * 		he searches for "Wynyard Station" 
	 * Then 
	 * 		a stop named "Wynyard Station, Sydney" is found And the stop provides more than one mode of transport
	 * 
	 */
	@Test
	public void testSearchStopByNameAndValidateModes() {
		String sName = "Wynyard Station";
		String url = "https://www.transportnsw.info/web/XML_STOPFINDER_REQUEST?TfNSWSF=true&language=en&outputFormat=rapidJSON&type_sf=any&version=10.2.2.48";

		// The direct replacement can have encoding issues ?
		// Response resp =
		// RestAssured.get("https://www.transportnsw.info/web/XML_STOPFINDER_REQUEST?TfNSWSF=true&language=en&name_sf="
		// + sName +"&outputFormat=rapidJSON&type_sf=any&version=10.2.2.48");

		LOG.info("Sending the request to transport service api.");

		// The Simpler approach to pass the request param for a get
		Response resp = RestAssured.given().param("name_sf", sName).get(url);

		int statusCode = resp.getStatusCode();

		LOG.info("Recieved the response from api. status code: " + statusCode);

		// check the response code if the service is returning the successful results.
		Assert.assertEquals(statusCode, 200, "The transport service failed to serve the request.");

		LOG.info("Asserting the response.. ");

		// get the stop name for the search input.
		String name = resp.jsonPath().getString("locations[0].name");
		// get the transport modes
		List<Integer> modes = resp.jsonPath().getList("locations[0].modes");

		LOG.info("Response stop-name="+ name +", transport-modes: "+ modes);
		// assert the results ...
		// the name must match or fail the test with proper message
		Assert.assertEquals(name, "Wynyard Station, Sydney", "Stop name does not match.");

		// assert modes is non null
		Assert.assertNotNull(modes, "The transport modes are not found.");

		// assert the modes have more than 1 transport modes.
		Assert.assertTrue(modes.size() > 1, "Multiple transport modes are not available.");

		LOG.info("Finished result validation.");

	}

}
