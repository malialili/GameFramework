package snake.entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

import gameframework.base.MoveStrategyStraightLine;
import gameframework.game.GameMovableDriverDefaultImpl;

public class SnakeBody extends Snake{

	protected Snake head;
	MoveStrategyStraightLine strat;
	GameMovableDriverDefaultImpl snakeDriver;
	String str="";

	public SnakeBody(Canvas defaultCanvas, Snake head) {
		super(defaultCanvas);
		this.head = head;
	}

	@Override
	public void draw(Graphics g) {
		String spriteType = "";
		movable = true;
		System.out.println(this.head.getLastMove()+this.head.getCurrentMove());
		//Watch where his attach part is going
		switch(this.head.getLastMove() + this.head.getCurrentMove()){
		case "rightup": 
			spriteType = "turn-down-left";
			currentMove = "turn-down-left";
			break;
		case "downleft":
			spriteType = "turn-down-left";
			currentMove = "turn-down-left";
			break;
		case "leftup": 
			spriteType = "turn-down-right";
			currentMove = "turn-down-right";
			break;
		case "downright":
			spriteType = "turn-down-right";
			currentMove = "turn-down-right";
			break;
		case "rightdown": 
			spriteType = "turn-up-left";
			currentMove = "turn-up-left";
			break;
		case "upleft":
			spriteType = "turn-up-left";
			currentMove = "turn-up-left";
			break;
		case "leftdown": 
			spriteType = "turn-up-right";
			currentMove = "turn-up-right";
			break;
		case "upright":
			spriteType = "turn-up-right";
			currentMove = "turn-up-right";
			break;
		case "leftleft": 
			spriteType = "horizontal";
			currentMove = "horizontal";
			break;
		case "rightright":
			spriteType = "horizontal";
			currentMove = "horizontal";
			break;
		case "upup": 
			spriteType = "vertical";
			currentMove = "vertical";
			break;
		case "downdown":
			spriteType = "vertical";
			currentMove = "vertical";
			break;
		default:
			spriteType = "horizontal";
			currentMove = "horizontal";
			movable = false;
			break;
		}
		str=spriteType;
		lastMove = currentMove;
		spriteManager.setType(spriteType);
		spriteManager.draw(g, getPosition());
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		//Watch if the part is movable
		if(this.head.movable){
			Point goal = null;
			/*System.out.println(str);
			System.out.println(this.head.getLastMove());*/
			//Watch where his attach part is going
			switch (this.head.getCurrentMove()){
			case "up":
				if(str=="vertical"){
					goal = new Point((int)this.getPosition().getX(), (int)this.getPosition().getY()-1);
				} else if(str == "turn-down-right"){
					goal = new Point((int)this.getPosition().getX()-1, (int)this.getPosition().getY());
				}else if(str == "turn-down-left"){
					goal = new Point((int)this.getPosition().getX()+1, (int)this.getPosition().getY());
				}
				break;
			case "down":
				if(str=="vertical"){
					goal = new Point((int)this.getPosition().getX()+1, (int)this.getPosition().getY());
					} else if(str == "turn-up-left"){
						goal = new Point((int)this.getPosition().getX()+1, (int)this.getPosition().getY());
					}else if(str == "turn-up-right"){
						goal = new Point((int)this.getPosition().getX()-1, (int)this.getPosition().getY());
					}
				break;
			case "right":
				if(str=="horizontal"){
				goal = new Point((int)this.getPosition().getX()+1, (int)this.getPosition().getY());
				} else if(str == "turn-up-right"){
					goal = new Point((int)this.getPosition().getX(), (int)this.getPosition().getY()-1);
				}else if(str == "turn-down-right"){
					goal = new Point((int)this.getPosition().getX(), (int)this.getPosition().getY()+1);
				}
				break;
			case "left":
				if(str=="horizontal"){
					goal = new Point((int)this.getPosition().getX()-1, (int)this.getPosition().getY());
					} else if(str == "turn-down-left"){
						goal = new Point((int)this.getPosition().getX(), (int)this.getPosition().getY()+1);
					}else if(str == "turn-up-left"){
						goal = new Point((int)this.getPosition().getX(), (int)this.getPosition().getY()-1);
					}
				break;
			default:
				goal = null;
				break;
			}

			
			
			/*
			Point tmp = this.head.getSpeedVector().getDirection();
			if (tmp.getX() == 1) {
				goal = new Point((int)this.getPosition().getX()+1, (int)this.getPosition().getY());
			} else if (tmp.getX() == -1) {
				goal = new Point((int)this.getPosition().getX()-1, (int)this.getPosition().getY());
			} else if (tmp.getY() == 1) {
				goal = new Point((int)this.getPosition().getX(), (int)this.getPosition().getY()+1);
			} else if (tmp.getY() == -1) {
				goal = new Point((int)this.getPosition().getX(), (int)this.getPosition().getY()-1);
			} else { 
				movable = false;
			}
			
			this.getSpeedVector().setDirection(goal);*/
		}
	}
}