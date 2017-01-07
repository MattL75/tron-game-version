package tronGame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TronPane extends BorderPane {
	
	static Text score1;
	static Text score2;
	static TronGamePane trGame;
	
	public TronPane(String player1, String color1, String player2, String color2) {
		
		HBox hbox = new HBox(30);
		hbox.setPadding(new Insets(15, 0, 15, 0));
		ImageView headerView = new ImageView(new Image("res/logoSmaller.png"));
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(headerView);
		
		Pane pane = new Pane();
		pane.setPrefWidth(1000);
		pane.setStyle("-fx-background-color: black; -fx-border-color: darkturquoise; -fx-border-width: 3");
		
		Text p1Text = new Text(player1.length() > 15 ? player1.substring(0, 15) : player1);
		p1Text.setFont(new Font("Copperplate Gothic Bold", 25));
		p1Text.setFill(Color.DARKTURQUOISE);
		p1Text.setX(pane.getPrefWidth() * 0.25 - (p1Text.getLayoutBounds().getWidth() / 2));
		p1Text.setY(30);
		Text p2Text = new Text(player2.length() > 15 ? player2.substring(0, 15) : player2);
		p2Text.setFont(new Font("Copperplate Gothic Bold", 25));
		p2Text.setFill(Color.DARKTURQUOISE);
		p2Text.setX(pane.getPrefWidth() * 0.75 - (p2Text.getLayoutBounds().getWidth() / 2));
		p2Text.setY(30);
		
		Pane gamePane = new Pane();
		trGame = new TronGamePane(player1, player2, color1, color2);
		
		score1 = new Text(String.valueOf(trGame.p1Wins));
		score1.setFont(new Font("Tahoma", 40));
		score1.setFill(Color.WHITE);
		score1.setX(p1Text.getX() - 10 + p1Text.getLayoutBounds().getWidth() / 2);
		score1.setY(80);
		score2 = new Text(String.valueOf(trGame.p2Wins));
		score2.setFont(new Font("Tahoma", 40));
		score2.setFill(Color.WHITE);
		score2.setY(80);
		score2.setX(p2Text.getX() - 10 + p2Text.getLayoutBounds().getWidth() / 2);
		
		pane.getChildren().addAll(p1Text, score1, p2Text, score2);
		hbox.getChildren().add(pane);
		setTop(hbox);
		
		gamePane.getChildren().add(trGame);
		setPadding(new Insets(0, 20, 20, 20));
		setStyle("-fx-background-color: black");
		setAlignment(pane, Pos.CENTER);
		setCenter(gamePane);
	}
	
	public static void modifyScore() {
		score1.setText(String.valueOf(trGame.p1Wins));
		score2.setText(String.valueOf(trGame.p2Wins));
	}
}
