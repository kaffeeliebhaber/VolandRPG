package de.voland.game.chunksystem;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

import de.voland.game.core.Camera;

public class TilemapManager {

	private final int ID;
	private final Collection<Tilemap> tilemaps;
	
	public TilemapManager(final int ID) {
		this.ID = ID;
		tilemaps = new ArrayList<Tilemap>();
	}
	
	public int getID() {
		return ID;
	}
	
	public void enter() {}
	
	public void exit() {}
	
	public void addTilemap(final Tilemap tilemap) {
		tilemaps.add(tilemap);
	}
	
	public void removeTilemap(final Tilemap tilemap) {
		tilemaps.remove(tilemap);
	}
	
	public void update(float timeSinceLastFrame) {
		tilemaps.stream().forEach(t -> t.update(timeSinceLastFrame));
	}
	
	public void render(final Graphics g, final Camera camera) {
		tilemaps.stream().forEach(t -> t.render(g, camera));
	}

}
