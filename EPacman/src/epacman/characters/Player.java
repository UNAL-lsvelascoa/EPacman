package epacman.characters;

import epacman.sprites.SpritesSheet;
import java.awt.Transparency;

/**
 *
 * @author ErickSteven
 */
public class Player extends CharacterDefault {

    public Player(int x, int y, String uriSpriteSheet) {
        initPlayer(x, y, uriSpriteSheet);
    }

    private void initPlayer(int x, int y, String uriSpriteSheet) {
        this.x = x;
        this.y = y;
        this.spritesSheet = new SpritesSheet(uriSpriteSheet, 32, 32, Transparency.TRANSLUCENT);
    }

}
