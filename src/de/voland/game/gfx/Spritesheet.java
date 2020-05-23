package de.voland.game.gfx;

import java.awt.image.BufferedImage;

public class Spritesheet {
	
	private int tileWidth;
	private int tileHeight;
	
	private BufferedImage spritesheet;
	
	private BufferedImage[][] subImages;
	
	private int rows;
	private int cols;
	
	public Spritesheet(final String path, int tileWidth, int tileHeight)  {
		this(ImageLoader.loadImage(path), tileWidth, tileHeight);
	}
	
	public Spritesheet(final BufferedImage spritesheet, int tileWidth, int tileHeight) {
		this.spritesheet = spritesheet;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		
		this.rows = spritesheet.getHeight() / tileHeight;
		this.cols = spritesheet.getWidth() / tileWidth;
		
		subImages = new BufferedImage[rows][cols];
		
	}
	
	public void load() {
		if (spritesheet == null) {
			return;
		}

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				subImages[y][x] = spritesheet.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
			}
		}
	}

	public BufferedImage getImageByIndex(int index) {
		return getImageAt(index / cols, index % cols);
	}
	
	public BufferedImage getImageAt(int row, int col) {
		
		try {
			return subImages[row][col];
		} catch (ArrayIndexOutOfBoundsException e) {
			//System.out.println("row: " + row + ", col: " + col);
		}
		return null;
	}
	
	public BufferedImage[] getImages(int row) {
		return subImages[row];
	}
	
	public BufferedImage getSpritesheet() {
		return spritesheet;
	}
}
