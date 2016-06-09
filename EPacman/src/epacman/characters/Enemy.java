package epacman.characters;

import epacman.common.BoardMatrix;
import epacman.common.Constants;
import epacman.common.Variables;
import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Transparency;
import java.util.Random;
import epacman.sounds.Sound;
import epacman.sounds.SoundManager;
import epacman.statesmachine.StatesManager;
import epacman.statesmachine.states.game.CharactersManager;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author ErickSteven
 */
public class Enemy extends Character implements Entity {

    private boolean eateable = false;
    private boolean alive = true;

    public Enemy(int x, int y, String uriSpriteSheet) {
        initEnemy(x, y, uriSpriteSheet);
    }

    private void initEnemy(int xSprite, int ySprite, String uriSpriteSheet) {
        this.sprite = new Point(xSprite, ySprite);
        this.pixel = new Point(xSprite * Variables.spriteRenderWidth, ySprite * Variables.spriteRenderHeight);
        this.indexPosition = xSprite * ySprite;
        this.spritesSheet = new SpritesSheet(uriSpriteSheet, Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT, Transparency.TRANSLUCENT);
        this.limitSize = Variables.spriteRenderWidth / 2;
        this.center = new Point((pixel.x + (Variables.spriteRenderWidth / 2)),
                (pixel.y + (Variables.spriteRenderHeight / 2)));
        this.limit = new Rectangle(center.x - (limitSize / 2), center.y - (limitSize / 2), limitSize, limitSize);
    }

    @Override
    public void update() {
        if (CharactersManager.getPLAYER().isAlive()) {
            changeDirection();
            moveEnemy();
            if (limit.intersects(CharactersManager.getPLAYER().limit) && alive) {
                if (eateable) {
                    SoundManager.playEatEnemy();
                    alive = false;
                } else {
                    SoundManager.playDie();
                    StatesManager.changeState(Constants.STATE_PAUSE);
                    CharactersManager.getPLAYER().setAlive(false);
                    SoundManager.stopEat();
                }
            }
        }
        super.update();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (eateable && alive) {
            g.drawImage(spritesSheet.getSprite(currentIndexSprite + (4 * SIDE_SPRITE_SHEET)).getImagen(),
                    pixel.x, pixel.y, Variables.spriteRenderWidth, Variables.spriteRenderHeight, null);
        } else if (alive) {
            g.drawImage(spritesSheet.getSprite(currentIndexSprite + (direction * SIDE_SPRITE_SHEET)).getImagen(),
                    pixel.x, pixel.y, Variables.spriteRenderWidth, Variables.spriteRenderHeight, null);
        } else {
            g.drawImage(spritesSheet.getSprite(currentIndexSprite + (5 * SIDE_SPRITE_SHEET)).getImagen(),
                    pixel.x, pixel.y, Variables.spriteRenderWidth, Variables.spriteRenderHeight, null);
        }
    }

    private void moveEnemy() {
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
    }

    private void changeDirection() {
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 0) + 1);
        predirection = randomNum;
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
        if (pixel.x == (sprite.x) * Variables.spriteRenderWidth && pixel.y == (sprite.y) * Variables.spriteRenderHeight) {
            switch (direction) {
                case Constants.LEFT:
                    if (BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition - 1] == 0) {
                        return true;
                    }
                    break;
                case Constants.UP:
                    if (BoardMatrix.CLASSIC_BOARD_FOOD[indexPosition - Constants.BOARD_WIDTH] == 0) {
                        return true;
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
        }
        return false;
    }

    private boolean outBoard() {
        return sprite.y % Constants.BOARD_HEIGHT == 0
                || sprite.y % Constants.BOARD_HEIGHT == Constants.BOARD_HEIGHT - 1
                || sprite.x % Constants.BOARD_WIDTH == 0
                || sprite.x % Constants.BOARD_WIDTH == Constants.BOARD_WIDTH - 1;
    }

    public void setEateable(boolean eateable) {
        this.eateable = eateable;
        this.velocity = velocity / 1;
    }

    public boolean isAlive() {
        return alive;
    }

}
