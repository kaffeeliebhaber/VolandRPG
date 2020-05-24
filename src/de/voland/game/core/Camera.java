package de.voland.game.core;

public class Camera {

	private float x;
	private float y;
	
	private final int width;
	private final int height;
	
	public Camera(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public float getX() { return x; }
	public float getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
}
