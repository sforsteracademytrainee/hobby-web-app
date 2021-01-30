package com.qa.hobby.frontend.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VehiclesReadPage extends BackButtonPage {
	
	@FindBy(xpath = "//*[@id=\"vehicleId\"]")
	private WebElement idInput;
	
	@FindBy(xpath = "/html/body/div/div[2]/div/button")
	private WebElement searchButton;
	
	private WebDriver driver;
	
	public VehiclesReadPage(WebDriver driver) {
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
	
	public String readMakeModel() {
		WebElement modelLine = driver.findElement(By.xpath("//*[@id=\"results\"]/ul/li[3]"));
		return modelLine.getText();
	}
	
	public boolean deleteVehicle() {
		Long id = this.readId();
		WebElement deleteButton = driver.findElement(By.xpath("//*[@id=\"results\"]/button"));
		deleteButton.click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOf(deleteButton));
		WebElement deleteMessage = driver.findElement(By.xpath("//*[@id=\"results\"]"));
		return (deleteMessage.getText().equals("Vehicle " + id + " has succesfully been deleted."));
	}
	
	public void setKeeper() {
		WebElement keeperInput = driver.findElement(By.xpath("//*[@id=\"keeperId\"]"));
		WebElement keeperButton = driver.findElement(By.xpath("//*[@id=\"results\"]/div/button"));
		
		keeperInput.sendKeys("1");
		keeperButton.click();
	}
	
	public void removeKeeper() {
		WebElement keeperRemoveButton = driver.findElement(By.xpath("//*[@id=\"keeperButton\"]"));
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(keeperRemoveButton));
		keeperRemoveButton.click();
	}
	
	public boolean checkKeeper() {
		WebElement keeperInfo = driver.findElement(By.xpath("//*[@id=\"results\"]/ul/li[4]"));
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(keeperInfo));
		return !keeperInfo.getText().isEmpty();
	}
	
	public boolean checkNoKeeper() {
		WebElement keeperInfo = driver.findElement(By.xpath("//*[@id=\"results\"]/ul/li[4]"));
		new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOf(keeperInfo));
		List<WebElement> keeperTest = driver.findElements(By.xpath("//*[@id=\"results\"]/ul/li[4]"));
		return keeperTest.isEmpty();
	}
}
