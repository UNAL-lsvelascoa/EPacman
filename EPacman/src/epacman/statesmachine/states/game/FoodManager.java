package epacman.statesmachine.states.game;

import epacman.common.BoardMatrix;
import epacman.common.Constants;
import epacman.common.Variables;
import epacman.characters.Food;
import epacman.characters.SpecialFood;
import epacman.statesmachine.StateGame;
import java.awt.Graphics;

/**
 *
 * @author ErickVelasco
 */
public class FoodManager implements StateGame {

    public static final Food FOOD = new Food(0, 0);
    private final SpecialFood specialFood = new SpecialFood();

    @Override
    public void update() {
        FOOD.update();
        specialFood.update();
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < Constants.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Constants.BOARD_WIDTH; j++) {
                if (BoardMatrix.CLASSIC_BOARD_FOOD[j + (i * Constants.BOARD_WIDTH)] == 1) {
                    FOOD.setxPixel(j * Variables.spriteRenderWidth);
                    FOOD.setyPixel(i * Variables.spriteRenderHeight);
                    FOOD.paint(g);
                } else if (BoardMatrix.CLASSIC_BOARD_FOOD[j + (i * Constants.BOARD_WIDTH)] == 2) {
                    specialFood.setxPixel(j * Variables.spriteRenderWidth);
                    specialFood.setyPixel(i * Variables.spriteRenderHeight);
                    specialFood.paint(g);
                }
            }
        }
    }

}
