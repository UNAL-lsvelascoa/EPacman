package epacman.statesmachine.states.initgame;

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
        soundManager.playInitGame();
        CharactersManager.getPLAYER().reset(14, 23);
        CharactersManager.getENEMY(1).reset(12, 13);
        CharactersManager.getENEMY(2).reset(12, 15);
        CharactersManager.getENEMY(3).reset(15, 13);
        CharactersManager.getENEMY(4).reset(15, 15);
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
