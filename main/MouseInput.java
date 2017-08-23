package main.core;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Game;
import main.Menu;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {

		int mx = e.getX();
		int my = e.getY();
		/*
		 * public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 500,
		 * 350, 300, 50); public Rectangle optionButton = new
		 * Rectangle(Game.WIDTH / 2 + 500, 450, 300, 50); public Rectangle
		 * quitButton = new Rectangle(Game.WIDTH / 2 + 500, 550, 300, 50);
		 */
		if (Game.state == Game.STATE.MENU) {
			if (mx >= Game.WIDTH / 2 + 500 && mx <= Game.WIDTH / 2 + 800) {
				if (my >= 350 && my <= 400) {
					Menu.stateGame();

					Game.state = Game.STATE.GAME;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
