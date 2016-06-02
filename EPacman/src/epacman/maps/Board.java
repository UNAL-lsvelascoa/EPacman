package epacman.maps;

import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Transparency;

/**
 *
 * @author ErickVelasco
 */
public class Board {

    private final SpritesSheet classicBoard = new SpritesSheet("/media/sprites/ClassicBoard.png", 32, 32, Transparency.TRANSLUCENT);
    private final int[] map = {1, 1, 1, 1};
    private final int mapAlto = 2;
    private final int mapAncho = 2;

    public void update() {

    }

    public void paint(final Graphics g) {
        for (int i = 0; i < mapAlto; i++) {
            for (int j = 0; j < mapAncho; j++) {
                g.drawImage(classicBoard.getSprite(map[i * j + mapAncho]).getImagen(), j * 32, i * 32, null);
            }
        }
    }

}
