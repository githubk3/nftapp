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

//import main.java.util.CSVFileManager;

public class BinanceScraper extends WebScraper {

	public BinanceScraper(WebDriver driver) {
		super(driver);
	}

	@Override
	public List<String[]> handleScrapingData(String baseUrlResource) {
		// css-kyd4a2
		// a > getText()
		// css-h4kozk > img
		// css-1wr4jig > css-1iqk42z :: name
		// css-79h90x > css-9bhndi :: volume
		// css-xs8nuv > css-1g8p7ho :: change
		// css-1j59jpm > css-9bhndi :: floor price
		// css-15n6x8q :: sale

		List<String[]> data = new ArrayList<>();
		int count = 0;

//		CSVFileManager filer = new CSVFileManager(
//				"E:/WorkSpace/WebScraper/src/main/resources/binance/binance-12-12-2023.csv");
//
//		String[] header = { "idNFT", "urlNFT", "imageNFT", "nameNFT", "salesNFT", "volumnNFT", "changeNFT",
//				"floorPriceNFT" };

//		filer.writeRow(header);

		try {
			this.driver.get(baseUrlResource);

			while (true) {

				try {
					WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".css-m3366f")));

					JavascriptExecutor jsExecutor = (JavascriptExecutor) this.driver;

					// Lấy chiều cao ban đầu của trình duyệt
					long initialHeight = (long) jsExecutor.executeScript("return window.innerHeight");

					List<WebElement> listNTF = this.getListElementByCssSelector(this.getElementByClass(this.driver, "css-m3366f"), ".css-vurnku");

					for (WebElement nft : listNTF) {
						String idNFT = this.getElementByClass(nft, "css-ffknne") != null ? this.getElementByClass(nft, "css-ffknne").getText():"";
						count++;
						String urlNFT = this.getElementByTagName(nft, "a").getAttribute("href");
						String imageNFT = this.getElementByTagName(this.getElementByClass(nft, "css-h4kozk"), "img") != null ? this.getElementByTagName(this.getElementByClass(nft, "css-h4kozk"), "img").getAttribute("src") : "";
						String nameNFT = this.getElementByClass(nft, "css-1iqk42z").getText();
						String salesNFT = this.getElementByClass(nft, "css-15n6x8q").getText();

						String volumnNFT = this.getElementByClass(this.getElementByClass(nft,"css-79h90x"), "css-9bhndi").getText();
						String changeNFT = this.getElementByClass(nft, "css-1g8p7ho").getText();
						
						
						String floorPriceNFT = this.getElementByClass(nft, "css-1j59jpm").getText();
						// this.getElementByClass(this.getElementByClass(nft,"css-t03i0x"), "css-9bhndi ").getText();

						String[] payload = { idNFT, urlNFT, imageNFT, nameNFT, salesNFT, volumnNFT,
								changeNFT, floorPriceNFT };
						
//						filer.writeRow(payload);
						
						data.add(payload);

						System.out.println(count + " - " + imageNFT + " - " + nameNFT + " - " + urlNFT + " - "
								+ volumnNFT + " - " + changeNFT + " - " + floorPriceNFT + " - " + salesNFT);
					}
					
					if (count >= 100) break;

					// Cuộn trang bằng JavaScript
					jsExecutor.executeScript("window.scrollBy(0, 680)");

					// Dừng lại trong một khoảng thời gian ngắn để trang tải dữ liệu mới
					Thread.sleep(4000);

					// Lấy chiều cao hiện tại của trình duyệt
					long currentHeight = (long) jsExecutor.executeScript("return window.innerHeight");

					// Kiểm tra xem trang có còn dữ liệu để cuộn hay không. Nếu không còn thì
					if (currentHeight >= initialHeight) {
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
