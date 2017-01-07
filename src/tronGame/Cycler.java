package tronGame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cycler {

	private int dir;	//0 = UP, 1 = RIGHT, 2 = DOWN, 3 = LEFT
	
	private Color color;
	protected Rectangle mainRec;
	
	public Cycler(int startPosX, int startPosY, Color color, int dir) {
		this.dir = dir;
		this.mainRec = new Rectangle(startPosX, startPosY, 20, 20);
		this.color = color;
		this.mainRec.setFill(this.color);
	}
	
	public void move() {
		if (dir == 0)
			this.mainRec.setY(this.mainRec.getY() - 20);
		else if (dir == 1)
			this.mainRec.setX(this.mainRec.getX() + 20);
		else if (dir == 2)
			this.mainRec.setY(this.mainRec.getY() + 20);
		else if (dir == 3) 
			this.mainRec.setX(this.mainRec.getX() - 20);
	}
	
	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
	
	public Color getColor() {
		return this.color;
	}
	
}
