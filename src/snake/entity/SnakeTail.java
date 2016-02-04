package snake.entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import gameframework.base.MoveStrategyStraightLine;
import gameframework.game.GameMovableDriverDefaultImpl;

public class SnakeTail extends SnakeAbstract{

	protected static SnakeAbstract body;
	MoveStrategyStraightLine strat;
	GameMovableDriverDefaultImpl snakeDriver;
	
	private static SnakeTail singleSnakeTail;

	private SnakeTail(Canvas defaultCanvas, SnakeAbstract body) {
		super(defaultCanvas);
		SnakeTail.body = body;
	}
	
	public static SnakeTail getInstance(Canvas defaultCanvas, SnakeAbstract body){
		if(singleSnakeTail == null){
			singleSnakeTail = new SnakeTail(defaultCanvas, body);
		}else
			System.out.println("une instance a déja été crée");
		return singleSnakeTail;
	}
	
	@Override
	public void draw(Graphics g) {
		String spriteType = "tail-";
		Point tmp=SnakeTail.body.getSpeedVector().getDirection();

		switch(SnakeTail.body.getCurrentMove()){
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
			movable= false;
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

	@Override
	public void add(SnakeAbstract s) {
	}

	@Override
	public void remove(SnakeAbstract s) {
		
	}

	@Override
	public List<SnakeAbstract> getChildren() {
		return null;
	}
}
