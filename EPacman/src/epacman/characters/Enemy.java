package epacman.characters;

import epacman.BoardMatrix;
import epacman.Constants;
import epacman.Variables;
import static epacman.characters.Character.animationDuration;
import static epacman.characters.Character.quantitySprites;
import static epacman.characters.Character.squareSide;
import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Transparency;
import java.util.Random;

/**
 *
 * @author ErickSteven
 */
public class Enemy implements Character {

    private SpritesSheet spritesSheet;
    private int xSprite;
    private int ySprite;
    private int xPixel;
    private int yPixel;
    private final double velocity = 2.0;
    private int direction = Constants.LEFT;
    private int predirection = Constants.LEFT;
    private int currentIndexSprite = 0;
    private int counterAnimation = 0;
    private int indexPosition = 0;
    private boolean animateOrder;

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
    }

    @Override
    public void update() {
        changeDirection();
        movePlayer();
        if (counterAnimation == animationDuration) {
            if (currentIndexSprite == quantitySprites - 1) {
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
        g.drawImage(spritesSheet.getSprite(currentIndexSprite + (direction * sideSpriteSheet)).getImagen(), xPixel - ((squareSide - 32) / 2), yPixel - ((squareSide - 32) / 2), squareSide, squareSide, null);
    }

    private void movePlayer() {
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
        xSprite = xPixel / Variables.spriteRenderWidth;
        ySprite = yPixel / Variables.spriteRenderHeight;
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
        switch (direction) {
            case Constants.LEFT:
                if (BoardMatrix.CLASSIC_BOARD_SPRITES[indexPosition - 1] != 6) {
                    if (xPixel == (xSprite) * Variables.spriteRenderWidth) {
                        return true;
                    }
                }
                break;
            case Constants.UP:
                if (BoardMatrix.CLASSIC_BOARD_SPRITES[indexPosition - Constants.BOARD_WIDTH] != 6) {
                    if (yPixel == (ySprite) * Variables.spriteRenderHeight) {
                        return true;
                    }
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
        return false;
    }

    private boolean outBoard() {
        return ySprite % Constants.BOARD_HEIGHT == 0
                || ySprite % Constants.BOARD_HEIGHT == Constants.BOARD_HEIGHT - 1
                || xSprite % Constants.BOARD_WIDTH == 0
                || xSprite % Constants.BOARD_WIDTH == Constants.BOARD_WIDTH - 1;
    }

}
