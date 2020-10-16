/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LazyThief.utils;

import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Resources {

    public enum GeneralImages {

        ICON("/resources/images/icons/stickIcon.png"),
        STICK("/resources/images/stick/1.png"),
        BLACKBASE("/resources/images/bloccoNero.png"),
        LAZY("/resources/images/animation/l2.png"),
        LAZY1("/resources/images/animation/l1.png"),
        BULLET("/resources/images/animation/bullet-removebg.png"),
        TERRAIN("/resources/images/animation/blo.png"),
        DIAMANTE("/resources/images/animation/fire/ballrm.png"),
        MENU("/resources/images/menu.png"),
        RANKING("/resources/images/ranking.png"),
        MUSICOFF("/resources/images/musicOff.png"),
        MUSICON("/resources/images/musicOn.png"),
        S1("/resources/images/s1.jpg"),
        S2("/resources/images/s2.jpg"),
        S3("/resources/images/s3.jpg");

        private Image img = null;

        GeneralImages(String fileSrc) {
            URL imgSrc = this.getClass().getResource(fileSrc);
            img = new Image(imgSrc.toString());
        }

        public Image getImage() {
            return this.img;
        }

    }

    public enum Music {
        SOUNDTRACK("/resources/sounds/stickmanArchery.mp3");
        private boolean musicEnabled;
        private MediaPlayer audioMedia;
        private final static double MUSIC_VOLUME = 0.25;

        Music(String fileSrc) {
            URL url = this.getClass().getResource(fileSrc);
            audioMedia = new MediaPlayer(new Media(url.toString()));
            audioMedia.setCycleCount(MediaPlayer.INDEFINITE);
            this.musicEnabled = true;
        }

        public void play() {
            if (this.musicEnabled) {
                this.audioMedia.setVolume(MUSIC_VOLUME);
                this.audioMedia.play();
            } else {
                this.audioMedia.stop();
            }
        }

        public void stop() {
            this.audioMedia.stop();
        }

        public void toggleMusicEnabled() {
            this.musicEnabled = !musicEnabled;
        }

    }

    public enum SoundEffects {
        CANNON("/resources/sounds/arrowFlyng.wav"),
        BOUNCE("/resources/sounds/bounce.wav"),
        READYFIGHT("/resources/sounds/startfight.wav"),
        COIN("/resources/sounds/Coin.wav");

        private boolean soundEnabled;
        private AudioClip media;

        SoundEffects(String fileSrc) {
            URL url = this.getClass().getResource(fileSrc);
            media = new AudioClip(url.toString());
            this.soundEnabled = true;
        }

        public void play() {
            if (this.soundEnabled) {
                media.play();
            }
        }

        public void toggleSoundEnabled() {
            this.soundEnabled = !soundEnabled;
        }
    }

    public enum stickmanAnimation {

        LAZY("/resources/images/animation/l2.png"),
        LAZY1("/resources/images/animation/l1.png"),
        LAZY2("/resources/images/animation/l1.png"),
        LAZY3("/resources/images/animation/l1.png"),
        LAZY4("/resources/images/animation/l1.png"),
        LAZY5("/resources/images/animation/l2.png");

        private Image img = null;

        stickmanAnimation(String fileSrc) {
            URL imgSrc = this.getClass().getResource(fileSrc);
            img = new Image(imgSrc.toString());

        }

        public Image getImage() {
            return this.img;
        }
    }

    public enum fireAnimation {

        STONE1("/resources/images/animation/fire/stone2.png"),
        STONE2("/resources/images/animation/fire/v1.png"),
        STONE3("/resources/images/animation/fire/v2.png");

        private Image img = null;

        fireAnimation(String fileSrc) {
            URL imgSrc = this.getClass().getResource(fileSrc);
            img = new Image(imgSrc.toString());

        }

        public Image getImage() {
            return this.img;
        }
    }

}
