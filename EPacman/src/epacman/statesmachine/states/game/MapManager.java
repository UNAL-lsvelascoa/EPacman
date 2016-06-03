package epacman.statesmachine.states.game;

import epacman.maps.Board;
import epacman.statesmachine.StateGame;
import java.awt.Graphics;

public class MapManager implements StateGame{

    private final Board board = new Board();
    private final FoodManager foodManager = new FoodManager();
    
    @Override
    public void update() {
        foodManager.update();
    }

    @Override
    public void paint(Graphics g) {
        board.paint(g);
        foodManager.paint(g);
    }
    
}
