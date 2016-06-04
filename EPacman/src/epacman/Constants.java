package epacman;

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

}
