package de.voland.game.gamestate;

import java.awt.Graphics;

public abstract class GameState {

	protected GameStateManager gameStateManager;
	
	public GameState(GameStateManager gameStateManager) {
		this.gameStateManager = gameStateManager;
	}

    public abstract void update(float timeSinceLastFrame);
    
    public abstract void render(Graphics g);
    
    public abstract void enter();
    
    public abstract void exit();
}
