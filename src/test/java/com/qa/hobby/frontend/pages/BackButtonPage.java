package com.qa.hobby.frontend.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BackButtonPage {
	
	@FindBy(xpath = "/html/body/div/div[1]/div/a")
	private WebElement backButton;
	
	public void navBack() {
		backButton.click();
	}
}
