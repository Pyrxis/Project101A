package main.util;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import main.ImageLoader;

public class GraphicSet {
	
	private static ImageLoader im = new ImageLoader();
	
	public static BufferedImage skyBox;
	public static HashMap<String, BufferedImage> tileSet = new HashMap<String, BufferedImage>();
	
	public static void changeTileSet(int type) {
		switch(type) {
		case 0: // Grass
			tileSet.put("dirt1", im.load("en/grass"));
			break;
		case 1: //dirt
			tileSet.put("dirt", im.load("en/dirt"));
			break;
		case 2: //dirt
			tileSet.put("air", im.load("en/dirt"));
			break;
		default://Default
			tileSet.put("dirt1", im.load("en/grass"));
			break;
		}
	}
	
	public static void changeSkyBox(String name) {
		skyBox = im.load(name);//set sky box
	}

}
