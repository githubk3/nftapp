package com.application.screen;

import com.application.controller.PostDetailController;
import com.application.model.NFT;
import com.application.model.Post;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class PostDetail extends JFrame {
    private Post post;
    public PostDetail(Post post) {
        super();
        this.post = post;
        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);
        this.setResizable(false);
        this.setSize(1024, 768);
        this.setTitle("Post Details");
        this.setVisible(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    PostDetailController controller = new PostDetailController(post);
                    FXMLLoader loader = new FXMLLoader(new URL("file:" +
                            "C:\\Users\\admin\\IdeaProjects\\nftapp\\src\\main\\java\\com\\application\\view\\PostDetail.fxml"));
                    loader.setController(controller);
                    Parent root = loader.load();
                    fxPanel.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

    }
}
