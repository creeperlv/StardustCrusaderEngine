package org.StardustCrusader.GameEngine;

import java.io.*;
import javax.sound.sampled.*;

public class AudioPlayer {

    private Clip clip;
    private File file;
    private InputStream IS;
    boolean usingStream = false;

    public AudioPlayer(File file) {
        this.file = file;
    }

    public AudioPlayer(InputStream s) {
        usingStream = true;
        this.IS = s;
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }

    protected void open() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clip = AudioSystem.getClip();
        // clip.addLineListener(new LineListener() {
        // @Override
        // public void update(LineEvent event) {
        // System.out.println(event.getFramePosition());
        // if (event.getType().equals(LineEvent.Type.STOP)) {
        // }
        // }
        // });
        if (usingStream) {
            clip.open(AudioSystem.getAudioInputStream(IS));

        } else {

            clip.open(AudioSystem.getAudioInputStream((file)));

        }
        opend = true;
    }

    FloatControl control;

    public void SetVolume(float value) {
        control.setValue(value);
    }

    boolean opend = false;

    public void play() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        if (clip == null || !clip.isRunning()) {
            if (opend == false)
                open();
            control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void play(boolean willLoop) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        if (clip == null || !clip.isRunning()) {
            if (opend == false)
                open();
            control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.setFramePosition(0);
            clip.start();
            if (willLoop)
                clip.loop(Integer.MAX_VALUE);
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.flush();
            // dispose();
        }
    }

    public void dispose() {
        try {
            clip.close();
        } finally {
            clip = null;
        }
    }

}