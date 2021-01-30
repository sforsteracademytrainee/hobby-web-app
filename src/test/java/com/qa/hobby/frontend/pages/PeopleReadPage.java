package com.qa.hobby.frontend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PeopleReadPage extends BackButtonPage {
	
	@FindBy(xpath = "//*[@id=\"personId\"]")
	private WebElement idInput;
	
	@FindBy(xpath = "/html/body/div/div[2]/div/div/button")
	private WebElement searchButton;
	
	private WebDriver driver;
	
	public PeopleReadPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public Long search(Long id) {
		idInput.sendKeys(id.toString());
		searchButton.click();
		return this.readId();
	}
	
	public Long readId() {
		WebElement idDisplay = driver.findElement(By.xpath("//*[@id=\"results\"]/ul/li[1]"));
		String[] split = idDisplay.getText().split(" ");
		return Long.parseLong(split[split.length-1]);
	}
	
	public String readAddress() {
		WebElement addressLine = driver.findElement(By.xpath("//*[@id=\"results\"]/ul/li[3]"));
		return addressLine.getText();
	}
	
	public boolean deletePerson() {
		Long id = this.readId();
		WebElement deleteButton = driver.findElement(By.xpath("//*[@id=\"results\"]/button"));
		deleteButton.click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOf(deleteButton));
		WebElement deleteMessage = driver.findElement(By.xpath("//*[@id=\"results\"]"));
		return (deleteMessage.getText().equals("User " + id + " has succesfully been deleted."));
	}
}
