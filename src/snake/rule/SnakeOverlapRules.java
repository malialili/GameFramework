package snake.rule;

import gameframework.base.ObservableValue;
/*import gameframework.base.MoveStrategyStraightLine;
import gameframework.base.Overlap;
import gameframework.game.GameMovableDriverDefaultImpl;*/
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;

import java.awt.Canvas;
import java.awt.Point;
import java.util.Vector;

import snake.GameLevelOne;
import snake.entity.Bomb;
import snake.entity.Ghost;
import snake.entity.Snake;
import snake.entity.SnakeHead;
import snake.entity.Wall;
import snake.entity.boardgame.BordGame;
import snake.entity.grain.GrainDead;
import snake.entity.grain.GrainFactory;
import snake.entity.grain.GrainLife;
import snake.entity.grain.GrainScore;
import snake.entity.grain.IGrainFactory;

public class SnakeOverlapRules extends OverlapRulesApplierDefaultImpl{
	
	protected GameUniverse universe;
	protected Vector<Ghost> vGhosts = new Vector<Ghost>();

	static final int MIN_XY= 2;
	static final int MAX_X= 25;
	static final int MAX_Y= 28;
	static final int SPRITE_SIZE = 16;
	static final int IGRAIN_DURATION = 15;
	
	private final ObservableValue<Integer> score;
	private final ObservableValue<Integer> life;
	private final ObservableValue<Boolean> endOfGame;
	protected IGrainFactory grainFact;
	private Wall wall;
	private int totalNbGrains = 0;
	private int nbEatenGrains = 0;
	protected Canvas canvas;
	private int[][] tab = BordGame.getTab();;

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
		//life.setValue(life.getValue()-1);
		//if(life.getValue()==0){
		System.out.println(" je tappe le mur");
			endOfGame.setValue(true);
		//}
	}	

	GrainLife grainLife;
	boolean isGrainLifeCreated = false;

	public void overlapRule(SnakeHead p, GrainScore grainScore) {
		score.setValue(score.getValue() + 5);
		universe.removeGameEntity(grainScore);
		grainEatenHandler();
		
		if(totalNbGrains == 0){
			grainScore= (GrainScore) grainFact.creerGrainScore(canvas, new Point(random(MIN_XY, MAX_X) * SPRITE_SIZE, random(MIN_XY, MAX_Y) * SPRITE_SIZE));
			universe.addGameEntity( grainScore);
			totalNbGrains++;		
		}

	
		if(nbEatenGrains == 1){
			int j = 15;
			while (j < 27) {
				if (tab[5][j]== 0) {
					System.out.println("modif plateau "+ j);
					universe.addGameEntity(new Wall(canvas, j* SPRITE_SIZE, 5* SPRITE_SIZE));
				}
			    j++;
			}
		}
		if(nbEatenGrains == 2){
			int j = 6;
			while (j < 15) {
				if (tab[15][j]== 0) {
					System.out.println("modif plateau "+ j);
					universe.addGameEntity(new Wall(canvas, j* SPRITE_SIZE, 15* SPRITE_SIZE));
				}
			    j++;
			}
		}
		if(nbEatenGrains == 3){
			int j = 0;
			while (j < 17) {
				if (tab[22][j]== 0) {
					System.out.println("modif plateau "+ j);
					universe.addGameEntity(new Wall(canvas, j* SPRITE_SIZE, 22* SPRITE_SIZE));
				}
			    j++;
			}
		}
		
		if(nbEatenGrains == 5 || nbEatenGrains == 15 || nbEatenGrains == 25){
			System.out.println("je crée grainLife");
			grainLife = (GrainLife) grainFact.creerGrainLife(canvas, new Point(random(MIN_XY, MAX_X) * SPRITE_SIZE, random(MIN_XY, MAX_Y) * SPRITE_SIZE));
			isGrainLifeCreated = true;
			universe.addGameEntity(grainLife);
			grainLife.setGrainVisible(IGRAIN_DURATION);
		}
		
		if(isGrainLifeCreated){
			if(grainLife.isInvisible()== true){
				System.out.println("je suis à off");
				universe.removeGameEntity(grainLife);
			}else{
				grainLife.operation();
			}
		}
		
		if(nbEatenGrains == 8 || nbEatenGrains == 18|| nbEatenGrains == 28){
			System.out.println("je crée grainDead");
			universe.addGameEntity(grainFact.creerGrainDead(canvas, new Point(random(MIN_XY, MAX_X) * SPRITE_SIZE, random(MIN_XY, MAX_Y) * SPRITE_SIZE)));
		}
		
	}
	
	//GrainLife
	public void overlapRule(SnakeHead p, GrainLife grainLife){
		System.out.println("je veux a life");
		System.out.println("g mangé " + nbEatenGrains);
		life.setValue(life.getValue() + 1);
		universe.removeGameEntity(grainLife);
	}
	
	public void overlapRule(SnakeHead p, GrainDead graindead){
		life.setValue(life.getValue() - 1);
		score.setValue(0);
		universe.removeGameEntity(graindead);
		System.out.println("j'ai mangé grainDead");
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
		/*if (nbEatenGrains >= totalNbGrains) {
			endOfGame.setValue(true);  //you win
		}*/
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
