package com.application.screen;

import com.application.controller.PostController;
import com.application.model.Bucket;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class PostScreen extends JFrame {
    private Bucket bucket;
    public PostScreen(Bucket bucket) {
        super();
        this.bucket = bucket;
        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);
        this.setResizable(false);
        this.setSize(1024, 768);
        this.setTitle("Post");
        this.setVisible(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    PostController controller = new PostController(bucket);
                    FXMLLoader loader = new FXMLLoader(new URL("file:" +
                            "C:\\Users\\admin\\IdeaProjects\\nftapp\\src\\main\\java\\com\\application\\view\\Post.fxml"));
                    loader.setController(controller);
                    Parent root = loader.load();
                    fxPanel.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    public static void main(String[] args) {
        Bucket bucket1 = new Bucket();
        new PostScreen(bucket1);
    }
}
