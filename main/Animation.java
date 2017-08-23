package main.core.entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import main.util.Utility;

public class Animation {

	private HashMap<String, ArrayList<BufferedImage>> frames = new HashMap<String, ArrayList<BufferedImage>>();
	private HashMap<String, Integer> frame_index = new HashMap<String, Integer>();

	// Count starts from 1 for name_pattern
	public void createSequence(String name, String url, String name_pattern, int n) {
		ArrayList<BufferedImage> buffer = new ArrayList<BufferedImage>();

		if (n != 0) {
			for (int i = 1; i <= n; i++) {
				buffer.add(Utility.im.load(url + "/" + name_pattern + i));
			}
		} else {
			buffer.add(Utility.im.load(url + "/" + name_pattern));
		}

		frames.put(name, buffer);
		frame_index.put(name, 0);
	}

	public BufferedImage getSequenceNext(String name) {
		int buffer_size = frames.get(name).size();
		int index = frame_index.get(name);

		BufferedImage img = frames.get(name).get(index);
		index = (index + 1) % buffer_size;
		frame_index.put(name, index);

		return img;
	}
}
