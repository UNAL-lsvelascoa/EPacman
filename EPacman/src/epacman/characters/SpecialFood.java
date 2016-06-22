package epacman.characters;

import epacman.common.Constants;
import epacman.common.Variables;
import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Transparency;

/**
 *
 * @author ErickVelasco
 */
public class SpecialFood extends Food implements Entity {

    private final SpritesSheet classicBoard = new SpritesSheet("/media/sprites/ClassicFood.png", Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT, Transparency.TRANSLUCENT);

    public SpecialFood(int xPixel, int yPixel) {
        super(xPixel, yPixel);
        initialSprite = 8;
        currentIndexSprite = 8;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(classicBoard.getSprite(currentIndexSprite).getImagen(), pixel.x + Variables.marginLeft, pixel.y, Variables.spriteRenderWidth, Variables.spriteRenderHeight, null);
    }

}
