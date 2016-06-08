package epacman.characters;

import epacman.common.BoardMatrix;
import epacman.common.Constants;
import epacman.common.Variables;
import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Transparency;
import java.util.Random;
import static epacman.characters.Entity.ANIMATION_DURATION;
import static epacman.characters.Entity.QUANTITY_SPRITES;
import epacman.sounds.Sound;
import epacman.statesmachine.states.game.CharactersManager;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author ErickSteven
 */
public class Enemy extends Character implements Entity {

    private boolean eateable = false;
    private Sound eatSpecialFoodSound;

    public Enemy(int x, int y, String uriSpriteSheet) {
        initEnemy(x, y, uriSpriteSheet);
    }

    private void initEnemy(int xSprite, int ySprite, String uriSpriteSheet) {
        this.xSprite = xSprite;
        this.ySprite = ySprite;
        this.xPixel = xSprite * Variables.spriteRenderWidth;
        this.yPixel = ySprite * Variables.spriteRenderHeight;
        this.indexPosition = xSprite * ySprite;
        this.spritesSheet = new SpritesSheet(uriSpriteSheet, Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT, Transparency.TRANSLUCENT);
        this.limitSize = Variables.spriteRenderWidth;
        this.center = new Point((xPixel + (Variables.spriteRenderWidth / 2)),
                (yPixel + (Variables.spriteRenderHeight / 2)));
        this.limit = new Rectangle(center.x - (limitSize / 2), center.y - (limitSize / 2), limitSize, limitSize);
        this.eatSpecialFoodSound = new Sound(Constants.URI_CLASSIC_SOUND_EAT_SPECIAL_FOOD);
    }

    @Override
    public void update() {
        changeDirection();
        moveEnemy();
        if(limit.intersects(CharactersManager.getPLAYER().limit)){
            eatSpecialFoodSound.play();
        }
        if (counterAnimation == ANIMATION_DURATION) {
            if (currentIndexSprite == QUANTITY_SPRITES - 1) {
                animateOrder = false;
            } else if (currentIndexSprite == 0) {
                animateOrder = true;
            }
            if (animateOrder) {
                currentIndexSprite++;
            } else {
                currentIndexSprite--;
            }
            counterAnimation = 0;
        } else {
            counterAnimation++;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (eateable) {
            g.drawImage(spritesSheet.getSprite(currentIndexSprite + (4 * SIDE_SPRITE_SHEET)).getImagen(),
                    xPixel, yPixel, Variables.spriteRenderWidth, Variables.spriteRenderHeight, null);
        } else {
            g.drawImage(spritesSheet.getSprite(currentIndexSprite + (direction * SIDE_SPRITE_SHEET)).getImagen(),
                    xPixel, yPixel, Variables.spriteRenderWidth, Variables.spriteRenderHeight, null);
        }
    }

    private void moveEnemy() {
        if (!isWall(direction)) {
            switch (direction) {
                case Constants.LEFT:
                    xPixel -= velocity;
                    break;
                case Constants.UP:
                    yPixel -= velocity;
                    break;
                case Constants.RIGHT:
                    xPixel += velocity;
                    break;
                case Constants.DOWN:
                    yPixel += velocity;
                    break;
            }
        }
        if (outBoard()) {
            if (xPixel < 0 && direction == Constants.LEFT) {
                xPixel = Variables.spriteRenderWidth * Constants.BOARD_WIDTH;
            } else if (xPixel > Variables.spriteRenderWidth * Constants.BOARD_WIDTH && direction == Constants.RIGHT) {
                xPixel = 0;
            }
        }
        changeLimits();
    }

    private void changeLimits() {
        xSprite = center.x / Variables.spriteRenderWidth;
        ySprite = center.y / Variables.spriteRenderHeight;
        limit.x = center.x - (limitSize / 2);
        limit.y = center.y - (limitSize / 2);
        center.x = xPixel + (Variables.spriteRenderWidth / 2);
        center.y = yPixel + (Variables.spriteRenderHeight / 2);
        indexPosition = (ySprite * Constants.BOARD_WIDTH) + xSprite;
    }

    private void changeDirection() {
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 0) + 1);
        predirection = randomNum;
        if (xPixel % Variables.spriteRenderWidth == 0 && yPixel % Variables.spriteRenderHeight == 0) {
            if (!isWall(predirection)) {
                direction = predirection;
            }
        }
    }

    private boolean isWall(int direction) {
        if (outBoard()) {
            return false;
        }
        if (xPixel == (xSprite) * Variables.spriteRenderWidth && yPixel == (ySprite) * Variables.spriteRenderHeight) {
            switch (direction) {
                case Constants.LEFT:
                    if (BoardMatrix.CLASSIC_BOARD_SPRITES[indexPosition - 1] != 6) {
                        return true;
                    }
                    break;
                case Constants.UP:
                    if (BoardMatrix.CLASSIC_BOARD_SPRITES[indexPosition - Constants.BOARD_WIDTH] != 6) {
                        return true;
                    }
                    break;
                case Constants.RIGHT:
                    if (BoardMatrix.CLASSIC_BOARD_SPRITES[indexPosition + 1] != 6) {
                        return true;
                    }
                    break;
                case Constants.DOWN:
                    if (BoardMatrix.CLASSIC_BOARD_SPRITES[indexPosition + Constants.BOARD_WIDTH] != 6) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    private boolean outBoard() {
        return ySprite % Constants.BOARD_HEIGHT == 0
                || ySprite % Constants.BOARD_HEIGHT == Constants.BOARD_HEIGHT - 1
                || xSprite % Constants.BOARD_WIDTH == 0
                || xSprite % Constants.BOARD_WIDTH == Constants.BOARD_WIDTH - 1;
    }

    public void setEateable(boolean eateable) {
        this.eateable = eateable;
        this.velocity = velocity / 1;
    }

}
