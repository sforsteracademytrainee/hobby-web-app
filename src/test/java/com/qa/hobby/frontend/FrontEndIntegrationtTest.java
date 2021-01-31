package com.qa.hobby.frontend;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import com.qa.hobby.frontend.pages.HomePage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class FrontEndIntegrationtTest {
	
	private static WebDriver driver;
	private static ExtentReports extent;
	private static ExtentTest test;
	
	@BeforeAll
	public static void init() {
		// when extent reporting get extent here
		
		extent = new ExtentReports("target/reports/report.html");
		
		System.setProperty("webdriver.chrome.driver",
				"src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1366, 768));
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
	}
	
	@AfterEach
	public void postTest() {
		extent.endTest(test);
	}
	
	@AfterAll
	public static void cleanUp() {
		driver.quit();
		System.out.println("The Selenium driver has been cleaned up.");
		extent.close();
	}
	
	// --- PEOPLE ---
	
	@Test
	public void testPersonRead() { // working
		test = extent.startTest("Person read");
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navPeople();
		website.people.navCreate();
		website.people.create.createPerson("Test", "Name", "2 Road Lane", "132");
		Long test_id = website.people.read.readId();
		Long result = website.people.read.search(test_id);
		assertEquals(result, test_id);
		if (result == test_id) {
			test.log(LogStatus.PASS, "Successfully read the person.");
		} else {
			test.log(LogStatus.FAIL, "Failed to read the person.");
		}
	}
	
	@Test
	public void testPersonReadAll() { // working
		test = extent.startTest("Person read all");
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navPeople();
		website.people.navReadAll();
		boolean result = website.people.readAll.getItemsExist();
		assertTrue(result);
		if (result) {
			test.log(LogStatus.PASS, "Successfully read all people.");
		} else {
			test.log(LogStatus.FAIL, "Failed to read all people.");
		}
	}
	
	@Test
	public void testPersonCreate() {
		test = extent.startTest("Person create");
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navPeople();
		website.people.navCreate();
		website.people.create.createPerson("Jerry", "Faketest", "43 Money Road", "12 345 678 901");
		String result = website.people.read.readAddress();
		assertEquals(result, "43 Money Road");
		website.people.read.deletePerson();
		if (result.equals("43 Money Road")) {
			test.log(LogStatus.PASS, "Successfully created person.");
		} else {
			test.log(LogStatus.FAIL, "Failed to create person.");
		}
	}
	
	@Test
	public void testPersonDelete() { // relies on the fact that read and create works
		test = extent.startTest("Person delete");
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navPeople();
		website.people.navCreate();
		website.people.create.createPerson("Jerry", "Faketest", "43 Money Road", "12 345 678 901");
		boolean result = website.people.read.deletePerson();
		assertTrue(result);
		if (result) {
			test.log(LogStatus.PASS, "Successfully deleted the person.");
		} else {
			test.log(LogStatus.FAIL, "Failed to delete the person.");
		}
	}
	
	@Test
	public void testPersonUpdate() { // relies on the fact that read and create works
		test = extent.startTest("Person update");
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navPeople();
		website.people.navCreate();
		website.people.create.createPerson("Jerry", "Faketest", "43 Money Road", "12 345 678 901");
		Long id = website.people.read.readId();
		website.people.read.navBack();
		website.people.navUpdate();
		website.people.update.searchId(id);
		website.people.update.updatePerson("Jerry", "Faketest", "43 Penny Road", "12 345 678 901");
		String result =  website.people.read.readAddress();
		assertEquals(result, "43 Penny Road");
		website.people.read.deletePerson();
		if (result.equals("43 Penny Road")) {
			test.log(LogStatus.PASS, "Successfully updated the person.");
		} else {
			test.log(LogStatus.FAIL, "Failed to update the person.");
		}
	}
	
	// --- VEHICLES ---
	
	@Test
	public void testVehicleRead() {
		test = extent.startTest("Vehicle read");
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navVehicles();
		website.vehicles.navCreate();
		website.vehicles.create.createVehicle("DD11 DDD", "Fiat", "500");
		Long test_id = website.vehicles.read.readId();
		Long result = website.vehicles.read.search(test_id);
		assertEquals(result, test_id);
		if (result == test_id) {
			test.log(LogStatus.PASS, "Successfully read the vehicle.");
		} else {
			test.log(LogStatus.FAIL, "Failed to read the vehicle.");
		}
	}
	
	@Test
	public void testVehicleReadAll() {
		test = extent.startTest("Vehicle read all");
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navVehicles();
		website.vehicles.navReadAll();
		boolean result = website.vehicles.readAll.getItemsExist();
		assertTrue(result);
		if (result) {
			test.log(LogStatus.PASS, "Successfully read all vehicles.");
		} else {
			test.log(LogStatus.FAIL, "Failed to read all vehicles.");
		}
	}
	
	@Test
	public void testVehicleCreate() {
		test = extent.startTest("Vehicle create");
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navVehicles();
		website.vehicles.navCreate();
		website.vehicles.create.createVehicle("GG94 GGG", "Mazda", "RX8");
		String result =  website.vehicles.read.readMakeModel();
		assertEquals(result, "Mazda RX8");
		website.vehicles.read.deleteVehicle();
		if (result.equals("Mazda RX8")) {
			test.log(LogStatus.PASS, "Successfully created the vehicle.");
		} else {
			test.log(LogStatus.FAIL, "Failed to create the vehicle.");
		}
	}
	
	@Test
	public void testVehicleDelete() {
		test = extent.startTest("Vehicle delete");
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navVehicles();
		website.vehicles.navCreate();
		website.vehicles.create.createVehicle("AA11 FF", "Brand", "Fakecar");
		boolean result = website.vehicles.read.deleteVehicle();
		assertTrue(result);
		if (result) {
			test.log(LogStatus.PASS, "Successfully deleted the vehicle.");
		} else {
			test.log(LogStatus.FAIL, "Failed to delete the vehicle.");
		}
	}
	
	@Test
	public void testVehicleUpdate() {
		test = extent.startTest("Vehicle update");
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navVehicles();
		website.vehicles.navCreate();
		website.vehicles.create.createVehicle("AA11 FF", "Brand", "Fakecar");
		Long id = website.people.read.readId();
		website.vehicles.read.navBack();
		website.vehicles.navUpdate();
		website.vehicles.update.searchId(id);
		website.vehicles.update.updateVehicle("AA11 FF", "Brand", "Faketestcar");
		String result = website.vehicles.read.readMakeModel();
		assertEquals(result, "Brand Faketestcar");
		website.vehicles.read.deleteVehicle();
		if (result.equals("Brand Faketestcar")) {
			test.log(LogStatus.PASS, "Successfully updated the vehicle.");
		} else {
			test.log(LogStatus.FAIL, "Failed to update the vehicle.");
		}
	}
	
	// TEST KEEPER FUNCTIONS
	
	@Test
	public void testAddKeeper() {
		test = extent.startTest("Keeper add");
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navVehicles();
		website.vehicles.navCreate();
		website.vehicles.create.createVehicle("GG94 GGG", "Mazda", "RX8");
		website.vehicles.read.setKeeper();
		boolean result = website.vehicles.read.checkKeeper();
		assertTrue(result);
		website.vehicles.read.deleteVehicle();
		if (result) {
			test.log(LogStatus.PASS, "Successfully added a keeper to the vehicle.");
		} else {
			test.log(LogStatus.FAIL, "Failed to add a keeper to the vehicle.");
		}
	}
	
	@Test
	public void testRemoveKeeper() {
		test = extent.startTest("Keeper remove");
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navVehicles();
		website.vehicles.navCreate();
		website.vehicles.create.createVehicle("GG94 GGG", "Mazda", "RX8");
		website.vehicles.read.setKeeper();
		website.vehicles.read.removeKeeper();
		boolean result = website.vehicles.read.checkNoKeeper();
		assertTrue(result);
		website.vehicles.read.deleteVehicle();
		if (result) {
			test.log(LogStatus.PASS, "Successfully deleted a keeper from the vehicle.");
		} else {
			test.log(LogStatus.FAIL, "Failed delete a keeper from the vehicle.");
		}
	}
}
