package epacman.statesmachine.states.initialmenu;

import epacman.statesmachine.states.die.*;
import epacman.statesmachine.states.game.*;
import epacman.common.Constants;
import epacman.common.Variables;
import epacman.sounds.SoundManager;
import epacman.statesmachine.StateGame;
import java.awt.Graphics;

public class InitialMenuManager implements StateGame {

    private final MapManager mapManager = new MapManager();
    private final CharactersManager charactersManager = new CharactersManager();
    private final SoundManager soundManager = new SoundManager();

    @Override
    public void init() {
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
