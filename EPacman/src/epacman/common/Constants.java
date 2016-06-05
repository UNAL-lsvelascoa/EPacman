package epacman.common;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author ErickSteven
 */
public interface Constants {

    int LEFT = 0;
    int UP = 1;
    int RIGHT = 2;
    int DOWN = 3;
    int BOARD_HEIGHT = 31;
    int BOARD_WIDTH = 28;
    int SPRITE_HEIGHT = 32;
    int SPRITE_WIDTH = 32;
    Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    int SCREEN_WIDTH = (int) SCREEN_SIZE.getWidth();
    int SCREEN_HEIGHT = (int) SCREEN_SIZE.getHeight();
    
    String URI_CLASSIC_SOUND_EAT_FOOD = "src/media/audio/ClassicEatFood.wav";
    String URI_CLASSIC_SOUND_EAT_SPECIAL_FOOD = "src/media/audio/ClassicEatSpecialFood.wav";
    String URI_CLASSIC_SOUND_BACKGROUND_NORMAL = "src/media/audio/ClassicBackgroundNormal.wav";
    String URI_CLASSIC_SOUND_BACKGROUND_SPECIAL = "src/media/audio/ClassicBackgroundSpecial.wav";
    String URI_CLASSIC_SOUND_BACKGROUND_TO_HOME = "src/media/audio/ClassicBackgroundToHome.wav";
    
    int TYPE_BACKGROUND_NORMAL = 1;
    int TYPE_BACKGROUND_SPECIAL= 2;
    int TYPE_BACKGROUND_TO_HOME = 3;

}
