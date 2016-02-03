package snake.entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.Spring;

import gameframework.base.MoveStrategyStraightLine;
import gameframework.game.GameMovableDriverDefaultImpl;

public class SnakeTail extends Snake{

	protected Snake body;
	MoveStrategyStraightLine strat;
	GameMovableDriverDefaultImpl snakeDriver;

	public SnakeTail(Canvas defaultCanvas, Snake body) {
		super(defaultCanvas);
		this.body = body;
	}

	@Override
	public void draw(Graphics g) {
		String spriteType = "tail-";

		//Watch where his attach part is going
		switch(this.body.getCurrentMove()){
		case "turn-up-right": 
			spriteType += "right";
			break;
		case "turn-up-left":
			spriteType += "left";
			break;
		case "turn-down-left": 
			spriteType += "left";
			break;
		case "turn-down-right":
			spriteType += "right";
			break;
		case "vertical":
			spriteType +="up";
			break;
		case "horizontal":
			spriteType +="left";
			break;
		default:
			spriteType += "left";
		}

		spriteManager.setType(spriteType);
		spriteManager.draw(g, getPosition());
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		//Watch if the part is movable
		if(this.body.movable){
			Point goal = null;
			//Watch where his attach part is going
			switch (this.body.getLastMove()){
			case "up":
				goal = new Point((int)this.getPosition().getX(), (int)this.getPosition().getY()-1);
				break;
			case "down":
				goal = new Point((int)this.getPosition().getX(), (int)this.getPosition().getY()+1);
				break;
			case "right":
				goal = new Point((int)this.getPosition().getX()+1, (int)this.getPosition().getY());
				break;
			case "left":
				goal = new Point((int)this.getPosition().getX()-1, (int)this.getPosition().getY());
				break;
			default:
				goal = null;
				break;
			}
/*
			//Allows to move to his current position to his attach part
			this.strat = new MoveStrategyStraightLine(this.getPosition(), goal);
			snakeDriver = new GameMovableDriverDefaultImpl();
			snakeDriver.setStrategy(this.strat);
			this.setDriver(snakeDriver);*/
		}
	}
}
