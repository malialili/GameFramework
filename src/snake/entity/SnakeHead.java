package snake.entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

public class SnakeHead extends Snake{
	
	public SnakeHead(Canvas defaultCanvas) {
		super(defaultCanvas);
	}

	@Override
	public void draw(Graphics g) {
		String spriteType = "head-";
		
		Point tmp = getSpeedVector().getDirection();
		movable = true;	
		lastMove = currentMove;

		if (tmp.getX() == 1) {
			spriteType += "right";
			currentMove = "right";
		} else if (tmp.getX() == -1) {
			spriteType += "left";
			currentMove = "left";
		} else if (tmp.getY() == 1) {
			spriteType += "down";
			currentMove = "down";
		} else if (tmp.getY() == -1) {
			spriteType += "up";
			currentMove = "up";
		} else {
			spriteType += "left";
			currentMove= "left";
			movable = false;
		}
		lastPosition=this.getPosition();
		
		spriteManager.setType(spriteType);
		spriteManager.draw(g, getPosition());
	}
	
	@Override
	public void oneStepMoveAddedBehavior() {
		if (movable) {
			spriteManager.increment();		
		}
	}
}
