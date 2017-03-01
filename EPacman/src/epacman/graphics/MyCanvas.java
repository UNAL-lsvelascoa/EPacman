package epacman.graphics;

import epacman.common.Constants;
import epacman.common.Variables;
import epacman.control.ControlManager;
import epacman.statesmachine.StatesManager;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MyCanvas extends JPanel {

    private final int width;
    private final int height;
    private StatesManager statesManager;

    public MyCanvas(StatesManager statesManager) {
        this.width = Variables.boardWidth;
        this.height = Variables.boardHeight;
        this.statesManager = statesManager;
        initMyCanvas();
    }

    private void initMyCanvas() {
        /*
         * Hace que java no pueda forzar el repintado del canvas
         * Solo nosotros podremos repintar el canvas
         */
        setIgnoreRepaint(true);
        //setLocation((Variables.windowWidth / 2) - (Variables.boardWidth / 2), 0);
        setPreferredSize(new Dimension(width, height));
        addKeyListener(ControlManager.KEYBOARD);
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void paint(Graphics g) {
        /*BufferStrategy bufferS = getBufferStrategy();
        if (bufferS == null) {
            createBufferStrategy(2);
            return;
        }*/

        // g va a pintar dentro del buffer
        //Graphics g = bufferS.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Variables.windowWidth, Variables.windowHeight);
        statesManager.paint(g);

        /*
         * Esto es para pintar solamente cuando el monitor se actualice
         * y esto depende del S.O. y de la pantalla.
         */
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        //bufferS.show();
    }
}
