package com.application.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import com.application.service.LoadDataManager;

public class Bucket implements IPostManager, INFTManager {
	private List<Post> listPost = new ArrayList<Post>();
	private List<NFT> listNFT = new ArrayList<NFT>();
	private final LoadDataManager loader = new LoadDataManager();

	public Bucket() {
		List<Post> listTweetData = loader.loadDataTweet("/tweet/tweet-23-12-2023.csv");
		this.addPost(listTweetData);

		List<NFT> listNFTData1 = loader.loadDataNFT("/opensea/opensea-22-12-2023.csv");
		this.addNFT(listNFTData1);

		List<NFT> listNFTData2 = loader.loadDataNFT("/binance/binance-22-12-2023.csv");
		this.addNFT(listNFTData2);

		List<NFT> listNFTData3 = loader.loadDataNFT("/rarible/rarible-22-12-2023.csv");
		this.addNFT(listNFTData3);
	}

	@Override
	public void addNFT(NFT nft) {
		listNFT.add(nft);
	}

	@Override
	public void addNFT(List<NFT> nft) {
		listNFT.addAll(nft);
	}

	@Override
	public ArrayList<NFT> getNFTsByGateway(String gateway) {

		return listNFT.stream().filter(nft -> nft.getGateway().equals(gateway))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public ArrayList<NFT> getNFTsByName(String name) {

		return listNFT.stream().filter(nft -> nft.getName().equals(name))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public double calculateTotalVolume(String gateway, String datetime) {
		List<NFT> listNFTByDate = new ArrayList<NFT>();
		
		List<NFT> listNFTData1 = loader.loadDataNFT("/" + gateway + "/" + gateway + "-" + datetime + ".csv");
		listNFTByDate.addAll(listNFTData1);
		
		String editDatetime = datetime.replaceAll("-", "/");

		Double totalVolume = (double) 0;
		for (NFT nft : listNFTByDate)
		{
			if (nft.getGateway().equals(gateway))
			{
				totalVolume += 
						nft.getNftIntervalList()
							.stream()
							.filter(nftInterval -> nftInterval.getDatetime().equals(editDatetime))
							.mapToDouble(NFTInterval::getVolume)
							.sum();
			}
		}
		
		return totalVolume;
	}

	@Override
	public void addPost(Post post) {
		listPost.add(post);
	}

	@Override
	public void addPost(List<Post> post) {
		listPost.addAll(post);
	}

	@Override
	public ArrayList<Post> getPostsByTag(String tag) {
		return listPost.stream().filter(post -> post.getTags().stream().anyMatch(str -> str.equals(tag)))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public ArrayList<Post> getPostsByKeyWord(String keyword) {
		return listPost.stream().filter(post -> post.getContent().contains(keyword))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public ArrayList<String> getTagHot(int day, int month, int year) {

		Map<String, Integer> tagCountMap = new HashMap<>();
		int maxTagCount = 0;

		String date = (day != -1) ? day + "/" + month + "/" + year : month + "/" + year;

		DateTimeFormatter formart = day != -1 ? DateTimeFormatter.ofPattern("d/M/yyyy")
				: DateTimeFormatter.ofPattern("M/yyyy");

		DateTimeFormatter formartOfPost = DateTimeFormatter.ofPattern("d/M/yyyy");

		for (Post post : listPost) {
			if (date.equals(formart.format(LocalDate.parse(post.getDatetime(), formartOfPost)))) {
				for (String tag : post.getTags()) {
					int tagCount = tagCountMap.getOrDefault(tag, 0) + 1;
					tagCountMap.put(tag, tagCount);
					maxTagCount = Integer.max(maxTagCount, tagCount);
				}
			}
		}
		;

		ArrayList<String> listTagHot = new ArrayList<String>();
		for (Post post : listPost) {
			for (String tag : post.getTags()) {
				if (tagCountMap.getOrDefault(tag, 0) == maxTagCount) {
					listTagHot.add(tag);
					tagCountMap.put(tag, 0);
				}
			}
		}

		return listTagHot;
	}

	@Override
	public ArrayList<String> getTagHot(int month, int year) {
		return getTagHot(-1, month, year);
	}

}
