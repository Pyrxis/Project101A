package main.core.entity;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.core.GameManager;
import main.core.MapManager;
import main.util.Utility;
import audio.AudioPlayer;
import java.util.Timer;
import java.util.TimerTask;
public class Player extends Entity {

	private int v = 0;
	private int indx = 0;
	private int dir = 0;
	private int hitindx = 0;
	private boolean isIdle = false;
	private Animation sprites = new Animation();

	private AudioPlayer soundeffect;
	
	public Player(int xpos, int ypos, BufferedImage img) {
		x_pos = xpos;
		y_pos = ypos;
		this.img = img;
		hp = 10;
		
		AudioPlayer.load("/SFX/movement/kick.wav", "playerattack");

		// Player Sprite
		String url = "Player/test files";
		sprites.createSequence("left", url, "playerleft", 0);
		sprites.createSequence("right", url, "playerright", 0);
		url = "Player/walk cycle";
		sprites.createSequence("run", url, "run", 4);
		sprites.createSequence("runL", url, "runL", 4);
		sprites.createSequence("reset", url, "reset", 0);
		sprites.createSequence("hit", url, "hit", 0);
		sprites.createSequence("hitL", url, "hitL", 0);
		url = "Player/new walk";
		sprites.createSequence("nrun", url, "f", 8);

	}
	

	
	public void death() {
		System.out.println("Dead");
	}

	public void update(ArrayList<Boolean> collision, ArrayList<Entity> entities, int width) {

		if (GameManager.noKeysPressed() && !isIdle) {
			if (dir == 0) {
				img = sprites.getSequenceNext("nrun");
			} else {
				img = sprites.getSequenceNext("left");
			}

			isIdle = true;
		}
		int ssize = (int) (Utility.SPRITE_SIZE/1.7);
		int arr_x = x_pos / Utility.SPRITE_SIZE;

		int arr_yb = (y_pos + ssize) / Utility.SPRITE_SIZE;

		// Gravity
		if (collision.get(conv2D(arr_x, arr_yb, width))) {
			v = 0;
		} else {
			if (v < 10) {
				v++;
			}
		}

		int xvelocity = 5;
		if (GameManager.getKeyPressed(KeyEvent.VK_A)) {
			dir = 1;
			if (x_pos > 1) {
				if (indx == 0) {
					img = sprites.getSequenceNext("runL");
				}
				indx = (indx + 1) % Utility.UPDATE_PER_FRAME;
				x_pos -= xvelocity; // moving left
				isIdle = false;
			}
		} else if (GameManager.getKeyPressed(KeyEvent.VK_D)) {// if pressed
																// right
			dir = 0;
			
			if (x_pos < Utility.SPRITE_SIZE*MapManager.getWidth()-240) {
				if (indx == 0) {
					img = sprites.getSequenceNext("nrun");
				}
				indx = (indx + 1) % Utility.UPDATE_PER_FRAME;

				x_pos += xvelocity;// moving right
				isIdle = false;
			}
		} else if (GameManager.getKeyPressed(KeyEvent.VK_J)) {
			hitindx++;
			if (dir == 0) {
				
				img = sprites.getSequenceNext("hit");
				
				for (int i = 1; i < entities.size(); i++) {
					int monx = entities.get(i).x_pos;
					int mony = entities.get(i).y_pos;
					int delta_x = monx - x_pos;
					int delta_y = mony - y_pos;
					if ((delta_x >= 5) && (delta_x <= 50)) {
						EntityManager.damageEntity(i, 1);
					
					}
				}
			}
			if (dir == 1) {

				img = sprites.getSequenceNext("hitL");
				for (int i = 1; i < entities.size(); i++) {
					int monx = entities.get(i).x_pos;
					int mony = entities.get(i).y_pos;
					int delta_x = monx - x_pos;
					int delta_y = mony - y_pos;
					if ((delta_x <= -5) && (delta_x >= -50)) {
						EntityManager.damageEntity(i, 1);
					}
				}
			}
			isIdle = false;
		}

		if (GameManager.getKeyPressed(KeyEvent.VK_W)) {
			if (collision.get(conv2D(arr_x, arr_yb, width))) {
				v = -15;
			}
			isIdle = false;
		}

		y_pos += v;
	}

}
