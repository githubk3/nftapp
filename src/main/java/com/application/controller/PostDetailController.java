package com.application.controller;

import com.application.model.Post;
import com.application.model.Tweet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class PostDetailController {
    private Post post;
    public PostDetailController(Post post){this.post = post;}
    @FXML
    private Text comment;

    @FXML
    private Text content;

    @FXML
    private Text datetime;

    @FXML
    private Label heart;

    @FXML
    private Label quote;
    @FXML
    private ImageView image;
    @FXML
    private Label reTweet;
    public void initialize(){
        datetime.setText("Created at: " + post.getDatetime());
        String url = post.getImage();
        Image image1 = new Image(url);
        image.setImage(image1);

        content.setText(post.getContent());
        Tweet tweet = (Tweet)post;
        comment.setText(String.valueOf(tweet.getTotalComment()));
        quote.setText(String.valueOf(tweet.getTotalQuote()));
        reTweet.setText(String.valueOf(tweet.getTotalReTweet()));
        heart.setText(String.valueOf(tweet.getTotalHeart()));
    }

}
