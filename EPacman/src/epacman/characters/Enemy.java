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
    private int direction = Constants.left;
    private int predirection = Constants.left;
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
        this.xPixel = xSprite * 32;
        this.yPixel = ySprite * 32;
        this.indexPosition = xSprite * ySprite;
        this.spritesSheet = new SpritesSheet(uriSpriteSheet, 32, 32, Transparency.TRANSLUCENT);
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
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 0) + 1);
        predirection = randomNum;
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

}
