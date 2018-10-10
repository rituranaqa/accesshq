package com.tripplanner.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TripPlanner {
	static Logger LOG = Logger.getLogger(TripPlanner.class);

	WebDriver driver = null;

	@BeforeTest
	public void launchUrl() throws InterruptedException {
		driver = new FirefoxDriver();  
		//Puts an Implicit wait, Will wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Launch website
		driver.navigate().to("https://transportnsw.info/trip");
		//Maximize window
		driver.manage().window().maximize();

	}

	@Test
	public void findTrip() throws InterruptedException {
		// Click on Trip Planner9
		com.tripplanner.objectsRepo.HomePageObject.trip_planner(driver).click();
		// Enter From Station
		com.tripplanner.objectsRepo.HomePageObject.from_station(driver).sendKeys("North Sydney");
		// Wait for Auto Search Screen
		com.tripplanner.utils.Wait.waitByXpath(driver, 10,"//*[@id='suggestions-From']");
		//Click on first best match
		com.tripplanner.objectsRepo.HomePageObject.from_best_match(driver).click();
		//Enter To Station
		com.tripplanner.objectsRepo.HomePageObject.to_station(driver).sendKeys("Town Hall");
		// Wait for Auto Search Screen
		com.tripplanner.utils.Wait.waitByXpath(driver, 10,"//*[@id='suggestions-To']");
		//Click on first best match
		com.tripplanner.objectsRepo.HomePageObject.to_best_match(driver).click();
		// Click on GO
		com.tripplanner.objectsRepo.HomePageObject.go_search(driver).click();
		// Get Result
		List<WebElement> list = com.tripplanner.objectsRepo.HomePageObject.trip_list(driver);

		Assert.assertNotNull(list, "List of trips is not available.");
		Assert.assertTrue(list.size()>0, "List of trips is empty.");

		LOG.info("Available trips list : "+list.size());

		for(WebElement e : list) {
			LOG.info(e.getText());
			LOG.info("-------------------");
		}

		// Get Route Image
		//utils.Screenshort.takeScreenshort(driver, objectsRepo.HomePage.trip_route(driver), "C:\\SeleniumPrac\\result\\result2.jpg");
	}

	@AfterTest
	public void closeDriver () {
		//Close driver
		driver.close();
	}
	
}