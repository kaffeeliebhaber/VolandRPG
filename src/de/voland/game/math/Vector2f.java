package de.voland.game.math;


public class Vector2f {
	
	private float x;
	private float y;
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float squareDistance(Vector2f vec) {
		
		float distanceX = vec.x - x;
		float distanceY = vec.y - y;
		
		return distanceX * distanceX + distanceY * distanceY;
	}
	
	public void translate(final float dx, final float dy) {
		this.x += dx;
		this.y += dy;
	}
	
	@Override
	public String toString() {
		return "(Vector2f) x: " + x + " | y: " + y;
	}
	
	@Override
	public boolean equals(Object o) {

		if (!(o instanceof Vector2f)) return false;
		
		Vector2f vec = (Vector2f) o;
		
		if (this.x != vec.x || this.y != vec.y) return false;
		
		return true;
	}
	
	@Override
	public Vector2f clone() {
		return new Vector2f(x, y);
	}
}
