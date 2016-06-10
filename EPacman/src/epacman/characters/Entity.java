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

    int SIDE_SPRITE_SHEET = 8;
    int QUANTITY_SPRITES = 8;
    int SPRITE_SIDE = 48;
    ArrayList<Rectangle> FOODS = new ArrayList<>();

    void update();

    void paint(final Graphics g);

}
