package de.voland.game.chunksystem;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import de.voland.game.core.Camera;

public class Tilemap {

	private final int ID;
	private Tile[][] tiles;
	private final int tilesX;
	private final int tilesY;
	private final int tileWidth;
	private final int tileHeight;
	
	public Tilemap(final int ID, final int tilesX, final int tilesY, final int tileWidth, final int tileHeight) {
		this.ID = ID;
		this.tilesX = tilesX;
		this.tilesY = tilesY;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		
		tiles = new Tile[tilesX][tilesY];
	}
	
	public int getID() {
		return ID;
	}
	
	public void addTile(final Tile tile, final int col, final int row) {
		tiles[col][row] = tile;
	}
	
	public void addTile(final int ID, final int col, final int row, final BufferedImage image) {
		if (ID > 0) {
			addTile(new Tile(ID, col * tileWidth, row * tileHeight, tileWidth, tileHeight, image), col, row);
		}
	}
	
	public void update(float timeSinceLastFrame) {}
	
	public void render(Graphics g, Camera camera) {
		
		// HOW MANY TILES MUST BE DRAWN: X-DIRECTION
		int startX = (int) camera.getX() / tileWidth; 
		int endX = (int) (camera.getX() + camera.getWidth()) / tileWidth + 1;
		
		// HOW MANY TILES MUST BE DRAWN: Y-DIRECTION
		int startY = (int) camera.getY() / tileHeight; 
		int endY = (int) (camera.getY() + camera.getHeight()) / tileHeight + 1;
		
		if (startX < 0) {
			startX = 0;
		} else if (endX >= tilesX) {
			endX = tilesX - 1;
		}
		
		if (startY < 0) {
			startY = 0;
		} else if (endY >= tilesY) {
			endY = tilesY - 1;
		}
		
		for (int x = startX; x <= endX; x++) {
			for (int y = startY; y <= endY; y++) {
			
				if (tiles[x][y] != null) {
					tiles[x][y].render(g, camera);
				}
			}
		}

	}

}
