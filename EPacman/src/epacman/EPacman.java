package epacman;

import epacman.graphics.MyCanvas;
import epacman.graphics.Window;
import epacman.statesmachine.StatesManager;

/**
 *
 * @author ErickSteven
 */
public class EPacman {

    private static final int NANOS_POR_SEG = 1000000000;
    private static final byte APS_OBJETIVO = 60;
    private static final double NANOS_POR_APS = NANOS_POR_SEG / APS_OBJETIVO;

    private boolean running = false;
    private final String titulo;
    private String stringAPS = "APS", stringFPS = "FPS";
    private static int ancho, alto, aps = 0, fps = 0;

    private MyCanvas superficieD;
    private Window ventana;
    private StatesManager gestorE;

    private EPacman(final String titulo, final int ancho, final int alto) {
        this.titulo = titulo;
        EPacman.ancho = ancho;
        EPacman.alto = alto;
    }

    private void iniciarJuego() {
        running = true;
        superficieD = new MyCanvas(ancho, alto);
        ventana = new Window(titulo, superficieD);
        gestorE = new StatesManager();
    }

    private void iniciarBuclePrincipal() {
        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();
        double tiempoTranscurrido, delta = 0;  //Cantidad de tiempo que pasa hasta una actualización
        //Bucle del juego
        while (running) {
            final long inicioBucle = System.nanoTime();
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            delta += tiempoTranscurrido / NANOS_POR_APS;

            while (delta >= 20) {
                actualizar();
                delta = 0;
            }

            dibujar();

            if (System.nanoTime() - referenciaContador > NANOS_POR_SEG) {
                stringAPS = "APS: " + aps;
                stringFPS = "FPS: " + fps;
                System.out.println(stringAPS + " " + stringFPS);
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }

    private void actualizar() {
        superficieD.getKeyboard().actualizar();
        gestorE.update();
        aps++;
    }

    private void dibujar() {
        superficieD.dibujar(gestorE);
        fps++;
    }

    public static void main(String[] args) {
        EPacman juego = new EPacman("E-Pacman", 496, 448);
        juego.iniciarJuego();
        juego.iniciarBuclePrincipal();
    }
}
