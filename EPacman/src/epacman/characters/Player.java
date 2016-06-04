package epacman.characters;

import epacman.BoardMatrix;
import epacman.Constants;
import static epacman.characters.Character.animationDuration;
import static epacman.characters.Character.quantitySprites;
import static epacman.characters.Character.squareSide;
import epacman.control.ControlManager;
import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Transparency;

/**
 *
 * @author ErickSteven
 */
public class Player implements Character {

    private SpritesSheet spritesSheet;
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
        this.xPixel = xSprite * 32;
        this.yPixel = ySprite * 32;
        this.xSprite = xSprite;
        this.ySprite = ySprite;
        this.indexPosition = xSprite * ySprite;
        this.spritesSheet = new SpritesSheet(uriSpriteSheet, 32, 32, Transparency.TRANSLUCENT);
    }

    @Override
    public void update() {
        changeDirection();
        movePlayer();
        switch (direction) {
            case Constants.left:
                if (xPixel % 32 >= 0 && xPixel % 32 < 4) {
                    eatFood();
                }
                break;
            case Constants.up:
                if (yPixel % 32 >= 0 && yPixel % 32 < 4) {
                    eatFood();
                }
                break;
            case Constants.right:
                if (xPixel % 32 >= 0 && xPixel % 32 < 4) {
                    eatFood();
                }
                break;
            case Constants.down:
                if (yPixel % 32 >= 0 && yPixel % 32 < 4) {
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
        g.drawImage(spritesSheet.getSprite(currentIndexSprite + (direction * sideSpriteSheet)).getImagen(), xPixel - ((squareSide - 32) / 2), yPixel - ((squareSide - 32) / 2), squareSide, squareSide, null);
    }

    private void movePlayer() {
        if (!isWall(direction)) {
            switch (direction) {
                case Constants.left:
                    xPixel -= velocity;
                    break;
                case Constants.up:
                    yPixel -= velocity;
                    break;
                case Constants.right:
                    xPixel += velocity;
                    break;
                case Constants.down:
                    yPixel += velocity;
                    break;
            }
        }
        if (outBoard()) {
            if (xPixel < 0 && direction == Constants.left) {
                xPixel = 32 * Constants.boardAncho;
            } else if (xPixel > 32 * Constants.boardAncho && direction == Constants.right) {
                xPixel = 0;
            }
        }
        xSprite = xPixel / 32;
        ySprite = yPixel / 32;
        indexPosition = (ySprite * Constants.boardAncho) + xSprite;
    }

    private void changeDirection() {
        if (ControlManager.KEYBOARD.isLeft()) {
            predirection = Constants.left;
        } else if (ControlManager.KEYBOARD.isUp()) {
            predirection = Constants.up;
        } else if (ControlManager.KEYBOARD.isRight()) {
            predirection = Constants.right;
        } else if (ControlManager.KEYBOARD.isDown()) {
            predirection = Constants.down;
        }
        if (xPixel % 32 == 0 && yPixel % 32 == 0) {
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
            case Constants.left:
                if (BoardMatrix.classicBoardSprites[indexPosition - 1] != 6) {
                    if (xPixel == (xSprite) * 32) {
                        return true;
                    }
                }
                break;
            case Constants.up:
                if (BoardMatrix.classicBoardSprites[indexPosition - Constants.boardAncho] != 6) {
                    if (yPixel == (ySprite) * 32) {
                        return true;
                    }
                }
                break;
            case Constants.right:
                if (BoardMatrix.classicBoardSprites[indexPosition + 1] != 6) {
                    return true;
                }
                break;
            case Constants.down:
                if (BoardMatrix.classicBoardSprites[indexPosition + Constants.boardAncho] != 6) {
                    return true;
                }
                break;
        }
        return false;
    }

    private boolean outBoard() {
        return ySprite % Constants.boardAlto == 0
                || ySprite % Constants.boardAlto == Constants.boardAlto - 1
                || xSprite % Constants.boardAncho == 0
                || xSprite % Constants.boardAncho == Constants.boardAncho - 1;
    }

    private void eatFood() {
        BoardMatrix.classicBoardFood[(ySprite * Constants.boardAncho) + xSprite] = 0;
    }
}
