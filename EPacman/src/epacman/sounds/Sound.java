package epacman.sounds;

import epacman.common.ResourcesLoader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author ErickSteven
 */
public class Sound implements LineListener {

    private static final ResourcesLoader loader = new ResourcesLoader();
    private Clip sound;
    private String uri;

    public Sound(final String uri) {
        this.uri = uri;
        initSound();
        sound.addLineListener(this);
    }

    public void play() {
        initSound();
        sound.start();
    }
    public void stop() {
        sound.stop();
    }

    private void initSound() {
        try {
            AudioInputStream audioStream = loader.loadSound(uri);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            sound = (Clip) AudioSystem.getLine(info);
            sound.open(audioStream);
        } catch (LineUnavailableException | IOException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
        if (type == LineEvent.Type.STOP) {
            sound.close();
        }
    }
}
