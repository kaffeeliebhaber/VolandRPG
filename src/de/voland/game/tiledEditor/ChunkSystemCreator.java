package de.voland.game.tiledEditor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import de.voland.game.chunksystem.ChunkSystem;
import de.voland.game.chunksystem.TilemapManager;

public class ChunkSystemCreator {

	private final ChunkSystemCreatorModel model;
	private final Collection<Chunk> chunks;
	private final int INITIAL_CHUNKID = 0;

	public ChunkSystemCreator(final ChunkSystemCreatorModel model) {
		this.model = model;
		checkChunkSystemCreatorModel(model);
		chunks = new ArrayList<Chunk>();
		init();
	}

	private void checkChunkSystemCreatorModel(final ChunkSystemCreatorModel model) {}
	
	private void init() {
		
		final LayerDataMapper layerDataMapper = model.getLayerDataMapper();
		final int numberOfChunks = calcNumberOfChunks(model);
		final Set<Integer> layerIDs = layerDataMapper.getLayerIDs();

		for (int layerID : layerIDs) {
			
			int[][] layerData = layerDataMapper.get(layerID);

			for (int chunk = 0; chunk < numberOfChunks; chunk++) {
				
				createChunkIfNotExist(chunk);
				
				int[][] extractedLayerData = createExtractedLayerData(chunk, layerData, model.getTilesX(), model.getTilesY(), model.getChunkWidth(), model.getChunkHeight());
				
				getChunk(chunk).addLayer(layerID, extractedLayerData, model.getChunkWidth(), model.getChunkHeight(), model.getTileWidth(), model.getTileHeight());
			}
		}
	}
	
	private int[][] createExtractedLayerData(final int chunkID, final int[][] layerData, final int tilesX, final int tilesY, final int chunkWidth, final int chunkHeight) {
		
		int chunkStartX = calcChunkStartPositionX(chunkID, tilesX, chunkWidth);
		int chunkStartY = calcChunkStartPositionY(chunkID, tilesY, chunkHeight);
		int chunkEndX = chunkStartX + chunkWidth;
		int chunkEndY = chunkStartY + chunkHeight;
		
		return extractLayerData(layerData, chunkStartX, chunkStartY, chunkEndX, chunkEndY);
	}
	
	private int calcNumberOfChunks(ChunkSystemCreatorModel model) {
		return (model.getTilesX() / model.getChunkWidth()) * (model.getTilesY() / model.getChunkHeight());
	}
	
	private Chunk getChunk(final int chunkID) {
		return chunks.stream().filter(e -> e.getChunkID() == chunkID).findAny().get();
	}
	
	private void createChunkIfNotExist(final int chunkID) {
		final List<Chunk> chunkList = chunks.stream().filter(e -> e.getChunkID() == chunkID).collect(Collectors.toList());
		
		if (chunkList == null || chunkList.size() <= 0) {
			chunks.add(new Chunk(chunkID));
		} 
	}
	
	private int calcChunkStartPositionX(final int chunkID, final int tilesX, final int chunkWidth) {
		final int chunkX = tilesX / chunkWidth;
		final int x = chunkID % chunkX;
		return x * chunkWidth;
	}
	
	private int calcChunkStartPositionY(final int chunkID, final int tilesY, final int chunkHeight) {
		final int chunkY = tilesY / chunkHeight;
		final int y = chunkID / chunkY;
		return y * chunkHeight;
	}
	
	private int[][] extractLayerData(final int[][] layerData, final int startX, final int startY, final int endX, final int endY) {
		int[][] subLayer = create2DArray(startX, startY, endX, endY);

		for (int x = startX; x < endX; x++) {
			for (int y = startY; y < endY; y++) {
				subLayer[x - startX][y - startY] = layerData[x][y];
			}
		}
		
		return subLayer;
	}
	
	private int getArrayLenghtByIntervall(final int intervallStart, final int intervallEnd) {
		return (intervallEnd - intervallStart);
	}
	
	private int[][] create2DArray(final int intervallStartX, final int intervallStartY, final int intervallEndX, final int intervallEndY) {
		final int arrayDimX = getArrayLenghtByIntervall(intervallStartX, intervallEndX);
		final int arrayDimY = getArrayLenghtByIntervall(intervallStartY, intervallEndY);
		return new int[arrayDimX][arrayDimY];
	}
	
	public ChunkSystem createChunkSystem() {
		final Map<Integer, TilemapManager> chunkSystemData = new HashMap<Integer, TilemapManager>();
		
		chunks.stream().forEach(c -> chunkSystemData.put(c.getChunkID(), c.createTilemapManager()));
		
		return new ChunkSystem(chunkSystemData, model.getObjectLayerID(), INITIAL_CHUNKID);
	}
	
//	public static int[][] convertToIntArray(final Collection<String> layerDataCollection, final int width, final int height) {
//
//		int[][] result = layerDataCollection
//				.stream()
//		        .filter(s -> s.trim().length() > 0)
//		        .map(Integer::parseInt)
//		        .map(i -> new int[]{i})
//		        .toArray(int[][]::new);
//		
//		return result;
//	}
}
