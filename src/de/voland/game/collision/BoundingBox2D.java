package de.voland.game.collision;

public class BoundingBox2D {

	private float x;
	private float y;
	private int width;
	private int height;
	
	public BoundingBox2D(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}
