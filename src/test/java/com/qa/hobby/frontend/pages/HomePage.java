package com.qa.hobby.frontend.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	@FindBy(xpath = "/html/body/nav/a")
	private WebElement logoLink;
	
	@FindBy(xpath = "/html/body/div/div[2]/div[2]/a")
	private WebElement peopleButton;
	
	@FindBy(xpath = "/html/body/div/div[2]/div[3]/a")
	private WebElement vehiclesButton;
	
	public PeoplePage people;
	public VehiclesPage vehicles;
	
	public HomePage(WebDriver driver) {
		people = PageFactory.initElements(driver, PeoplePage.class);
		vehicles = PageFactory.initElements(driver, VehiclesPage.class);
	}
	
	public void navHome() {
		logoLink.click();
	}
	
	public void navPeople() {
		peopleButton.click();
	}
	
	public void navVehicles() {
		vehiclesButton.click();
	}
}
