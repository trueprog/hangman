package rp.hangman;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Game extends Application {

	private Image bgImage;
	private Label question;
	private TextField answer;
	private Canvas canvas;

	public static void main(String[] args) {
        launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Hangman");
		StackPane root = new StackPane();
		addBackground(root);
	    addCanvas(root);
	    addQA(root);
		primaryStage.setScene(new Scene(root, bgImage.getWidth(), bgImage.getHeight()));
		primaryStage.show();
	}

	private void addCanvas(StackPane root) {
		canvas = new Canvas(bgImage.getWidth(), bgImage.getHeight());
	    root.getChildren().add(canvas);
	}

	private void addQA(StackPane root) {
		question = new Label("A question");
	    question.setFont(Font.font("serif", FontWeight.BOLD, 20));
	    answer = new TextField();
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);
	    vbox.getChildren().addAll(question, answer);
		root.getChildren().add(vbox);
	}

	private void addBackground(StackPane root) {
		bgImage = new Image("./jungle.jpg");
		ImageView bgView = new ImageView();
		bgView.setImage(bgImage);
		root.getChildren().add(bgView);
	}

}
