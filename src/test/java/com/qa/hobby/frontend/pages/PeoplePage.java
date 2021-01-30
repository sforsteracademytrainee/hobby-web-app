package com.qa.hobby.frontend.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PeoplePage {
	
	@FindBy(xpath = "/html/body/div/div/div/a[1]")
	private WebElement readAllLink;
	
	@FindBy(xpath = "/html/body/div/div/div/a[2]")
	private WebElement readLink;
	
	@FindBy(xpath = "/html/body/div/div/div/a[3]")
	private WebElement createLink;
	
	@FindBy(xpath = "/html/body/div/div/div/a[4]")
	private WebElement updateLink;
	
	public PeopleReadAllPage readAll;
	public PeopleReadPage read;
	public PeopleCreatePage create;
	public PeopleUpdatePage update;
	
	public PeoplePage(WebDriver driver) {
		readAll = PageFactory.initElements(driver, PeopleReadAllPage.class);
		read = PageFactory.initElements(driver, PeopleReadPage.class);
		create = PageFactory.initElements(driver, PeopleCreatePage.class);
		update = PageFactory.initElements(driver, PeopleUpdatePage.class);
	}
	
	public void navReadAll() {
		readAllLink.click();
	}
	
	public void navRead() {
		readLink.click();
	}
	
	public void navCreate() {
		createLink.click();
	}
	
	public void navUpdate() {
		updateLink.click();
	}
}
