package tronGame;

import java.io.File;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import tronUtil.ImageButton;

public class TronMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public static ImageButton btStaticBack = new ImageButton("res/back.png", "res/backHover.png", "res/backClick.png", 0.8);
	
	@Override
	public void start(Stage stage) throws Exception {
		//Main header logo
		ImageView headerView = new ImageView(new Image("res/tronlogo3.png"));
		headerView.setScaleX(0.8);
		headerView.setScaleY(0.8);
		btStaticBack.setStyle("-fx-background-color: transparent");
		
		//Soundtrack
		String soundFile = "src/res/tronTrack.mp3";
		Media sound = new Media(new File(soundFile).toURI().toString());
		MediaPlayer mainTrack = new MediaPlayer(sound);
		mainTrack.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				mainTrack.seek(Duration.ZERO);
			}	
		});
		mainTrack.setVolume(0.5);
		mainTrack.play();
		
		//Custom class for image buttons, built-in hover/click graphics
		ImageButton btStart = new ImageButton("res/startbtn2.png", "res/startClick.png", "res/starthover.png", 0.8);
		ImageButton btOptions = new ImageButton("res/optionspng.png", "res/optionsClick.png", "res/optionshover.png", 0.8);
		ImageButton btExit = new ImageButton("res/exitpng.png", "res/exitClick.png", "res/exithover.png", 0.8);
		
		//Vertical box for middle buttons
		VBox vertBox = new VBox(20);
		vertBox.setAlignment(Pos.TOP_CENTER);
		vertBox.setPadding(new Insets(50, 0, 0, 0));
		vertBox.getChildren().addAll(btStart, btOptions, btExit);
		
		//Main pane controls
		BorderPane mainPane = new BorderPane();
		mainPane.setTop(headerView);
		mainPane.setCenter(vertBox);
		BorderPane.setAlignment(headerView, Pos.CENTER);
		BackgroundImage tronBack = new BackgroundImage(new Image("res/BGSymmetrical.jpg", 1920, 1080, false, true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		mainPane.setBackground(new Background(tronBack));
		
		//Scene controls
		Scene mainScene = new Scene(mainPane, 1920, 1080);
		mainScene.getStylesheets().add(TronMain.class.getResource("application.css").toExternalForm());
		stage.setScene(mainScene);
		stage.getIcons().add(new Image("res/icon.png"));
		stage.setFullScreen(true);
		stage.setTitle("Tron");
		stage.show();
		
		//START MENU SETUP
		VBox overBox = new VBox(5);
		overBox.setPadding(new Insets(50, 0, 0, 0));
		overBox.setAlignment(Pos.TOP_CENTER);
		ImageView p1 = new ImageView(new Image("res/player1.png"));
		p1.setScaleX(0.8);
		p1.setScaleY(0.8);
		ImageView p2 = new ImageView(new Image("res/player2.png"));
		p2.setScaleX(0.8);
		p2.setScaleY(0.8);
		ImageView color = new ImageView(new Image("res/color.png"));
		color.setScaleX(0.8);
		color.setScaleY(0.8);
		ImageView name = new ImageView(new Image("res/name.png"));
		name.setScaleX(0.8);
		name.setScaleY(0.8);
		ImageView color2 = new ImageView(new Image("res/color.png"));
		color2.setScaleX(0.8);
		color2.setScaleY(0.8);
		ImageView name2 = new ImageView(new Image("res/name.png"));
		name2.setScaleX(0.8);
		name2.setScaleY(0.8);
		overBox.getChildren().add(p1);
		
		GridPane gPane = new GridPane();
		gPane.setAlignment(Pos.CENTER);
		
		//Player 1 start
		TextField p1Tx = new TextField();
		p1Tx.setText("Player One");
		p1Tx.setPrefWidth(400);
		p1Tx.setPrefHeight(48);
		p1Tx.setFont(new Font("Copperplate Gothic Bold", 25));
		p1Tx.setStyle("-fx-text-fill: darkturquoise; -fx-border-color: darkturquoise; -fx-border-width: 3; -fx-background-color: black");
		gPane.add(name, 0, 0);
		gPane.add(p1Tx, 1, 0);
		
		//Player 1 combobox
		ComboBox<String> cb = new ComboBox<>();
		cb.setPrefSize(400, 48);
		//cb.setStyle("-fx-background-color: black; -fx-border-color: darkturquoise; -fx-border-width: 3");
		
	    ObservableList<String> data = FXCollections.observableArrayList(
	            "blue", "red", "limegreen", "yellow", "magenta", "white");

	    cb.setItems(data);
	    cb.setValue(data.get(0));

	    Callback<ListView<String>, ListCell<String>> factory = new Callback<ListView<String>, ListCell<String>>() {
	        @Override
	        public ListCell<String> call(ListView<String> list) {
	            return new ColorRectCell();
	        }
	    };
	    cb.setCellFactory(factory);

	    Callback<ListView<String>, ListCell<String>> factoryTooltip = new Callback<ListView<String>, ListCell<String>>() {
	        @Override
	        public ListCell<String> call(ListView<String> list) {
	            return new ColorRectTooltipCell();
	        }
	    };
	    cb.setButtonCell(factoryTooltip.call(null));
		
		gPane.add(color, 0, 1);
		gPane.add(cb, 1, 1);
		
		VBox tempBox = new VBox(0);
		tempBox.setPadding(new Insets(20, 0, 0, 0));
		tempBox.setAlignment(Pos.CENTER);
		tempBox.getChildren().add(p2);
		overBox.getChildren().addAll(gPane, tempBox);
		
		//Player 2 start
		GridPane gPane2 = new GridPane();
		TextField p2Tx = new TextField();
		p2Tx.setText("Player Two");
		p2Tx.setPromptText("Enter name");
		p2Tx.setPrefWidth(400);
		p2Tx.setPrefHeight(48);
		p2Tx.setFont(new Font("Copperplate Gothic Bold", 25));
		p2Tx.setStyle("-fx-text-fill: darkturquoise; -fx-border-color: darkturquoise; -fx-border-width: 3; -fx-background-color: black");
		gPane2.add(name2, 0, 0);
		gPane2.add(p2Tx, 1, 0);
		
		//player 2 combobox
		ComboBox<String> cb2 = new ComboBox<>();
		cb2.setPrefSize(400, 48);
		//cb2.setStyle("-fx-background-color: black; -fx-border-color: darkturquoise; -fx-border-width: 3");

	    cb2.setItems(data);
	    cb2.setValue(data.get(1));
	    cb2.setCellFactory(factory);
	    cb2.setButtonCell(factoryTooltip.call(null));
	    
		gPane2.add(color2, 0, 1);
		gPane2.add(cb2, 1, 1);
		gPane2.setAlignment(Pos.CENTER);
		
		ImageButton btStart2 = new ImageButton("res/startbtn2.png", "res/startClick.png", "res/starthover.png", 0.6);
		ImageButton btBack = new ImageButton("res/back.png", "res/backHover.png", "res/backClick.png", 0.6);
		HBox hbox1 = new HBox(10);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.getChildren().addAll(btBack, btStart2);
		overBox.getChildren().addAll(gPane2, hbox1);
		
		btBack.setOnAction(e -> {
			mainPane.setCenter(vertBox);
		});
		
		btStaticBack.setOnAction(e -> {
			mainPane.setCenter(vertBox);
			mainScene.setRoot(mainPane);
		});
		
		btStart2.setOnAction(e -> {
			TronPane tp = new TronPane(p1Tx.getText(), cb.getValue(), p2Tx.getText(), cb2.getValue());
			mainScene.setRoot(tp);
			tp.setFocusTraversable(true);
			tp.requestFocus();
			tp.setOnKeyPressed(r -> {
				if (r.getCode() == KeyCode.UP && TronPane.trGame.cycler2.getDir() != 2) {
					TronPane.trGame.cycler2.setDir(0);
				} else if (r.getCode() == KeyCode.RIGHT && TronPane.trGame.cycler2.getDir() != 3) {
					TronPane.trGame.cycler2.setDir(1);
				} else if (r.getCode() == KeyCode.DOWN && TronPane.trGame.cycler2.getDir() != 0) {
					TronPane.trGame.cycler2.setDir(2);
				} else if (r.getCode() == KeyCode.LEFT && TronPane.trGame.cycler2.getDir() != 1) {
					TronPane.trGame.cycler2.setDir(3);
				} 
				
				if (r.getCode() == KeyCode.W && TronPane.trGame.cycler1.getDir() != 2) {
					TronPane.trGame.cycler1.setDir(0);
				} else if (r.getCode() == KeyCode.D && TronPane.trGame.cycler1.getDir() != 3) {
					TronPane.trGame.cycler1.setDir(1);
				} else if (r.getCode() == KeyCode.S && TronPane.trGame.cycler1.getDir() != 0) {
					TronPane.trGame.cycler1.setDir(2);
				} else if (r.getCode() == KeyCode.A && TronPane.trGame.cycler1.getDir() != 1) {
					TronPane.trGame.cycler1.setDir(3);
				} 
				
				if (r.getCode() == KeyCode.TAB) {
					btStaticBack.fire();
				}
			});
			
		});
		
		//Options menu design
		VBox optionsBox = new VBox(20);
		optionsBox.setPadding(new Insets(50, 0, 0, 0));
		optionsBox.setAlignment(Pos.TOP_CENTER);
		Text volume = new Text("MUSIC VOLUME");
		volume.setFill(Color.DARKTURQUOISE);
		volume.setFont(new Font("Copperplate Gothic Bold", 40));
		
		ImageButton btBack32 = new ImageButton("res/back.png", "res/backHover.png", "res/backClick.png", 0.6);
		btBack32.setOnAction(e -> {
			mainPane.setCenter(vertBox);
		});
		
		Slider musicSlider = new Slider(0.0, 100.0, 50.0);
		musicSlider.setMaxWidth(300);
		musicSlider.setMajorTickUnit(100.0);
		musicSlider.setShowTickLabels(true);
		musicSlider.valueProperty().addListener(e -> {
			mainTrack.setVolume(musicSlider.getValue() / 100.0);
		});
		
		Text speed = new Text("GAME SPEED");
		speed.setFill(Color.DARKTURQUOISE);
		speed.setFont(new Font("Copperplate Gothic Bold", 40));
		
		Slider speedSlider = new Slider(120, 200, 160);
		speedSlider.setMaxWidth(300);
		speedSlider.setMajorTickUnit(40);
		speedSlider.setMinorTickCount(3);
		speedSlider.setSnapToTicks(true);
		speedSlider.setShowTickLabels(true);
		speedSlider.valueProperty().addListener(e -> {
			TronGamePane.speed = speedSlider.getValue() - (speedSlider.getValue() - 120) * 2;
		});
		
		//Make slider ticks Strings
        speedSlider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                if (n == 120) return "Slower";
                if (n == 160) return "Default";
                if (n == 200) return "Faster";
                return "Faster";
            }

            @Override
            public Double fromString(String s) {
                switch (s) {
                    case "Slower":
                        return 0d;
                    case "Default":
                        return 1d;
                    case "Faster":
                        return 2d;
                    default:
                        return 3d;
                }
            }
        });
		
		optionsBox.getChildren().addAll(volume, musicSlider, speed, speedSlider, btBack32);
		
		btStart.setOnAction(e -> {
			mainPane.setCenter(overBox);
		});
		
		btOptions.setOnAction(e -> {
			mainPane.setCenter(optionsBox);
		});
		
		btExit.setOnAction(e -> {
			System.exit(0);
		});
	}
	
	static class ColorRectTooltipCell extends ColorRectCell {
		  @Override
		  public void updateItem(String item, boolean empty) {
		      super.updateItem(item, empty);
		      if (item != null) {
		          Tooltip.install(this.getParent(), new Tooltip(item));
		      }
		  }
	}
	static class ColorRectCell extends ListCell<String>{
	    @Override
	    public void updateItem(String item, boolean empty){
	        super.updateItem(item, empty);
	        Rectangle rect = new Rectangle(120,18);
	        if(item != null){
	            rect.setFill(Color.web(item));
	            setGraphic(rect);
	        }
	    }
	}
}
