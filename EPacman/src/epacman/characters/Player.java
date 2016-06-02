package epacman.characters;

import static epacman.characters.Character.animationDuration;
import static epacman.characters.Character.quantitySprites;
import static epacman.characters.Character.squareSide;
import epacman.control.ControlManager;
import epacman.sprites.SpritesSheet;
import java.awt.Graphics;
import java.awt.Transparency;

/**
 *
 * @author ErickSteven
 */
public class Player implements Character {

    private SpritesSheet spritesSheet;
    private int x;
    private int y;
    private int currentIndexSprite = 0;
    private int counterAnimation = 0;
    private int direction = 0;
    private boolean animateOrder;

    public Player(int x, int y, String uriSpriteSheet) {
        initPlayer(x, y, uriSpriteSheet);
    }

    private void initPlayer(int x, int y, String uriSpriteSheet) {
        this.x = x;
        this.y = y;
        this.spritesSheet = new SpritesSheet(uriSpriteSheet, 32, 32, Transparency.TRANSLUCENT);
    }

    @Override
    public void update() {
        if (ControlManager.KEYBOARD.isLeft()) {
            direction = 0;
        } else {
            if (ControlManager.KEYBOARD.isUp()) {
                direction = 1;
            } else {
                if (ControlManager.KEYBOARD.isRight()) {
                    direction = 2;
                } else {
                    if (ControlManager.KEYBOARD.isDown()) {
                        direction = 3;
                    }
                }
            }
        }

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
        System.out.println(direction);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(spritesSheet.getSprite(currentIndexSprite + (direction * sideSpriteSheet)).getImagen(), (x * 32) - ((squareSide - 32) / 2), (y * 32) - ((squareSide - 32) / 2), squareSide, squareSide, null);
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}
