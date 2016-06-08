package epacman.characters;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ErickSteven
 */
public interface Entity {

    int SIDE_SPRITE_SHEET = 6;
    int QUANTITY_SPRITES = 3;
    int ANIMATION_DURATION = 3;
    int SPRITE_SIDE = 48;
    HashMap<Rectangle, Boolean> FOODS = new HashMap<>();

    void update();

    void paint(final Graphics g);

}
