package com.qa.hobby.frontend.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PeopleUpdatePage extends BackButtonPage {
	
	@FindBy(xpath = "//*[@id=\"personId\"]")
	private WebElement idBox;
	
	@FindBy(xpath = "/html/body/div/div[2]/div/div/button")
	private WebElement goButton;
	
	@FindBy(xpath = "//*[@id=\"personFirstName\"]")
	private WebElement firstName;
	
	@FindBy(xpath = "//*[@id=\"personSurname\"]")
	private WebElement surname;
	
	@FindBy(xpath = "//*[@id=\"personAddress\"]")
	private WebElement address;
	
	@FindBy(xpath = "//*[@id=\"personPhone\"]")
	private WebElement phone;
	
	@FindBy(xpath = "//*[@id=\"updateButton\"]")
	private WebElement submit;
	
	public void searchId(Long id) {
		idBox.sendKeys(id.toString());
		goButton.click();
	}
	
	public void updatePerson(String first, String sur, String add, String pho) {
		firstName.clear();
		surname.clear();
		address.clear();
		phone.clear();
		firstName.sendKeys(first);
		surname.sendKeys(sur);
		address.sendKeys(add);
		phone.sendKeys(pho);
		submit.click();
	}
	
}
