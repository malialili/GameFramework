package snake;

import java.awt.Canvas;
import java.awt.Point;

import gameframework.base.MoveStrategyKeyboard;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.GameUniverseViewPortDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
import gameframework.game.OverlapProcessor;
import gameframework.game.OverlapProcessorDefaultImpl;
import snake.entity.Bomb;
import snake.entity.SnakeBody;
import snake.entity.SnakeComposite;
import snake.entity.SnakeHead;
import snake.entity.SnakeTail;
import snake.entity.Wall;
import snake.entity.grain.GrainFactory;
import snake.entity.grain.IGrainFactory;
import snake.rule.SnackMoveBlockers;
import snake.rule.SnakeOverlapRules;
import snake.entity.boardgame.BordGame;


public class GameLevelOne extends GameLevelDefaultImpl{
	Canvas canvas;
	IGrainFactory grainFact;
	int totalNbGrains = 0;
	int nb;
	private boolean isEaten = false;
	
	public GameLevelOne(Game g) {
		super(g);
		canvas = g.getCanvas();
		grainFact = new GrainFactory();
	}
	
	int tab[][] = BordGame.getTab();
	
	public static final int SPRITE_SIZE = 16;
	
	//Random
	int random(int min, int max){
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}
	
	@Override
	protected void init() {
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();

		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		moveBlockerChecker.setMoveBlockerRules(new SnackMoveBlockers());

		SnakeOverlapRules overlapRules = new SnakeOverlapRules(new Point(14 * SPRITE_SIZE, 17 * SPRITE_SIZE),
				new Point(14 * SPRITE_SIZE, 15 * SPRITE_SIZE), life[1], score[0], endOfGame);
		overlapProcessor.setOverlapRules(overlapRules);
		
		nb = overlapRules.getNbEatenGrains(); 
		
		isEaten = overlapRules.isEaten();

		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		overlapRules.setUniverse(universe);
		
		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		
		for (int i = 0; i < 31; ++i) {
			for (int j = 0; j < 28; ++j) {
				
				if (tab[i][j]== 2) {
					universe.addGameEntity(grainFact.creerGrainScore(canvas, new Point(j * SPRITE_SIZE, i * SPRITE_SIZE)));
					totalNbGrains++;
				}
				if (tab[i][j]== 1) {
					universe.addGameEntity(new Wall(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
				}
				
				if(tab[i][j]== 4){
					universe.addGameEntity(new Bomb(canvas,new Point(j*SPRITE_SIZE, i* SPRITE_SIZE)));
				}			
			}
		}
		
		overlapRules.setTotalNbGrains(totalNbGrains);
		
		final SnakeComposite scomposite = new SnakeComposite(canvas);
		final SnakeHead head = SnakeHead.getInstance(canvas);
		final SnakeBody body = new SnakeBody(canvas,head);
		final SnakeTail tail = SnakeTail.getInstance(canvas, body);
		
		scomposite.add(head);
		scomposite.add(body);
		scomposite.add(tail);
		
		GameMovableDriverDefaultImpl snakeDriver = new GameMovableDriverDefaultImpl();
		MoveStrategyKeyboard keyStr = new MoveStrategyKeyboard();			
		snakeDriver.setStrategy(keyStr);		
		snakeDriver.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);		
		
		scomposite.setDriver1(snakeDriver);
		scomposite.setPosition1(new Point(14 * SPRITE_SIZE, 17 * SPRITE_SIZE));
		//universe.addGameEntity(scomposite);
		universe.addGameEntity(head);
		universe.addGameEntity(body);
		universe.addGameEntity(tail);
				
	}
	
	public void gameInLoad(){
		System.out.println("je suis la " + isEaten);
	}
	
}

