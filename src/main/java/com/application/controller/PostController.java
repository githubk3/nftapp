package com.application.controller;

import com.application.model.Bucket;
import com.application.model.Post;
import com.application.screen.NFTScreen;
import com.application.screen.PostDetail;
import com.application.screen.TagScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PostController {
    private Bucket bucket;
    public PostController(Bucket bucket) {
        this.bucket = bucket;}
    @FXML
    private MenuButton btnFilter;

    @FXML
    private TableView<Post> tblPost;
    @FXML
    private TableColumn<Post, Integer> colId;

    @FXML
    private TableColumn<Post, String> colContent;
    @FXML
    private TableColumn<Post, String> colUrl;

    @FXML
    private MenuItem filterKeyword;

    @FXML
    private MenuItem filterTag;

    @FXML
    private Button search;

    @FXML
    private Button btnViewDetails;

    public void initialize(){
        colContent.setCellValueFactory(new PropertyValueFactory<Post, String>("content"));
        colId.setCellValueFactory(new PropertyValueFactory<Post, Integer>( "id"));
        colUrl.setCellValueFactory(new PropertyValueFactory<Post, String>("url"));

        btnViewDetails.setVisible(false);

        tblPost.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    if(newValue!=null){
                        btnViewDetails.setVisible(true);
                    }
                }
        );
    }

    @FXML
    private TextField filter;
    @FXML
    void filterKeyword(ActionEvent event) {
        btnFilter.setText("Keyword");
        search.setOnAction(e -> tblPost.setItems(bucket.getPostsByKeyWord(filter.getText())));
    }

    @FXML
    void filterTag(ActionEvent event) {
        btnFilter.setText("Tag");
        search.setOnAction(e -> tblPost.setItems(bucket.getPostsByTag(filter.getText())));
    }

    @FXML
    void btnViewDetailsPressed(ActionEvent event) {
        Post post = tblPost.getSelectionModel().getSelectedItem();
        new PostDetail(post);
    }
    @FXML
    void NFTView(ActionEvent event) {
        new NFTScreen(bucket);
    }

    @FXML
    void tagView(ActionEvent event) {
        new TagScreen(bucket);
    }
	
}
