package epacman.graphics;

import epacman.Tools;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;

public class Window extends JFrame implements ComponentListener{

    private final String title;

    public Window(final String title, final MyCanvas surface) {
        this.title = title;
        initWindow(surface);
    }

    private void initWindow(final MyCanvas surface) {
        setTitle(title);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(null);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(400, 300));
        add(surface);
        setLocationRelativeTo(null);
        setVisible(true);
        Tools.changeWindowSize(this);
        addComponentListener(this);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Tools.changeWindowSize(this);
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

}
