package pers.yhy.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import pers.yhy.entities.Ground;
import pers.yhy.entities.Shape;
import pers.yhy.entities.ShapeFactory;
import pers.yhy.listener.ShapeListener;
import pers.yhy.view.GamePanel;

/**
 * it is used to control the user key down and user logic
 * 
 * @author yuehe
 *
 */
public class Controller extends KeyAdapter implements ShapeListener {

	private Shape shape;
	private ShapeFactory shapeFactory;
	private Ground ground;
	private GamePanel gamePanel;

	public Controller(ShapeFactory shapeFactory, Ground ground, GamePanel gamePanel) {
		this.shapeFactory = shapeFactory;
		this.ground = ground;
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (ground.isMoveable(shape, Shape.ROTATE)) {
				shape.rotate();
			}
			break;
		case KeyEvent.VK_DOWN:
			if (isShapeMoveDownable(shape)) {
				shape.moveDown();
			}
			break;
		case KeyEvent.VK_LEFT:
			if (ground.isMoveable(shape, Shape.LEFT)) {
				shape.moveLeft();
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (ground.isMoveable(shape, Shape.RIGHT)) {
				shape.moveRight();
			}
			break;
		}
		gamePanel.display(ground, shape);
	}

	/**
	 * listen the shape move down
	 */
	@Override
	public void shapeMoveDown(Shape shape) {
		gamePanel.display(ground, shape);
	}

	/**
	 * start a new game when have a shape move down
	 */
	public void newGame() {
		shape = shapeFactory.getShape(this);

	}

	/**
	 * judge shape whether arrive to the bottom
	 * 
	 * @param shape
	 * @return
	 */
	@Override
	public synchronized boolean isShapeMoveDownable(Shape shape) {
		// if movind dowm shape is different with the current shape,return false,not
		// become obstacle
		if (this.shape != shape) {
			return false;
		}

		if (ground.isMoveable(shape, Shape.DOWN)) {
			return true;
		}
		ground.acceptShape(this.shape);
		if (!ground.isFull()) {
			this.shape = shapeFactory.getShape(this);
		}
		return false;
	}
}
