package snake.entity;

import gameframework.game.GameMovableDriver;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class SnakeComposite extends SnakeAbstract {

	List<SnakeAbstract> listeComposantSnake = new ArrayList<SnakeAbstract>();
	int indexLastSnakeBody = 1;
	
	public SnakeComposite(Canvas defaultCanvas) {
		super(defaultCanvas);
	}

	@Override
	public void draw(Graphics g) {
      for (SnakeAbstract s : listeComposantSnake)
    	  s.draw(g);
	}

	@Override
	public void add(SnakeAbstract s) {
		listeComposantSnake.add(s);	
	}
	
	public void addBody(SnakeAbstract s) {
		listeComposantSnake.add(indexLastSnakeBody, s);
	}

	@Override
	public void remove(SnakeAbstract s) {
		listeComposantSnake.remove(s);
	}

	@Override
	public List<SnakeAbstract> getChildren() {
		return listeComposantSnake;
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		for (SnakeAbstract s : listeComposantSnake)
	    	  s.oneStepMoveAddedBehavior();
		
	}
	
	public void setPosition1(Point p){
		for (SnakeAbstract s : listeComposantSnake)
	    	  s.setPosition(p);		
	}
	
	public void setDriver1(GameMovableDriver driver){
		for (SnakeAbstract s : listeComposantSnake)
	    	  s.setDriver(driver);		
	}

}
