package audio;

import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {

	private static HashMap<String, Clip> clips = new HashMap<String, Clip>();;

	public static void load(String s, String n) {
		if (clips.get(n) != null)
			return;
		Clip clip;
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(AudioPlayer.class.getResourceAsStream(s));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
					baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
			clips.put(n, clip);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void play(String s) {
		Clip c = clips.get(s);
		if (c.isRunning())
			c.stop();
		while (!c.isRunning())
			c.start();
		
	}
	
	public static void loop(String s){
		stop(s);
		clips.get(s).loop(Clip.LOOP_CONTINUOUSLY);
	}

	public static void stop(String s) {
		if (clips.get(s) == null)
			return;
		if (clips.get(s).isRunning())
			clips.get(s).stop();
	}

	public static void close(String s) {
		stop(s);
		clips.get(s).close();
	}
}
