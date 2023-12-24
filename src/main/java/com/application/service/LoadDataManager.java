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
				int idTweet = Integer.valueOf(item[0]);
				String urlTweet = item[1];
				String contentTweet = item[2];
				String imageTweet = item[3];
				String tweetDate = item[4];
				int commentTweet = item[5] != "" ? Integer.valueOf(item[5].replace(",", "")) : 0;
				int reTweet = item[6] != ""  ? Integer.valueOf(item[6].replace(",", "")) : 0;
				int quoteTweet = item[7] != "" ? Integer.valueOf(item[7].replace(",", "")) : 0;
				int heartTweet = item[8] != "" ? Integer.valueOf(item[8].replace(",", "")) : 0;

				Tweet tweet = new Tweet(idTweet, urlTweet, contentTweet, imageTweet, tweetDate, commentTweet, reTweet, quoteTweet,
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
				int sales = item[4].contains("K") ? Integer.valueOf(item[4].replaceAll("[,|%|ETH|>|<|K|.]", "").replaceAll("--", "0"))*100 : Integer.valueOf(item[4].replaceAll("[,|%|ETH|>|<]", "").replaceAll("--", "0"));
				double volume = Double.valueOf(item[5].replaceAll("[,|%|ETH|>|<|MAIC]", "").replaceAll("K", "000"));
				double change = Double.valueOf(item[6].replaceAll("[,|%|ETH|>|<]", "").replaceAll("—", "0"));
				double floorPrice = Double.valueOf(item[7].replaceAll("[,|%|ETH|PRIM|>|<|~|AC]", "").replaceAll("—", "0"));
				String gateway = item[8];
				String datetime = item[9];

				NFTInterval newNFTInterval = new NFTInterval(floorPrice, change, volume, sales, sales, datetime);
				NFT newNFT = new NFT(id, name, url, image, gateway, newNFTInterval);

				listNFT.add(newNFT);
			}
			return listNFT;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
