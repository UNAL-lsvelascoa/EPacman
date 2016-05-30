package epacman.graphics;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Ventana extends JFrame{
    
    private final String titulo;
    
    public Ventana(final String titulo, final SuperficieDeDibujo superficie){
        this.titulo = titulo;
        configurarVentana(superficie);
    }
    
    private void configurarVentana(final SuperficieDeDibujo superficie){
        setTitle(titulo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setIconImage(null);
        setLayout(new BorderLayout());
        add(superficie, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
