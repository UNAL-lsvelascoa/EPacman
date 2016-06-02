package epacman.maps;

import epacman.BoardMatrix;
import epacman.Constants;
import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Transparency;

/**
 *
 * @author ErickVelasco
 */
public class Board {

    private final SpritesSheet classicBoard = new SpritesSheet("/media/sprites/ClassicBoard.png", 32, 32, Transparency.TRANSLUCENT);

    public void update() {

    }

    public void paint(final Graphics g) {
        for (int i = 0; i < Constants.boardAlto; i++) {
            for (int j = 0; j < Constants.boardAncho; j++) {
                g.drawImage(classicBoard.getSprite(BoardMatrix.classicBoardSprites[j + (i * Constants.boardAncho)]).getImagen(), j * 32, i * 32, null);
            }
        }
    }

}
