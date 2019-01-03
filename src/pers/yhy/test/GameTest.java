package pers.yhy.test;


import javax.swing.JFrame;

import pers.yhy.control.Controller;
import pers.yhy.entities.Ground;
import pers.yhy.entities.ShapeFactory;
import pers.yhy.view.GamePanel;

public class GameTest {
	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();
		Ground ground = new Ground();
		GamePanel gamePanel = new GamePanel();

		Controller controller = new Controller(shapeFactory, ground, gamePanel);

		JFrame jFrame = new JFrame();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(gamePanel.getSize().width + 10, gamePanel.getSize().width + 35);
		jFrame.add(gamePanel);
		gamePanel.addKeyListener(controller);
		jFrame.addKeyListener(controller);
		jFrame.setVisible(true);
		controller.newGame();
	}
}
