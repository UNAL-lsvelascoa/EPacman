package epacman.characters;

import epacman.common.Constants;
import epacman.common.Variables;
import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Transparency;
import java.util.Random;
import epacman.sounds.SoundManager;
import epacman.statesmachine.StatesManager;
import epacman.statesmachine.states.game.CharactersManager;
import epacman.statesmachine.states.game.DataManager;
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
        reset(xSprite, ySprite);
        this.spritesSheet = new SpritesSheet(uriSpriteSheet, Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT, Transparency.TRANSLUCENT);
        this.walls.add(0);
    }
    
    public void reset(int xSprite, int ySprite){
        this.sprite = new Point(xSprite, ySprite);
        this.pixel = new Point(xSprite * Variables.spriteRenderWidth, ySprite * Variables.spriteRenderHeight);
        this.spritePosition = xSprite * ySprite;
        this.center = new Point((pixel.x + (Variables.spriteRenderWidth / 2)),
                (pixel.y + (Variables.spriteRenderHeight / 2)));
        this.limitSize = Variables.spriteRenderWidth / 2;
        this.limit = new Rectangle(center.x - (limitSize / 2), center.y - (limitSize / 2), limitSize, limitSize);
        this.direction = Constants.RIGHT;
        this.predirection = Constants.RIGHT;
        this.counterAnimation = 0;
        this.currentIndexSprite = 2;
        this.velocity = 1;
    }

    @Override
    public void update() {
        switch (Variables.state) {
            case Constants.STATE_GAMING:
                if (CharactersManager.getPLAYER().isAlive()) {
                    changeDirection();
                    move();
                    if (limit.intersects(CharactersManager.getPLAYER().limit) && alive) {
                        if (eateable) {
                            SoundManager.playEatEnemy();
                            alive = false;
                        } else {
                            StatesManager.changeState(Constants.STATE_DIE);
                        }
                    }
                }
                break;
        }
        super.update();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (eateable && alive) {
            g.drawImage(spritesSheet.getSprite(currentIndexSprite + (4 * SIDE_SPRITE_SHEET)).getImagen(),
                    pixel.x + Variables.marginLeft, pixel.y, Variables.spriteRenderWidth, Variables.spriteRenderHeight, null);
        } else if (alive) {
            g.drawImage(spritesSheet.getSprite(currentIndexSprite + (direction * SIDE_SPRITE_SHEET)).getImagen(),
                    pixel.x + Variables.marginLeft, pixel.y, Variables.spriteRenderWidth, Variables.spriteRenderHeight, null);
        } else {
            g.drawImage(spritesSheet.getSprite(currentIndexSprite + (5 * SIDE_SPRITE_SHEET)).getImagen(),
                    pixel.x + Variables.marginLeft, pixel.y, Variables.spriteRenderWidth, Variables.spriteRenderHeight, null);
        }
    }

    protected void move() {
        if (!isWall(direction)) {
            super.move();
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

    @Override
    public void changeLimits() {
        super.changeLimits();
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

    @Override
    protected boolean isWall(int direction) {
        return super.isWall(direction);
    }

    @Override
    protected boolean outBoard() {
        return super.outBoard();
    }

    public void setEateable(boolean eateable) {
        this.eateable = eateable;
        this.velocity = velocity / 1;
    }

    public boolean isAlive() {
        return alive;
    }

}
