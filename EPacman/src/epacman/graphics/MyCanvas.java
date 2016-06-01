package epacman.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import epacman.control.Keyboard;
import epacman.statesmachine.GestorDeEstados;

public class MyCanvas extends Canvas {

    private final int ancho, alto;
    private final Keyboard keyboard;

    public MyCanvas(final int ancho, final int alto) {
        this.ancho = ancho;
        this.alto = alto;

        keyboard = new Keyboard();
        initMyCanvas();
    }
    
    private void initMyCanvas(){
        /*
         * Hace que java no pueda forzar el repintado del canvas
         * Solo nosotros podremos repintar el canvas
         */
        setIgnoreRepaint(true);
        setPreferredSize(new Dimension(ancho, alto));
        addKeyListener(keyboard);
        setFocusable(true);
        requestFocus();
    }

    public void dibujar(GestorDeEstados gestorE) {
        BufferStrategy bufferS = getBufferStrategy();
        if (bufferS == null) {
            createBufferStrategy(3);
            return;
        }

        // g va a pintar dentro del buffer
        Graphics g = bufferS.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ancho, alto);
        gestorE.dibujar(g);

        /*
         * Esto es para pintar solamente cuando el monitor se actualice
         * y esto depende del S.O. y de la pantalla.
         */
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        bufferS.show();
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }

}
