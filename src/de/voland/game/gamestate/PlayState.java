package de.voland.game.gamestate;

import java.awt.Color;
import java.awt.Graphics;

public class PlayState extends GameState {

	public PlayState(GameStateManager gameStateManager) {
		super(gameStateManager);
	}

	@Override
	public void update(float timeSinceLastFrame) {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 800);
	}

	@Override
	public void enter() {
		
	}

	@Override
	public void exit() {
		
	}

}
