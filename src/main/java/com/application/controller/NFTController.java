package com.application.controller;

import com.application.model.Bucket;
import com.application.model.NFT;
import com.application.screen.NFTDetail;
import com.application.screen.PostScreen;
import com.application.screen.TagScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class NFTController {
    private Bucket bucket;
    int choice = 1;
    public NFTController(Bucket bucket) {
        this.bucket = bucket;}


    @FXML
    private TableColumn<NFT, String> colImage;

    @FXML
    private TableColumn<NFT, String> colName;

    @FXML
    private TableColumn<NFT, Integer> colRank;
    @FXML
    private TableColumn<NFT, String> colGateway;

    @FXML
    private TableView<NFT> tblNFT;
    @FXML
    private TextField filter;

    public void initialize(){
        colName.setCellValueFactory(new PropertyValueFactory<NFT, String>("name"));
        colRank.setCellValueFactory(new PropertyValueFactory<NFT, Integer>("id"));
        colImage.setCellValueFactory(new PropertyValueFactory<NFT, String>("image"));
        colGateway.setCellValueFactory(new PropertyValueFactory<NFT, String>( "gateway"));
        btnViewDetails.setVisible(false);

        tblNFT.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    if(newValue!=null){
                        btnViewDetails.setVisible(true);
                    }
                }
        );
    }
    @FXML
    private Button search;
    @FXML
    private Button btnViewDetails;

    @FXML
    private MenuButton btnFilter;

    @FXML
    void filterGateway(ActionEvent event) {
        btnFilter.setText("Gateway");
        search.setOnAction(e -> tblNFT.setItems(bucket.getNFTsByGateway(filter.getText())));
    }
    @FXML
    void filterName(ActionEvent event) {
        btnFilter.setText("Name");
        search.setOnAction(e -> tblNFT.setItems(bucket.getNFTsByName(filter.getText())));
    }

    @FXML
    void btnViewDetailsPressed(ActionEvent event) {
        NFT nft = tblNFT.getSelectionModel().getSelectedItem();
        new NFTDetail(nft);
    }
    @FXML
    void postView(ActionEvent event) {
        new PostScreen(bucket);
    }

    @FXML
    void tagView(ActionEvent event) {
        new TagScreen(bucket);
    }
}
