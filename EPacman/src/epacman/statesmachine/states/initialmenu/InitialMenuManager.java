package epacman.statesmachine.states.initialmenu;

import epacman.statesmachine.StateGame;
import java.awt.Graphics;
import javax.swing.JButton;

public class InitialMenuManager implements StateGame {

    @Override
    public void init() {
        JButton btnInitGame = new JButton("Jugar");
        
    }

    @Override
    public void update() {
    }

    @Override
    public void paint(Graphics g) {
    }

    @Override
    public void finish() {
    }

}
