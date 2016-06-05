package epacman.sounds;

import epacman.common.Constants;

/**
 *
 * @author ErickSteven
 */
public class SoundManager {
    
    private final Sound backgroundNormal = new Sound(Constants.URI_CLASSIC_SOUND_BACKGROUND_NORMAL);
    
    public void playBackground(){
        backgroundNormal.playInLoop();
    }
    
}
