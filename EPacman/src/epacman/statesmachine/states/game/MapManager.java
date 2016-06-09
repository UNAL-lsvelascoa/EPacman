package epacman.statesmachine.states.game;

import epacman.control.ControlManager;
import epacman.maps.Board;
import java.awt.Graphics;

public class MapManager {

    private final Board board = new Board();
    private final FoodManager foodManager = new FoodManager();
    
    public void update() {
        foodManager.update();
        if(ControlManager.KEYBOARD.isExit()){
            System.exit(0);
        }
    }

    public void paint(Graphics g) {
        board.paint(g);
        foodManager.paint(g);
    }
    
}
