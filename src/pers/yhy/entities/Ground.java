package pers.yhy.entities;

/**
 * it is used to accept the shapes to show the page change
 * 
 * @author yuehe
 *
 */
public class Ground {
	
	/**
	 * accept the shape .shape will become the part of the ground
	 */
	public void acceptShape() {
		System.out.println("acceptShape");
	}
	
	/**
	 * draw the shape that our accept
	 */
	public void drawMe() {
		System.out.println("ground drawMe");
	}
}
