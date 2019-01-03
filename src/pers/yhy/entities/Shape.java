package pers.yhy.entities;

import java.awt.Color;
import java.awt.Graphics;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import pers.yhy.constant.Global;
import pers.yhy.listener.ShapeListener;

/**
 * it contains shape's move function:down left and right,rotate function
 * 
 * @author yuehe
 *
 */
public class Shape {
	// THE action of shape
	public static final int ROTATE = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	// store the shape,we use the two-level array to represent a shape,first[] store
	// the status of shape
	private int[][] body;
	// each shape has 4 status, because we can rotate it
	private int status;
	// set the position info
	private int left;
	private int top;

	private ShapeListener shapeListener;// shape listener

	/**
	 * move to left
	 */
	public void moveLeft() {
		left--;
	}

	/**
	 * move to right
	 */
	public void moveRight() {
		left++;
	}

	/**
	 * move to down
	 */
	public void moveDown() {
		top++;
	}

	/**
	 * shape rotation
	 */
	public void rotate() {
		status = (status + 1) % body.length;
	}

	/**
	 * show the shape on the game panel
	 */
	public void drawMe(Graphics g) {
		g.setColor(Color.BLUE);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (getFlagByPoint(i, j)) {
					// draw the rectangle
					g.fill3DRect((left + i) * Global.CELL_SIZE, (top + j) * Global.CELL_SIZE, Global.CELL_SIZE,
							Global.CELL_SIZE, true);
				}
			}
		}
	}

	/**
	 * get the shape array to judge which one is 1 and then fill this grid
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean getFlagByPoint(int x, int y) {
		return body[status][y * 4 + x] == 1;
	}

	/**
	 * inner class,it is used to let shape move to down automatically.
	 * 
	 * @author yuehe
	 *
	 */
	private class ShapeDriver implements Runnable {
		@Override
		public void run() {
			while (shapeListener.isShapeMoveDownable(Shape.this)) {
				moveDown();
				shapeListener.shapeMoveDown(Shape.this);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Shape() {
		new Thread(new ShapeDriver()).start();
		;
	}

	/**
	 * register shape listener
	 */
	public void addShapeListener(ShapeListener sl) {
		if (sl != null) {
			this.shapeListener = sl;
		}
	}

	/**
	 * set body value
	 * 
	 * @param body
	 */
	public void setBody(int[][] body) {
		this.body = body;
	}

	/**
	 * set status value
	 * 
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * get shape position info
	 * 
	 * @return
	 */
	public int getTop() {
		return top;
	}

	/**
	 * get shape position info
	 * 
	 * @return
	 */
	public int getLeft() {
		return left;
	}

	/**
	 * judge if this point is belonged this shape
	 * 
	 * @param x
	 * @param y
	 * @param rotate
	 *            : whether rotate
	 * @return
	 */
	public boolean isMember(int x, int y, boolean rotate) {
		int tempStatus = status;
		if (rotate) {
			tempStatus = (status + 1) % body.length;
		}
		return body[tempStatus][y * 4 + x] == 1;
	}

}
