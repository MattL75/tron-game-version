package tronGame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TronGamePane extends Pane {
	
	private String player1;
	private String player2;
	private Color color1;
	private Color color2;
	protected Cycler cycler1;
	protected Cycler cycler2;
	protected Rectangle[][] squares = new Rectangle[94][46];
	protected Boolean[][] sqUsed = new Boolean[94][46];
	protected Timeline line;
	int p1Wins = 0;
	int p2Wins = 0;
	int count = 0;
	int startX1 = 100;
	int startX2 = 1780;
	int startY = 460;
	static double speed = 80;
	
	public TronGamePane(String player1, String player2, String color1, String color2) {
		
		this.color1 = getColor(color1);
		this.color2 = getColor(color2);
		this.player1 = player1.length() > 15 ? player1.substring(0, 15) : player1;
		this.player2 = player2.length() > 15 ? player2.substring(0, 15) : player2;
		
		//Maybe make this dependent on screen resolution
		setWidth(1880);
		setHeight(920);
		setPrefWidth(1880);
		setPrefHeight(920);
		setStyle("-fx-border-color: darkturquoise; -fx-border-width: 3");
		
		setupGrid();
		setupSnakes();
		initAnim();
	}
	
	private Color getColor(String color) {
		if (color.equals("red"))
			return Color.RED;
		else if (color.equals("blue"))
			return Color.BLUE;
		else if (color.equals("limegreen"))
			return Color.LIMEGREEN;
		else if (color.equals("white"))
			return Color.WHITE;
		else if (color.equals("magenta"))
			return Color.MAGENTA;
		else
			return Color.YELLOW;
	}
	
	private void setupGrid() {
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[0].length; j++) {
				squares[i][j] = new Rectangle(i * 20, j * 20, 20, 20);
				squares[i][j].setFill(Color.BLACK);
				squares[i][j].setStroke(Color.DARKGREY);
				squares[i][j].setStrokeWidth(0.4);
				squares[i][j].setOpacity(0.3);
				getChildren().add(squares[i][j]);
				
				sqUsed[i][j] = false;
			}
		}
	}
	
	private void emptyGrid() {
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[0].length; j++) {
				squares[i][j].setFill(Color.BLACK);
				sqUsed[i][j] = false;
			}
		}
	}
	
	private void setupSnakes() {
		cycler1 = new Cycler(startX1, startY, this.color1, 1);
		cycler2 = new Cycler(startX2, startY, this.color2, 3);
		getChildren().addAll(cycler1.mainRec, cycler2.mainRec);
	}

	private void initAnim() {
		
		Text countdown = new Text("3");
		countdown.setFont(new Font("Copperplate Gothic Bold", 120));
		countdown.setFill(Color.WHITE);
		countdown.setX(this.getPrefWidth() / 2 - (countdown.getLayoutBounds().getWidth() / 2));
		countdown.setY(430);
		getChildren().add(countdown);
		
		EventHandler<ActionEvent> moveEvent = e -> {
			if (speed > 100) {
				if (count != 30) {
					cycler1.setDir(1);
					cycler2.setDir(3);
					if (count == 10)
						countdown.setText("2");
					if (count == 20)
						countdown.setText("1");
					count++;
					return;
				}
			} else if (speed < 60) {
				if (count != 90) {
					cycler1.setDir(1);
					cycler2.setDir(3);
					if (count == 30)
						countdown.setText("2");
					if (count == 60)
						countdown.setText("1");
					count++;
					return;
				}
			} else {
				if (count != 60) {
					cycler1.setDir(1);
					cycler2.setDir(3);
					if (count == 20)
						countdown.setText("2");
					if (count == 40)
						countdown.setText("1");
					count++;
					return;
				}
			}
			getChildren().remove(countdown);
			
			paintTiles(cycler1);
			paintTiles(cycler2);
			cycler1.move();
			getChildren().remove(countdown);
			cycler2.move();
			getChildren().remove(countdown);
			
			//Condition for player1 runs into wall
			if (cycler1.mainRec.getX() > 1860 || cycler1.mainRec.getX() < 0 || cycler1.mainRec.getY() > 900 || cycler1.mainRec.getY() < 0) {
				line.stop();
				p2Wins++;
				TronPane.modifyScore();
				getChildren().remove(countdown);
				if (p2Wins == 5) {
					Text winner = new Text(player2 + " wins!");
					winner.setFont(new Font("Copperplate Gothic Bold", 130));
					winner.setX(this.getPrefWidth() / 2 - winner.getLayoutBounds().getWidth() / 2);
					winner.setY(420);
					winner.setFill(color2);
					winner.setStroke(Color.WHITE);
					winner.setStrokeWidth(2);
					
					TronMain.btStaticBack.setLayoutX(this.getPrefWidth() / 2 - TronMain.btStaticBack.getGraphic().getLayoutBounds().getWidth() / 2);
					TronMain.btStaticBack.setLayoutY(480);
					
					getChildren().addAll(winner, TronMain.btStaticBack);
				} else {
					resetPane();
				}
			}
			//Condition for player2 runs into wall
			if (cycler2.mainRec.getX() > 1860 || cycler2.mainRec.getX() < 0 || cycler2.mainRec.getY() > 900 || cycler2.mainRec.getY() < 0) {
				line.stop();
				p1Wins++;
				TronPane.modifyScore();
				getChildren().remove(countdown);
				if (p1Wins == 5) {
					Text winner = new Text(player1 + " wins!");
					winner.setFont(new Font("Copperplate Gothic Bold", 130));
					winner.setX(this.getPrefWidth() / 2 - winner.getLayoutBounds().getWidth() / 2);
					winner.setY(420);
					winner.setFill(color1);
					winner.setStroke(Color.WHITE);
					winner.setStrokeWidth(2);
					
					TronMain.btStaticBack.setLayoutX(this.getPrefWidth() / 2 - TronMain.btStaticBack.getGraphic().getLayoutBounds().getWidth() / 2);
					TronMain.btStaticBack.setLayoutY(480);
					
					getChildren().addAll(winner, TronMain.btStaticBack);
				} else {
					resetPane();
				}
			}
			
			//Condition for mainRec vs mainRec
			if (cycler1.mainRec.getX() == cycler2.mainRec.getX() && cycler1.mainRec.getY() == cycler2.mainRec.getY()) {
				line.stop();
				getChildren().remove(countdown);
				resetPane();
			}
			
			//Condition for player1 runs into itself or other
			for (int i = 0; i < squares.length; i++) {
				for (int j = 0; j < squares[0].length; j++) {
					if (sqUsed[i][j]) {
						if (cycler1.mainRec.getX() == squares[i][j].getX() && cycler1.mainRec.getY() == squares[i][j].getY()) {
							line.stop();
							p2Wins++;
							TronPane.modifyScore();
							getChildren().remove(countdown);
							if (p2Wins == 5) {
								Text winner = new Text(player2 + " wins!");
								winner.setFont(new Font("Copperplate Gothic Bold", 130));
								winner.setX(this.getPrefWidth() / 2 - winner.getLayoutBounds().getWidth() / 2);
								winner.setY(420);
								winner.setFill(color2);
								winner.setStroke(Color.WHITE);
								winner.setStrokeWidth(2);
								
								TronMain.btStaticBack.setLayoutX(this.getPrefWidth() / 2 - TronMain.btStaticBack.getGraphic().getLayoutBounds().getWidth() / 2);
								TronMain.btStaticBack.setLayoutY(480);
								
								getChildren().addAll(winner, TronMain.btStaticBack);
							} else {
								resetPane();
							}
						}
						if (cycler2.mainRec.getX() == squares[i][j].getX() && cycler2.mainRec.getY() == squares[i][j].getY()) {
							line.stop();
							p1Wins++;
							TronPane.modifyScore();
							getChildren().remove(countdown);
							if (p1Wins == 5) {
								Text winner = new Text(player1 + " wins!");
								winner.setFont(new Font("Copperplate Gothic Bold", 130));
								winner.setX(this.getPrefWidth() / 2 - winner.getLayoutBounds().getWidth() / 2);
								winner.setY(420);
								winner.setFill(color1);
								winner.setStroke(Color.WHITE);
								winner.setStrokeWidth(2);
								
								TronMain.btStaticBack.setLayoutX(this.getPrefWidth() / 2 - TronMain.btStaticBack.getGraphic().getLayoutBounds().getWidth() / 2);
								TronMain.btStaticBack.setLayoutY(480);
								
								getChildren().addAll(winner, TronMain.btStaticBack);
							} else {
								resetPane();
							}
						}
					}
				}
			}
			
		};
		
		line = new Timeline(new KeyFrame(Duration.millis(speed), moveEvent));
		line.setCycleCount(Timeline.INDEFINITE);
		line.play();
	}
	
	private void paintTiles(Cycler cyc1) {
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[0].length; j++) {
				if (squares[i][j].getX() == cyc1.mainRec.getX() && squares[i][j].getY() == cyc1.mainRec.getY()) {
					squares[i][j].setFill(cyc1.getColor());
					sqUsed[i][j] = true;
				}
			}
		}
	}
	
	private void resetPane() {
		emptyGrid();
		count = 0;
		cycler1.mainRec.setX(startX1);
		cycler1.mainRec.setY(startY);
		cycler2.mainRec.setX(startX2);
		cycler2.mainRec.setY(startY);
		initAnim();
	}
}
