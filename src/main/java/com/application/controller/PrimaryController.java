package com.application.controller;

import java.io.IOException;

import com.application.App;

import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("Secondary");
    }
}