package snake.rule;

import gameframework.base.ObservableValue;
import gameframework.base.MoveStrategyStraightLine;
import gameframework.base.Overlap;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;
import pacman.entity.Wall;

import java.awt.Canvas;
import java.awt.Point;
import java.util.Vector;

import snake.entity.Bomb;
import snake.entity.Ghost;
import snake.entity.Snake;
import snake.entity.grain.GrainLife;
import snake.entity.grain.GrainScore;
import snake.entity.grain.IGrain;
import snake.entity.grain.IGrainFactory;

public class SnakeOverlapRules extends OverlapRulesApplierDefaultImpl{
	
	protected GameUniverse universe;
	protected Vector<Ghost> vGhosts = new Vector<Ghost>();
	IGrainFactory grainFact;

	static final int INVULNERABLE_DURATION = 60;
	protected Point snakeStartPos;
	protected Point ghostStartPos;
	protected boolean manageSnakeDeath;
	private final ObservableValue<Integer> score;
	private final ObservableValue<Integer> life;
	private final ObservableValue<Boolean> endOfGame;
	private int totalNbGrains = 0;
	private int nbEatenGrains = 0;
	protected Canvas canvas;

	public SnakeOverlapRules(Point snakePos, Point gPos,
			ObservableValue<Integer> life, ObservableValue<Integer> score,
			ObservableValue<Boolean> endOfGame, Canvas canvas ) {
		snakeStartPos = (Point) snakePos.clone();
		ghostStartPos = (Point) gPos.clone();
		this.life = life;
		this.score = score;
		this.endOfGame = endOfGame;
		this.canvas =canvas;
	}

	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}

	public void setTotalNbGrains(int totalNbGrains) {
		this.totalNbGrains = totalNbGrains;
	}

	public void addGhost(Ghost g) {
		vGhosts.addElement(g);
	}

	
	public void applyOverlapRules(Vector<Overlap> overlappables) {
		manageSnakeDeath = true;
		super.applyOverlapRules(overlappables);
	}

	public void overlapRule(Snake p, Ghost g) {
		if (!p.isVulnerable()) {
			if (g.isActive()) {
				g.setAlive(false);
				MoveStrategyStraightLine strat = new MoveStrategyStraightLine(
						g.getPosition(), ghostStartPos);
				GameMovableDriverDefaultImpl ghostDriv = (GameMovableDriverDefaultImpl) g
						.getDriver();
				ghostDriv.setStrategy(strat);

			}
		} else {
			if (g.isActive()) {
				if (manageSnakeDeath) {
					life.setValue(life.getValue() - 1);
					p.setPosition(snakeStartPos);
					for (Ghost ghost : vGhosts) {
						ghost.setPosition(ghostStartPos);
					}
					manageSnakeDeath = false;
				}
			}
		}
	}

/*
	public void overlapRule(Snake p, IGrain pg) {
		System.out.println(pg.getClass().getName());
		System.out.println("je suis dans la fonction");
		switch (pg.getClass().getName()){
		case "GrainScore":
			score.setValue(score.getValue() + 5);
			universe.removeGameEntity(pg);
			grainEatenHandler();
			break;
		case "GrainLife":
			life.setValue(life.getValue() + 1);
			universe.removeGameEntity(pg);
			System.out.println("manger");
			break;
		default: System.out.println("rien");
		}
	}*/
	
	// overlap wall
	public void overlapRule(Snake p, Wall w) {
		life.setValue(life.getValue()-1);
		if(life.getValue()==0)
			endOfGame.setValue(true);
	}

	int random(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}
	
	public void overlapRule(Snake p, GrainScore grainScore) {
		score.setValue(score.getValue() + 1);
		universe.removeGameEntity(grainScore);
		grainEatenHandler();
		
		System.out.println("je mange"+  totalNbGrains);
		if(totalNbGrains==0){
			System.out.println("aaaaaaaa");
			universe.addGameEntity( new GrainScore(canvas, new Point(random(0, 27)*16, random(0, 30)*16)));
			//universe.addGameEntity(grainScore);
			totalNbGrains++;
			
		}
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public void overlapRule(Snake p, Bomb bomb) {
		life.setValue(life.getValue() - 1);
		universe.removeGameEntity(bomb);
		if(life.getValue()==0)
			endOfGame.setValue(true);
		
	}

	private void grainEatenHandler() {
		nbEatenGrains++;
		totalNbGrains--;
		/*if (nbEatenGrains >= totalNbGrains) {
			endOfGame.setValue(true);  //you win
		}*/
	}
	
}
