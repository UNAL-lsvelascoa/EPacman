package epacman.characters;

import epacman.common.Constants;
import epacman.common.Variables;
import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Transparency;

/**
 *
 * @author ErickVelasco
 */
public class Food extends Character implements Entity {

    private final SpritesSheet classicBoard = new SpritesSheet("/media/sprites/Sprites.png", Constants.SPRITE_WIDTH, Constants.BOARD_HEIGHT, Transparency.TRANSLUCENT);

    public Food(int xPixel, int yPixel) {
        this.xPixel = xPixel;
        this.yPixel = yPixel;
        currentIndexSprite = 1;
        this.limitSize = 2;
        this.limit = new Rectangle(xPixel, yPixel, limitSize, limitSize);
        this.center = new Point(xPixel-(limitSize/2), yPixel-(limitSize/2));
    }

    @Override
    public void update() {
        if (counterAnimation == ANIMATION_DURATION) {
            if (currentIndexSprite == 1 + QUANTITY_SPRITES - 1) {
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
        super.paint(g);
        g.drawImage(classicBoard.getSprite(currentIndexSprite).getImagen(), xPixel, yPixel, Variables.spriteRenderWidth, Variables.spriteRenderHeight, null);
        g.drawRect(limit.x, limit.y, limit.width, limit.height);
        if (!FOODS.containsKey(limit)) {
            FOODS.put(new Rectangle(limit), true);
        }
    }

    public void setxPixel(int xPixel) {
        this.xPixel = xPixel;
        this.limit.x = (xPixel + (Variables.spriteRenderWidth / 2) - (limitSize / 2));
        this.center.x = (xPixel + (Variables.spriteRenderWidth / 2));
    }

    public void setyPixel(int yPixel) {
        this.yPixel = yPixel;
        this.limit.y = (yPixel + (Variables.spriteRenderHeight / 2) - (limitSize / 2));
        this.center.y = (yPixel + (Variables.spriteRenderHeight / 2));
    }

}
