package com.application.controller;

import java.awt.Button;
import java.io.IOException;

import com.application.App;

import javafx.fxml.FXML;

public class HeaderController {
	@FXML
	private Button btnIntro;
	@FXML
	private Button btnPost;
	@FXML
	private Button btnNFT;

	@FXML
	private void switchToPost() throws IOException {
		System.out.print("switch to post");
		App.setPage("Post");

	}

	@FXML
	private void switchToIntro() throws IOException {
		System.out.print("switch to intro");
		App.setRoot("Intro");
	}

	@FXML
	private void switchToTag() throws IOException {
		System.out.print("switch to tag");
		App.setRoot("TagView");
	}

	@FXML
	private void switchToNFT() throws IOException {
		System.out.print("switch to nft");
		App.setPage("NFT");
	}

	@FXML
	private void switchToAnalysist() throws IOException {
		System.out.print("switch to intro");
		App.setRoot("Analysist");
	}

}
