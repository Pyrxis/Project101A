package main.core.entity.enemies;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import main.core.entity.Animation;
import main.core.entity.Entity;
import main.core.entity.EntityManager;
import main.core.entity.Player;
import main.util.Utility;

public class Slime extends Entity {

	@SuppressWarnings("unused")
	private final int VISION_RANGE_X = 4 * Utility.SPRITE_SIZE, VISION_RANGE_Y = Utility.SPRITE_SIZE;
	private Animation sprites = new Animation();
	public FLAG_TYPE flag = FLAG_TYPE.IDLE;

	private int v = 0;
	private final int THINK_BASE = 120;
	private final int DELAY_BASE = 90;
	private final int DELAY_MOVE = 6;

	private int think = 0;
	private int dir = 0;
	private int indx = 0;
	private int indx_m = 0;
	private int indx_x = 0;
	private int damagex = 0;

	public Slime(int x, int y, BufferedImage img) {
		this.x_pos = x;
		this.y_pos = y;
		this.img = img;
		this.hp = 30;
		sprites.createSequence("s", "entity/slime1", "s", 15);

	}

	@SuppressWarnings("unused")
	@Override
	public void update(ArrayList<Boolean> collision, ArrayList<Entity> entities, int width) {
		int player_x = entities.get(0).getX();
		int player_y = entities.get(0).getY();
		if (indx_x == 0) {
			img = sprites.getSequenceNext("s");
		}
		indx_x++;
		Random rand1 = new Random();
		int v1 = rand1.nextInt(2);
		indx_x = (indx_x + v1 + 1) % ((Utility.UPDATE_PER_FRAME + 3));

		switch (flag) {
		case IDLE:
			/*
			 * if (!EntityManager.dead) { if (((player_x <= x_pos + 200) &&
			 * (player_x >= x_pos)) || ((player_x >= x_pos - 200) && (player_x
			 * <= x_pos))) { flag = FLAG_TYPE.ENGAGING; } } else { flag =
			 * FLAG_TYPE.IDLE; }
			 */
			if (think == 0) {
				Random rand = new Random();
				think = THINK_BASE + rand.nextInt(DELAY_BASE + 1);
				dir = rand.nextInt(3);

			}
			indx++;
			if ((indx / think) == 1) {
				indx = 0;
				think = 0;
			}
			break;
		case RUNNING:

			break;
		case ENGAGING:
			if (!EntityManager.dead) {
				if (player_x == x_pos || (player_x >= x_pos - 10 && player_x <= x_pos + 10)) {
					damagex++;
					if (damagex % 20 == 0) {
						EntityManager.damageEntity(0, 1);
						damagex = 0;
					}
				}
				if (player_x <= x_pos + 200 && (player_x >= x_pos)) {
					dir = 1;
				} else if (player_x >= x_pos - 200 && player_x <= x_pos) {
					dir = 0;
				} else {
					flag = FLAG_TYPE.IDLE;
				}
				break;

			}
			if (EntityManager.dead) {
				flag = FLAG_TYPE.IDLE;
			}


		}
		move(collision, dir, width);
		grav(collision, width);
	}

	private void grav(ArrayList<Boolean> collision, int width) {
		int ssize = Utility.SPRITE_SIZE - 10;
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

		y_pos += v;
	}

	@SuppressWarnings("unused")
	private void move(ArrayList<Boolean> collision, int dir, int width) {
		int xvelocity = 2;
		int mov = indx_m % DELAY_MOVE;
		if (Utility.UPDATE_PER_FRAME > 1000)
			xvelocity = 0;

		switch (dir) {
		case 0:
			if (x_pos > (Utility.SPRITE_SIZE / 2) - Utility.SPRITE_SIZE / 2 + xvelocity - 1) {
				if (mov == 0)
					x_pos -= xvelocity;

			}
			break;
		case 1:
			if (x_pos < (Utility.WIDTH) - Utility.SPRITE_SIZE - xvelocity) {
				if (mov == 0)
					x_pos += xvelocity;
			}
			break;
		default:
			break;
		}

		indx_m++;
	}
}
