package snake.entity;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.DrawableImage;

public class Bomb implements GrainFactory {
	
	protected static DrawableImage image = null;
	protected Point position;
	public static final int RENDERING_SIZE = 16;
	
	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle((int) position.getX(), (int) position.getY(),
				RENDERING_SIZE, RENDERING_SIZE));
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image.getImage(), (int) getPosition().getX(),
				(int) getPosition().getY(), RENDERING_SIZE, RENDERING_SIZE, null);
	}

}
