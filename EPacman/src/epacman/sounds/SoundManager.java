package epacman.sounds;

import epacman.common.Constants;

/**
 *
 * @author ErickSteven
 */
public class SoundManager {

    private final Sound backgroundNormal = new Sound(Constants.URI_CLASSIC_SOUND_BACKGROUND_NORMAL);
    private final Sound backgroundSpecial = new Sound(Constants.URI_CLASSIC_SOUND_BACKGROUND_SPECIAL);
    private final Sound backgroundToHome = new Sound(Constants.URI_CLASSIC_SOUND_BACKGROUND_TO_HOME);

    public void playBackground(final int typeBackground) {
        switch (typeBackground) {
            case Constants.TYPE_BACKGROUND_NORMAL:
                backgroundNormal.playInLoop();
                backgroundSpecial.close();
                backgroundToHome.close();
                break;
            case Constants.TYPE_BACKGROUND_SPECIAL:
                backgroundSpecial.playInLoop();
                backgroundNormal.close();
                backgroundToHome.close();
                break;
            case Constants.TYPE_BACKGROUND_TO_HOME:
                backgroundToHome.playInLoop();
                backgroundNormal.close();
                backgroundSpecial.close();
                break;
            default:
                break;
        }
    }
}
