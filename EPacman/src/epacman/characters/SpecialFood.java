package epacman.characters;

import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Transparency;

/**
 *
 * @author ErickVelasco
 */
public class SpecialFood extends Food implements Entity {

    private final SpritesSheet classicBoard = new SpritesSheet("/media/sprites/Sprites.png", 32, 32, Transparency.TRANSLUCENT);

    public SpecialFood(int xPixel, int yPixel) {
        super(xPixel, yPixel);
        currentIndexSprite = 4;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(classicBoard.getSprite(currentIndexSprite).getImagen(), pixel.x, pixel.y, null);
    }

}
