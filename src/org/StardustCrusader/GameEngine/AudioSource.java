package org.StardustCrusader.GameEngine;

import java.io.File;
import java.io.InputStream;

public class AudioSource extends GameBehavior {
	public boolean isLoop = false;
	// AudioFormat AF = null;
	// SourceDataLine SDL = null;
	// DataLine.Info DLI = null;
	// AudioInputStream AIS = null;
	File file;
	InputStream IS;

	public void Start() {

	}

	public void LoadFile(File file) throws Exception {
		// AIS = AudioSystem.getAudioInputStream(file);
		// AF = AIS.getFormat();
		// DLI = new DataLine.Info(SourceDataLine.class, AF);
		// SDL = (SourceDataLine) AudioSystem.getLine(DLI);
		this.file = file;
		player = new AudioPlayer(file);
	}

	public void LoadStream(InputStream S) throws Exception {
		// AIS = AudioSystem.getAudioInputStream(file);
		// AF = AIS.getFormat();
		// DLI = new DataLine.Info(SourceDataLine.class, AF);
		// SDL = (SourceDataLine) AudioSystem.getLine(DLI);
		this.IS = S;
		player = new AudioPlayer(IS);
	}

	public boolean StopRequired = false;
	AudioPlayer player;

	public void Stop() {
		player.stop();
	}

	public void SetVolume(float value) {
		player.SetVolume(value);
	}

	public void Play() throws Exception {

		player.play(isLoop);
	}
}
