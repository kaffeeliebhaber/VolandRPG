package de.voland.game.gamestate;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

public class GameStateManager {

    private final Map<String, GameState> gameStates;
    private GameState currentGameState;

    public GameStateManager() {
    	gameStates = new HashMap<>();
        currentGameState = new EmptyState(this);
        gameStates.put(null, currentGameState);
    }

    public void add(String name, GameState gameState) {
    	gameStates.put(name, gameState);
    }

    public void change(String name) {
    	currentGameState.exit();
    	currentGameState = gameStates.get(name);
    	currentGameState.enter();
    }

    public void update(float timeSinceLastFrame) {
    	currentGameState.update(timeSinceLastFrame);
    }

    public void render(Graphics g) {
    	currentGameState.render(g);
    }

    public void enter() {
    	currentGameState.enter();
    }

    public void exit() {
    	currentGameState.exit();
    }
}
