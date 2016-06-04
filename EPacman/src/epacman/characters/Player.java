package epacman.characters;

import static epacman.characters.Character.animationDuration;
import static epacman.characters.Character.quantitySprites;
import epacman.common.BoardMatrix;
import epacman.common.Constants;
import epacman.common.Variables;
import epacman.control.ControlManager;
import epacman.sounds.Sound;
import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Transparency;

/**
 *
 * @author ErickSteven
 */
public class Player implements Character {

    private SpritesSheet spritesSheet;
    private Sound sound;
    private int xPixel;
    private int yPixel;
    private int xSprite;
    private int ySprite;
    private int indexPosition;
    private final double velocity = 2.0;
    private int currentIndexSprite = 0;
    private int counterAnimation = 0;
    private int direction = 0;
    private int predirection = 0;
    private boolean animateOrder;

    public Player(int x, int y, String uriSpriteSheet) {
        initPlayer(x, y, uriSpriteSheet);
    }

    private void initPlayer(int xSprite, int ySprite, String uriSpriteSheet) {
        this.xPixel = xSprite * Variables.spriteRenderWidth;
        this.yPixel = ySprite * Variables.spriteRenderHeight;
        this.xSprite = xSprite;
        this.ySprite = ySprite;
        this.indexPosition = xSprite * ySprite;
        this.spritesSheet = new SpritesSheet(uriSpriteSheet, Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT, Transparency.TRANSLUCENT);
        this.sound = new Sound(Constants.URI_CLASSIC_SOUND_EAT_FOOD);
    }

    @Override
    public void update() {
        changeDirection();
        movePlayer();
        switch (direction) {
            case Constants.LEFT:
            case Constants.RIGHT:
                if (xPixel % Variables.spriteRenderWidth >= 0 && xPixel % Variables.spriteRenderWidth < 4) {
                    eatFood();
                }
                break;
            case Constants.UP:
            case Constants.DOWN:
                if (yPixel % Variables.spriteRenderHeight >= 0 && yPixel % Variables.spriteRenderHeight < 4) {
                    eatFood();
                }
                break;
        }
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
        g.drawImage(spritesSheet.getSprite(currentIndexSprite + (direction * sideSpriteSheet)).getImagen(),
                //xPixel - ((squareSide - 32) / 2), yPixel - ((squareSide - 32) / 2), squareSide, squareSide, null);
                xPixel, yPixel, Variables.spriteRenderWidth, Variables.spriteRenderHeight, null);
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
        if (ControlManager.KEYBOARD.isLeft()) {
            predirection = Constants.LEFT;
        } else if (ControlManager.KEYBOARD.isUp()) {
            predirection = Constants.UP;
        } else if (ControlManager.KEYBOARD.isRight()) {
            predirection = Constants.RIGHT;
        } else if (ControlManager.KEYBOARD.isDown()) {
            predirection = Constants.DOWN;
        }
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
                if (BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition - 1] == 0) {
                    if (xPixel == (xSprite) * Variables.spriteRenderWidth) {
                        return true;
                    }
                }
                break;
            case Constants.UP:
                if (BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition - Constants.BOARD_WIDTH] == 0) {
                    if (yPixel == (ySprite) * Variables.spriteRenderHeight) {
                        return true;
                    }
                }
                break;
            case Constants.RIGHT:
                if (BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition + 1] == 0) {
                    return true;
                }
                break;
            case Constants.DOWN:
                if (BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition + Constants.BOARD_WIDTH] == 0) {
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

    private void eatFood() {
        if (BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition] == 1) {
            BoardMatrix.CLASSIC_BOARD_FOOD[(ySprite * Constants.BOARD_WIDTH) + xSprite] = 3;
            sound.play();
        }
    }
}
