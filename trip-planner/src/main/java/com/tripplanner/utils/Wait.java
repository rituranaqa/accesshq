package com.tripplanner.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {

	public static void waitByXpath (WebDriver driver, int waitTime, String xpathString)
	{
		// Wait for Auto Search Screen
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathString)));
	}
}