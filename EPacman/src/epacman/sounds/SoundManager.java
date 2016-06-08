package epacman.sounds;

import epacman.common.Constants;

/**
 *
 * @author ErickSteven
 */
public class SoundManager {

    private static final Sound backgroundNormal = new Sound(Constants.URI_CLASSIC_SOUND_BACKGROUND_NORMAL);
    private static final Sound backgroundSpecial = new Sound(Constants.URI_CLASSIC_SOUND_BACKGROUND_SPECIAL);
    private static final Sound backgroundToHome = new Sound(Constants.URI_CLASSIC_SOUND_BACKGROUND_TO_HOME);

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

    public static void changeBackground(final int typeBack) {
        switch (typeBack) {
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
