package main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.core.GameManager;
import main.core.InputManager;
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

	//private boolean renderInit = false;

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
		this.addKeyListener(input);
		gm = new GameManager();

		//basic frame setup
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Utility.WIDTH, Utility.HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);

		JPanel pane = new JPanel();
		frame.add(pane);
		frame.add(this);
	}

	public void run() {
		init();

		double one_tick = 1_000_000_000 / 60D;
		long last = System.nanoTime();
		double delta = 0;

		long counter = System.currentTimeMillis();
		int ups = 0, fps = 0;
		//frams per second and update per second

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
				frame.setTitle("Project_101A | UPS: " + ups + " | FPS: " + fps);//title
				ups = 0;
				fps = 0;
				counter = System.currentTimeMillis();
			}
		}
	}

	public void update() {
		// Update the buffer here
		gm.update();
		gfxBuffer = gm.createBuffer();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.fillRect(0, 0, Utility.WIDTH, Utility.HEIGHT);
		g.drawImage(GraphicSet.skyBox, 0, 0, Utility.WIDTH, Utility.HEIGHT, null);//skybox (background)
		// Just shut up and render what's in the buffer
		// Don't question it
		for(int i = 0; i < gfxBuffer.getX() * gfxBuffer.getY(); i++) {
			if(gfxBuffer.getMap().get(i) != null) {
				int x = i % gfxBuffer.getX();
				int y = i / gfxBuffer.getX();
				int ssize = Utility.SPRITE_SIZE;
				
				g.drawImage(gfxBuffer.getMap().get(i), x * ssize, y * ssize, null);
			}
		}

		if (gfxBuffer.hasEntities()) {//drawing entities
			for (int i = 0; i < gfxBuffer.getEntities().length; i++) {
				if (gfxBuffer.getEntities()[i] != null)
					g.drawImage(gfxBuffer.getEntities()[i].getImage(), gfxBuffer.getEntities()[i].getX(),
							gfxBuffer.getEntities()[i].getY(), null);
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
