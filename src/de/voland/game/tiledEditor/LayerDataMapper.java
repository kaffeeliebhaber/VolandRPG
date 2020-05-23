package de.voland.game.tiledEditor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LayerDataMapper {

	private Map<Integer, int[][]> layerData;
	
	public LayerDataMapper() {
		layerData = new HashMap<Integer, int[][]>();
	}
	
	public void put(final Integer layerID, final int[][] data) {
		layerData.put(layerID, data);
	}
	
	public int[][] get(final Integer layerID) {
		return layerData.get(layerID);
	}
	
	public Set<Integer> getLayerIDs() {
		return layerData.keySet();
	}
	
}
