package de.voland.game.chunksystem;

/*
 
 Es muss eine Liste geben, in der die BoundingBoxen abgespeichert werden. Im Konstrukur muss direkt abgespeichert werden, 
 ob es mehr als eine BoundingBox gibt. Warum?
 
 Idee: Es kann sein, dass nicht die komplette Tile, sondern nur gewisse Bereiche in der Tile eine BoundingBox haben. Dazu obigen Idee der Implementierung.
 
 */
public class Tile {

	private final int ID;
	@SuppressWarnings("unused")
	private final float positionX;
	@SuppressWarnings("unused")
	private final float positionY;
	@SuppressWarnings("unused")
	private final int width;
	@SuppressWarnings("unused")
	private final int height;
	
	public Tile(final int ID, final float positionX, final float positionY, final int width, final int height) {
		this.ID = ID;
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		this.height = height;
	}
	
	public int getID() {
		return ID;
	}
	
	// TODO: REMOVE LATER | JUST FOR TESTING PURPOSES
	public void print() {
		System.out.print(ID + " ");
	}
}
