package com.application.model;

import javafx.collections.ObservableList;

import java.util.*;

public interface INFTManager {
	public void addNFT(NFT nft);
	public void addNFT(List<NFT> nft);
	public ObservableList<NFT> getNFTsByGateway(String gateway);
	public ObservableList<NFT> getNFTsByName(String name);
	public double calculateTotalVolume(String gateway, String datetime);
}
