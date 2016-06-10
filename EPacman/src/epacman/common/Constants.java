package epacman.common;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author ErickSteven
 */
public interface Constants {

    int RIGHT = 0;
    int DOWN = 1;
    int LEFT = 2;
    int UP = 3;
    int DIE = 4;
    int BOARD_HEIGHT = 31;
    int BOARD_WIDTH = 28;
    int SPRITE_HEIGHT = 64;
    int SPRITE_WIDTH = 64;
    Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    int SCREEN_WIDTH = (int) SCREEN_SIZE.getWidth();
    int SCREEN_HEIGHT = (int) SCREEN_SIZE.getHeight();
    
    int STATE_GAME = 0;
    int STATE_PAUSE = 1;
    
    String URI_CLASSIC_SOUND_EAT_FOOD = "src/media/audio/ClassicEatFood.wav";
    String URI_CLASSIC_SOUND_EAT_SPECIAL_FOOD = "src/media/audio/ClassicEatSpecialFood.wav";
    String URI_CLASSIC_SOUND_EAT_ENEMY = "src/media/audio/ClassicEatEnemy.wav";
    String URI_CLASSIC_SOUND_BACKGROUND_NORMAL = "src/media/audio/ClassicBackgroundNormal.wav";
    String URI_CLASSIC_SOUND_BACKGROUND_SPECIAL = "src/media/audio/ClassicBackgroundSpecial.wav";
    String URI_CLASSIC_SOUND_BACKGROUND_TO_HOME = "src/media/audio/ClassicBackgroundToHome.wav";
    String URI_CLASSIC_SOUND_DIE = "src/media/audio/ClassicDie.wav";
    
    int TYPE_BACKGROUND_NORMAL = 1;
    int TYPE_BACKGROUND_SPECIAL= 2;
    int TYPE_BACKGROUND_TO_HOME = 3;
    
    int TIME_SPECIAL = 20000;

}
