package epacman.statesmachine.states.game;

import epacman.maps.Board;
import epacman.statesmachine.StateGame;
import java.awt.Graphics;

public class MapManager implements StateGame{

    private final Board board = new Board();
    
    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paint(Graphics g) {
        board.paint(g);
    }
    
}
