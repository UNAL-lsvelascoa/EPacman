package epacman.sounds;

import epacman.common.Constants;

/**
 *
 * @author ErickSteven
 */
public class SoundManager {

    private static final Sound eatFoodSound = new Sound(Constants.URI_CLASSIC_SOUND_EAT_FOOD);
    private static final Sound eatSpecialFoodSound = new Sound(Constants.URI_CLASSIC_SOUND_EAT_SPECIAL_FOOD);
    private static final Sound backgroundNormal = new Sound(Constants.URI_CLASSIC_SOUND_BACKGROUND_NORMAL);
    private static final Sound backgroundSpecial = new Sound(Constants.URI_CLASSIC_SOUND_BACKGROUND_SPECIAL);
    private static final Sound backgroundToHome = new Sound(Constants.URI_CLASSIC_SOUND_BACKGROUND_TO_HOME);
    private static final Sound die = new Sound(Constants.URI_CLASSIC_SOUND_DIE);

    public static void playEat(boolean special) {
        if (special) {
            eatSpecialFoodSound.play();
        } else {
            eatFoodSound.playInLoop();
        }
    }

    public static void stopEat() {
        eatFoodSound.close();
    }

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

    public void playDie() {
        die.play();
    }

    public void closeAll() {
        backgroundNormal.close();
        backgroundSpecial.close();
        backgroundToHome.close();
        die.close();
    }
}
