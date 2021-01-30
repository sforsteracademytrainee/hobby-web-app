package com.qa.hobby.frontend;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import com.qa.hobby.frontend.pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;


public class FrontEndIntegrationtTest {
	
	private static WebDriver driver;
	
	
	@BeforeAll
	public static void init() {
		// when extent reporting get extent here
		
		System.setProperty("webdriver.chrome.driver",
				"src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1366, 768));
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
	}
	
	@AfterAll
	public static void cleanUp() {
		driver.quit();
		System.out.println("The Selenium driver has been cleaned up.");
	}
	
	// --- PEOPLE ---
	
	@Test
	public void testPersonRead() { // working
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navPeople();
		website.people.navRead();
		assertEquals(website.people.read.search(1L), 1L);
	}
	
	@Test
	public void testPersonReadAll() { // working
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navPeople();
		website.people.navReadAll();
		assertTrue(website.people.readAll.getItemsExist());
	}
	
	@Test
	public void testPersonCreate() {
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navPeople();
		website.people.navCreate();
		website.people.create.createPerson("Jerry", "Faketest", "43 Money Road", "12 345 678 901");
		assertEquals(website.people.read.readAddress(), "43 Money Road");
		website.people.read.deletePerson();
	}
	
	@Test
	public void testPersonDelete() { // relies on the fact that read and create works
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navPeople();
		website.people.navCreate();
		website.people.create.createPerson("Jerry", "Faketest", "43 Money Road", "12 345 678 901");
		assertTrue(website.people.read.deletePerson());
	}
	
	@Test
	public void testPersonUpdate() { // relies on the fact that read and create works
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
		assertEquals(website.people.read.readAddress(), "43 Penny Road");
		website.people.read.deletePerson();
	}
	
	// --- VEHICLES ---
	
	@Test
	public void testVehicleRead() {
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navVehicles();
		website.vehicles.navRead();
		assertEquals(website.vehicles.read.search(1L), 1L);
	}
	
	@Test
	public void testVehicleReadAll() {
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navVehicles();
		website.vehicles.navReadAll();
		assertTrue(website.vehicles.readAll.getItemsExist());
	}
	
	@Test
	public void testVehicleCreate() {
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navVehicles();
		website.vehicles.navCreate();
		website.vehicles.create.createVehicle("GG94 GGG", "Mazda", "RX8");
		assertEquals(website.vehicles.read.readMakeModel(), "Mazda RX8");
		website.vehicles.read.deleteVehicle();
	}
	
	@Test
	public void testVehicleDelete() {
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navVehicles();
		website.vehicles.navCreate();
		website.vehicles.create.createVehicle("AA11 FF", "Brand", "Fakecar");
		assertTrue(website.vehicles.read.deleteVehicle());
	}
	
	@Test
	public void testVehicleUpdate() {
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
		assertEquals(website.vehicles.read.readMakeModel(), "Brand Faketestcar");
		website.vehicles.read.deleteVehicle();
	}
	
	// TEST KEEPER FUNCTIONS
	
	@Test
	public void testAddKeeper() {
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navVehicles();
		website.vehicles.navCreate();
		website.vehicles.create.createVehicle("GG94 GGG", "Mazda", "RX8");
		website.vehicles.read.setKeeper();
		assertTrue(website.vehicles.read.checkKeeper());
		website.vehicles.read.deleteVehicle();
	}
	
	@Test
	public void testRemoveKeeper() {
		driver.get("http://localhost:8080");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navVehicles();
		website.vehicles.navCreate();
		website.vehicles.create.createVehicle("GG94 GGG", "Mazda", "RX8");
		website.vehicles.read.setKeeper();
		website.vehicles.read.removeKeeper();
		assertTrue(website.vehicles.read.checkNoKeeper());
		website.vehicles.read.deleteVehicle();
	}
}
