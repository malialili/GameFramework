package snake.entity.grain;

import gameframework.game.GameEntity;

import java.awt.Canvas;
import java.awt.Point;

public interface IGrainFactory {

		IGrain creerGrainScore(Canvas defaultCanvas, Point pos);
		
		IGrain creerGrainLife(Canvas defaultCanvas, Point pos);
		
		IGrain creerGrainDead(Canvas defaultCanvas, Point pos);
		
		IGrain creerBomb(Canvas defaultCanvas, Point pos);
}
