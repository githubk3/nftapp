package com.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("Intro"), 640, 480);
		scene.getStylesheets().add(App.class.getResource("styles/" + "Intro" + ".css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
		scene.getStylesheets().add(App.class.getResource("styles/" + fxml + ".css").toExternalForm());
	}

	public static void setPage(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader(App.class.getResource("view/" + fxml + ".fxml"));
			Node page = loader.load();

			 // Tạo một BorderPane mới
	        BorderPane borderPane = new BorderPane();

	        // Lấy header từ scene hiện tại
	        Node header = scene.getRoot().getChildrenUnmodifiable().get(0);

	        // Đặt header vào vùng top của BorderPane
	        borderPane.setTop(header);

	        // Đặt node mới vào vùng trung tâm của BorderPane
	        borderPane.setCenter(page);

			// Cập nhật gốc của scene thành AnchorPane
			scene.setRoot(borderPane);

			scene.getStylesheets().add(App.class.getResource("styles/" + fxml + ".css").toExternalForm());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

}