package snake.entity.grain;

import java.awt.Canvas;
import java.awt.Point;

public class GrainFactory implements IGrainFactory {

	@Override
	public IGrain creerGrainScore(Canvas defaultCanvas, Point pos) {
		return new GrainScore(defaultCanvas, pos );
	}

	@Override
	public IGrain creerGrainLife(Canvas defaultCanvas, Point pos) {
		return new GrainLife(defaultCanvas, pos);
	}

	@Override
	public IGrain creerGrainDeath(Canvas defaultCanvas, Point pos) {
		return new GrainDeath(defaultCanvas, pos);
	}

	@Override
	public IGrain creerBomb(Canvas defaultCanvas, Point pos) {
		return new Bomb(defaultCanvas, pos);
	}

}
