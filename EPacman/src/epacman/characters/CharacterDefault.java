/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epacman.characters;

import epacman.sprites.SpritesSheet;
import java.awt.Graphics;

/**
 *
 * @author ErickSteven
 */
public class CharacterDefault implements Character {

    protected SpritesSheet spritesSheet;
    protected int x;
    protected int y;
    protected int currentIndexSprite = 0;
    protected int counterAnimation = 0;
    protected boolean animateOrder;

    @Override
    public void update() {
        if (counterAnimation == animationDuration) {
            if (currentIndexSprite == quantitySprites - 1) {
                animateOrder = false;
            } else {
                if (currentIndexSprite == 0) {
                    animateOrder = true;
                }
            }
            if (animateOrder) {
                currentIndexSprite++;
            } else {
                currentIndexSprite--;
            }
            counterAnimation = 0;
        } else {
            counterAnimation++;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(spritesSheet.getSprite(currentIndexSprite).getImagen(), (x * 32) - ((squareSide - 32) / 2), (y * 32) - ((squareSide - 32) / 2), squareSide, squareSide, null);
    }

}
