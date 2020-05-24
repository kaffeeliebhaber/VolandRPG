package de.voland.game.chunksystem;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import de.voland.game.collision.BoundingBox2D;
import de.voland.game.core.Camera;

public class Tile {

	private final int ID;
	private final float positionX;
	private final float positionY;
	private final int width;
	private final int height;
	private final List<BoundingBox2D> boundingBoxes;
	
	private BufferedImage image;
	
	public Tile(final int ID, final float positionX, final float positionY, final int width, final int height, final BufferedImage image) {
		this.ID = ID;
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		this.height = height;
		this.image = image;
		this.boundingBoxes = new ArrayList<BoundingBox2D>();
	}
	
	public int getID() {
		return ID;
	}
	
	public void addBoundingBox2D(final BoundingBox2D boundingBox2D) {
		boundingBoxes.add(boundingBox2D);
	}
	
	public void render(Graphics g, Camera camera) {
		g.drawImage(image, (int) (positionX - camera.getX()), (int) (positionY - camera.getY()), width, height, null);
	}
	
}
