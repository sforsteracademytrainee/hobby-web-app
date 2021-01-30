package com.qa.hobby.frontend.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PeopleReadAllPage extends BackButtonPage {
	
	private WebDriver driver;
	
	public PeopleReadAllPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean getItemsExist() {
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"results\"]/table/tbody/tr"));
		new WebDriverWait(driver, 1).until(ExpectedConditions.visibilityOf(elements.get(0)));
		return !elements.isEmpty();
	}
}
