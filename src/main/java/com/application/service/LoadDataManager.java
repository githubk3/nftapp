package com.application.service;

import java.util.ArrayList;
import java.util.List;

import com.application.App;
import com.application.model.NFT;
import com.application.model.NFTInterval;
import com.application.model.Post;
import com.application.model.Tweet;

public class LoadDataManager {
	private final String baseDist = String.valueOf(App.class.getResource("data")).substring(6);

	public List<Post> loadDataTweet(String sourcePath) {
		try {
			CSVFileManager filer = new CSVFileManager(baseDist + sourcePath);

			List<String[]> data = filer.readAllData();
			List<Post> listTweet = new ArrayList<>();
			
			for (String[] item : data) {
				int id = Integer.valueOf(item[0]);
				String urlTweet = item[1];
				String contentTweet = item[2];
				String tweetDate = item[3];
				int commentTweet = Integer.valueOf(item[4]);
				int reTweet = Integer.valueOf(item[5]);
				int quoteTweet = Integer.valueOf(item[6]);
				int heartTweet = Integer.valueOf(item[7]);

				Tweet tweet = new Tweet(id, urlTweet, contentTweet, tweetDate, commentTweet, reTweet, quoteTweet,
						heartTweet);
				listTweet.add(tweet);
			}

			return listTweet;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<NFT> loadDataNFT(String sourcePath) {
		try {
			CSVFileManager filer = new CSVFileManager(baseDist + sourcePath);

			List<String[]> data = filer.readAllData();
			List<NFT> listNFT = new ArrayList<>();

			for (String[] item : data) {
				int id = Integer.valueOf(item[0]);
				String url = item[1];
				String image = item[2];
				String name = item[3];
				int sales = Integer.valueOf(item[4]);
				double volume = Double.valueOf(item[5]);
				double change = Double.valueOf(item[6]);
				double floorPrice = Double.valueOf(item[7]);
				String gateway = item[8];
				String datetime = item[9];

				NFT newNFT = new NFT(id, name, url, image, gateway);
				newNFT.addNftIntervalList(new NFTInterval(floorPrice, change, volume, sales, sales, datetime));

				listNFT.add(newNFT);
			}

			return listNFT;

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}
