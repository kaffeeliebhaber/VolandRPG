package de.voland.game.tiledEditor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.voland.game.collision.BoundingBox2D;

public class BoundingBox2DMapper {

	private final Map<Integer, List<BoundingBox2D>> boundingBoxes;
	
	public BoundingBox2DMapper() {
		boundingBoxes = new HashMap<Integer, List<BoundingBox2D>>();
	}
	
	 public void put(final int tileID, final BoundingBox2D boundingBox2D) {
		 getBoundingBox2DList(tileID).add(boundingBox2D);
	 } 
	 
	 public List<BoundingBox2D> get(final int tileID) {
		 return getBoundingBox2DList(tileID);
	 }
	 
	 private List<BoundingBox2D> getBoundingBox2DList(final int tileID) {
		 List<BoundingBox2D> boundingBoxList = null;
		 
		 if (boundingBoxes.containsKey(tileID)) {
			 boundingBoxList = boundingBoxes.get(tileID);
		 } else {
			 boundingBoxList = new ArrayList<BoundingBox2D>();
		 }
		 
		 return boundingBoxList;
	 }
}
