package com.application.screen;

import com.application.model.Bucket;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class TagScreen extends JFrame {
    private Bucket bucket;
    public TagScreen(Bucket bucket) {
        super();
        this.bucket = bucket;
        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);
        this.setResizable(false);
        this.setSize(1024, 768);
        this.setTitle("Tag");
        this.setVisible(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    TagController controller = new TagController(bucket);
                    FXMLLoader loader = new FXMLLoader(new URL("file:" +
                            "C:\\Users\\admin\\IdeaProjects\\nftapp\\src\\main\\java\\com\\application\\view\\Tag.fxml"));
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
        System.out.println(bucket1.getTagHot(12,2023));

        new TagScreen(bucket1);
    }
}
