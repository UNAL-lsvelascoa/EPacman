package epacman.graphics;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Window extends JFrame {

    private final String titulo;

    public Window(final String titulo, final MyCanvas superficie) {
        this.titulo = titulo;
        initWindow(superficie);
    }

    private void initWindow(final MyCanvas superficie) {
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
