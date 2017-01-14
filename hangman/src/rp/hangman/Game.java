package rp.hangman;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Game extends Application {

	public static void main(String[] args) {
        launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Hangman");
		StackPane root = new StackPane();
		addBackground(root);
		
		
		Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
			}
		});

		root.getChildren().add(btn);
		primaryStage.setScene(new Scene(root, bgImage.getWidth(), bgImage.getHeight()));
		primaryStage.show();
	}

	private void addBackground(StackPane root) {
		Image bgImage = new Image("./jungle.jpg");
		ImageView bgView = new ImageView();
		bgView.setImage(bgImage);
		root.getChildren().add(bgView);
	}

}
