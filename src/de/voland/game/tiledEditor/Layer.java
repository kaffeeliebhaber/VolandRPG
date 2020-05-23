package de.voland.game.tiledEditor;

import de.voland.game.chunksystem.Tile;
import de.voland.game.chunksystem.Tilemap;

public class Layer {

	private final int layerID;
	private final int[][] data;
	private final int tileWidth;
	private final int tileHeight;
	private final int tilesX;
	private final int tilesY;
	
	public Layer(final int layerID, int[][] data, final int tilesX, final int tilesY, final int tileWidth, final int tileHeight) {
		this.layerID = layerID;
		this.data = data;
		this.tilesX = tilesX;
		this.tilesY = tilesY;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}
	
	public int getLayerID() {
		return layerID;
	}
	
	public Tilemap createTilemap() {
		
		Tile[][] tiles = new Tile[tilesX][tilesY];
		
		for (int x = 0; x < data.length; x++) {
			for (int y = 0; y < data[x].length; y++) {
				tiles[x][y] = new Tile(data[x][y], x * tileWidth, y * tileHeight, tileWidth, tileHeight);
			}
		}
		
		return new Tilemap(tiles, tilesX, tilesY);
	}
	
	// TODO: REMOVE LATER | JUST FOR TESTING PURPOSES
	public void print() {
		for (int x = 0; x < data.length; x++) {
			for (int y = 0; y < data[x].length; y++) {
				System.out.print(data[x][y] + " ");
			}
			System.out.println("");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + layerID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Layer other = (Layer) obj;
		if (layerID != other.layerID)
			return false;
		return true;
	}
}
