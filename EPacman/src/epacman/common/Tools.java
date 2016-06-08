package epacman.common;

import epacman.sounds.SoundManager;
import epacman.statesmachine.states.game.CharactersManager;
import javax.swing.JFrame;

/**
 *
 * @author ErickSteven
 */
public class Tools {

    public static void changeWindowSize(final JFrame frame) {
        Variables.boardWidth = frame.getWidth();
        Variables.boardHeight = frame.getHeight();
        Variables.spriteRenderHeight = Variables.boardHeight / Constants.BOARD_HEIGHT;
        Variables.spriteRenderWidth = Variables.spriteRenderHeight;
    }

}
