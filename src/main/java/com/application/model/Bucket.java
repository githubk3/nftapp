package com.application.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import com.application.service.LoadDataManager;

public class Bucket implements IPostManager, INFTManager {
	static private List<Post> listPost = new ArrayList<>();
	static private List<NFT> listNFT = new ArrayList<NFT>();
	private final LoadDataManager loader = new LoadDataManager();
	
	public Bucket() {
		List<Post> listTweetData = loader.loadDataTweet(""); // source dist
		this.addPost(listTweetData);
		
		List<NFT> listNFTData1 = loader.loadDataNFT(""); // source
		this.addNFT(listNFTData1);
	}
	
//	public static void main(String[] args) 
//	{
//		listPost = new ArrayList<Post>(Arrays.asList(
//				new Tweet(0, "https://ahehe/user1.com", "Mua đê, mua đê #nftdeptrai #nftsiu", "1/2/2023", 30, 10, 100,1000),
//				new Tweet(1, "https://ahehe/user2.com", "Mua nhanh #nftdeptrai #nftiu", "1/2/2023", 30, 10, 100,3200),
//				new Tweet(2, "https://ahehe/user3.com", "Đừng mua đm #nftdeptrai #nftsiu", "1/2/2023", 30, 10, 100,100),
//				new Tweet(3, "https://ahehe/user4.com", "Đào lửa tổng hợp P1 #nftKhongmua #nftthief #nftsiu", "12/5/2023", 0, 0, 1,342),
//				new Tweet(4, "https://ahehe/user4.com", "Đào lửa tổng hợp P2 #nftDungMua #nftgrand #nftauto", "14/5/2023", 0, 0, 1,342),
//				new Tweet(5, "https://ahehe/user4.com", "Đào lửa tổng hợp P3 #nftDungMua #nftKhongmua", "15/5/2023", 0, 0, 1,342),
//				new Tweet(6, "https://ahehe/user4.com", "Đào lửa tổng hợp P1 #nftKhongmua #ahehe #nftsiu", "12/5/2023", 0, 0, 1,342),
//				new Tweet(7, "https://ahehe/user4.com", "Đào lửa tổng hợp P2 #nftDungMua #meme #nftauto", "14/5/2023", 0, 0, 1,342),
//				new Tweet(8, "https://ahehe/user4.com", "Đào lửa tổng hợp P3 #nftDungMua #adole", "15/5/2023", 0, 0, 1,342),
//				new Tweet(9, "https://ahehe/user1.com", "Mua đê, mua đê #nftdeptrai #nftsiu", "1/5/2023", 30, 10, 100,1000),
//				new Tweet(10, "https://ahehe/user2.com", "Mua nhanh #nftdeptrai #nftiu", "1/5/2023", 30, 10, 100,3200),
//				new Tweet(11, "https://ahehe/user3.com", "Đừng mua đm #nftdeptrai #nftsiu", "1/5/2023", 30, 10, 100,100)
//		));
//		
//		listNFT.addAll(Arrays.asList(
//				new NFT(0,"deptrai","https://opensea/user1.com", "//url...", "OpenSea"),
//				new NFT(1,"xautrai","https://opensea/user1.com", "//url...", "OpenSea"),
//				new NFT(2,"Bambo","https://opensea/user3.com", "//url...", "Binary"),
//				new NFT(3,"Airline","https://opensea/user3.com", "//url...", "Binary")
//		));
//		
//		listNFT.get(0).addNftIntervalList(new NFTInterval(10, 20, 30, 40, 50, "1/2/2023"));
//		listNFT.get(0).addNftIntervalList(new NFTInterval(10, 20, 40, 40, 50, "2/2/2023"));
//		listNFT.get(0).addNftIntervalList(new NFTInterval(10, 20, 50, 40, 50, "3/2/2023"));
//		
//		listNFT.get(1).addNftIntervalList(new NFTInterval(10, 20, 15, 40, 50, "1/2/2023"));
//		listNFT.get(1).addNftIntervalList(new NFTInterval(10, 20, 40, 40, 50, "2/2/2023"));
//		listNFT.get(1).addNftIntervalList(new NFTInterval(10, 20, 50, 40, 50, "3/2/2023"));
//
//		listNFT.get(2).addNftIntervalList(new NFTInterval(10, 20, 15, 40, 50, "1/2/2023"));
//		listNFT.get(2).addNftIntervalList(new NFTInterval(10, 20, 40, 40, 50, "2/2/2023"));
//		listNFT.get(2).addNftIntervalList(new NFTInterval(10, 20, 50, 40, 50, "3/2/2023"));
//	
//		listNFT.get(3).addNftIntervalList(new NFTInterval(10, 20, 15, 40, 50, "1/2/2023"));
//		listNFT.get(3).addNftIntervalList(new NFTInterval(10, 20, 40, 40, 50, "2/2/2023"));
//		listNFT.get(3).addNftIntervalList(new NFTInterval(10, 20, 50, 40, 50, "3/2/2023"));
//		
//		//System.out.print(_calculateTotalVolume("Binary", "1/2/2023"));
//		
//		for (String tag : _getTagHot(5, 2023))
//		{
//			System.out.println(tag);
//		}
//	}

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
		
		 return listNFT.stream()
				 	.filter(nft -> nft.getGateway().equals(gateway))
				 	.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public ArrayList<NFT> getNFTsByName(String name) {
		
		return listNFT.stream()
			 		.filter(nft -> nft.getName().equals(name))
			 		.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public double calculateTotalVolume(String gateway, String datetime) {
		
		Double totalVolume = (double) 0;
		for (NFT nft : listNFT)
		{
			if (nft.getGateway().equals(gateway))
			{
				totalVolume += 
						nft.getNftIntervalList()
							.stream()
							.filter(nftInterval -> nftInterval.getDatetime().equals(datetime))
							.mapToDouble(NFTInterval::getVolume)
							.sum();
			}
		}
		
		return totalVolume;
	}
	
//	public static double _calculateTotalVolume(String gateway, String datetime) {
//		
//		Double totalVolume = (double) 0;
//		for (NFT nft : listNFT)
//		{
//			if (nft.getGateway().equals(gateway))
//			{
//				totalVolume += 
//						nft.getNftIntervalList()
//							.stream()
//							.filter(nftInterval -> nftInterval.getDatetime().equals(datetime))
//							.mapToDouble(NFTInterval::getVolume)
//							.sum();
//			}
//		}
//		
//		return totalVolume;
//	}

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
		return listPost.stream()
				.filter(post -> post.getTags()
										.stream()
										.anyMatch(str -> str.equals(tag)))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public ArrayList<Post> getPostsByKeyWord(String keyword) {
		return listPost.stream()
						.filter(post -> post.getContent().contains(keyword))
						.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public ArrayList<String> getTagHot(int day, int month, int year) {
		
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
		
		
		return listTagHot;
	}
	
//	public static ArrayList<String> _getTagHot(int day, int month, int year) {
//		
//		Map<String, Integer> tagCountMap = new HashMap<>();
//		int maxTagCount = 0;
//		
//		String date = (day != -1) ?  
//						day + "/" + month + "/" + year :
//						month + "/" + year;
//		
//		DateTimeFormatter formart = day != -1 ?
//				DateTimeFormatter.ofPattern("d/M/yyyy") :
//				DateTimeFormatter.ofPattern("M/yyyy");
//		
//		DateTimeFormatter formartOfPost = DateTimeFormatter.ofPattern("d/M/yyyy");
//		
//		for (Post post : listPost)  {						
//			if (date.equals(formart.format(
//					LocalDate.parse(post.getDatetime(), formartOfPost))))
//			{
//				for (String tag : post.getTags()) 
//				{
//					int tagCount = tagCountMap.getOrDefault(tag, 0) + 1;
//					tagCountMap.put(tag, tagCount);
//					maxTagCount = Integer.max(maxTagCount, tagCount);
//				}
//			}
//		};
//		
//		ArrayList<String> listTagHot = new ArrayList<String>();
//		
//		//Chuyển Map thành List entry
//		List<Map.Entry<String, Integer>> MapToList = new ArrayList<>(tagCountMap.entrySet());
//		//Sort lại list vừa tạo
//		Collections.sort(MapToList, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
//		
//		int count = 0;
//		//Lấy ra 10 phần tử đầu tiên
//		for (Map.Entry<String, Integer> entry : MapToList) {
//            if (count >= 10) {
//                break;
//            }
//            listTagHot.add(entry.getKey());
//            count++;
//        }
//		
//		
//		return listTagHot;
//	}

	@Override
	public ArrayList<String> getTagHot(int month, int year) {
		return getTagHot(-1, month, year);
	}
	
//	public static ArrayList<String> _getTagHot(int month, int year) {
//		return _getTagHot(-1, month, year);
//	}
}
