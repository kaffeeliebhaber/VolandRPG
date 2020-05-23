package test;

import de.voland.game.chunksystem.ChunkSystem;
import de.voland.game.tiledEditor.ChunkSystemCreator;
import de.voland.game.tiledEditor.ChunkSystemCreatorModel;
import de.voland.game.tiledEditor.TiledEditorMapLoader;

public class TiledEditorTestClass {

	public static void main(String[] args) {
		
		final ChunkSystemCreatorModel model = new TestDefaultChunkSystemCreatorModel();
//		final ChunkSystemCreatorModel model = new TiledEditorMapLoader("src/de/voland/game/assets/tiledEditor/scene2.xml");
		final ChunkSystemCreator creator = new ChunkSystemCreator(model);
		final ChunkSystem chunkSystem = creator.createChunkSystem();
		
		chunkSystem.print();
		
	}
}
