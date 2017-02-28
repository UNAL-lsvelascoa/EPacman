package epacman.common;

import epacman.graphics.Window;
import epacman.statesmachine.StatesManager;

/**
 *
 * @author ErickSteven
 */
public class Tools {

    public static void changeWindowSize(final Window frame) {
        StatesManager.changeState(Constants.STATE_PAUSE);
        Variables.boardWidth = frame.getCanvas().getWidth();
        Variables.boardHeight = frame.getCanvas().getHeight();// - Variables.spriteRenderHeight * 3;
        Variables.spriteRenderHeight = Variables.boardHeight / Constants.BOARD_HEIGHT;
        Variables.spriteRenderWidth = Variables.spriteRenderHeight;
        Variables.windowHeight = frame.getHeight();
        Variables.windowWidth = frame.getWidth();
        Variables.marginLeft = (Variables.windowWidth / 2) - (Variables.boardWidth / 2);
        //Variables.spriteRenderWidth = Variables.boardWidth / Constants.BOARD_WIDTH;
        StatesManager.changeState(Constants.STATE_INIT_GAME);
    }
}
