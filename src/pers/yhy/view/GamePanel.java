package pers.yhy.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import pers.yhy.entities.Ground;
import pers.yhy.entities.Shape;

/**
 * it is used to show the game panel
 * 
 * @author yuehe
 *
 */
public class GamePanel extends JPanel {

	private Ground ground;
	private Shape shape;

	@Override
	protected void paintComponent(Graphics g) {
		// reshow and clear the old shape
		g.setColor(new Color(0xcfcfcf));
		g.fillRect(0, 0, 300, 300);
		if (shape != null && ground != null) {
			shape.drawMe(g);
			ground.drawMe();
		}
	}

	public GamePanel() {
		this.setSize(300, 300);
	}

	/**
	 * show the game panel
	 */
	public void display(Ground ground, Shape shape) {
		this.ground = ground;
		this.shape = shape;
		System.out.println("game panel display");
		this.repaint();
	}

}
