package epacman.statesmachine;

import epacman.common.Constants;
import epacman.statesmachine.states.initgame.InitGameManager;
import epacman.statesmachine.states.pause.PauseManager;
import epacman.statesmachine.states.die.DieManager;
import epacman.statesmachine.states.game.GameManager;
import epacman.statesmachine.states.initialmenu.InitialMenuManager;
import java.awt.Graphics;
import java.util.ArrayList;

public class StatesManager {

    private static final ArrayList<StateGame> STATES = new ArrayList<>();
    private static StateGame currentState;

    public StatesManager() {
        initStates();
    }

    private void initStates() {
        STATES.add(new InitGameManager());
        STATES.add(new GameManager());
        STATES.add(new PauseManager());
        STATES.add(new DieManager());
        STATES.add(new InitialMenuManager());
        changeState(Constants.STATE_INIT_GAME);
    }

    public void update() {
        currentState.update();
    }

    public void paint(final Graphics g) {
        currentState.paint(g);
    }

    //para cambiar a otros estados en el juego
    public static void changeState(final int newState) {
        if (currentState != null) {
            currentState.finish();
        }
        if (!STATES.isEmpty()) {
            currentState = STATES.get(newState);
            currentState.init();
        }

    }

    public StateGame getEstadoActual() {
        return currentState;
    }

}
