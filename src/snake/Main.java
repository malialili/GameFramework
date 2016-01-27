package snake;

import java.util.ArrayList;

import gameframework.game.GameDefaultImpl;
import gameframework.game.GameLevel;
import snake.GameLevelOne;

public class Main {
	
	public static void main(String[] args) {
		GameDefaultImpl g = new GameDefaultImpl();
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();

		levels.add(new GameLevelOne(g)); // only one level is available at this time
		
		g.setLevels(levels);
		g.start();
	}

}
