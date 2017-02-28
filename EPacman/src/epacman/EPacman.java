package epacman;

import epacman.common.Constants;
import epacman.common.Variables;
import epacman.graphics.MyCanvas;
import epacman.graphics.Window;
import epacman.statesmachine.StatesManager;

/**
 *
 * @author ErickSteven
 */
public class EPacman {

    private static final double NANOS_BY_SEC = 1000000000;
    private static final byte APS_GOAL = 60;
    private static final double NANOS_BY_APS = NANOS_BY_SEC / APS_GOAL;

    private boolean running = false;
    private static int aps = 0, fps = 0;

    private MyCanvas surface;
    private StatesManager stateManager;

    private EPacman() {
        Variables.spriteRenderHeight = (int) (Constants.SCREEN_HEIGHT / Constants.BOARD_HEIGHT);
        Variables.spriteRenderWidth = Variables.spriteRenderHeight;
        Variables.boardHeight = Constants.BOARD_HEIGHT * Variables.spriteRenderHeight;
        Variables.boardWidth = Constants.BOARD_WIDTH * Variables.spriteRenderWidth;
    }

    private void initGame() {
        running = true;
        surface = new MyCanvas();
        new Window(surface);
        stateManager = new StatesManager();
    }

    long referenciaActualizacion = System.nanoTime();
    long referenciaContador = System.nanoTime();
    double tiempoTranscurrido;
    double delta = 0;  //Cantidad de tiempo que pasa hasta una actualización

    private void initMainLoop() {
        referenciaActualizacion = System.nanoTime();
        referenciaContador = System.nanoTime();
        delta = 0;  //Cantidad de tiempo que pasa hasta una actualización
        //Bucle del juego
        while (running) {
            final long inicioBucle = System.nanoTime();
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            delta += tiempoTranscurrido / NANOS_BY_APS;

            if (delta >= 1) {
                update();
                delta = 0;
            }
            paint();

            if (System.nanoTime() - referenciaContador > NANOS_BY_SEC) {
                System.out.println("APS: " + aps + " " + "FPS: " + fps);
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }

    private void update() {
        stateManager.update();
        aps++;
    }

    private void paint() {
        surface.paint(stateManager);
        fps++;
    }

    public static void main(String[] args) {
        EPacman mainGame = new EPacman();
        mainGame.initGame();
        mainGame.initMainLoop();
    }
}
