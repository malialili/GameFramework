package snake.entity.grain;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

public interface IGrain extends Drawable, GameEntity, Overlappable{

	public Point getPosition();

	public void draw(Graphics g);
	
	public Rectangle getBoundingBox();
	
	public abstract String toString();

}
