package de.voland.game.tiledEditor;

public interface ChunkSystemCreatorModel {

	LayerDataMapper getLayerDataMapper();
	
	BoundingBox2DMapper getBoundingBox2DMapper();
	
	int getTileWidth();
	
	int getTileHeight();
	
	int getTilesX();
	
	int getTilesY();
	
	int getChunkWidth();
	
	int getChunkHeight();
	
	int getObjectLayerID();
	
}
