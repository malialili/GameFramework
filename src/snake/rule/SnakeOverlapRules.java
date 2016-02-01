package snake.rule;

import gameframework.base.ObservableValue;
import gameframework.base.MoveStrategyStraightLine;
import gameframework.base.Overlap;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;
import pacman.entity.Wall;

import java.awt.Point;
import java.util.Vector;

import snake.entity.Ghost;
import snake.entity.Snake;
import snake.entity.grain.GrainFactory;
import snake.entity.grain.GrainLife;
import snake.entity.grain.IGrain;
import snake.entity.grain.IGrainFactory;

public class SnakeOverlapRules extends OverlapRulesApplierDefaultImpl{
	
	protected GameUniverse universe;
	protected Vector<Ghost> vGhosts = new Vector<Ghost>();

	// Time duration during which pacman is invulnerable and during which ghosts
	// can be eaten (in number of cycles)
	static final int INVULNERABLE_DURATION = 60;
	protected Point snakeStartPos;
	protected Point ghostStartPos;
	protected boolean manageSnakeDeath;
	private final ObservableValue<Integer> score;
	private final ObservableValue<Integer> life;
	private final ObservableValue<Boolean> endOfGame;
	private int totalNbGrains = 0;
	private int nbEatenGrains = 0;

	public SnakeOverlapRules(Point snakePos, Point gPos,
			ObservableValue<Integer> life, ObservableValue<Integer> score,
			ObservableValue<Boolean> endOfGame) {
		snakeStartPos = (Point) snakePos.clone();
		ghostStartPos = (Point) gPos.clone();
		this.life = life;
		this.score = score;
		this.endOfGame = endOfGame;
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


	/*public void overlapRule(Snake g) {
		if (!g.isActive()) {
			g.setAlive(true);
			MoveStrategyRandom strat = new MoveStrategyRandom();
			GameMovableDriverDefaultImpl ghostDriv = (GameMovableDriverDefaultImpl) g
					.getDriver();
			ghostDriv.setStrategy(strat);
			g.setPosition(ghostStartPos);
		}
	}*/

	/*public void overlapRule(Snake p, Graine spg) {
		score.setValue(score.getValue() + 5);
		universe.removeGameEntity(spg);
		pacgumEatenHandler();
		p.setInvulnerable(INVULNERABLE_DURATION);
		for (Ghost ghost : vGhosts) {
			ghost.setAfraid(INVULNERABLE_DURATION);
		}
	}*/

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
	}
	
	// overlap wall
	public void overlapRule(Snake p, Wall w) {
		life.setValue(life.getValue()-1);
		if(life.getValue()==0)
			endOfGame.setValue(true);
	}
	/*
	public void overlapRule(Snake p, GrainLife grainLife) {
		life.setValue(life.getValue() + 1);
		universe.removeGameEntity(grainLife);
		System.out.println("je mange");
	}
	*/
	private void grainEatenHandler() {
		nbEatenGrains++;
		/*if (nbEatenGrains >= totalNbGrains) {
			endOfGame.setValue(true);
		}*/
	}
}
