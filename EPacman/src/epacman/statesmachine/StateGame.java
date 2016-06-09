package epacman.statesmachine;

import java.awt.Graphics;

public interface StateGame {

    void init();

    void update();

    void paint(final Graphics g);

    void finish();

}
