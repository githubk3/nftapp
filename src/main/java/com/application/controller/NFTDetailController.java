package com.application.controller;

import com.application.model.NFT;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class NFTDetailController {
    private NFT nft;
    public NFTDetailController(NFT nft){
        this.nft = nft;
    }

    @FXML
    private Label change;
    @FXML
    private Text datetime;
    @FXML
    private Label floorPrice;
    @FXML
    private ImageView image;
    @FXML
    private Text name;
    @FXML
    private Text rank;
    @FXML
    private Text sales;
    @FXML
    private Label volumn;

    public void initialize(){
        name.setText(nft.getName());
        rank.setText("Rank: "+ nft.getId());
        datetime.setText("Datetime: " + nft.getNftIntervalList().get(0).getDatetime());

        String url = nft.getImage();
        Image image1 = new Image(url);
        image.setImage(image1);

        sales.setText(String.valueOf(nft.getNftIntervalList().get(0).getSales()));
        volumn.setText(String.valueOf(nft.getNftIntervalList().get(0).getVolume()));
        change.setText(String.valueOf(nft.getNftIntervalList().get(0).getChange()));
        floorPrice.setText(String.valueOf(nft.getNftIntervalList().get(0).getFloorPrice()));
    }

}
