package com.qa.hobby.frontend.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PeopleCreatePage extends BackButtonPage {

	@FindBy(xpath = "//*[@id=\"personFirstName\"]")
	private WebElement firstName;
	
	@FindBy(xpath = "//*[@id=\"personSurname\"]")
	private WebElement surname;
	
	@FindBy(xpath = "//*[@id=\"personAddress\"]")
	private WebElement address;
	
	@FindBy(xpath = "//*[@id=\"personPhone\"]")
	private WebElement phone;
	
	@FindBy(xpath = "/html/body/div/form/div[3]/div/div/div/div/button")
	private WebElement submit;
	
	public void createPerson(String first, String sur, String add, String pho) {
		firstName.sendKeys(first);
		surname.sendKeys(sur);
		address.sendKeys(add);
		phone.sendKeys(pho);
		submit.click();
	}

}