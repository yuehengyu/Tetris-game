package pers.yhy.entities;


import java.util.Random;

import pers.yhy.listener.ShapeListener;

/**
 * it is used to generate the different shapes
 * 
 * @author yuehe
 *
 */
public class ShapeFactory {
	//store all the possible shapes,the first [] store the status of shape
	private int shapes[][][] = new int[][][] { { { 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 } } };

	/**
	 * generate the shape
	 * 
	 * @return
	 */
	public Shape getShape(ShapeListener listener) {
		System.out.println("getShape");
		// start listener
		Shape shape = new Shape();
		shape.addShapeListener(listener);
		int status=new Random().nextInt(shapes.length);
		shape.setBody(shapes[status]);;
		shape.setStatus(0);
		return shape;
	}
}
