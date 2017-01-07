package tronUtil;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageButton extends Button {
	public ImageButton(String graphicPath, String hoverPath, String clickPath, double scale) {
		setStyle("-fx-background-color: black");
		
		ImageView graphicView = new ImageView(new Image(graphicPath));
		graphicView.setScaleX(scale);
		graphicView.setScaleY(scale);
		setGraphic(graphicView);
		
		ImageView hoverView = new ImageView(new Image(hoverPath));
		hoverView.setScaleX(scale);
		hoverView.setScaleY(scale);
		
		ImageView clickView = new ImageView(new Image(clickPath));
		clickView.setScaleX(scale);
		clickView.setScaleY(scale);
		
		this.setOnMouseEntered(e -> {
			setGraphic(hoverView);
		});
		
		this.setOnMouseExited(e -> {
			setGraphic(graphicView);
		});
		
		this.setOnMousePressed(e -> {
			setGraphic(clickView);
		});
		
		this.setOnMouseReleased(e -> {
			setGraphic(graphicView);
		});
	}
}
