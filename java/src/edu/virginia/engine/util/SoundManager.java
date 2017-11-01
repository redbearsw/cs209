package edu.virginia.engine.util;

import javax.sound.sampled.*;
import java.util.HashMap;
import java.io.File;
import java.io.*;

public class SoundManager {
    private String id;
    private String filename;
    private HashMap <String, Clip> soundeffects;
    private Clip music;

    //constructor

    //getters and setters for id and filename
    public String getId() {
        return this.id;
    }

    public void setId(String i) {
        this.id = i;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String fn) {
        this.filename = fn;
    }


    public void LoadSoundEffect(String id, String filename) {
        try {
        File file = new File(filename);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        this.soundeffects.put(id, clip);
        } catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public void PlaySoundEffect(String id) {
        this.soundeffects.get(id).start();
    }

    public void LoadMusic(String id, String filename){
        try {
            File soundFile = new File(filename);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            this.music = AudioSystem.getClip();
            this.music.open(audioIn);
        }catch (UnsupportedAudioFileException ex) {
        ex.printStackTrace();
    } catch (IOException ex) {
        ex.printStackTrace();
    } catch (LineUnavailableException ex) {
        ex.printStackTrace();
    }

    }
    public void PlayMusic(String id){
        this.music.loop(Clip.LOOP_CONTINUOUSLY);
    }


}
