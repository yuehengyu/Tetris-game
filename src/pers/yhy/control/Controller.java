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
			shape.rotate();
			break;
		case KeyEvent.VK_DOWN:
			shape.moveDown();
			break;
		case KeyEvent.VK_LEFT:
			shape.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			shape.moveRight();
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
}
