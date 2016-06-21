package epacman.statesmachine.states.game;

import epacman.characters.Enemy;
import epacman.characters.Player;
import epacman.common.Constants;
import epacman.statesmachine.StateGame;
import java.awt.Graphics;

public class CharactersManager {

    private static final Player PLAYER = new Player(14, 23, Constants.URI_CLASSIC_SPRITES_PLAYER);
    private static final Enemy ENEMY1 = new Enemy(12, 13, "/media/sprites/ClassicEnemy1.png");
    private static final Enemy ENEMY2 = new Enemy(12, 15, "/media/sprites/ClassicEnemy2.png");
    private static final Enemy ENEMY3 = new Enemy(15, 13, "/media/sprites/ClassicEnemy3.png");
    private static final Enemy ENEMY4 = new Enemy(15, 15, "/media/sprites/ClassicEnemy4.png");

    public void update() {
        PLAYER.update();
        ENEMY1.update();
        ENEMY2.update();
        ENEMY3.update();
        ENEMY4.update();
    }

    public void paint(Graphics g) {
        PLAYER.paint(g);
        ENEMY1.paint(g);
        ENEMY2.paint(g);
        ENEMY3.paint(g);
        ENEMY4.paint(g);
    }

    public static void changeEateables(final boolean eateable) {
        if (ENEMY1.isAlive()) {
            ENEMY1.setEateable(eateable);
        }
        if (ENEMY2.isAlive()) {
            ENEMY2.setEateable(eateable);
        }
        if (ENEMY3.isAlive()) {
            ENEMY3.setEateable(eateable);
        }
        if (ENEMY4.isAlive()) {
            ENEMY4.setEateable(eateable);
        }
    }

    public static Player getPLAYER() {
        return PLAYER;
    }

}
