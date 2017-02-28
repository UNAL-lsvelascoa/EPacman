package epacman.sounds;

import epacman.common.Constants;
import epacman.common.ResourcesLoader;
import epacman.common.Variables;
import epacman.statesmachine.StatesManager;
import epacman.statesmachine.states.game.DataManager;
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
    private final String uri;
    private boolean inLoop = false;

    public Sound(final String uri) {
        this.uri = uri;
        initSound();
    }

    public void play() {
        initSound();
        sound.addLineListener(this);
        sound.start();
    }

    public void playInLoop() {
        initSound();
        inLoop = true;
        sound.loop(Integer.MAX_VALUE);
        sound.addLineListener(this);
        sound.start();
    }

    public void stop() {
        sound.stop();
    }

    public void close() {
        inLoop = false;
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
            switch (uri) {
                case Constants.URI_CLASSIC_SOUND_INIT_GAME:
                    StatesManager.changeState(Constants.STATE_GAMING);
                    break;
                case Constants.URI_CLASSIC_SOUND_DIE:
            {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    if(DataManager.getLives().isEmpty()){
                        StatesManager.changeState((Constants.STATE_INITIAL_MENU));
                    }else{
                        StatesManager.changeState(Constants.STATE_INIT_GAME);
                    }
                    break;
            }
            if (inLoop) {
                playInLoop();
            } else {
                sound.close();
            }
        }
    }

}
