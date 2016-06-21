package epacman.characters;

import epacman.common.Constants;
import epacman.common.Variables;
import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Transparency;

/**
 *
 * @author ErickSteven
 */
public class Live extends Character implements Entity {

    public Live(int x, int y, String uriSpriteSheet) {
        initLive(x, y, uriSpriteSheet);
    }

    private void initLive(int xSprite, int ySprite, String uriSpriteSheet) {
        this.pixel = new Point(xSprite * Variables.spriteRenderWidth, ySprite * Variables.spriteRenderHeight);
        this.sprite = new Point(xSprite, ySprite);
        this.spritePosition = xSprite * ySprite;
        this.spritesSheet = new SpritesSheet(uriSpriteSheet, Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT, Transparency.TRANSLUCENT);
        this.limitSize = Variables.spriteRenderWidth / 2;
        this.center = new Point((pixel.x + (Variables.spriteRenderWidth / 2)),
                (pixel.y + (Variables.spriteRenderHeight / 2)));
        this.limit = new Rectangle(center.x - (limitSize / 2), center.y - (limitSize / 2), limitSize, limitSize);
        this.walls.add(0);
        this.walls.add(4);
        this.velocity = 3;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(spritesSheet.getSprite(currentIndexSprite + (direction * SIDE_SPRITE_SHEET)).getImagen(),
                pixel.x, pixel.y, Variables.spriteRenderWidth, Variables.spriteRenderHeight, null);
    }

    @Override
    protected void move() {
        
    }

    @Override
    public void changeLimits() {
    }


    @Override
    protected boolean isWall(int direction) {
        return super.isWall(direction);
    }

    @Override
    protected boolean outBoard() {
        return super.outBoard();
    }

}
