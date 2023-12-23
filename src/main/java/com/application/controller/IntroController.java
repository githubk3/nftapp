package com.application.controller;

import java.io.IOException;

import com.application.App;

import javafx.fxml.FXML;

public class IntroController {
	 @FXML
	    private void switchToHome() throws IOException {
	        App.setRoot("Post");
	    }
}
