package epacman.statesmachine.states.game;

import epacman.characters.Enemy;
import epacman.characters.Player;
import epacman.statesmachine.StateGame;
import java.awt.Graphics;

public class CharactersManager implements StateGame {

    private final Player player = new Player(14, 23, "/media/sprites/ClassicPlayer.png");
    private static final Enemy ENEMY1 = new Enemy(12, 13, "/media/sprites/ClassicEnemy1.png");
    private static final Enemy ENEMY2 = new Enemy(12, 15, "/media/sprites/ClassicEnemy2.png");
    private static final Enemy ENEMY3 = new Enemy(15, 13, "/media/sprites/ClassicEnemy3.png");
    private static final Enemy ENEMY4 = new Enemy(15, 15, "/media/sprites/ClassicEnemy4.png");

    @Override
    public void update() {
        player.update();
        ENEMY1.update();
        ENEMY2.update();
        ENEMY3.update();
        ENEMY4.update();
    }

    @Override
    public void paint(Graphics g) {
        player.paint(g);
        ENEMY1.paint(g);
        ENEMY2.paint(g);
        ENEMY3.paint(g);
        ENEMY4.paint(g);
    }
    
    public static void changeEateables(final boolean eateable){
        ENEMY1.setEateable(eateable);
        ENEMY2.setEateable(eateable);
        ENEMY3.setEateable(eateable);
        ENEMY4.setEateable(eateable);
    }

}
