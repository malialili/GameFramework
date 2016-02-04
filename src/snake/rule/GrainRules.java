package snake.rule;

import java.awt.Canvas;

import snake.entity.Wall;

public class GrainRules {
	
	private static final int SPRITE_SIZE = 16;

	//Random
	int random(int min, int max){
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}

}
