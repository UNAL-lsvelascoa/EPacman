package epacman.statesmachine;

import epacman.statesmachine.states.game.GameManager;
import java.awt.Graphics;

public class StatesManager {

    private StateGame[] states;
    private StateGame currentState;

    public StatesManager() {
        initStates();
    }

    private void initStates() {
        states = new StateGame[1];
        states[0] = new GameManager();
        currentState = states[0];
    }

    public void update() {
        currentState.update();
    }

    public void paint(final Graphics g) {
        currentState.paint(g);
    }

    //para cambiar a otros estados en el juego
    public void changeState(final int newState) {
        currentState = states[newState];
    }

    public StateGame getEstadoActual() {
        return currentState;
    }

}
