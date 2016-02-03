package snake.entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

public class SnakeHead extends SnakeAbstract{
	
	private static SnakeHead singleSnakeHead = null;
	
	public SnakeHead(Canvas defaultCanvas) {
		super(defaultCanvas);
	}
	
	public static SnakeHead getInstance(){
		if(singleSnakeHead == null){
			singleSnakeHead = new SnakeHead(new Canvas());
		}
		return singleSnakeHead;
	}

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

	@Override
	public void add(SnakeAbstract s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(SnakeAbstract s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SnakeAbstract> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}
}
