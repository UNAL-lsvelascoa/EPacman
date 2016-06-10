package epacman.common;

import epacman.statesmachine.StatesManager;
import javax.swing.JFrame;

/**
 *
 * @author ErickSteven
 */
public class Tools {

    public static void changeWindowSize(final JFrame frame) {
        StatesManager.changeState(Constants.STATE_PAUSE);
        Variables.boardWidth = frame.getWidth();
        Variables.boardHeight = frame.getHeight();
        Variables.spriteRenderWidth = Variables.boardWidth / Constants.BOARD_WIDTH;
        Variables.spriteRenderHeight = Variables.boardHeight / Constants.BOARD_HEIGHT;
        StatesManager.changeState(Constants.STATE_GAME);
    }

}
