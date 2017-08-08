package main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.core.entity.Entity;

public class RenderData {

	private ArrayList<BufferedImage> map;
	private int x, y;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private boolean hasEntities = false;

	public void setMap(ArrayList<BufferedImage> map, int x, int y) {
		this.map = map;
		this.x = x;
		this.y = y;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
		hasEntities = true;
	}

	public ArrayList<BufferedImage> getMap() {
		return map;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public Entity[] getEntities() {
		Entity[] e = new Entity[entities.size()];
		return entities.toArray(e);
	}
	
	public boolean hasEntities() {
		return hasEntities;
	}
}
