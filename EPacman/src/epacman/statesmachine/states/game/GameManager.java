package epacman.statesmachine.states.game;

import epacman.statesmachine.StateGame;
import java.awt.Graphics;

public class GameManager implements StateGame {

    private final MapManager mapManager = new MapManager();
    private final CharactersManager charactersManager = new CharactersManager();

    @Override
    public void update() {
        mapManager.update();
        charactersManager.update();
    }

    @Override
    public void paint(Graphics g) {
        mapManager.paint(g);
        charactersManager.paint(g);
    }

}
