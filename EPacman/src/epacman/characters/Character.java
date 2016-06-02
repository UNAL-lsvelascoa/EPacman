package epacman.characters;

import java.awt.Graphics;

/**
 *
 * @author ErickSteven
 */
public interface Character {

    int quantitySprites = 3;
    int animationDuration = 10;
    int squareSide = 48;
    int center = squareSide / 2;

    void update();

    void paint(final Graphics g);

}
