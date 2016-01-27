package snake.rule;

import gameframework.game.IllegalMoveException;
import gameframework.game.MoveBlockerRulesApplierDefaultImpl;
import snake.entity.Wall;


public class SnackMoveBlockers extends MoveBlockerRulesApplierDefaultImpl{
	
	
	public void moveBlockerRule(Wall w) throws IllegalMoveException {
	
			throw new IllegalMoveException();
	}
	

}
