package de.voland.game.chunksystem;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.voland.game.core.Camera;

/**
 * Ein TilemapManager verwaltet die unterschiedlichen Tilemaps je Chunk.
 * Layer:1 -> Tilemap a
 * Layer:2 -> Tilemap b
 * 
 * usw.
 * 
 * @author User
 *
 */

public class TilemapManager {

	private final Map<Integer, Tilemap> tilemaps;
	
	public TilemapManager() {
		tilemaps = new HashMap<Integer, Tilemap>();
	}
	
	/**
	 * Wird aufgerufen, wenn der TilemapManager zum ersten mal aufgerufen wird
	 */
	public void enter() {
		
	}
	
	/**
	 * Wird aufgerufen, wenn der TilemapManager verlassen wird
	 */
	public void exit() {
		
	}
	
	public void addTilemap(final int layerID, final Tilemap tilemap) {
		if (!tilemaps.containsKey(layerID)) {
			tilemaps.put(layerID, tilemap);
		}
	}
	
	public void removeTilemap(final int layerID) {
		if (containsTilemapId(layerID)) {
			tilemaps.remove(layerID);
		}
	}
	
	private boolean containsTilemapId(int layerID) {
		return tilemaps.containsKey(layerID);
	}
	
	public Tilemap getTilemap(final int layerID) {
		
		Tilemap tilemap = null;
		
		if (containsTilemapId(layerID)) {
			tilemap = tilemaps.get(layerID);
		}
		
		return tilemap;
	}
	
	public void update(float timeSinceLastFrame) {
		tilemaps.values().stream().forEach(t -> t.update(timeSinceLastFrame));
	}
	
	public void render(Graphics g, Camera camera) {
		tilemaps.values().stream().forEach(t -> t.render(g, camera));
	}
	
	// TODO: REMOVE LATER | JUST FOR TESTING PURPOSES
	public void print() {
		Set<Integer> tilemapIDs = tilemaps.keySet();
		
		for (int tilemapID : tilemapIDs) {
			final Tilemap tilemap = tilemaps.get(tilemapID);
			tilemap.print();
		}
	}
}
