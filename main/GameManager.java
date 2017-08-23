package main.core;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import main.Game;
import main.RenderData;
import main.core.entity.EntityManager;

public class GameManager {
	
	//Hashmap of boolean, of key pressed
	private static HashMap<Integer, Boolean> keyPressed = new HashMap<Integer, Boolean>();
	
	private static EntityManager em = new EntityManager();
	private static MapManager mm = new MapManager();
	
	public GameManager() {
		//resets the keys
		setKeyPressed(KeyEvent.VK_J, false);
		setKeyPressed(KeyEvent.VK_K, false);
		setKeyPressed(KeyEvent.VK_L, false);
		setKeyPressed(KeyEvent.VK_W, false);
		setKeyPressed(KeyEvent.VK_A, false);
		setKeyPressed(KeyEvent.VK_S, false);
		setKeyPressed(KeyEvent.VK_D, false);
		setKeyPressed(KeyEvent.VK_DOWN,false);
		setKeyPressed(KeyEvent.VK_UP,false);
		setKeyPressed(KeyEvent.VK_SHIFT,false);
		setKeyPressed(KeyEvent.VK_ESCAPE,false);
		setKeyPressed(KeyEvent.VK_ENTER,false);
		setKeyPressed(KeyEvent.VK_T,false);
		setKeyPressed(KeyEvent.VK_0,false);
		setKeyPressed(KeyEvent.VK_1,false);
		setKeyPressed(KeyEvent.VK_2,false);
		setKeyPressed(KeyEvent.VK_3,false);
		setKeyPressed(KeyEvent.VK_4,false);
		setKeyPressed(KeyEvent.VK_5,false);
		setKeyPressed(KeyEvent.VK_6,false);
		setKeyPressed(KeyEvent.VK_7,false);
		setKeyPressed(KeyEvent.VK_8,false);
		setKeyPressed(KeyEvent.VK_9,false);
		setKeyPressed(KeyEvent.VK_B,false);
		setKeyPressed(KeyEvent.VK_C,false);
		setKeyPressed(KeyEvent.VK_E,false);
		setKeyPressed(KeyEvent.VK_F,false);
		setKeyPressed(KeyEvent.VK_G,false);
		setKeyPressed(KeyEvent.VK_H,false);
		setKeyPressed(KeyEvent.VK_I,false);
		setKeyPressed(KeyEvent.VK_M,false);
		setKeyPressed(KeyEvent.VK_N,false);
		setKeyPressed(KeyEvent.VK_O,false);
		setKeyPressed(KeyEvent.VK_P,false);
		setKeyPressed(KeyEvent.VK_Q,false);
		setKeyPressed(KeyEvent.VK_R,false);
		setKeyPressed(KeyEvent.VK_V,false);
		setKeyPressed(KeyEvent.VK_U,false);
		setKeyPressed(KeyEvent.VK_W,false);
		setKeyPressed(KeyEvent.VK_X,false);
		setKeyPressed(KeyEvent.VK_Y,false);
		setKeyPressed(KeyEvent.VK_Z,false);
		setKeyPressed(KeyEvent.VK_SLASH,false);
		setKeyPressed(KeyEvent.VK_SPACE,false);
		setKeyPressed(KeyEvent.VK_BACK_SPACE,false);
		
		
		mm.update("res/Map/map1.txt");
	}
	
	public void update() {
		em.update(mm.getCollision(), mm.getWidth());
		//sets the map to map1
		
	}
	 public static void updatemap(int x){
		 
		switch(x){
		case 1:
			mm.update("res/Map/map1.txt");
		case 2:
			mm.update("res/Map/map3.txt");
		}
	}
	
	public RenderData createBuffer() {
		// Change it up next session
		RenderData rd = mm.createRD();
		rd.setEntities(em.updateBuffer());
		
		return rd;
	}
	
	public static void setKeyPressed(int e, boolean b) {
		keyPressed.put(e, b);
	}
	
	public static boolean getKeyPressed(int e) {
		return keyPressed.get(e);
	}
	
	public static boolean noKeysPressed() {
		Boolean[] keys = new Boolean[keyPressed.size()];
		keyPressed.values().toArray(keys);
		
		for(int i = 0; i < keyPressed.size(); i++) {
			if(keys[i]) {
				return false;
			}
		}
		return true;
	}
}
