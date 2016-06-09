package epacman.characters;

import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Transparency;

/**
 *
 * @author ErickVelasco
 */
public class SpecialFood extends Food implements Entity {

    private final SpritesSheet classicBoard = new SpritesSheet("/media/sprites/ClassicFood.png", 32, 32, Transparency.TRANSLUCENT);

    public SpecialFood(int xPixel, int yPixel) {
        super(xPixel, yPixel);
        initialSprite = 6;
        currentIndexSprite = 6;
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
