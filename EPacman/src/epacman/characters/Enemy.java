package epacman.characters;

import epacman.sprites.SpritesSheet;
import java.awt.Transparency;

/**
 *
 * @author ErickSteven
 */
public class Enemy extends CharacterDefault {

    public Enemy(int x, int y, String uriSpriteSheet) {
        initEnemy(x, y, uriSpriteSheet);
    }

    private void initEnemy(int x, int y, String uriSpriteSheet) {
        this.x = x;
        this.y = y;
        this.spritesSheet = new SpritesSheet(uriSpriteSheet, 32, 32, Transparency.TRANSLUCENT);
    }

}
