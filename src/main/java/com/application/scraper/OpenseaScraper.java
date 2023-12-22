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

import com.application.service.CSVFileManager;


public class OpenseaScraper extends WebScraper {

	public OpenseaScraper(WebDriver driver) {
		super(driver);
	}

	public List<String[]> handleScrapingData(String baseUrlResource) {
		
		List<String[]> data = new ArrayList<>();
		int count = 0;
		
		CSVFileManager filer = new CSVFileManager("E:/WorkSpace/WebScraper/src/main/resources/openseas/opensea-12-12-2023.csv");
		
		String[] header =  { "idNFT", "urlNFT", "imageNFT", "nameNFT", "salesNFT", "volumnNFT", "changeNFT",
				"floorPriceNFT" };
		
		filer.writeRow(header);

		try {
			this.driver.get(baseUrlResource);

			while (true) {

				try {
					WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(2));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fWxQZN")));

					JavascriptExecutor jsExecutor = (JavascriptExecutor) this.driver;

					// Lấy chiều cao ban đầu của trình duyệt
					long initialHeight = (long) jsExecutor.executeScript("return window.innerHeight");

					List<WebElement> listNTF = this.getListElementByCssSelector(this.driver, ".fWxQZN");

					for (WebElement nft : listNTF) {
						String idNFT = this.getElementByClass(nft, "hHChfp").getText();
						String urlNFT = this.getElementByTagName(nft, "a").getAttribute("href");
						String imageNFT = this.getElementByTagName(this.getElementByClass(nft, "dPkIoo"), "img")
								.getAttribute("src");
						String nameNFT = this.getElementByClass(this.getElementByClass(nft, "cqKvtL"), "bguyED")
								.getText();
						String salesNFT = this.getElementByClass(this.getElementByClass(nft, "cuwMwy"), "axQXd")
								.getText();

						List<WebElement> vcfList = this.getListElementByClass(nft, "hcyTCW");

						String volumnNFT = this.getElementByClass(vcfList.get(0), "axQXd").getText();
						String changeNFT = this.getElementByClass(vcfList.get(1), "axQXd").getText();
						String floorPriceNFT = this.getElementByClass(vcfList.get(2), "axQXd").getText();

						String[] payload = { idNFT, urlNFT, imageNFT, nameNFT, salesNFT, volumnNFT, changeNFT,
								floorPriceNFT };
						
						filer.writeRow(payload);
						
						data.add(payload);
						
						count++;

//						System.out.println(idNFT + " - " + imageNFT + " - " + nameNFT + " - " + urlNFT + " - "
//								+ volumnNFT + " - " + changeNFT + " - " + floorPriceNFT + " - " + salesNFT);
					}
					
					if (count >= 200) break;

					// Cuộn trang bằng JavaScript
					jsExecutor.executeScript("window.scrollBy(0, 700)");

					// Dừng lại trong một khoảng thời gian ngắn để trang tải dữ liệu mới
					Thread.sleep(4000);

					// Lấy chiều cao hiện tại của trình duyệt
					long currentHeight = (long) jsExecutor.executeScript("return window.innerHeight");

					// Kiểm tra xem trang có còn dữ liệu để cuộn hay không. Nếu không còn thì
					if (currentHeight >= initialHeight) {
//						 break;
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
