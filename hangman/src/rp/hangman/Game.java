package rp.hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Game extends Application {

	private Image bgImage;
	private Label questionLabel;
	private TextField answerLabel;
	private Label statusLabel;
	private ImageView hangman;
	private Image[] pic = new Image[7];
	
	private int wrong;
	private List<String> questions = new ArrayList<>();
	private List<String> answers = new ArrayList<>();
	private int correct = 0;
	private int question = 0;
	private Label correctLabel;
	private Label wrongLabel;

	public static void main(String[] args) {
        launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		loadQuestions();
		loadPictures();
		wrong = 0;
		primaryStage.setTitle("Hangman");
		StackPane root = new StackPane();
		addBackground(root);
	    addHangman(root);
	    BorderPane bp = new BorderPane();
	    bp.setTop(createQA());
	    bp.setBottom(createStatus());
	    root.getChildren().add(bp);
		primaryStage.setScene(new Scene(root, bgImage.getWidth(), bgImage.getHeight()));
		primaryStage.show();
	}

	private void loadQuestions() {
		InputStream is = this.getClass().getResourceAsStream("/questions.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		Iterator<String> lines = br.lines().iterator();
		while (lines.hasNext()) {
			questions.add(lines.next());
			answers.add(lines.next());
		}
		try {
			br.close();
		} catch (IOException e) {
			// ignore failed close
		}
	}

	private void loadPictures() {
		for (int i = 0; i < pic.length; i++) {
			pic[i] = new Image("/hangman-" + i + ".png");
		}
	}

	private void addHangman(StackPane root) {
		hangman = new ImageView();
		hangman.setFitWidth(100);
		hangman.setPreserveRatio(true);
		hangman.setSmooth(true);
		hangman.setCache(true);
		hangman.setImage(pic[wrong]);
	    root.getChildren().add(hangman);
	}

	private Node createQA() {
		questionLabel = new Label(questions.get(0));
	    questionLabel.setFont(Font.font("serif", FontWeight.BOLD, 20));
	    answerLabel = new TextField();
	    answerLabel.setOnAction((ActionEvent e) -> { this.processAnswer(); });
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);
	    vbox.getChildren().addAll(questionLabel, answerLabel);
		return(vbox);
	}

	private Node createStatus() {
		statusLabel = new Label("");
		statusLabel.setFont(Font.font("serif", FontWeight.BOLD, 20));
		statusLabel.setMaxWidth(Double.MAX_VALUE);
		correctLabel = new Label("Correct: 0");
		correctLabel.setFont(Font.font("serif", FontWeight.BOLD, 20));
		correctLabel.setMaxWidth(Double.MAX_VALUE);
		wrongLabel = new Label("Wrong: 0");
		wrongLabel.setFont(Font.font("serif", FontWeight.BOLD, 20));
		wrongLabel.setMaxWidth(Double.MAX_VALUE);
		HBox hBox = new HBox();
		hBox.getChildren().addAll(statusLabel, correctLabel, wrongLabel);
		HBox.setHgrow(statusLabel, Priority.ALWAYS);
		HBox.setHgrow(correctLabel, Priority.ALWAYS);
		HBox.setHgrow(wrongLabel, Priority.ALWAYS);
		return hBox;
	}

	private void addBackground(StackPane root) {
		bgImage = new Image("/jungle.jpg");
		ImageView bgView = new ImageView();
		bgView.setImage(bgImage);
		root.getChildren().add(bgView);
	}

	private void processAnswer() {
		if (answerLabel.getText().equalsIgnoreCase(answers.get(question))) {
			statusLabel.setText("Correct!");
			correct++;
			correctLabel.setText("Correct: " + correct);
		} else {
			statusLabel.setText("Your answer was wrong!");
			wrong++;
			wrongLabel.setText("Wrong: " + wrong);
			hangman.setImage(pic[wrong]);
		}
		answerLabel.setText("");
		question++;
		questionLabel.setText(questions.get(question));
	}

}
