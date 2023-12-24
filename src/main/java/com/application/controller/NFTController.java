package com.application.controller;

import java.io.IOException;

import com.application.App;

import javafx.fxml.FXML;

public class NFTController {
	@FXML
    private void switchToIntro() throws IOException {
        App.setRoot("Intro");
    }
	
	@FXML
    private void switchToPost() throws IOException {
        App.setRoot("Post");
    }
}
