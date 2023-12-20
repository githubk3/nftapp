package com.application.scraper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class WebScraper {
	protected WebDriver driver;

	public WebScraper(WebDriver driver) {
		this.driver = driver;
	}

	public abstract List<String[]> handleScrapingData(String baseUrlResource);

	public <T extends SearchContext> WebElement getElementByClass(T parentElement, String elementClass) {
		try {
			return parentElement.findElement(By.className(elementClass));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public <T extends SearchContext> List<WebElement> getListElementByClass(T parentElement, String elementClass) {
		try {
			return parentElement.findElements(By.className(elementClass));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public <T extends SearchContext> WebElement getElementByTagName(T parentElement, String tagName) {
		try {
			return parentElement.findElement(By.tagName(tagName));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public <T extends SearchContext> List<WebElement> getListElementByTagName(T parentElement, String tagName) {
		try {
			return parentElement.findElements(By.tagName(tagName));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public <T extends SearchContext> WebElement getElementByCssSelector(T parentElement, String cssSelector) {
		try {
			return parentElement.findElement(By.cssSelector(cssSelector));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public <T extends SearchContext> List<WebElement> getListElementByCssSelector(T parentElement, String cssSelector) {
		try {
			return parentElement.findElements(By.cssSelector(cssSelector));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

