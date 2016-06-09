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
public class FoodManager {

    private final Food food = new Food(0, 0);
    private final SpecialFood specialFood = new SpecialFood(0, 0);

    public void update() {
        food.update();
        specialFood.update();
    }

    public void paint(Graphics g) {
        for (int i = 0; i < Constants.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Constants.BOARD_WIDTH; j++) {
                if (BoardMatrix.CLASSIC_BOARD_FOOD[j + (i * Constants.BOARD_WIDTH)] == 1) {
                    food.setxPixel(j * Variables.spriteRenderWidth);
                    food.setyPixel(i * Variables.spriteRenderHeight);
                    food.paint(g);
                } else if (BoardMatrix.CLASSIC_BOARD_FOOD[j + (i * Constants.BOARD_WIDTH)] == 2) {
                    specialFood.setxPixel(j * Variables.spriteRenderWidth);
                    specialFood.setyPixel(i * Variables.spriteRenderHeight);
                    specialFood.paint(g);
                }
            }
        }
    }

}
