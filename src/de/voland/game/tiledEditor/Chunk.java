package de.voland.game.tiledEditor;

import java.util.ArrayList;
import java.util.Collection;

import de.voland.game.chunksystem.TilemapManager;

public class Chunk {

	private final int chunkID;
	private final Collection<Layer> layers;
	
	public Chunk(final int chunkID) {
		this.chunkID = chunkID;
		
		layers = new ArrayList<Layer>();
	}
	
	public int getChunkID() {
		return chunkID;
	}
	
	public void addLayer(final Layer layer) {
		layers.add(layer);
	}
	
	//
	public void addLayer(final int layerID, final int[][] layerData, final int tilesX, final int tilesY, final int tileWidth, final int tileHeight) {
		addLayer(new Layer(layerID, layerData, tilesX, tilesY, tileWidth, tileHeight));
	}

	public TilemapManager createTilemapManager() {
		
		final TilemapManager tilemapManager = new TilemapManager();
		
		layers.stream().forEach(l -> tilemapManager.addTilemap(l.getLayerID(), l.createTilemap()));
		
		return tilemapManager;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chunkID;
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
		Chunk other = (Chunk) obj;
		if (chunkID != other.chunkID)
			return false;
		return true;
	}
	
}
