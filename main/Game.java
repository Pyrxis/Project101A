package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.core.GameManager;
import main.core.InputManager;
import main.core.MapManager;
import main.core.MouseInput;
import main.core.entity.EntityManager;
import main.util.GraphicSet;
import main.util.Utility;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -1997325596863821774L;
	private boolean running;

	private Thread gameThread;
	private JFrame frame = new JFrame("Project_101A");

	private GameManager gm;
	private InputManager input = new InputManager();
	private RenderData gfxBuffer = new RenderData();
	private Menu menu;
	private int gamewidth = MapManager.getSpriteWidth();
	private int viewport = Utility.WIDTH;
	int offsetMinX = 0;
	private int camX;
	private boolean dieng = false;

	public enum STATE {
		MENU, GAME, OPTION, DEATH, NEWGAME, TEXT,
	};

	public enum MAP {
		M1, M2
	};

	public static boolean uppressed = false;
	public static boolean downpressed = false;
	public static STATE state = STATE.MENU;
	public static MAP map = MAP.M1;
	// private boolean renderInit = false;
	public static int test = 0;
	public static ImageLoader il = new ImageLoader();

	public synchronized void start() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace(); // DEBUG
		}
	}

	public void init() {
		menu = new Menu();
		this.addKeyListener(input);
		this.addMouseListener(new MouseInput());
		gm = new GameManager();

		// basic frame setup
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Utility.WIDTH, Utility.HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);

		JPanel pane = new JPanel();
		frame.add(pane);
		frame.add(this);
		EntityManager.addEntity(32, 0, il.load("p/walk cycle/reset"), "player");
	}

	public void run() {
		init();

		double one_tick = 1_000_000_000 / 60D;
		long last = System.nanoTime();
		double delta = 0;

		long counter = System.currentTimeMillis();
		int ups = 0, fps = 0;
		// frames per second and update per second

		while (running) {
			long now = System.nanoTime();

			delta += (now - last) / one_tick;
			last = now;

			if (delta >= 1) {
				update();
				delta--;
				ups++;
			}
			render();
			fps++;

			if (System.currentTimeMillis() - counter >= 1000) {
				frame.setTitle("Project_101A | UPS: " + ups + " | FPS: " + fps);// title
				ups = 0;
				fps = 0;
				counter = System.currentTimeMillis();
			}
		}
	}

	public void camera() {
		if (state == STATE.GAME && state != STATE.DEATH) {
			int targetX =  gfxBuffer.getEntities()[0].getX() - Utility.WIDTH / 2;
			camX += (targetX-camX)*0.03;
		
		}
	}

	public void update() {
		// Update the buffer here
		camera();
		gm.update();
		gfxBuffer = gm.createBuffer();
	}

	public static void statereset() {
		state = STATE.GAME;
	}

	public void render() {
		if (state == STATE.DEATH) {
			dieng = Boolean.valueOf(true);
			System.out.println("PLAYER HAS DIED!!");
			map = MAP.M2;
			EntityManager.change = Boolean.valueOf(true);
			GameManager.updatemap();
			state = STATE.GAME;
		}
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		if (state == STATE.TEXT) {
			g.setColor(Color.gray);
			g.fillRect(0, 0, Utility.WIDTH, Utility.HEIGHT);
			Text.render(g);
		}
		if (state == STATE.MENU) {
			
			g.setColor(Color.black);
			g.fillRect(0, 0, Utility.WIDTH, Utility.HEIGHT);
			menu.render(g);
			
		
	
		}
		if (state == STATE.TEXT) {
			if (GameManager.getKeyPressed(KeyEvent.VK_B)) {
				state = STATE.GAME;
			}
		}
		if (state == STATE.GAME) {
			if (!dieng) {
				g.translate(-camX, 0);
			}
			if (GameManager.getKeyPressed(KeyEvent.VK_T)) {
				state = STATE.TEXT;
			}
			if (GameManager.getKeyPressed(KeyEvent.VK_ESCAPE)) {
				state = STATE.MENU;
			}
	
			g.drawImage(GraphicSet.skyBox, 0, 0, Utility.WIDTH, Utility.HEIGHT, null);

			for (int i = 0; i < gfxBuffer.getX() * gfxBuffer.getY(); i++) {
				if (gfxBuffer.getMap().get(i) != null) {
					int x = i % gfxBuffer.getX();
					int y = i / gfxBuffer.getX();
					int ssize = Utility.SPRITE_SIZE;

					g.drawImage(gfxBuffer.getMap().get(i), x * ssize-100, y * ssize, null);
				}
			}
			Font fnt0 = new Font("Arial", Font.BOLD, 15);
			g.setFont(fnt0);
			if (!dieng) {
				g.drawString(
						"Player x = " + gfxBuffer.getEntities()[0].getX() + "Player y = "
								+ gfxBuffer.getEntities()[0].getY() + " Health = " + gfxBuffer.getEntities()[0].getHP(),
						camX+550, 15);
			}
			if (gfxBuffer.hasEntities()) {// drawing entities
				for (int i = 0; i < gfxBuffer.getEntities().length; i++) {
					if (gfxBuffer.getEntities()[i] != null)
						g.drawImage(gfxBuffer.getEntities()[i].getImage(), gfxBuffer.getEntities()[i].getX(),
								gfxBuffer.getEntities()[i].getY(), null);
				}

			}

		}

		g.dispose();
		bs.show();
	}

	public static void main(String args[]) {

		Game game = new Game();
		game.start();

	}



}
