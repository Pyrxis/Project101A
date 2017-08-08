package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import main.core.GameManager;

public class Menu {

	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 500, 350, 300, 50);
	public Rectangle optionButton = new Rectangle(Game.WIDTH / 2 + 500, 450, 300, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 500, 550, 300, 50);
	public Rectangle playButton1 = new Rectangle(Game.WIDTH / 2 + 500, 350, 310, 60);
	public Rectangle optionButton1 = new Rectangle(Game.WIDTH / 2 + 500, 450, 310, 60);
	public Rectangle quitButton1 = new Rectangle(Game.WIDTH / 2 + 500, 550, 310, 60);
	public static int indx = 3;

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (GameManager.getKeyPressed(KeyEvent.VK_A)) {
			System.out.println("a");
		}
		Font fnt0 = new Font("Archeologicaps", Font.BOLD, 100);
		Font fnt1 = new Font("Archeologicaps", Font.ITALIC, 25);
		Font fnt2 = new Font("Arial", Font.BOLD, 25);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Chaotic Legacy", Game.WIDTH / 2 + 125, 150);

		g.setFont(fnt1);
		Color color1 = Color.cyan;
		Color color = Color.LIGHT_GRAY;
		g.setColor(color);
		g.drawString("The Last of Tempus", Game.WIDTH / 2 + 500, 200);

		if (indx == 1) {
			g.setColor(color1);
			g2d.draw(playButton);// 1
			g.setColor(color);
			g2d.draw(quitButton);// 2
			g2d.draw(optionButton);// 3
			g.setColor(color);
		}

		if (indx == 3) {
			g.setColor(color);
			g2d.draw(playButton);// 1
			g.setColor(color1);
			g2d.draw(quitButton);// 2
			g.setColor(color);
			g2d.draw(optionButton);// 3
			g.setColor(color);
		}

		if (indx == 2) {
			g.setColor(color);
			g2d.draw(playButton);// 1

			g2d.draw(quitButton);// 2
			g.setColor(color1);
			g2d.draw(optionButton);// 3
			g.setColor(color);
		}

		g.setFont(fnt2);
		g.setColor(color);
		g.drawString("Load Game", Game.WIDTH / 2 + 590, 383);
		g.drawString("New Game", Game.WIDTH / 2 + 590, 483);
		g.drawString("  Options", Game.WIDTH / 2 + 590, 583);

	}

	public static void stateGame() {
		indx = 1;
	}
}
