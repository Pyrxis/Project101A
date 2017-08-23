package main.core.entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Entity {

	protected int x_pos;
	protected int y_pos;
	public boolean dead;
	protected BufferedImage img;

	protected int hp;

	public int getX() {
		return x_pos;
	}

	public int getY() {
		return y_pos;
	}

	public boolean dead() {
		return dead;
	}

	public BufferedImage getImage() {
		return img;
	}

	public void subtractHP(int damage) {
		hp -= damage;
	}

	public int getHP() {
		return hp;
	}

	public abstract void update(ArrayList<Boolean> collision, ArrayList<Entity> entities, int width);

	protected int conv2D(int x, int y, int width) {
		return (y * width) + x;
	}
}
