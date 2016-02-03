package snake.entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

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
		Point tmp=this.body.getSpeedVector().getDirection();

		switch(this.body.getCurrentMove()){
		case "turn-up-right": 
			if(tmp.getY()==-1){
				spriteType +="up";
				this.setPosition(new Point((int)body.getPosition().getX(), (int)body.getPosition().getY()+16));
			}
			else {
				spriteType +="left";
				this.setPosition(new Point((int)body.getPosition().getX()+16, (int)body.getPosition().getY()));
			}
			break;
		case "turn-up-left":
			if(tmp.getY()==-1){
				spriteType +="up";
				this.setPosition(new Point((int)body.getPosition().getX(), (int)body.getPosition().getY()+16));
			}
			else {
				spriteType +="right";
				this.setPosition(new Point((int)body.getPosition().getX()-16, (int)body.getPosition().getY()));
			}
			break;
		case "turn-down-left": 
			if(tmp.getY()==1){
				spriteType +="down";
				this.setPosition(new Point((int)body.getPosition().getX(), (int)body.getPosition().getY()-16));
			}
			else {
				spriteType +="right";
				this.setPosition(new Point((int)body.getPosition().getX()-16, (int)body.getPosition().getY()));
			}
			break;
		case "turn-down-right":
			if(tmp.getX()==-1){
				spriteType +="left";
				this.setPosition(new Point((int)body.getPosition().getX()+16, (int)body.getPosition().getY()));
			}
			else {
				spriteType +="down";
				this.setPosition(new Point((int)body.getPosition().getX(), (int)body.getPosition().getY()-16));
			}
			break;
		case "vertical":
			if(tmp.getY()==1){
				spriteType +="down";
				this.setPosition(new Point((int)body.getPosition().getX(), (int)body.getPosition().getY()-16));
			}
			else{
				spriteType +="up";
				this.setPosition(new Point((int)body.getPosition().getX(), (int)body.getPosition().getY()+16));
			}
			break;
		case "horizontal":
			if(tmp.getX()==1){
				spriteType +="right";
				this.setPosition(new Point((int)body.getPosition().getX()-16, (int)body.getPosition().getY()));
			}
			else {
				spriteType +="left";
				this.setPosition(new Point((int)body.getPosition().getX()+16, (int)body.getPosition().getY()));
			}
			break;
		default:
			spriteType += "left";
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
