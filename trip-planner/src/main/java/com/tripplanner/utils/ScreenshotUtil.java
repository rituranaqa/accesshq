package com.tripplanner.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ScreenshotUtil {

	public static void takeScreenshot (WebDriver driver, WebElement element, String filepath)
	{
		File resultImage = driver.findElement(By.xpath(".//*[@id='tp-result-list']")).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(resultImage, new File(filepath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
