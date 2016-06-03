package epacman.characters;

import epacman.BoardMatrix;
import epacman.Constants;
import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Transparency;

/**
 *
 * @author ErickVelasco
 */
public class SpecialFood implements Character {

    private final SpritesSheet classicBoard = new SpritesSheet("/media/sprites/Sprites.png", 32, 32, Transparency.TRANSLUCENT);
    private int xPixel;
    private int yPixel;
    private int counterAnimation = 0;
    private int currentIndexSprite = 4;
    private boolean animateOrder = false;

    public SpecialFood() {

    }

    public SpecialFood(int xPixel, int yPixel) {
        this.xPixel = xPixel;
        this.yPixel = yPixel;
    }

    @Override
    public void update() {
        if (counterAnimation == animationDuration) {
            if (currentIndexSprite == 4 + quantitySprites - 1) {
                animateOrder = false;
            } else if (currentIndexSprite == 4) {
                animateOrder = true;
            }
            if (animateOrder) {
                currentIndexSprite++;
            } else {
                currentIndexSprite--;
            }
            counterAnimation = 4;
        } else {
            counterAnimation++;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(classicBoard.getSprite(currentIndexSprite).getImagen(), xPixel, yPixel, null);
    }

    public void setxPixel(int xPixel) {
        this.xPixel = xPixel;
    }

    public void setyPixel(int yPixel) {
        this.yPixel = yPixel;
    }

}
