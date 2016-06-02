package epacman.statesmachine.states.game;

import epacman.characters.Enemy;
import epacman.characters.Player;
import epacman.statesmachine.StateGame;
import java.awt.Graphics;

public class CharactersManager implements StateGame {

    private final Player player = new Player(14, 23, "/media/sprites/ClassicPlayer.png");
    private final Enemy enemy1 = new Enemy(12, 13, "/media/sprites/ClassicEnemy1.png");
    private final Enemy enemy2 = new Enemy(12, 15, "/media/sprites/ClassicEnemy2.png");
    private final Enemy enemy3 = new Enemy(15, 13, "/media/sprites/ClassicEnemy3.png");
    private final Enemy enemy4 = new Enemy(15, 15, "/media/sprites/ClassicEnemy4.png");

    @Override
    public void update() {
        player.update();
        enemy1.update();
        enemy2.update();
        enemy3.update();
        enemy4.update();
    }

    @Override
    public void paint(Graphics g) {
        player.paint(g);
        enemy1.paint(g);
        enemy2.paint(g);
        enemy3.paint(g);
        enemy4.paint(g);
    }

}
