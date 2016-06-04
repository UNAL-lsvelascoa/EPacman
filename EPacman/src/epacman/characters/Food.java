package epacman.characters;

import epacman.Constants;
import epacman.Variables;
import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Transparency;

/**
 *
 * @author ErickVelasco
 */
public class Food implements Character {

    private final SpritesSheet classicBoard = new SpritesSheet("/media/sprites/Sprites.png", Constants.SPRITE_WIDTH, Constants.BOARD_HEIGHT, Transparency.TRANSLUCENT);
    private int xPixel;
    private int yPixel;
    private int counterAnimation = 0;
    private int currentIndexSprite = 1;
    private boolean animateOrder = false;

    public Food() {

    }

    public Food(int xPixel, int yPixel) {
        this.xPixel = xPixel;
        this.yPixel = yPixel;
    }

    @Override
    public void update() {
        if (counterAnimation == animationDuration) {
            if (currentIndexSprite == 1 + quantitySprites - 1) {
                animateOrder = false;
            } else if (currentIndexSprite == 1) {
                animateOrder = true;
            }
            if (animateOrder) {
                currentIndexSprite++;
            } else {
                currentIndexSprite--;
            }
            counterAnimation = 1;
        } else {
            counterAnimation++;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(classicBoard.getSprite(currentIndexSprite).getImagen(), xPixel, yPixel, Variables.spriteRenderWidth, Variables.spriteRenderHeight, null);

    }

    public void setxPixel(int xPixel) {
        this.xPixel = xPixel;
    }

    public void setyPixel(int yPixel) {
        this.yPixel = yPixel;
    }

}
