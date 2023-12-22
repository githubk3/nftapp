package com.application.model;

import java.util.*;

import com.application.service.LoadDataManager;

public class Bucket implements IPostManager, INFTManager {
	private List<Post> listPost = new ArrayList<>();
	private List<NFT> listNFT = new ArrayList<NFT>();
	private final LoadDataManager loader = new LoadDataManager();
	
	public Bucket() {
		List<Post> listTweetData = loader.loadDataTweet(""); // source dist
		this.addPost(listTweetData);
		
		List<NFT> listNFTData1 = loader.loadDataNFT(""); // source
		this.addNFT(listNFTData1);
	}

	@Override
	public void addNFT(NFT nft) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<NFT> getNFTsByGateway(String gateway) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NFT> getNFTsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calculateTotalVolume(int gateway, String datetime) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addPost(Post post) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void addPost(List<Post> post) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Post> getPostsByTag(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Post> getPostsByKeyWord(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getTagHot(int day, int month, int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getTagHot(int month, int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNFT(List<NFT> nft) {
		// TODO Auto-generated method stub
		
	}

}
