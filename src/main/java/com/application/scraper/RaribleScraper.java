package com.application.scraper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RaribleScraper extends WebScraper {

	// jCYSWn
	// brbJBC: list
	// 0 .getText: id
	// 1 > a. href
	// 1. > img
	// 1. > cBLZmI : name
	// 2. getText: floorPrice
	// 4. getText: volumn
	// 5. getText: change
	// 7. getText: sales (thực ra là owners)

	public RaribleScraper(WebDriver driver) {
		super(driver);
	}

	@Override
	public List<String[]> handleScrapingData(String baseUrlResource) {

		List<String[]> data = new ArrayList<>();
		int count = 0;

		String[] header = { "idNFT", "urlNFT", "imageNFT", "nameNFT", "salesNFT", "volumnNFT", "changeNFT",
				"floorPriceNFT" };

		data.add(header);
		
		try {
			this.driver.get(baseUrlResource);

			while (true) {

				try {
					WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(2));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".DKpiL")));

					JavascriptExecutor jsExecutor = (JavascriptExecutor) this.driver;

					// Lấy chiều cao ban đầu của trình duyệt
					long initialHeight = (long) jsExecutor.executeScript("return document.body.scrollHeight");

					List<WebElement> listNTF = this.getListElementByCssSelector(this.driver, ".DKpiL");

					for (WebElement nft : listNTF) {
						List<WebElement> cell = this.getListElementByCssSelector(nft, ".brbJBC");

						String idNFT = cell.get(0).getText();
						String urlNFT = this.getElementByTagName(cell.get(1), "a").getAttribute("href");
						String imageNFT = this.getElementByTagName(cell.get(1), "img").getAttribute("src");
						String nameNFT = this.getElementByClass(cell.get(1), "cBLZmI").getText();
						String salesNFT = cell.get(7).getText();
						String volumnNFT = cell.get(4).getText();
						String changeNFT = cell.get(5).getText();
						String floorPriceNFT = cell.get(2).getText();

						String[] payload = { idNFT, urlNFT, imageNFT, nameNFT, salesNFT, volumnNFT, changeNFT,
								floorPriceNFT };

						data.add(payload);

						count++;

						System.out.println(idNFT + " - " + imageNFT + " - " + nameNFT + " - " + urlNFT + " - "
								+ volumnNFT + " - " + changeNFT + " - " + floorPriceNFT + " - " + salesNFT);
					}

					// Cuộn trang bằng JavaScript
					jsExecutor.executeScript("window.scrollBy(0, 1000)");

					// Dừng lại trong một khoảng thời gian ngắn để trang tải dữ liệu mới
					Thread.sleep(4000);

					// Lấy chiều cao hiện tại của trình duyệt
					long currentHeight = (long) jsExecutor.executeScript("return document.body.scrollHeight");

					// Kiểm tra xem trang có còn dữ liệu để cuộn hay không. Nếu không còn thì
					if (currentHeight == initialHeight || count >= 200) {
						 break;
					} else {
						initialHeight = currentHeight;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.driver.quit();
		}

		return data;
	}

}
