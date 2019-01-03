package pers.yhy.listener;

import pers.yhy.entities.Shape;

/**
 * judge if the shape has been moved to down
 * @author yuehe
 *
 */
public interface ShapeListener {
	
	
	void shapeMoveDown(Shape shape);
	boolean isShapeMoveDownable(Shape shape);
	
	
}
