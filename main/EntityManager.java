package main.core.entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import main.Game;
import main.ImageLoader;
import main.core.entity.enemies.BigSlime;
import main.core.entity.enemies.Portal;
import main.core.entity.enemies.Slime;

public class EntityManager {

	private static LinkedList<Entity> entities_ = new LinkedList<Entity>();
	private static ArrayList<Entity> entities = new ArrayList<Entity>();
	private static Player player;
	private static ImageLoader il = new ImageLoader();
	public static boolean dead = false;
	public static boolean init = true;
	public static boolean change = false;
	
	public static void clearEntity(){
			for(int i =1; i< entities.size();i++){
				entities.remove(i);
				entities_.remove(i);
			}
	}
	public static void clearEntityex(){
		for(int i =1; i< entities.size();i++){
			entities.remove(i);
			entities_.remove(i);
		}reset();
}

	public static void addEntity(int x, int y, BufferedImage img, String type) {
		switch (type) {
		case "slime":
			Slime slime = new Slime(x, y, il.load("entity/slime1/s1"));
			entities_.add(slime);
			break;
		case "bigslime":
			BigSlime bslime = new BigSlime(x, y, il.load("entity/slime2/b1"));
			entities_.add(bslime);
			break;
		case "player":
			reset();
			break;
		default:
			break;
		}
	}

	public void update(ArrayList<Boolean> collision, int width) {
		boolean death_flag = false;

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update(collision, entities, width);
		}

		for (int i = 0; i < entities_.size(); i++) {
			if (entities_.get(i).getHP() <= 0) {
				
				death_flag = true;
				if(i==0&&death_flag){
					dead = Boolean.valueOf(true);
					Game.state=Game.STATE.DEATH;
				}
				entities_.remove(i);
				i--;
			}
		}

		if (death_flag) {
			ArrayList<Entity> new_entities = new ArrayList<Entity>();
			for (int i = 0; i < entities_.size(); i++) {
				new_entities.add(entities_.get(i));
			}

			entities = new_entities;
		}

	}

	public ArrayList<Entity> updateBuffer() {
		return entities;
	}
	public static void reset(){
		player = new Player(2, 300, il.load("Player/test files/playerright"));
		entities.clear();
		entities.add(player);
		
		for(int i = 0; i < entities_.size(); i++){
			entities.add(entities_.get(i));
		}
		entities_.clear();
		for(int i =0; i < entities.size();i++){
			entities_.add(entities.get(i));
		}
		
	}
	

	public static void damageEntity(int i, int damage) {
		entities_.get(i).subtractHP(damage);

	}

}
