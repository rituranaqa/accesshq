package com.tripplanner.test;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TestSuite {
	public static void main(String[] args) {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { TripPlanner.class });
		testng.addListener(tla);
		testng.run();
	}
}
