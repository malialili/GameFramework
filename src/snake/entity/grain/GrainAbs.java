package snake.entity.grain;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


import gameframework.base.DrawableImage;

public abstract class GrainAbs implements IGrain {

	protected static DrawableImage image = null;
	protected Point position;
	public static final int RENDERING_SIZE = 16;

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
	
	public abstract String toString();

}