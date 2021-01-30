package com.qa.hobby.frontend.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VehiclesCreatePage {
	
	@FindBy(xpath = "//*[@id=\"reg\"]")
	private WebElement reg;
	
	@FindBy(xpath = "//*[@id=\"make\"]")
	private WebElement make;
	
	@FindBy(xpath = "//*[@id=\"model\"]")
	private WebElement model;
	
	@FindBy(xpath = "/html/body/div/form/div[3]/div/div/div/div/button")
	private WebElement submit;
	
	public void createVehicle(String reg, String make, String model) {
		this.reg.sendKeys(reg);
		this.make.sendKeys(make);
		this.model.sendKeys(model);
		submit.click();
	}
	
}