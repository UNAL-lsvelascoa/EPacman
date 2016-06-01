package epacman.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

    private final static int NUMERO_TECLAS = 1024;
    private boolean[] teclas = new boolean[NUMERO_TECLAS];
    
    public boolean arriba, abajo, izquierda, derecha, shift, salir;
    
    public void actualizar(){
        arriba = teclas[KeyEvent.VK_W];
        abajo = teclas[KeyEvent.VK_S];
        izquierda = teclas[KeyEvent.VK_A];
        derecha = teclas[KeyEvent.VK_D];
        shift = teclas[KeyEvent.VK_SHIFT];
        salir = teclas[KeyEvent.VK_ESCAPE];
    }
    
    @Override
    public void keyTyped(KeyEvent e) { 
    }
    @Override
    public void keyPressed(KeyEvent e) {
        teclas[e.getKeyCode()] = true;
        actualizar();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        teclas[e.getKeyCode()] = false;
        actualizar();
    }
    
}
