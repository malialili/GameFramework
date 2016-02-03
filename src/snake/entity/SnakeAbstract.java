package snake.entity;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManager;
import gameframework.game.SpriteManagerDefaultImpl;

public abstract class SnakeAbstract extends GameMovable implements Drawable, GameEntity, Overlappable{
	
	protected final SpriteManager spriteManager;
	public static final int RENDERING_SIZE = 16;
	protected boolean movable = true;
	protected boolean vulnerable = false;
	protected int vulnerableTimer = 0;
	protected String lastMove = "";
	protected String currentMove = "";
	protected Point lastPosition=new Point(14 * 16, 17 * 16);

	public SnakeAbstract(Canvas defaultCanvas) {
		spriteManager = new SpriteManagerDefaultImpl("images/snake.gif",
				defaultCanvas, RENDERING_SIZE, 1);
		
		spriteManager.setTypes(
				//
				"head-right",
				"head-down", 
				"head-left", 
				"head-up", //
				"horizontal", 
				"vertical", 
				"turn-up-right", 
				"turn-up-left", 
				"turn-down-left", 
				"turn-down-right",// 
				"tail-down",
				"tail-left", 
				"tail-up", 
				"tail-right");
	}

	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
	}
	public String getLastMove() {
		return lastMove;
	}
	
	public void setLastMove(String lastMove) {
		this.lastMove = lastMove;
	}

	public String getCurrentMove() {
		return currentMove;
	}

	public void setCurrentMove(String currentMove) {
		this.currentMove = currentMove;
	}
	
	public abstract void add(SnakeAbstract s);
	public abstract void remove(SnakeAbstract s);
	public abstract List<SnakeAbstract> getChildren();
}
