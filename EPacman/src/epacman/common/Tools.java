package epacman.common;

import epacman.statesmachine.StatesManager;
import java.awt.Canvas;
import javax.swing.JFrame;

/**
 *
 * @author ErickSteven
 */
public class Tools {

    public static void changeWindowSize(final Canvas frame) {
        StatesManager.changeState(Constants.STATE_PAUSE);
        Variables.boardWidth = frame.getWidth();
        Variables.boardHeight = frame.getHeight() - Variables.spriteRenderHeight;
        Variables.spriteRenderHeight = Variables.boardHeight / Constants.BOARD_HEIGHT;
        Variables.spriteRenderWidth = Variables.spriteRenderHeight;
        //Variables.spriteRenderWidth = Variables.boardWidth / Constants.BOARD_WIDTH;
        StatesManager.changeState(Constants.STATE_GAME);
    }
}
