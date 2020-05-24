package de.voland.game.tiledEditor;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.voland.game.collision.BoundingBox2D;

public class TiledEditorMapLoader implements ChunkSystemCreatorModel {

	private final String filePath;
	private final BoundingBox2DMapper boundingBox2DMapper;
	private final LayerDataMapper layerDataMapper;
	private int tileWidth;
	private int tileHeight;
	private int tilesX;
	private int tilesY;
	private int chunkWidth;
	private int chunkHeight;
	private int objectLayerID;
	
	private final int TILED_ID_DELTA = 1;
	
	public TiledEditorMapLoader(final String filePath) {
		this.filePath = filePath;
		
		boundingBox2DMapper = new BoundingBox2DMapper();
		layerDataMapper = new LayerDataMapper();
		
		load();
	}
	
	private void load() {
		
		final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		Document document = null;
		
		long startTime = System.currentTimeMillis();
		
		try {
			
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(filePath);
			document.getDocumentElement().normalize();

			final Element elementTagMap = document.getDocumentElement();
			final Element elementTagEditorSettings = (Element) elementTagMap.getElementsByTagName("editorsettings").item(0);
			final Element elementTagProperties = (Element) elementTagMap.getElementsByTagName("properties").item(0);
			final NodeList nodeListTagTileset = elementTagMap.getElementsByTagName("tile");
			final NodeList nodeListTagLayer = document.getElementsByTagName("layer");
			
			loadMapSettings(elementTagMap);
			loadEditorSettings(elementTagEditorSettings);
			loadCustomerSettings(elementTagProperties);
			loadBoundingBoxes(nodeListTagTileset);
			loadLayers(nodeListTagLayer);
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("LOADING TIME: " + (endTime - startTime));
	}
	
	/**
	 * Loads necessary data from the <map> XML node.
	 * @param mapSettings
	 */
	private void loadMapSettings(final Element mapSettings) {
		tileWidth = Integer.parseInt(mapSettings.getAttribute("tilewidth"));
		tileHeight = Integer.parseInt(mapSettings.getAttribute("tileheight"));
		tilesX = Integer.parseInt(mapSettings.getAttribute("width"));
		tilesY = Integer.parseInt(mapSettings.getAttribute("height"));
	}
	
	/**
	 * Loads necessary data from the <editorsettings> XML node.
	 * @param elementTagEditorSettings
	 */
	private void loadEditorSettings(final Element elementTagEditorSettings) {
		
		Element elementTagChunksize = (Element) elementTagEditorSettings.getElementsByTagName("chunksize").item(0);
		
		chunkWidth = Integer.parseInt(elementTagChunksize.getAttribute("width"));
		chunkHeight = Integer.parseInt(elementTagChunksize.getAttribute("height"));
	}
	
	/**
	 * Loads necessary data from the <properties> XML node.
	 * @param elementTagProperties
	 */
	private void loadCustomerSettings(final Element elementTagProperties) {
		Element elementTagProperty = (Element) elementTagProperties.getElementsByTagName("property").item(0);
		objectLayerID = Integer.parseInt(elementTagProperty.getAttribute("value"));
	}
	
	/**
	 * Loads the necessary data from the <layer> XML node.
	 * @param nodeListTagLayer
	 */
	private void loadLayers(final NodeList nodeListTagLayer) {
		
		final int layers = nodeListTagLayer.getLength();
	
		Element elementTagLayer;
		
		for (int layer = 0; layer < layers; layer++) {
			elementTagLayer = (Element) nodeListTagLayer.item(layer);
			String currentLayerData = elementTagLayer.getElementsByTagName("data").item(0).getTextContent();
			final int layerID = Integer.parseInt(elementTagLayer.getAttribute("id"));
			String[] splittedLayerData = currentLayerData.split(",");
			int[][] convertedLayerData = convertLayerData(splittedLayerData, tilesX, tilesY);
			layerDataMapper.put(layerID, convertedLayerData);
		}
	}
	
	private int[][] convertLayerData(final String[] currentLayerData, final int width, final int height) {
		int[][] data = new int[width][height];
		
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height; row++) {
				
				final int converted1DIndex = convert2DDimInto2DIndex(col, row, width);
				final String rawCellData = replaceEmptySpace(currentLayerData[converted1DIndex]);
				final int parseIntRawCellData = Integer.parseInt(rawCellData) - TILED_ID_DELTA; // TODO: WRITE OWN TRANSFORMER CLASS FOR THIS!!!
				setData(data, col, row, parseIntRawCellData);
			}
		}
		
		return data;
	}
	
	private int convert2DDimInto2DIndex(final int col, final int row, final int width) {
		return col + row * width;
	}
	
	private String replaceEmptySpace(String currentLayerData) {
		return currentLayerData.replaceAll("\\s+", "");
	}
	
	private void setData(int[][] data, int col, int row, int cellValue) {
		data[col][row] = cellValue;
	}
	
	/**
	 * Loads the 
	 * @param nodeListTagTileset
	 */
	private void loadBoundingBoxes(final NodeList nodeListTagTileset) {
		
		final int elements = nodeListTagTileset.getLength();
		
		for (int index = 0; index < elements; index++) {
			final Element elementTagTile = (Element) nodeListTagTileset.item(index);
			loadBoundingBox(elementTagTile);
		}
	}
	
	/**
	 * Loads the specific bounding box.
	 * @param elementTagTile
	 */
	private void loadBoundingBox(final Element elementTagTile) {
		
		final NodeList nodeListTagObject = elementTagTile.getElementsByTagName("object");
		
		final int objects = nodeListTagObject.getLength();
		final int tileID = Integer.parseInt(elementTagTile.getAttribute("id"));
		
		for (int index = 0; index < objects; index++) {
			Element elementTagTileObject = (Element) nodeListTagObject.item(index);
			insertBoundingBox(tileID, createBoundingBox(elementTagTileObject));
		}
	}
	
	/**
	 * Creates the specific bounding box object.
	 * @param elementTileTagObject
	 * @return
	 */
	private BoundingBox2D createBoundingBox(final Element elementTileTagObject) {
		return new BoundingBox2D(
				Float.parseFloat(elementTileTagObject.getAttribute("x")), 
				Float.parseFloat(elementTileTagObject.getAttribute("y")), 
				(int) Float.parseFloat(elementTileTagObject.getAttribute("width")), 
				(int) Float.parseFloat(elementTileTagObject.getAttribute("height")));
	}
	
	/**
	 * Inserts the given bounding box into the collection.
	 * @param tileID
	 * @param boundingBox2D
	 */
	private void insertBoundingBox(final int tileID, final BoundingBox2D boundingBox2D) {
		boundingBox2DMapper.put(tileID, boundingBox2D);
	}

	@Override
	public LayerDataMapper getLayerDataMapper() {
		return layerDataMapper;
	}

	@Override
	public BoundingBox2DMapper getBoundingBox2DMapper() {
		return boundingBox2DMapper;
	}

	@Override
	public int getTileWidth() {
		return tileWidth;
	}

	@Override
	public int getTileHeight() {
		return tileHeight;
	}

	@Override
	public int getTilesX() {
		return tilesX;
	}

	@Override
	public int getTilesY() {
		return tilesY;
	}

	@Override
	public int getChunkWidth() {
		return chunkWidth;
	}

	@Override
	public int getChunkHeight() {
		return chunkHeight;
	}

	@Override
	public int getObjectLayerID() {
		return objectLayerID;
	}
}
