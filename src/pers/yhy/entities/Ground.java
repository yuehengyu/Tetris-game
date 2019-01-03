package pers.yhy.entities;

import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;

import pers.yhy.constant.Global;

/**
 * it is used to accept the shapes to show the page change
 * 
 * @author yuehe
 *
 */
public class Ground {
	// store the shape that has been down to the bottom ,and then become obstacle
	private int[][] obstacles = new int[Global.WIDTH][Global.HEIGHT];

	/**
	 * accept the shape .shape will become the part of the ground
	 */
	public void acceptShape(Shape shape) {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (shape.isMember(x, y, false)) {
					obstacles[shape.getLeft() + x][shape.getTop() + y] = 1;
				}
			}
		}
		deleteFullLine();
	}

	/**
	 * draw the shape that our accept
	 */
	public void drawMe(Graphics g) {
		for (int x = 0; x < Global.WIDTH; x++) {
			for (int y = 0; y < Global.HEIGHT; y++) {
				if (obstacles[x][y] == 1) {
					g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}

	/**
	 * edge judge
	 * 
	 * @param shape
	 * @param status
	 * @return
	 */
	public boolean isMoveable(Shape shape, int action) {
		int left = shape.getLeft();
		int top = shape.getTop();
		switch (action) {
		case Shape.LEFT:
			left--;
			break;
		case Shape.RIGHT:
			left++;
			break;
		case Shape.DOWN:
			top++;
			break;
		}
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (shape.isMember(x, y, action == Shape.ROTATE)) {
					if (top + y >= Global.HEIGHT || left + x < 0 || left + x >= Global.WIDTH
							|| obstacles[left + x][top + y] == 1) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * when one line filled up,delete it and all lines move down
	 */
	private void deleteFullLine() {
		for (int y = Global.HEIGHT - 1; y >= 0; y--) {
			boolean fullLine = true;
			for (int x = 0; x < Global.WIDTH; x++) {
				if (obstacles[x][y] == 0) {
					fullLine = false;
				}
			}
			if (fullLine) {
				deleteLine(y);
			}
		}
	}

	/**
	 * delete one line according to line number
	 * 
	 * @param lineNum
	 */
	private void deleteLine(int lineNum) {
		for (int y = lineNum; y > 0; y--) {
			for (int x = 0; x < Global.WIDTH; x++) {
				obstacles[x][y] = obstacles[x][y - 1];
			}
		}
		for (int x = 0; x < Global.WIDTH; x++) {
			obstacles[x][0] = 0;
		}
	}

	/**
	 * it is used to set the trigger of game over
	 */
	public boolean isFull() {
		for (int x = 0; x < Global.WIDTH; x++) {
			if (obstacles[x][0] == 1) {
				return true;
			}
		}
		return false;
	}
}
