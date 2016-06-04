package epacman.graphics;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Window extends JFrame {

    private final String title;

    public Window(final String title, final MyCanvas surface) {
        this.title = title;
        initWindow(surface);
    }

    private void initWindow(final MyCanvas surface) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setIconImage(null);
        setLayout(new BorderLayout());
        add(surface, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
