package com.qa.hobby.frontend.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VehiclesPage {
	
	@FindBy(xpath = "/html/body/div/div/div/a[1]")
	private WebElement readAllLink;
	
	@FindBy(xpath = "/html/body/div/div/div/a[2]")
	private WebElement readLink;
	
	@FindBy(xpath = "/html/body/div/div/div/a[3]")
	private WebElement createLink;
	
	@FindBy(xpath = "/html/body/div/div/div/a[4]")
	private WebElement updateLink;
	
	public VehiclesReadAllPage readAll;
	public VehiclesReadPage read;
	public VehiclesCreatePage create;
	public VehiclesUpdatePage update;
	
	public VehiclesPage(WebDriver driver) {
		readAll = PageFactory.initElements(driver, VehiclesReadAllPage.class);
		read = PageFactory.initElements(driver, VehiclesReadPage.class);
		create = PageFactory.initElements(driver, VehiclesCreatePage.class);
		update = PageFactory.initElements(driver, VehiclesUpdatePage.class);
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
