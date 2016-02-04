package snake.entity;

import gameframework.game.GameMovableDriver;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class SnakeComposite extends SnakeAbstract {

	List<SnakeAbstract> listeComposantSnake = new ArrayList<SnakeAbstract>();
	SnakeComposite snakeCompo;
	int sizeListe;
	public SnakeComposite(Canvas defaultCanvas) {
		super(defaultCanvas);
	}
	
	public SnakeComposite(Canvas defaultCanvas, SnakeComposite snakeCompo) {
		super(defaultCanvas);
		this.snakeCompo = snakeCompo;
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
		sizeListe = listeComposantSnake.size();
		listeComposantSnake.add(sizeListe-2, s);
		setSizeListe(sizeListe +1);
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


	public int getSizeListe() {
		return sizeListe;
	}

	public void setSizeListe(int sizeListe) {
		this.sizeListe = sizeListe;
	}
	
	public SnakeComposite getSnakeCompo() {
		return snakeCompo;
	}

	public void setSnakeCompo(SnakeComposite snakeCompo) {
		this.snakeCompo = snakeCompo;
	}

}
