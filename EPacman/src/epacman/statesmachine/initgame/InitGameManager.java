package epacman.statesmachine.initgame;

import epacman.statesmachine.pause.*;
import epacman.statesmachine.states.die.*;
import epacman.statesmachine.states.game.*;
import epacman.common.Constants;
import epacman.common.Variables;
import epacman.sounds.SoundManager;
import epacman.statesmachine.StateGame;
import java.awt.Graphics;

public class InitGameManager implements StateGame {

    private final MapManager mapManager = new MapManager();
    private final CharactersManager charactersManager = new CharactersManager();
    private final SoundManager soundManager = new SoundManager();

    @Override
    public void init(){
        Variables.state = Constants.STATE_PAUSE;
        SoundManager.playInitGame();
    }
    
    @Override
    public void update() {
        charactersManager.update();
    }

    @Override
    public void paint(Graphics g) {
        mapManager.paint(g);
        charactersManager.paint(g);
    }

    @Override
    public void finish() {
    }

}
