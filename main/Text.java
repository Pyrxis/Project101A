package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.Game.STATE;
import main.core.GameManager;
import main.util.Utility;

public class Text {
	public static String command = "";

	public static void render(Graphics g) {

		if (GameManager.getKeyPressed(KeyEvent.VK_SLASH)) {

			command = command + "/";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_SPACE)) {

			command = command + " ";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_ESCAPE)) {
			Game.state = STATE.GAME;

		}
		if (GameManager.getKeyPressed(KeyEvent.VK_ENTER)) {
			proccomm();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_BACK_SPACE)) {
			if (command.length() != 0) {
				command = command.substring(0, command.length() - 1);
				sleep();
			}

		}
		if (GameManager.getKeyPressed(KeyEvent.VK_A)) {
			command = command + "a";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_B)) {
			command = command + "b";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_C)) {
			command = command + "c";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_D)) {
			command = command + "d";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_E)) {
			command = command + "e";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_F)) {
			command = command + "f";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_G)) {
			command = command + "g";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_H)) {
			command = command + "h";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_I)) {
			command = command + "i";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_J)) {
			command = command + "j";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_K)) {
			command = command + "k";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_L)) {
			command = command + "l";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_M)) {
			command = command + "m";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_N)) {
			command = command + "n";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_O)) {
			command = command + "o";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_P)) {
			command = command + "p";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_Q)) {
			command = command + "q";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_R)) {
			command = command + "r";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_S)) {
			command = command + "s";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_T)) {
			command = command + "t";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_U)) {
			command = command + "u";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_V)) {
			command = command + "v";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_W)) {
			command = command + "w";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_X)) {
			command = command + "x";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_Y)) {
			command = command + "y";
			sleep();
		}
		if (GameManager.getKeyPressed(KeyEvent.VK_Z)) {
			command = command + "z";
			sleep();
		}

		Font fnt0 = new Font("Arial", Font.BOLD, 20);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString(command, 125, 50);
		System.out.println(command);
	}

	private static void proccomm() {
		switch (command) {
		case "/help":
			System.out.println("/help, /kill, /stat");
			break;
		case "/kill @e":
			System.out.println("kill");
			break;
		case "/stat":
		default:
			System.out.println("type /help to see all the commands");
			break;
		}

	}

	static void sleep() {
		try {
			Thread.sleep(200);
		} catch (Exception e) {
		}
	}
}
