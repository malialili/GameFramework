package snake.entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

public class SnakeBody extends Snake{

	protected Snake head;

	public SnakeBody(Canvas defaultCanvas, Snake head) {
		super(defaultCanvas);
		this.head = head;
	}

	public Snake getHead() {
		return head;
	}

	public void setHead(Snake head) {
		this.head = head;
	}

	@Override
	public void draw(Graphics g) {
		String spriteType = "";
		movable = true;
		lastMove = currentMove;
		switch(this.head.getLastMove() + this.head.getCurrentMove()){
		case "rightup": 
			spriteType = "turn-down-left";
			currentMove = "turn-down-left";
			this.setPosition(new Point((int)head.getPosition().getX(), (int)head.getPosition().getY()+16));	
			break;
		case "downleft":
			spriteType = "turn-down-left";
			currentMove = "turn-down-left";
			this.setPosition(new Point((int)head.getPosition().getX()+16, (int)head.getPosition().getY()));
			break;
		case "leftup": 
			spriteType = "turn-down-right";
			currentMove = "turn-down-right";
			this.setPosition(new Point((int)head.getPosition().getX(), (int)head.getPosition().getY()+16));
			break;
		case "downright":
			spriteType = "turn-down-right";
			currentMove = "turn-down-right";
			this.setPosition(new Point((int)head.getPosition().getX()-16, (int)head.getPosition().getY()));
			break;
		case "rightdown": 
			spriteType = "turn-up-left";
			currentMove = "turn-up-left";
			this.setPosition(new Point((int)head.getPosition().getX(), (int)head.getPosition().getY()-16));
			break;
		case "upleft":
			spriteType = "turn-up-left";
			currentMove = "turn-up-left";
			this.setPosition(new Point((int)head.getPosition().getX()+16, (int)head.getPosition().getY()));
			break;
		case "leftdown": 
			spriteType = "turn-up-right";
			currentMove = "turn-up-right";
			this.setPosition(new Point((int)head.getPosition().getX(), (int)head.getPosition().getY()-16));
			break;
		case "upright":
			spriteType = "turn-up-right";
			currentMove = "turn-up-right";
			this.setPosition(new Point((int)head.getPosition().getX()-16, (int)head.getPosition().getY()));
			break;
		case "leftleft": 
			spriteType = "horizontal";
			currentMove = "horizontal";
			this.setPosition(new Point((int)head.getPosition().getX()+16, (int)head.getPosition().getY()));
			break;
		case "rightright":
			spriteType = "horizontal";
			currentMove = "horizontal";
			this.setPosition(new Point((int)head.getPosition().getX()-16, (int)head.getPosition().getY()));
			break;
		case "upup": 
			spriteType = "vertical";
			currentMove = "vertical";
			this.setPosition(new Point((int)head.getPosition().getX(), (int)head.getPosition().getY()+16));
			break;
		case "downdown":
			spriteType = "vertical";
			currentMove = "vertical";
			this.setPosition(new Point((int)head.getPosition().getX(), (int)head.getPosition().getY()-16));
			break;
		default:
			spriteType = "horizontal";
			currentMove = "horizontal";
			this.setPosition(new Point((int)head.getPosition().getX()+16, (int)head.getPosition().getY()));
			movable = false;
			break;
		}
		lastMove = currentMove;
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