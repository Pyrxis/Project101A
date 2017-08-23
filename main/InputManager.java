package main.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;
import main.Game.STATE;

public class InputManager implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (Game.state == Game.STATE.MENU) {
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_DOWN:// menu scrolling
				Game.downpressed = Boolean.valueOf(true);
				GameManager.setKeyPressed(arg0.getKeyCode(), true);
				break;
			case KeyEvent.VK_UP:
				Game.uppressed = Boolean.valueOf(true);
				GameManager.setKeyPressed(arg0.getKeyCode(), true);
				break;
			case KeyEvent.VK_ENTER:
				GameManager.setKeyPressed(arg0.getKeyCode(), true);
			}
		}
		if (Game.state == Game.STATE.GAME) {
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_J:// hit
			case KeyEvent.VK_K:// menu
			case KeyEvent.VK_L:// menu
			case KeyEvent.VK_W:// up
			case KeyEvent.VK_A:// left
			case KeyEvent.VK_S:// down(not used currently)
			case KeyEvent.VK_D:// right
			case KeyEvent.VK_ESCAPE:// menu
			case KeyEvent.VK_T:// chat(Text)
				GameManager.setKeyPressed(arg0.getKeyCode(), true);
			}
		}
		if (Game.state == Game.STATE.TEXT) {
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_SLASH:
			case KeyEvent.VK_ENTER:
			case KeyEvent.VK_BACK_SPACE:
			case KeyEvent.VK_0:
			case KeyEvent.VK_1:
			case KeyEvent.VK_2:
			case KeyEvent.VK_3:
			case KeyEvent.VK_4:
			case KeyEvent.VK_5:
			case KeyEvent.VK_6:
			case KeyEvent.VK_7:
			case KeyEvent.VK_8:
			case KeyEvent.VK_9:
			case KeyEvent.VK_A:
			case KeyEvent.VK_B:
			case KeyEvent.VK_C:
			case KeyEvent.VK_D:
			case KeyEvent.VK_E:
			case KeyEvent.VK_F:
			case KeyEvent.VK_G:
			case KeyEvent.VK_H:
			case KeyEvent.VK_I:
			case KeyEvent.VK_J:
			case KeyEvent.VK_K:
			case KeyEvent.VK_L:
			case KeyEvent.VK_M:
			case KeyEvent.VK_N:
			case KeyEvent.VK_O:
			case KeyEvent.VK_P:
			case KeyEvent.VK_Q:
			case KeyEvent.VK_R:
			case KeyEvent.VK_S:
			case KeyEvent.VK_T:
			case KeyEvent.VK_U:
			case KeyEvent.VK_V:
			case KeyEvent.VK_W:
			case KeyEvent.VK_Y:
			case KeyEvent.VK_X:
			case KeyEvent.VK_Z:
			case KeyEvent.VK_SPACE:
				GameManager.setKeyPressed(arg0.getKeyCode(), true);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		if (Game.state == Game.STATE.MENU) {
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_DOWN:// menu scrolling
				Game.downpressed = Boolean.valueOf(false);
				GameManager.setKeyPressed(arg0.getKeyCode(), false);
				break;
			case KeyEvent.VK_UP:
				Game.uppressed = Boolean.valueOf(false);
				GameManager.setKeyPressed(arg0.getKeyCode(), false);
				break;
			case KeyEvent.VK_ENTER:
				GameManager.setKeyPressed(arg0.getKeyCode(), false);
			}
		} else {
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_J:
			case KeyEvent.VK_K:
			case KeyEvent.VK_L:
			case KeyEvent.VK_W:
			case KeyEvent.VK_A:
			case KeyEvent.VK_S:
			case KeyEvent.VK_D:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_UP:
			case KeyEvent.VK_SHIFT:
			case KeyEvent.VK_ENTER:
			case KeyEvent.VK_ESCAPE:
			case KeyEvent.VK_SLASH:
			case KeyEvent.VK_0:
			case KeyEvent.VK_1:
			case KeyEvent.VK_2:
			case KeyEvent.VK_3:
			case KeyEvent.VK_4:
			case KeyEvent.VK_5:
			case KeyEvent.VK_6:
			case KeyEvent.VK_7:
			case KeyEvent.VK_8:
			case KeyEvent.VK_9:
			case KeyEvent.VK_B:
			case KeyEvent.VK_C:
			case KeyEvent.VK_E:
			case KeyEvent.VK_F:
			case KeyEvent.VK_G:
			case KeyEvent.VK_H:
			case KeyEvent.VK_I:
			case KeyEvent.VK_M:
			case KeyEvent.VK_N:
			case KeyEvent.VK_O:
			case KeyEvent.VK_P:
			case KeyEvent.VK_Q:
			case KeyEvent.VK_R:
			case KeyEvent.VK_T:
			case KeyEvent.VK_U:
			case KeyEvent.VK_V:
			case KeyEvent.VK_Y:
			case KeyEvent.VK_X:
			case KeyEvent.VK_Z:
			case KeyEvent.VK_SPACE:
			case KeyEvent.VK_BACK_SPACE:
				GameManager.setKeyPressed(arg0.getKeyCode(), false);
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
