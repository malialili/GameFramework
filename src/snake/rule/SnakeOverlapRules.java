package snake.rule;

import gameframework.base.MoveStrategyStraightLine;
import gameframework.base.ObservableValue;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;

import java.awt.Canvas;
import java.awt.Point;
import java.util.Vector;
import snake.GameLevelOne;
import snake.entity.Bomb;
import snake.entity.Ghost;
import snake.entity.SnakeAbstract;
import snake.entity.SnakeHead;
import snake.entity.Wall;
import snake.entity.boardgame.BordGame;
import snake.entity.grain.GrainAbs;
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
	static final int IGRAIN_DURATION = 10;
	
	private final ObservableValue<Integer> score;
	private final ObservableValue<Integer> life;
	private final ObservableValue<Boolean> endOfGame;
	protected IGrainFactory grainFact;
	private int totalNbGrains = 0;
	private int nbEatenGrains = 0;
	protected Canvas canvas;
	private int[][] tab = BordGame.getTab();
	
	private boolean isEaten = false;

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
	public void overlapRule(SnakeAbstract p, Wall w) {
		System.out.println("je me suis cheurté au mur");
		life.setValue(life.getValue()-1);
		if(life.getValue()==0)
			endOfGame.setValue(true);
		//}
	}	

	GrainAbs grainLife, grainDead;
	boolean isGrainLifeCreated = false;
	boolean isGrainDeadCreated = false;
	
	public void overlapRule(SnakeHead p, GrainScore grainScore) {
		
		score.setValue(score.getValue() + 5);
		isEaten = true;
		universe.removeGameEntity(grainScore);
		grainEatenHandler();
	
		if(totalNbGrains == 0){
			grainScore = (GrainScore) grainFact.creerGrainScore(canvas, new Point(random(MIN_XY, MAX_X) * SPRITE_SIZE, random(MIN_XY, MAX_Y) * SPRITE_SIZE));
			universe.addGameEntity( grainScore);
			totalNbGrains++;		
		}
		
		GameWithGrainLife();
		GameWithGrainDead();
		GameWithBomb();
		GameWithWall();
	
	}
	
	//GrainLife
	public void overlapRule(SnakeHead p, GrainLife grainLife){
		System.out.println("vous avez une vie de gagnée :)");
		life.setValue(life.getValue() + 1);
		universe.removeGameEntity(grainLife);
	}
	
	public void overlapRule(SnakeHead p, GrainDead graindead){
		life.setValue(life.getValue() - 1);
		score.setValue(0);
		universe.removeGameEntity(graindead);
		System.out.println("vous avez mangé grainDead!!! vous perdez une vie :(");
		if (life.getValue()==0)
			System.out.println("Vous avez perdu votre dernière vie :( ");
	}

	public void overlapRule(SnakeHead p, Bomb bomb) {
		life.setValue(0);
		universe.removeGameEntity(bomb);

		if(life.getValue()==0){
			System.out.println("Vous vous êtes heurté à une Bombe !!! FIN DE JEU");
			endOfGame.setValue(true);
		}
	}

	public void GameWithGrainLife(){
		if(nbEatenGrains == 5 || nbEatenGrains == 15 || nbEatenGrains == 25){
			System.out.println("je crée grainLife");
			grainLife = (GrainAbs) grainFact.creerGrainLife(canvas, new Point(random(MIN_XY, MAX_X) * SPRITE_SIZE, random(MIN_XY, MAX_Y) * SPRITE_SIZE));
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
		
	}
	
	public void GameWithGrainDead(){
		
		if(nbEatenGrains == 8 || nbEatenGrains == 22 || nbEatenGrains == 32){
			System.out.println("un grainDead a été créé");
			grainDead = (GrainAbs) grainFact.creerGrainDead(canvas, new Point(random(MIN_XY, MAX_X) * SPRITE_SIZE, random(MIN_XY, MAX_Y) * SPRITE_SIZE));
			isGrainDeadCreated = true;
			universe.addGameEntity(grainDead);
			grainDead.setGrainVisible(IGRAIN_DURATION);
		}
		
		if(isGrainDeadCreated){
			if(grainDead.isInvisible()== true){
				universe.removeGameEntity(grainDead);
			}else{
				grainDead.operation();
			}
		}
		
	}
	
	public void GameWithBomb(){
		int i = 0;
		if (i<4){
			if(nbEatenGrains == 16 || nbEatenGrains == 36|| nbEatenGrains == 46){
				System.out.println("la bombe n°"+ i +" a été créée");
				universe.addGameEntity(grainFact.creerBomb(canvas, new Point(random(MIN_XY, MAX_X) * SPRITE_SIZE, random(MIN_XY, MAX_Y) * SPRITE_SIZE)));
			i++;
			}
		}
	}
	
	public void GameWithWall(){
		if(nbEatenGrains == 10){
			int j = 15;
			while (j < 27) {
				if (tab[5][j]== 0) {
					tab[5][j]= 1;
					System.out.println("modif plateau "+ j);
					universe.addGameEntity(new Wall(canvas, j* SPRITE_SIZE, 5* SPRITE_SIZE));
				}
			    j++;
			}
		}
		if(nbEatenGrains == 20){
			int j = 6;
			while (j < 15) {
				if (tab[15][j]== 0) {
					tab[15][j]= 1;
					System.out.println("modif plateau "+ j);
					universe.addGameEntity(new Wall(canvas, j* SPRITE_SIZE, 15* SPRITE_SIZE));
				}
			    j++;
			}
		}
		if(nbEatenGrains == 30){
			int j = 0;
			while (j < 17) {
				if (tab[22][j]== 0) {
					tab[22][j]= 1;
					System.out.println("modif plateau "+ j);
					universe.addGameEntity(new Wall(canvas, j* SPRITE_SIZE, 22* SPRITE_SIZE));
				}
			    j++;
			}
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

	public boolean isEaten() {
		return isEaten;
	}

	public void setEaten(boolean isEaten) {
		this.isEaten = isEaten;
	}

	
}
