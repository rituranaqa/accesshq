package com.tripplanner.objectsRepo;

import java.util.List;

import org.openqa.selenium.*;

public class HomePageObject{

	//Trip Planner Object
	public static WebElement trip_planner (WebDriver driver)
	{
		return driver.findElement(By.xpath(".//*[@id='tab-pane-tp']"));
	}

	//From Destination Object
	public static WebElement from_station (WebDriver driver)
	{
		return driver.findElement(By.xpath(".//*[@id='search-input-From']"));
	}

	//To Destination Object
	public static WebElement to_station (WebDriver driver)
	{
		return driver.findElement(By.xpath(".//*[@id='search-input-To']"));
	}

	//GO Object
	public static WebElement go_search (WebDriver driver)
	{
		return driver.findElement(By.xpath(".//*[@id='search-button']"));
	}

	//From Best Match
	public static WebElement from_best_match (WebDriver driver)
	{
		return  driver.findElement(By.xpath("//a[@id='suggestion-From-0']"));
	}

	//To Best Match
	public static WebElement to_best_match (WebDriver driver)
	{
		return  driver.findElement(By.xpath("//a[@id='suggestion-To-0']"));
	}
	//trip list
	public static List<WebElement> trip_list(WebDriver driver)
	{
		return  driver.findElements(By.xpath("//div[@role='list']//trip-summary"));
	}
	//trip route
	public static WebElement trip_route(WebDriver driver)
	{
		return driver.findElement(By.xpath(".//*[@id='tni-map']/div[1]/canvas"));
	}

}
