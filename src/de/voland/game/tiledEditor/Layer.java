package de.voland.game.tiledEditor;

import de.voland.game.chunksystem.Tilemap;
import de.voland.game.gfx.Spritesheet;

public class Layer {

	private final int layerID;
	private final int[][] data;
	private final int tileWidth;
	private final int tileHeight;
	private final int tilesX;
	private final int tilesY;
	private final Spritesheet spritesheet;
	
	public Layer(final int layerID, int[][] data, final int tilesX, final int tilesY, final int tileWidth, final int tileHeight, final Spritesheet spritesheet) {
		this.layerID = layerID;
		this.data = data;
		this.tilesX = tilesX;
		this.tilesY = tilesY;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.spritesheet = spritesheet;
	}
	
	public int getLayerID() {
		return layerID;
	}
	
	public Tilemap createTilemap() {
		
		final Tilemap tilemap = new Tilemap(layerID, tilesX, tilesY, tileWidth, tileHeight);
		
		for (int x = 0; x < data.length; x++) {
			for (int y = 0; y < data[x].length; y++) {
				final int currentLayerDataIndex = data[x][y];
				
				if (data[x][y] != -1) {
					tilemap.addTile(data[x][y], x, y, spritesheet.getImageByIndex(currentLayerDataIndex));
				}

			}
		}
		
		return tilemap;
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
