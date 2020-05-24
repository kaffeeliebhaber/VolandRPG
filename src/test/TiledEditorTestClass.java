package test;

import de.voland.game.chunksystem.ChunkSystem;
import de.voland.game.gfx.Spritesheet;
import de.voland.game.tiledEditor.ChunkSystemCreator;
import de.voland.game.tiledEditor.ChunkSystemCreatorModel;
import de.voland.game.tiledEditor.TiledEditorMapLoader;

public class TiledEditorTestClass {

	public static void main(String[] args) {
		
		final int tileSize = 32;
		final Spritesheet spritesheet = new Spritesheet("src/de/voland/game/assets/tileset/BaseChip_pipo.png", tileSize, tileSize);
		final ChunkSystemCreatorModel model = new TiledEditorMapLoader("src/de/voland/game/assets/tiledEditor/scene2.xml");
		final ChunkSystemCreator creator = new ChunkSystemCreator(model, spritesheet);
		
		@SuppressWarnings("unused")
		final ChunkSystem chunkSystem = creator.createChunkSystem();
	}
}
