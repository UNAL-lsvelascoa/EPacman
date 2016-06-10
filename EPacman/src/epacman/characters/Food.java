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

    private final SpritesSheet classicBoard = new SpritesSheet("/media/sprites/ClassicFood.png", Constants.SPRITE_WIDTH, Constants.BOARD_HEIGHT, Transparency.TRANSLUCENT);

    public Food(int xPixel, int yPixel) {
        this.pixel = new Point(xPixel, yPixel);
        currentIndexSprite = 0;
        this.limitSize = 2;
        this.limit = new Rectangle(xPixel, yPixel, limitSize, limitSize);
        this.center = new Point(xPixel-(limitSize/2), yPixel-(limitSize/2));
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(classicBoard.getSprite(currentIndexSprite).getImagen(), pixel.x, pixel.y, Variables.spriteRenderWidth, Variables.spriteRenderHeight, null);
        g.drawRect(limit.x, limit.y, limit.width, limit.height);
        if (!FOODS.contains(limit)) {
            FOODS.add(new Rectangle(limit));
        }
    }

    public void setxPixel(int xPixel) {
        this.pixel.x = xPixel;
        this.limit.x = (xPixel + (Variables.spriteRenderWidth / 2) - (limitSize / 2));
        this.center.x = (xPixel + (Variables.spriteRenderWidth / 2));
    }

    public void setyPixel(int yPixel) {
        this.pixel.y = yPixel;
        this.limit.y = (yPixel + (Variables.spriteRenderHeight / 2) - (limitSize / 2));
        this.center.y = (yPixel + (Variables.spriteRenderHeight / 2));
    }

}
