package com.application.scraper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TwitterScraper extends WebScraper {

	public TwitterScraper(WebDriver driver) {
		super(driver);
	}

	public List<String[]> handleScrapingData(String baseUrlResource) {
		int countTweet = 0;
		List<String[]> data = new ArrayList<>();
//		CSVFileManager filer = new CSVFileManager("E:/WorkSpace/WebScraper/src/main/resources/tweets/tweet-18-12-2023.csv");
//		String[] header = { "id", "urlTweet", "contentTweet", "tweetDate", "commentTweet", "reTweet", "quoteTweet",
//				"heartTweet" };
//
//		filer.writeRow(header);

		try {
			this.driver.get(baseUrlResource);

			// Cuộn trang và lấy dữ liệu
			while (true) {

				try {
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.cssSelector(".timeline-item:not(.show-more)")));

					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

					// Lấy chiều cao ban đầu của trình duyệt
					long initialHeight = (long) jsExecutor.executeScript("return window.innerHeight");

					List<WebElement> listTimeLineItems = this.getListElementByCssSelector(this.driver,
							".timeline-item:not(.show-more)");

					for (WebElement item : listTimeLineItems) {
						String urlTweet = this.getElementByTagName(item, "a").getAttribute("href");
						String contentTweet = this.getElementByClass(item, "tweet-content").getText();
						String tweetDate = this.getElementByTagName(this.getElementByClass(item, "tweet-date"), "a")
								.getAttribute("title");

						// get list element tweet stat
						List<WebElement> listTweetStatElement = this.getListElementByClass(item, "tweet-stat");

						String commentTweet = this.getElementByClass(listTweetStatElement.get(0), "icon-container")
								.getText();
						String reTweet = this.getElementByClass(listTweetStatElement.get(1), "icon-container")
								.getText();
						String quoteTweet = this.getElementByClass(listTweetStatElement.get(2), "icon-container")
								.getText();
						String heartTweet = this.getElementByClass(listTweetStatElement.get(3), "icon-container")
								.getText();

						String[] payload = { String.valueOf(countTweet), urlTweet, contentTweet, tweetDate,
								commentTweet, reTweet, quoteTweet, heartTweet };
						
//						filer.writeRow(payload);

						data.add(payload);

						countTweet++;
					}

					if (countTweet >= 100)
						break;

					// Cuộn trang bằng JavaScript
					jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");

					// Dừng lại trong một khoảng thời gian ngắn để trang tải dữ liệu mới
					Thread.sleep(4000);

					// Lấy chiều cao hiện tại của trình duyệt
					long currentHeight = (long) jsExecutor.executeScript("return window.innerHeight");

					// Kiểm tra xem trang có còn dữ liệu để cuộn hay không. Nếu không còn thì load
					// more
					if (currentHeight == initialHeight) {
						WebElement loadmoreBtn = this.getElementByCssSelector(driver, ".show-more:not(.timeline-item)");
						loadmoreBtn.click();
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
			driver.quit();
		}

		return data;
	}
}
