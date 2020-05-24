package de.voland.game.testObjects;

import de.voland.game.tiledEditor.BoundingBox2DMapper;
import de.voland.game.tiledEditor.ChunkSystemCreatorModel;
import de.voland.game.tiledEditor.LayerDataMapper;

public class ChunkSystemCreatorModelMock implements ChunkSystemCreatorModel {
	
	private final int chunkWidth;
	
	public ChunkSystemCreatorModelMock(final int chunkWidth) {
		this.chunkWidth = chunkWidth;
	}
	
	@Override
	public int getTilesY() {
		return 4;
	}
	
	@Override
	public int getTilesX() {
		return 4;
	}
	
	@Override
	public int getTileWidth() {
		return 0;
	}
	
	@Override
	public int getTileHeight() {
		return 0;
	}
	
	@Override
	public int getObjectLayerID() {
		return 0;
	}
	
	@Override
	public LayerDataMapper getLayerDataMapper() {
		LayerDataMapper mapper = new LayerDataMapper();
		
		int[][] data = {
				{1, 1, 2, 2},
				{3, 3, 4, 4},
				{5, 5, 6, 6},
				{7, 7, 8, 8}
		};
		
		
		mapper.put(0, data);
		
		return mapper;
	}
	
	@Override
	public int getChunkWidth() {
		return chunkWidth;
	}
	
	@Override
	public int getChunkHeight() {
		return chunkWidth;
	}
	
	@Override
	public BoundingBox2DMapper getBoundingBox2DMapper() {
		return null;
	}
}
