package de.voland.game.chunksystem;

import java.awt.Graphics;

import de.voland.game.core.Camera;

public class Tilemap {

	private final Tile[][] tiles;
	@SuppressWarnings("unused")
	private final int width;
	@SuppressWarnings("unused")
	private final int height;
	
	public Tilemap(final Tile[][] tiles, final int width, final int height) {
		this.tiles = tiles;
		this.width = width;
		this.height = height;
	}
	
	public void update(float timeSinceLastFrame) {}
	
	public void render(Graphics g, Camera camera) {}
	
	// TODO: REMOVE LATER | JUST FOR TESTING PURPOSES
	public void print() {
		System.out.println(("(   TILEMAP   )"));
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				tiles[x][y].print();
			}
			System.out.println(" ");
		}
	}
}
