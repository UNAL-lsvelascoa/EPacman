package epacman.statesmachine.states.game;

import epacman.common.Constants;
import epacman.common.Variables;
import epacman.sounds.SoundManager;
import epacman.statesmachine.StateGame;
import java.awt.Graphics;

public class GameManager implements StateGame {

    private final MapManager mapManager = new MapManager();
    private final CharactersManager charactersManager = new CharactersManager();
    private final SoundManager soundManager = new SoundManager();
    private final DataManager dataManager = new DataManager();

    @Override
    public void init() {
        soundManager.playBackground(Variables.backgroundSoundType);
        Variables.playingBackground = true;
    }

    @Override
    public void update() {
        mapManager.update();
        charactersManager.update();
        dataManager.update();
    }

    @Override
    public void paint(Graphics g) {
        mapManager.paint(g);
        charactersManager.paint(g);
        dataManager.paint(g);
    }

    @Override
    public void finish() {
        soundManager.closeAll();
    }

}
