package snake.entity.grain;

import gameframework.base.DrawableImage;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


public class GrainScore extends GrainAbs{

	protected static DrawableImage image = null;
	protected Point position;
	public static final int RENDERING_SIZE = 16;

	public GrainScore (Canvas defaultCanvas, Point pos) {
		image = new DrawableImage("images/grain.gif", defaultCanvas);
		position = pos;
	}

	public Point getPosition() {
		return position;
	}

	public void draw(Graphics g) {
		g.drawImage(image.getImage(), (int) getPosition().getX(),
				(int) getPosition().getY(), RENDERING_SIZE, RENDERING_SIZE,
				null);

	}

	public Rectangle getBoundingBox() {
		return (new Rectangle((int) position.getX(), (int) position.getY(),
				RENDERING_SIZE, RENDERING_SIZE));
	}
		
	public String toString(){
		return "GrainScore";
	}


}
