package de.voland.game.chunksystem;

import java.awt.Graphics;
import java.util.Map;
import java.util.Set;

import de.voland.game.core.Camera;

public class ChunkSystem {
	
	private final Map<Integer, TilemapManager> chunks;
	@SuppressWarnings("unused")
	private final int objectLayerID;
	private int currentChunkID;

	public ChunkSystem(final Map<Integer, TilemapManager> chunks, final int objectLayerID, int currentChunkID) {
		this.chunks = chunks;
		this.objectLayerID = objectLayerID;
		this.currentChunkID = currentChunkID;
	}
	
	public void changeChunk(final int newChunkID) {
		chunks.get(currentChunkID).exit();
		chunks.get(newChunkID).enter();
		changeChunkID(newChunkID);
	}
	
	private void changeChunkID(final int newChunkID) {
		currentChunkID = newChunkID;
	}
	
	public void update(float timeSinceLastFrame) {
		chunks.get(currentChunkID).update(timeSinceLastFrame);
	}
	
	public void render(Graphics g, Camera camera) {
		chunks.get(currentChunkID).render(g, camera);
	}
	
	// TODO: REMOVE LATER | JUST FOR TESTING PURPOSES
	public void print() {
		
		Set<Integer> chunkIDs = chunks.keySet();

		for (int chunkID : chunkIDs) {
			final TilemapManager tilemapManager = chunks.get(chunkID);
			tilemapManager.print();
		}
	}
	
}
