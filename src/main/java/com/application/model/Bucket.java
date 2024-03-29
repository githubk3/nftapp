package com.application.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import com.application.service.LoadDataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
	public ObservableList<NFT> getNFTsByGateway(String gateway) {
		ArrayList<NFT> filterList = listNFT.stream().filter(nft -> nft.getGateway().equals(gateway))
				.collect(Collectors.toCollection(ArrayList::new));
		return FXCollections.observableArrayList(filterList);
	}

	@Override
	public ObservableList<NFT> getNFTsByName(String name) {

		ArrayList<NFT> filterList = listNFT.stream().filter(nft -> nft.getName().equals(name))
				.collect(Collectors.toCollection(ArrayList::new));
		return FXCollections.observableArrayList(filterList);
	}

	@Override
	public double calculateTotalVolume(String gateway, String datetime) {

		Double totalVolume = (double) 0;
		for (NFT nft : listNFT) {
			totalVolume += nft.getNftIntervalList().stream()
					.filter(nftInterval -> nftInterval.getDatetime().equals(datetime))
					.mapToDouble(NFTInterval::getVolume).sum();
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
	public ObservableList<Post> getPostsByTag(String tag) {
		ArrayList<Post> filterList = listPost.stream().filter(post -> post.getTags().stream().anyMatch(str -> str.equals(tag)))
				.collect(Collectors.toCollection(ArrayList::new));
		return FXCollections.observableArrayList(filterList);
	}

	@Override
	public ObservableList<Post> getPostsByKeyWord(String keyword) {
		ArrayList<Post> filterList = listPost.stream().filter(post -> post.getContent().contains(keyword))
				.collect(Collectors.toCollection(ArrayList::new));
		return FXCollections.observableArrayList(filterList);
	}

	@Override
	public ObservableList<String> getTagHot(int day, int month, int year) {

		Map<String, Integer> tagCountMap = new HashMap<>();
		int maxTagCount = 0;

		String date = (day != -1) ?
				day + "/" + month + "/" + year :
				month + "/" + year;

		DateTimeFormatter formart = day != -1 ?
				DateTimeFormatter.ofPattern("d/M/yyyy") :
				DateTimeFormatter.ofPattern("M/yyyy");

		DateTimeFormatter formartOfPost = DateTimeFormatter.ofPattern("d/M/yyyy");

		for (Post post : listPost)  {
			if (date.equals(formart.format(
					LocalDate.parse(post.getDatetime(), formartOfPost))))
			{
				for (String tag : post.getTags())
				{
					int tagCount = tagCountMap.getOrDefault(tag, 0) + 1;
					tagCountMap.put(tag, tagCount);
					maxTagCount = Integer.max(maxTagCount, tagCount);
				}
			}
		};

		ArrayList<String> listTagHot = new ArrayList<String>();

		//Chuyển Map thành List entry
		List<Map.Entry<String, Integer>> MapToList = new ArrayList<>(tagCountMap.entrySet());
		//Sort lại list vừa tạo
		Collections.sort(MapToList, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

		int count = 0;
		//Lấy ra 10 phần tử đầu tiên
		for (Map.Entry<String, Integer> entry : MapToList) {
			if (count >= 10) {
				break;
			}
			listTagHot.add(entry.getKey());
			count++;
		}


		return FXCollections.observableArrayList(listTagHot);
	}

	@Override
	public ObservableList<String> getTagHot(int month, int year) {
		return getTagHot(-1, month, year);
	}

}
