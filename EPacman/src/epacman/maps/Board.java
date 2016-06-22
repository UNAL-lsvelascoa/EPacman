package epacman.maps;

import epacman.common.BoardMatrix;
import epacman.common.Constants;
import epacman.common.Variables;
import epacman.sounds.Sound;
import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Transparency;

/**
 *
 * @author ErickVelasco
 */
public class Board {

    private final SpritesSheet classicBoard = new SpritesSheet("/media/sprites/ClassicBoard.png", Constants.SPRITE_HEIGHT/2, Constants.SPRITE_WIDTH/2, Transparency.TRANSLUCENT);

    public void update() {

    }

    public void paint(final Graphics g) {
        for (int i = 0; i < Constants.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Constants.BOARD_WIDTH; j++) {
                g.drawImage(classicBoard.getSprite(BoardMatrix.CLASSIC_BOARD_SPRITES[j + (i * Constants.BOARD_WIDTH)]).getImagen(),
                        (j * Variables.spriteRenderWidth)+Variables.marginLeft, i * Variables.spriteRenderHeight,
                        Variables.spriteRenderWidth, Variables.spriteRenderHeight, null);
            }
        }
    }

}
