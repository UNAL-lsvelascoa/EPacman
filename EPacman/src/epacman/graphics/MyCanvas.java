package epacman.graphics;

import epacman.control.ControlManager;
import epacman.statesmachine.StatesManager;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

public class MyCanvas extends Canvas {

    private final int ancho, alto;

    public MyCanvas(final int ancho, final int alto) {
        this.ancho = ancho;
        this.alto = alto;

        initMyCanvas();
    }
    
    private void initMyCanvas(){
        /*
         * Hace que java no pueda forzar el repintado del canvas
         * Solo nosotros podremos repintar el canvas
         */
        setIgnoreRepaint(true);
        setPreferredSize(new Dimension(ancho, alto));
        addKeyListener(ControlManager.KEYBOARD);
        setFocusable(true);
        requestFocus();
    }

    public void paint(StatesManager gestorE) {
        BufferStrategy bufferS = getBufferStrategy();
        if (bufferS == null) {
            createBufferStrategy(3);
            return;
        }

        // g va a pintar dentro del buffer
        Graphics g = bufferS.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ancho, alto);
        gestorE.paint(g);

        /*
         * Esto es para pintar solamente cuando el monitor se actualice
         * y esto depende del S.O. y de la pantalla.
         */
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        bufferS.show();
    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }

}
