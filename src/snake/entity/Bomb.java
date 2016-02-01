package snake.entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import snake.entity.grain.IGrain;

import gameframework.base.DrawableImage;

public class Bomb implements IGrain {
	
	protected static DrawableImage image = null;
	protected Point position;
	public static final int RENDERING_SIZE = 16;
	
	public Bomb (Canvas defaultCanvas, Point pos) {
		image = new DrawableImage("images/bomb.gif", defaultCanvas);
		position = pos;
	}
	
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
