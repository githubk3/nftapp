package com.application.controller;

import java.io.IOException;

import com.application.App;

import javafx.fxml.FXML;

public class PostController {
	@FXML
    private void switchToIntro() throws IOException {
        App.setRoot("Intro");
    }
	
}
