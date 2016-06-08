package epacman.characters;

import epacman.common.BoardMatrix;
import epacman.common.Constants;
import epacman.common.Tools;
import epacman.common.Variables;
import epacman.control.ControlManager;
import epacman.sounds.Sound;
import epacman.sounds.SoundManager;
import epacman.sprites.SpritesSheet;
import epacman.statesmachine.states.game.CharactersManager;
import epacman.statesmachine.states.game.FoodManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.util.logging.Level;
import java.util.logging.Logger;
import static epacman.characters.Entity.ANIMATION_DURATION;
import static epacman.characters.Entity.QUANTITY_SPRITES;

/**
 *
 * @author ErickSteven
 */
public class Player extends Character implements Entity {

    private Sound eatFoodSound;
    private Sound eatSpecialFoodSound;
    private boolean eating = false;
    private int timeSpecial;

    private Thread thread = new Thread();

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
        this.eatFoodSound = new Sound(Constants.URI_CLASSIC_SOUND_EAT_FOOD);
        this.eatSpecialFoodSound = new Sound(Constants.URI_CLASSIC_SOUND_EAT_SPECIAL_FOOD);
        this.limitSize = Constants.SPRITE_WIDTH / 2;
        this.center = new Point((xPixel + (Variables.spriteRenderWidth / 2)),
                (yPixel + (Variables.spriteRenderHeight / 2)));
        this.limit = new Rectangle(center.x - (limitSize / 2), center.y - (limitSize / 2), limitSize, limitSize);
    }

    @Override
    public void update() {
        changeDirection();
        movePlayer();
        for (Rectangle rect : FOODS) {
            if (limit.intersects(rect)) {
                eat();
                break;
            }
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
        g.drawImage(spritesSheet.getSprite(currentIndexSprite + (direction * SIDE_SPRITE_SHEET)).getImagen(),
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
                    if (xPixel == (xSprite) * Variables.spriteRenderWidth) {
                        return true;
                    }
                }
                break;
            case Constants.DOWN:
                if (BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition + Constants.BOARD_WIDTH] == 0) {
                    if (yPixel == (ySprite) * Variables.spriteRenderHeight) {
                        return true;
                    }
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

    private void eat() {
        switch (BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition]) {
            case 1:
                eatFood();
                break;
            case 2:
                eatSpecialFood();
                break;
            case 3:
                eatFoodSound.close();
                eating = false;
                break;
        }
    }

    private void eatFood() {
        BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition] = 3;
        eatFoodSound.play();
        if (!eating) {
            eating = true;
            //eatFoodSound.playInLoop();
        }
    }

    private void eatSpecialFood() {
        eatFoodSound.close();
        eating = false;
        BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition] = 3;
        eatSpecialFoodSound.play();
        if (thread.isAlive()) {
            timeSpecial += Constants.TIME_SPECIAL;
        } else {
            thread = new Thread(() -> {
                timeSpecial = Constants.TIME_SPECIAL;
                CharactersManager.changeEateables(true);
                SoundManager.changeBackground(Constants.TYPE_BACKGROUND_SPECIAL);
                while (timeSpecial > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    timeSpecial -= 100;
                }
                CharactersManager.changeEateables(false);
                SoundManager.changeBackground(Constants.TYPE_BACKGROUND_NORMAL);
            });
            thread.start();
        }
    }
}
