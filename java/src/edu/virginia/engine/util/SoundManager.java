package edu.virginia.engine.util;

import javax.sound.sampled.*;
import java.util.HashMap;
import java.io.*;
import java.net.*;

public class SoundManager {
    private HashMap <String, Clip> soundeffects;
    private Clip music;

    //constructor


    public void LoadSoundEffect(String id, String filename) {
        try {
            File file = new File(filename);
            if(file.exists()){
                URL url = this.getClass().getClassLoader().getResource(filename);
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                this.soundeffects.put(id, clip);
                System.out.println("SUCCESS");
            } else {
                System.out.println("ERROR: FILE DOES NOT EXIST");
            }

//        if (file.exists()) {
//            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioIn);
//            this.soundeffects.put(id, clip);
//            System.out.println("SUCCESS");
//        } else {
//            System.out.println("ERROR: FILE DOES NOT EXIST");
//        }
         /*
         URL url = this.getClass().getClassLoader().getResource("gameover.wav");

         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
          */
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("\nUnsupported Audio");
        } catch (IOException ex) {
            System.out.println("\nIO");
        } catch (LineUnavailableException ex) {
            System.out.println("\nLineUnavailable");
        }
    }

    public void PlaySoundEffect(String id) {
        if(this.soundeffects.get(id)!= null) {
            this.soundeffects.get(id).start();
        } else {
            System.out.println("NULL SOUND EFFECT");
        }
    }

    public void LoadMusic(String id, String filename){
        try {
            File soundFile = new File(filename);
            if(soundFile.exists()) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                this.music = AudioSystem.getClip();
                this.music.open(audioIn);
            } else {
                System.out.println("ERROR: FILE DOES NOT EXIST");
            }
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
