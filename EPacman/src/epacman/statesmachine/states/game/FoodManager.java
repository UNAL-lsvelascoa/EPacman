package epacman.statesmachine.states.game;

import epacman.BoardMatrix;
import epacman.Constants;
import epacman.characters.Food;
import epacman.characters.SpecialFood;
import epacman.statesmachine.StateGame;
import java.awt.Graphics;

/**
 *
 * @author ErickVelasco
 */
public class FoodManager implements StateGame {

    private final Food food = new Food();
    private final SpecialFood specialFood = new SpecialFood();

    @Override
    public void update() {
        food.update();
        specialFood.update();
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < Constants.boardAlto; i++) {
            for (int j = 0; j < Constants.boardAncho; j++) {
                if (BoardMatrix.classicBoardFood[j + (i * Constants.boardAncho)] == 1) {
                    food.setxPixel(j * 32);
                    food.setyPixel(i * 32);
                    food.paint(g);
                } else if (BoardMatrix.classicBoardFood[j + (i * Constants.boardAncho)] == 2) {
                    specialFood.setxPixel(j * 32);
                    specialFood.setyPixel(i * 32);
                    specialFood.paint(g);
                }
            }
        }
    }

}
