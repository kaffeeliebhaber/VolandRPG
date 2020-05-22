package de.voland.game.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import de.voland.game.gamestate.GameStateManager;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -955735214025952380L;

	// FRAME
	private JFrame frame;
	private String title;
	public static int screenWidth; // TODO: Müssen diese 'static' sein?
	public static int screenHeight;

	private static final int TARGET_FPS = 60;

	// DEBUG
	public static boolean debug = true;

	// THREAD
	private Thread thread;
	private boolean isRunning;

	// GAMESTATEMANAGER
	private GameStateManager gameStateManager;

	public Game(final int screenWidth, final int screenHeight) {

		Game.screenWidth = screenWidth;
		Game.screenHeight = screenHeight;

		setPreferredSize(new Dimension(Game.screenWidth, Game.screenHeight));
		setMinimumSize(new Dimension(Game.screenWidth, Game.screenHeight));
		setMaximumSize(new Dimension(Game.screenWidth, Game.screenHeight));

//		final Keyboard keyboard = new Keyboard();
//
//		addKeyListener(keyboard);
//		// addMouseListener(keyboard);
//		addKeyListener(KeyManager.instance);
//		addMouseListener(MouseManager.instance);
//		addMouseMotionListener(MouseManager.instance);

		setFocusable(true);
		requestFocus();
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		title = frame.getTitle();
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	public synchronized void update(float timeSinceLastFrame) {
		gameStateManager.update(timeSinceLastFrame);
	}

	public synchronized void draw(Graphics g) {
		if (!hasFocus()) {
			renderFocus(g);
		} else {
			gameStateManager.render(g);
		}
	}

	/**
	 * Method is called, if the main window losed his focus.
	 * 
	 * @param g
	 */
	private void renderFocus(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 24));
		g.drawString("Click to focus", 400, 300);
	}

	public void init() {
		gameStateManager = new GameStateManager();
//		gameStateManager.add("PLAY", new PlayState(gameStateManager));
//		gameStateManager.change("PLAY");
	}

	public synchronized void stopp() {
		try {
			thread.join();
			isRunning = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * render - method. Called each core game loop tick.
	 */
	public void render() {

		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(2);
			return;
		}

		final Graphics g = bs.getDrawGraphics();

		// DRAWING STUFF
		this.setupGraphicsObj(g);
		draw(g);

		g.dispose();
		bs.show();
	}

	public void setupGraphicsObj(final Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	}

	/**
	 * Runs the core game loop
	 */
	public void run() {

		long lastLoopTime = System.nanoTime();
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
		int fps = 0;
		double lastFpsTime = 0;

		init();

		// keep looping round til the game ends
		while (isRunning) {
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			float delta = (float) updateLength / ((float) OPTIMAL_TIME);

			lastFpsTime += updateLength;

			fps++;

			if (lastFpsTime >= 1000000000) {
				if (frame != null && debug) {
					frame.setTitle(title + " | FPS: " + fps);
				}
				lastFpsTime = 0;
				fps = 0;
			}

			// update the game logic
			update(delta);

			// draw everyting
			render();

			try {
				Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
			} catch (Exception e) {
			}
		}

	}
}
