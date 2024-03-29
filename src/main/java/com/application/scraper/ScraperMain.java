package com.application.scraper;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.application.App;
import com.application.service.CSVFileManager;

public class ScraperMain {

	public static void main(String[] args) {
		String baseDist = String.valueOf(App.class.getResource("data")).substring(6);
		
		WebDriver driver1 = new ChromeDriver();
		WebScraper web1 = new TwitterScraper(driver1);
		scraping(web1, "https://nitter.net/search?f=tweets&q=%23nft&since=&until=&near=", baseDist + "/tweet/tweet-23-12-2023.csv");
		
//		WebDriver driver2 = new ChromeDriver();
//		WebScraper web2 = new OpenseaScraper(driver2);
//		scraping(web2, "https://opensea.io/rankings/trending?sortBy=seven_day_volume", baseDist + "/opensea/opensea-15-12-2023.csv");
//		
//		WebDriver driver3 = new ChromeDriver();
//		WebScraper web3 = new BinanceScraper(driver3);
//		scraping(web3, "https://www.binance.com/vi/nft/ranking?tab=trend", baseDist + "/binance/binance-15-12-2023.csv");
		
//		WebDriver driver4 = new ChromeDriver();
//		WebScraper web4 = new RaribleScraper(driver4);
//		scraping(web4, "https://rarible.com/explore/all/collections?period=WEEK", baseDist + "/rarible/rarible-15-12-2023.csv");
//		https://rarible.com/explore/all/collections?period=WEEK
// 		https://rarible.com/explore/all/collections
		
	}
	
	public static void scraping(WebScraper webScraper, String baseUrlResource, String fileName) {
		List<String[]> data = webScraper.handleScrapingData(baseUrlResource);
		
		CSVFileManager filer = new CSVFileManager(fileName);
		filer.writeAllData(data);
	}
	
}
