package com.application.model;

import java.util.*;

public class NFT {
	private int id;
	private String name;
	private String url;
	private String image;
	private String gateway;
	private List<NFTInterval> nftIntervalList = new ArrayList<>();
	
	public NFT(int id, String name, String url, String image, String gateway) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.image = image;
		this.gateway = gateway;
	}
	
	public NFT(int id, String name, String url, String image, String gateway, NFTInterval nftInterval)
	{
		this.id = id;
		this.name = name;
		this.url = url;
		this.image = image;
		this.gateway = gateway;
		
		nftIntervalList.add(nftInterval);
	}
	
	// to do
	public void addNftIntervalList(NFTInterval nftInterval) {
		nftIntervalList.add(nftInterval);
	}
	
	// to do
	public List<NFTInterval> getNftIntervalList(){
		return nftIntervalList;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

	public String getUrl() {
		return url;
	}

	public String getGateway() {
		return gateway;
	}
	

}
