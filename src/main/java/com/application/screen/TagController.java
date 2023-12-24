package com.application.screen;

import com.application.model.Bucket;
import com.application.screen.NFTScreen;
import com.application.screen.PostScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class TagController {
    private Bucket bucket;
    public TagController(Bucket bucket) {
        this.bucket = bucket;}

    @FXML
    private TextField day;

    @FXML
    private TextField month;

    @FXML
    private Button search;

    @FXML
    private ToggleGroup searchChoice;

    @FXML
    private Text row1, row2, row3, row4, row5, row6, row7, row8, row9, row10;

    @FXML
    private TextField year;


    @FXML
    void searchWithDay(ActionEvent event) {
        int dayi = Integer.parseInt(day.getText());
        int monthi = Integer.parseInt(month.getText());
        int yeari = Integer.parseInt(year.getText());
        row1.setText(bucket.getTagHot(dayi, monthi, yeari).get(0));
        row2.setText(bucket.getTagHot(dayi, monthi, yeari).get(1));
        row3.setText(bucket.getTagHot(dayi, monthi, yeari).get(2));
        row4.setText(bucket.getTagHot(dayi, monthi, yeari).get(3));
        row5.setText(bucket.getTagHot(dayi, monthi, yeari).get(4));
        row6.setText(bucket.getTagHot(dayi, monthi, yeari).get(5));
        row7.setText(bucket.getTagHot(dayi, monthi, yeari).get(6));
        row8.setText(bucket.getTagHot(dayi, monthi, yeari).get(7));
        row9.setText(bucket.getTagHot(dayi, monthi, yeari).get(8));
        row10.setText(bucket.getTagHot(dayi, monthi, yeari).get(9));
    }

    @FXML
    void searchWithMonth(ActionEvent event) {
        int monthi = Integer.parseInt(month.getText());
        int yeari = Integer.parseInt(year.getText());
        row1.setText(bucket.getTagHot(monthi, yeari).get(0));
        row2.setText(bucket.getTagHot(monthi, yeari).get(1));
        row3.setText(bucket.getTagHot(monthi, yeari).get(2));
        row4.setText(bucket.getTagHot(monthi, yeari).get(3));
        row5.setText(bucket.getTagHot(monthi, yeari).get(4));
        row6.setText(bucket.getTagHot(monthi, yeari).get(5));
        row7.setText(bucket.getTagHot(monthi, yeari).get(6));
        row8.setText(bucket.getTagHot(monthi, yeari).get(7));
        row9.setText(bucket.getTagHot(monthi, yeari).get(8));
        row10.setText(bucket.getTagHot(monthi, yeari).get(9));
    }
    @FXML
    void NFTView(ActionEvent event) {
        new NFTScreen(bucket);
    }

    @FXML
    void PostView(ActionEvent event) {
        new PostScreen(bucket);
    }

    }


