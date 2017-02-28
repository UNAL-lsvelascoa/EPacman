package epacman.characters;

import epacman.common.BoardMatrix;
import epacman.common.Constants;
import epacman.common.Variables;
import epacman.sprites.SpritesSheet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author ErickSteven
 */
public class Character implements Entity {

    protected SpritesSheet spritesSheet;

    protected Point pixel;
    protected Point sprite;

    protected int spritePosition;
    protected double velocity = 1;

    protected int animationDuration = 2;
    protected int currentIndexSprite = 2;
    protected int initialSprite = 0;
    protected int counterAnimation = 0;
    protected int direction = 0;
    protected int predirection = 0;

    protected int limitSize;
    protected Rectangle limit;
    protected Point center;

    protected ArrayList<Integer> walls = new ArrayList<>();

    @Override
    public void update() {
        switch (Variables.state) {
            case Constants.STATE_GAMING:
                if (counterAnimation == animationDuration) {
                    currentIndexSprite++;
                    if (currentIndexSprite == QUANTITY_SPRITES + initialSprite) {
                        currentIndexSprite = initialSprite;
                    }
                    counterAnimation = 0;
                } else {
                    counterAnimation++;
                }
                break;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(limit.x + Variables.marginLeft, limit.y, limit.width, limit.height);
        g.drawRect(center.x + Variables.marginLeft, center.y, 1, 1);
        g.setColor(Color.blue);
        g.drawRect((sprite.x * Variables.spriteRenderWidth) + Variables.marginLeft, sprite.y * Variables.spriteRenderHeight, Variables.spriteRenderWidth, Variables.spriteRenderHeight);
        g.setColor(Color.green);
        g.drawRect(pixel.x + Variables.marginLeft, pixel.y, Variables.spriteRenderWidth, Variables.spriteRenderHeight);
    }

    public void changeLimits() {
        center.x = pixel.x + (Variables.spriteRenderWidth / 2);
        center.y = pixel.y + (Variables.spriteRenderHeight / 2);
        sprite.x = center.x / Variables.spriteRenderWidth;
        sprite.y = center.y / Variables.spriteRenderHeight;
        limit.x = center.x - (limitSize / 2);
        limit.y = center.y - (limitSize / 2);
        spritePosition = (sprite.y * Constants.BOARD_WIDTH) + sprite.x;
    }

    protected void move() {
        switch (direction) {
            case Constants.LEFT:
                if (pixel.x > sprite.x * Variables.spriteRenderWidth
                        && pixel.x < (sprite.x * Variables.spriteRenderWidth) + velocity) {
                    pixel.x = (sprite.x * Variables.spriteRenderWidth);
                } else {
                    pixel.x -= velocity;
                }
                break;
            case Constants.UP:
                if (pixel.y > sprite.y * Variables.spriteRenderHeight
                        && pixel.y < (sprite.y * Variables.spriteRenderHeight) + velocity) {
                    pixel.y = (sprite.y * Variables.spriteRenderWidth);
                } else {
                    pixel.y -= velocity;
                }
                break;
            case Constants.RIGHT:
                if (pixel.x < sprite.x * Variables.spriteRenderWidth
                        && pixel.x > (sprite.x * Variables.spriteRenderWidth) - velocity) {
                    pixel.x = (sprite.x * Variables.spriteRenderWidth);
                } else {
                    pixel.x += velocity;
                }
                break;
            case Constants.DOWN:
                if (pixel.y < sprite.y * Variables.spriteRenderHeight
                        && pixel.y > (sprite.y * Variables.spriteRenderHeight) - velocity) {
                    pixel.y = (sprite.y * Variables.spriteRenderWidth);
                } else {
                    pixel.y += velocity;
                }
                break;
        }
    }

    protected boolean isWall(int direction) {
        if (outBoard()) {
            return true;
        }
        switch (direction) {
            case Constants.LEFT:
                for (Integer wall : walls) {
                    if (BoardMatrix.CLASSIC_BOARD_FOOD[spritePosition - 1] == wall
                            && pixel.x == sprite.x * Variables.spriteRenderWidth) {
                        return true;
                    }
                }
                break;

            case Constants.UP:
                for (Integer wall : walls) {
                    if (BoardMatrix.CLASSIC_BOARD_FOOD[spritePosition - Constants.BOARD_WIDTH] == wall
                            && pixel.y == sprite.y * Variables.spriteRenderHeight) {
                        return true;
                    }
                }
                break;
            case Constants.RIGHT:
                for (Integer wall : walls) {
                    if (BoardMatrix.CLASSIC_BOARD_FOOD[spritePosition + 1] == wall
                            && pixel.x == sprite.x * Variables.spriteRenderWidth) {
                        return true;
                    }
                }
                break;
            case Constants.DOWN:
                for (Integer wall : walls) {
                    if (BoardMatrix.CLASSIC_BOARD_FOOD[spritePosition + Constants.BOARD_WIDTH] == wall
                            && pixel.y == sprite.y * Variables.spriteRenderHeight) {
                        return true;
                    }
                }
                break;
        }

        return false;
    }

    protected boolean outBoard() {
        return sprite.y % Constants.BOARD_HEIGHT == 0
                || sprite.y % Constants.BOARD_HEIGHT == Constants.BOARD_HEIGHT - 1
                || sprite.x % Constants.BOARD_WIDTH == 0
                || sprite.x % Constants.BOARD_WIDTH == Constants.BOARD_WIDTH - 1;
    }

}
