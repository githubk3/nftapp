package com.application.model;

import java.util.*;

public interface INFTManager {
	public void addNFT(NFT nft);
	public void addNFT(List<NFT> nft);
	public ArrayList<NFT> getNFTsByGateway(String gateway);
	public ArrayList<NFT> getNFTsByName(String name);
	public double calculateTotalVolume(String gateway, String datetime);
}
