package epacman.characters;

import java.awt.Graphics;

/**
 *
 * @author ErickSteven
 */
public interface Character {

    int SIDE_SPRITE_SHEET = 6;
    int quantitySprites = 3;
    int animationDuration = 4;
    int squareSide = 48;
    int center = squareSide / 2;

    void update();

    void paint(final Graphics g);

}
