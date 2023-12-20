package com.application.scraper;

import java.util.List;

import com.application.App;
import com.application.service.CSVFileManager;

public class ScraperMain {

	public static void main(String[] args) {
		CSVFileManager filer = new CSVFileManager(String.valueOf(App.class.getResource("data/tweets/tweet-17-12-2023.csv")).substring(6));
		
		List<String[]> data = filer.readData();
		System.out.print(data);
		
		for (String[] item: data) {
			 String id = item[0];
             String urlTweet = item[1];
             String contentTweet = item[2];
             String tweetDate = item[3];
             String commentTweet = item[4];
             String reTweet = item[5];
             String quoteTweet = item[6];
             String heartTweet = item[7];
             
             System.out.println(id);
		}
	}

}
