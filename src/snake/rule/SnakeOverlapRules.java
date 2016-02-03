package snake.rule;

import gameframework.base.ObservableValue;
/*import gameframework.base.MoveStrategyStraightLine;
import gameframework.base.Overlap;
import gameframework.game.GameMovableDriverDefaultImpl;*/
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;

import java.awt.Canvas;
import java.awt.Point;

import snake.entity.SnakeHead;
import snake.entity.Wall;
import snake.entity.boardgame.BordGame;
import snake.entity.grain.Bomb;
import snake.entity.grain.GrainDead;
import snake.entity.grain.GrainFactory;
import snake.entity.grain.GrainLife;
import snake.entity.grain.GrainScore;
import snake.entity.grain.IGrainFactory;

public class SnakeOverlapRules extends OverlapRulesApplierDefaultImpl{
	
	static final int MIN_XY= 2;
	static final int MAX_X= 25;
	static final int MAX_Y= 27;
	static final int SPRITE_SIZE = 16;
	static final int IGRAIN_DURATION = 15;
	
	private final ObservableValue<Integer> score;
	private final ObservableValue<Integer> life;
	private final ObservableValue<Boolean> endOfGame;
	protected IGrainFactory grainFact;
	private int totalNbGrains = 0;
	private int nbEatenGrains = 0;
	private int[][] tab = BordGame.getTab();
	protected Canvas canvas;
	protected GameUniverse universe;

	GrainLife grainLife;
	boolean isGrainLifeCreated = false;
	
	public SnakeOverlapRules(Point snakePos, Point gPos,
			ObservableValue<Integer> life, ObservableValue<Integer> score,
			ObservableValue<Boolean> endOfGame) {
		this.life = life;
		this.score = score;
		this.endOfGame = endOfGame;
		this.canvas = new Canvas();
		grainFact = new GrainFactory();
	}
	
	//Random
	int random(int min, int max){
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}
	
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}

	public void setTotalNbGrains(int totalNbGrains) {
		this.totalNbGrains = totalNbGrains;
	}


	// overlap wall
	public void overlapRule(SnakeHead p, Wall w) {
		System.out.println("je suis là");
		life.setValue(0);
		if(life.getValue()==0)
			endOfGame.setValue(true);
	}	

	public void overlapRule(SnakeHead p, GrainScore grainScore) {
		score.setValue(score.getValue() + 5);
		universe.removeGameEntity(grainScore);
		grainEatenHandler();
		
		if(totalNbGrains == 0){
			int x = random(MIN_XY, MAX_X) ;
			int y = random(MIN_XY, MAX_Y) ;
			System.out.println( x+","+y);
			if(tab[x][y]==1){
				System.out.println("un autre random");
				 x = random(MIN_XY, MAX_X) ;
				 y = random(MIN_XY, MAX_Y) ;
				 grainScore= (GrainScore) grainFact.creerGrainScore(canvas, new Point(x * SPRITE_SIZE, y * SPRITE_SIZE));
				 universe.addGameEntity( grainScore);
				 System.out.println("un autre "+x+","+y);
				 totalNbGrains++;
			} else {
				grainScore= (GrainScore) grainFact.creerGrainScore(canvas, new Point(x * SPRITE_SIZE, y * SPRITE_SIZE));
				universe.addGameEntity( grainScore);
				totalNbGrains++;
			}
		}

		if(nbEatenGrains == 5){
			int j = 15;
			while (j < 27) {
				if (tab[5][j]== 0) {
					tab[5][j]= 1;
					universe.addGameEntity(new Wall(canvas, j* SPRITE_SIZE, 5 * SPRITE_SIZE));
				}
			    j++;
			}
		}
		if(nbEatenGrains == 10){
			int j = 6;
			while (j < 15) {
				if (tab[15][j]== 0) {
					tab[15][j]= 1;
					universe.addGameEntity(new Wall(canvas, j* SPRITE_SIZE, 15* SPRITE_SIZE));
				}
			    j++;
			}
		}
		if(nbEatenGrains == 20){
			int j = 0;
			while (j < 17) {
				if (tab[22][j]== 0) {
					tab[22][j]= 1;
					universe.addGameEntity(new Wall(canvas, j* SPRITE_SIZE, 22* SPRITE_SIZE));
				}
			    j++;
			}
		}
		
		if(nbEatenGrains == 5 || nbEatenGrains == 15 || nbEatenGrains == 25){
			grainLife = (GrainLife) grainFact.creerGrainLife(canvas, new Point(random(MIN_XY, MAX_X) * SPRITE_SIZE, random(MIN_XY, MAX_Y) * SPRITE_SIZE));
			isGrainLifeCreated = true;
			universe.addGameEntity(grainLife);
			grainLife.setGrainVisible(IGRAIN_DURATION);
		}
		
		if(isGrainLifeCreated){
			if(grainLife.isInvisible()== true)
				universe.removeGameEntity(grainLife);
			else
				grainLife.operation();
		}
		
		if(nbEatenGrains == 8 || nbEatenGrains == 18|| nbEatenGrains == 28){
			universe.addGameEntity(grainFact.creerGrainDead(canvas, new Point(random(MIN_XY, MAX_X) * SPRITE_SIZE, random(MIN_XY, MAX_Y) * SPRITE_SIZE)));
		}	
	}
	
	//GrainLife
	public void overlapRule(SnakeHead p, GrainLife grainLife){
		life.setValue(life.getValue() + 1);
		universe.removeGameEntity(grainLife);
	}
	
	public void overlapRule(SnakeHead p, GrainDead graindead){
		life.setValue(life.getValue() - 1);
		score.setValue(0);
		universe.removeGameEntity(graindead);
		if (life.getValue()==0)
			System.out.println("Vous avez perdu votre dernière vie :( ");
	}

	public void overlapRule(SnakeHead p, Bomb bomb) {
		life.setValue(0);
		universe.removeGameEntity(bomb);

		if(life.getValue()==0){
			System.out.println("Vous avez perdu !!!");
			endOfGame.setValue(true);
		}
	}

	private void grainEatenHandler() {
		nbEatenGrains++;
		totalNbGrains--;
	}
	
	public int getNbEatenGrains() {
		return nbEatenGrains;
	}

	public void setNbEatenGrains(int nbEatenGrains) {
		this.nbEatenGrains = nbEatenGrains;
	}

	public ObservableValue<Integer> getScore() {
		return score;
	}

	public int getTotalNbGrains() {
		return totalNbGrains;
	}

}
