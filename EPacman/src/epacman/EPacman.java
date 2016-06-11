package epacman;

import epacman.common.Constants;
import epacman.common.Variables;
import epacman.graphics.MyCanvas;
import epacman.graphics.Window;
import epacman.graphics.Window;
import epacman.statesmachine.StatesManager;

/**
 *
 * @author ErickSteven
 */
public class EPacman {

    private static final double NANOS_POR_SEG = 1000000000;
    private static final byte APS_OBJETIVO = 60;
    private static final double NANOS_POR_APS = NANOS_POR_SEG / APS_OBJETIVO;

    private boolean running = false;
    private final String title;
    private static int aps = 0, fps = 0;

    private MyCanvas surface;
    private StatesManager stateManager;

    private EPacman(final String title) {
        this.title = title;
        Variables.spriteRenderHeight = (int) (Constants.SCREEN_HEIGHT / Constants.BOARD_HEIGHT);
        Variables.spriteRenderWidth = Variables.spriteRenderHeight;
        Variables.boardHeight = Constants.BOARD_HEIGHT * Variables.spriteRenderHeight;
        Variables.boardWidth = Constants.BOARD_WIDTH * Variables.spriteRenderWidth;
    }

    private void iniciarJuego() {
        running = true;
        surface = new MyCanvas();
        new Window(surface);
        stateManager = new StatesManager();
    }

    long referenciaActualizacion = System.nanoTime();
    long referenciaContador = System.nanoTime();
    double tiempoTranscurrido;
    double delta = 0;  //Cantidad de tiempo que pasa hasta una actualización

    private void iniciarBuclePrincipal() {
        referenciaActualizacion = System.nanoTime();
        referenciaContador = System.nanoTime();
        delta = 0;  //Cantidad de tiempo que pasa hasta una actualización
        //Bucle del juego
        while (running) {
            final long inicioBucle = System.nanoTime();
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            delta += tiempoTranscurrido / NANOS_POR_APS;

            if (delta >= 1) {
                actualizar();
                delta = 0;
            }
            dibujar();

            if (System.nanoTime() - referenciaContador > NANOS_POR_SEG) {
                System.out.println("APS: " + aps + " " + "FPS: " + fps);
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }

    private void actualizar() {
        stateManager.update();
        aps++;
    }

    private void dibujar() {
        surface.paint(stateManager);
        fps++;
    }

    public static void main(String[] args) {
        EPacman juego = new EPacman("E-Pacman");
        juego.iniciarJuego();
        juego.iniciarBuclePrincipal();
    }
}
