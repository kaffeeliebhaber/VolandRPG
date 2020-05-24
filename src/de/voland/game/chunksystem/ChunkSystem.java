package de.voland.game.chunksystem;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

import de.voland.game.core.Camera;

public class ChunkSystem {
	
	private final Collection<TilemapManager> tilemapManagers;
	@SuppressWarnings("unused")
	private final int objectLayerID;
	private int currentChunkID;
	
	public ChunkSystem(final int objectLayerID) {
		this.objectLayerID = objectLayerID;
		tilemapManagers = new ArrayList<TilemapManager>();
	}
	
	public void changeChunk(final int newChunkID) {
		getTilemapManager(currentChunkID).exit();
		getTilemapManager(newChunkID).enter();
		changeChunkID(newChunkID);
	}
	
	public void addTilemapManager(final TilemapManager tilemapManager) {
		tilemapManagers.add(tilemapManager);
	}
	
	private void changeChunkID(final int newChunkID) {
		currentChunkID = newChunkID;
	}
	
	private TilemapManager getTilemapManager(final int ID) {
		return tilemapManagers.stream().filter(tm -> tm.getID() == ID).findAny().get();
	}
	
	public void update(float timeSinceLastFrame) {
		getTilemapManager(currentChunkID).update(timeSinceLastFrame);
	}
	
	public void render(Graphics g, Camera camera) {
		getTilemapManager(currentChunkID).render(g, camera);
	}
}
