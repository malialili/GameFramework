package snake.entity;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

public interface GrainFactory extends Drawable, GameEntity, Overlappable{

	public Point getPosition();

	public void draw(Graphics g);
	
	public Rectangle getBoundingBox();

}
