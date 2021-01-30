package com.qa.hobby.frontend.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VehiclesUpdatePage {
	
	@FindBy(xpath = "//*[@id=\"vehicleId\"]")
	private WebElement idBox;
	
	@FindBy(xpath = "/html/body/div/div[2]/div/div/button")
	private WebElement goButton;
	
	@FindBy(xpath = "//*[@id=\"reg\"]")
	private WebElement reg;
	
	@FindBy(xpath = "//*[@id=\"make\"]")
	private WebElement make;
	
	@FindBy(xpath = "//*[@id=\"model\"]")
	private WebElement model;
	
	@FindBy(xpath = "//*[@id=\"updateButton\"]")
	private WebElement submit;
	
	public void searchId(Long id) {
		idBox.sendKeys(id.toString());
		goButton.click();
	}
	
	public void updateVehicle(String reg, String make, String model) {
		this.reg.clear();
		this.make.clear();
		this.model.clear();
		this.reg.sendKeys(reg);
		this.make.sendKeys(make);
		this.model.sendKeys(model);
		submit.click();
	}
}
