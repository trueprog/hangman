package rp.hangman;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Game extends Application {

	private Image bgImage;
	private Label question;
	private TextField answer;
	private ImageView hangman;
	private Image[] pic = new Image[7];
	private int level;

	public static void main(String[] args) {
        launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		loadPictures();
		level = 0;
		primaryStage.setTitle("Hangman");
		StackPane root = new StackPane();
		addBackground(root);
	    addHangman(root);
	    BorderPane bp = new BorderPane();
	    bp.setTop(createQA());
	    bp.setBottom(createStartButton());
	    root.getChildren().add(bp);
		primaryStage.setScene(new Scene(root, bgImage.getWidth(), bgImage.getHeight()));
		primaryStage.show();
	}

	private void loadPictures() {
		for (int i = 0; i < pic.length; i++) {
			pic[i] = new Image("/hangman-" + i + ".png");
		}
	}

	private Node createStartButton() {
		Button btn = new Button("Start");
		return btn;
	}

	private void addHangman(StackPane root) {
		hangman = new ImageView();
		hangman.setFitWidth(100);
		hangman.setPreserveRatio(true);
		hangman.setSmooth(true);
		hangman.setCache(true);
		hangman.setImage(pic[level]);
	    root.getChildren().add(hangman);
	}

	private Node createQA() {
		question = new Label("A question");
	    question.setFont(Font.font("serif", FontWeight.BOLD, 20));
	    answer = new TextField();
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);
	    vbox.getChildren().addAll(question, answer);
		return(vbox);
	}

	private void addBackground(StackPane root) {
		bgImage = new Image("/jungle.jpg");
		ImageView bgView = new ImageView();
		bgView.setImage(bgImage);
		root.getChildren().add(bgView);
	}

}
