package epacman.characters;

import epacman.common.BoardMatrix;
import epacman.common.Constants;
import epacman.common.Variables;
import epacman.control.ControlManager;
import epacman.sounds.SoundManager;
import epacman.sprites.SpritesSheet;
import epacman.statesmachine.states.game.CharactersManager;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ErickSteven
 */
public class Player extends Character implements Entity {

    private boolean eating = false;
    private boolean alive = true;
    private int timeSpecial;
    private int prevStep;

    private Thread thread = new Thread();

    public Player(int x, int y, String uriSpriteSheet) {
        initPlayer(x, y, uriSpriteSheet);
    }

    private void initPlayer(int xSprite, int ySprite, String uriSpriteSheet) {
        this.pixel = new Point(xSprite * Variables.spriteRenderWidth, ySprite * Variables.spriteRenderHeight);
        this.sprite = new Point(xSprite, ySprite);
        this.indexPosition = xSprite * ySprite;
        this.spritesSheet = new SpritesSheet(uriSpriteSheet, Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT, Transparency.TRANSLUCENT);
        this.limitSize = Variables.spriteRenderWidth / 2;
        this.center = new Point((pixel.x + (Variables.spriteRenderWidth / 2)),
                (pixel.y + (Variables.spriteRenderHeight / 2)));
        this.limit = new Rectangle(center.x - (limitSize / 2), center.y - (limitSize / 2), limitSize, limitSize);
        this.velocity = 1.0;
    }

    @Override
    public void update() {
        if (alive) {
            changeDirection();
            movePlayer();
            for (Rectangle rect : FOODS.keySet()) {
                if (limit.intersects(rect)) {
                    if (FOODS.get(rect)) {
                        eat();
                        FOODS.replace(rect, false);
                    } else {
                    }
                    break;
                }
            }
        } else {
            direction = Constants.DIE;
            animationDuration = 20;
        }
        super.update();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(spritesSheet.getSprite(currentIndexSprite + (direction * SIDE_SPRITE_SHEET)).getImagen(),
                pixel.x, pixel.y, Variables.spriteRenderWidth, Variables.spriteRenderHeight, null);
    }

    private void movePlayer() {
        if (!isWall(direction)) {
            switch (direction) {
                case Constants.LEFT:
                    pixel.x -= velocity;
                    break;
                case Constants.UP:
                    pixel.y -= velocity;
                    break;
                case Constants.RIGHT:
                    pixel.x += velocity;
                    break;
                case Constants.DOWN:
                    pixel.y += velocity;
                    break;
            }
        } else {
            SoundManager.stopEat();
            eating = false;
        }
        if (outBoard()) {
            if (pixel.x < 0 && direction == Constants.LEFT) {
                pixel.x = Variables.spriteRenderWidth * Constants.BOARD_WIDTH;
            } else if (pixel.x > Variables.spriteRenderWidth * Constants.BOARD_WIDTH && direction == Constants.RIGHT) {
                pixel.x = 0;
            }
        }
        changeLimits();
    }

    private void changeLimits() {
        sprite.x = center.x / Variables.spriteRenderWidth;
        sprite.y = center.y / Variables.spriteRenderHeight;
        limit.x = center.x - (limitSize / 2);
        limit.y = center.y - (limitSize / 2);
        center.x = pixel.x + (Variables.spriteRenderWidth / 2);
        center.y = pixel.y + (Variables.spriteRenderHeight / 2);
        indexPosition = (sprite.y * Constants.BOARD_WIDTH) + sprite.x;
        if (prevStep != indexPosition && BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition] != 1) {
            SoundManager.stopEat();
            eating = false;
        }
        prevStep = indexPosition;
    }

    private void changeDirection() {
        switch (ControlManager.direction()) {
            case Constants.LEFT:
                if (direction == Constants.RIGHT) {
                    direction = Constants.LEFT;
                } else {
                    predirection = Constants.LEFT;
                }
                break;
            case Constants.UP:
                if (direction == Constants.DOWN) {
                    direction = Constants.UP;
                } else {
                    predirection = Constants.UP;
                }
                break;
            case Constants.RIGHT:
                if (direction == Constants.LEFT) {
                    direction = Constants.RIGHT;
                } else {
                    predirection = Constants.RIGHT;
                }
                break;
            case Constants.DOWN:
                if (direction == Constants.UP) {
                    direction = Constants.DOWN;
                } else {
                    predirection = Constants.DOWN;
                }
                break;
        }
        if (pixel.x % Variables.spriteRenderWidth == 0 && pixel.y % Variables.spriteRenderHeight == 0) {
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
                if (BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition - 1] == 0
                        || BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition - 1] == 4) {
                    if (pixel.x == (sprite.x) * Variables.spriteRenderWidth) {
                        return true;
                    }
                }
                break;
            case Constants.UP:
                if (BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition - Constants.BOARD_WIDTH] == 0
                        || BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition - Constants.BOARD_WIDTH] == 4) {
                    if (pixel.y == (sprite.y) * Variables.spriteRenderHeight) {
                        return true;
                    }
                }
                break;
            case Constants.RIGHT:
                if (BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition + 1] == 0
                        || BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition + 1] == 4) {
                    if (pixel.x == (sprite.x) * Variables.spriteRenderWidth) {
                        return true;
                    }
                }
                break;
            case Constants.DOWN:
                if (BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition + Constants.BOARD_WIDTH] == 0
                        || BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition + Constants.BOARD_WIDTH] == 4) {
                    if (pixel.y == (sprite.y) * Variables.spriteRenderHeight) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    private boolean outBoard() {
        return sprite.y % Constants.BOARD_HEIGHT == 0
                || sprite.y % Constants.BOARD_HEIGHT == Constants.BOARD_HEIGHT - 1
                || sprite.x % Constants.BOARD_WIDTH == 0
                || sprite.x % Constants.BOARD_WIDTH == Constants.BOARD_WIDTH - 1;
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
                break;
        }
    }

    private void eatFood() {
        BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition] = 3;
        //eatFoodSound.play();
        if (!eating) {
            eating = true;
            SoundManager.playEat(false);
        }
    }

    private void eatSpecialFood() {
        SoundManager.stopEat();
        eating = false;
        BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition] = 3;
        SoundManager.playEat(true);
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

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
