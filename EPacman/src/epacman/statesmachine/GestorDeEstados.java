package epacman.statesmachine;

import java.awt.Graphics;
import epacman.statesmachine.states.game.GestorDeJuego;

public class GestorDeEstados {

    private EstadoJuego[] estados;
    private EstadoJuego estadoActual;

    public GestorDeEstados() {
        iniciarEstados();
    }

    private void iniciarEstados() {
        estados = new EstadoJuego[1];
        estados[0] = new GestorDeJuego();
        estadoActual = estados[0];
    }

    public void actualizar() {
        estadoActual.actualizar();
    }

    public void dibujar(final Graphics g) {
        estadoActual.dibujar(g);
    }

    //para cambiar a otros estados en el juego
    public void cambiarEstado(final int nuevoEstado) {
        estadoActual = estados[nuevoEstado];
    }

    public EstadoJuego getEstadoActual() {
        return estadoActual;
    }

}
