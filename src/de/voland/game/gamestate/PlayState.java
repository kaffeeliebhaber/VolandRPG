package de.voland.game.gamestate;

import de.voland.game.chunksystem.ChunkSystem;
import de.voland.game.core.Camera;
import de.voland.game.gfx.Spritesheet;
import de.voland.game.tiledEditor.ChunkSystemCreator;
import de.voland.game.tiledEditor.ChunkSystemCreatorModel;
import de.voland.game.tiledEditor.TiledEditorMapLoader;

public class PlayState extends GameState {

	private final ChunkSystem chunkSystem;
	private final Camera camera;
	private final Spritesheet spritesheet;
	
	public PlayState(GameStateManager gameStateManager) {
		super(gameStateManager);
		
		final int tileSize = 32;
		spritesheet = new Spritesheet("src/de/voland/game/assets/tileset/BaseChip_pipo.png", tileSize, tileSize);
		spritesheet.load();
		
		final ChunkSystemCreatorModel model = new TiledEditorMapLoader("src/de/voland/game/assets/tiledEditor/scene2.xml");
		final ChunkSystemCreator creator = new ChunkSystemCreator(model, spritesheet);
		chunkSystem = creator.createChunkSystem();
		
		// CREATE CAMERA
		camera = new Camera(0, 0, 800, 600);
	}

	@Override
	public void update(float timeSinceLastFrame) {
		chunkSystem.update(timeSinceLastFrame);
	}

	@Override
	public void render(java.awt.Graphics g) {
		chunkSystem.render(g, camera);
	}

	@Override
	public void enter() {
		System.out.println("(PlayState) | WAS ENTERED");
	}

	@Override
	public void exit() {
		System.out.println("(PlayState) | EXIT");
	}

}
