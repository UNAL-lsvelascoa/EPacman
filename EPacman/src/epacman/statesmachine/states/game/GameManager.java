package epacman.statesmachine.states.game;

import java.awt.Graphics;
import java.awt.Transparency;
import epacman.sprites.SpritesSheet;
import epacman.statesmachine.StateGame;

public class GameManager implements StateGame {

    private final MapManager mapManager = new MapManager();
    /*private SpritesSheet classicCharacters = new SpritesSheet("/media/sprites/Sprites Personajes 1.png", 32, 32, Transparency.TRANSLUCENT);
    private HojaDeSprites hSPersonajes2 = new HojaDeSprites("/Multimedia/Sprites/Sprites Personajes 2.png", 32, 32, Transparency.TRANSLUCENT);
     private HojaDeSprites hSPersonajes3 = new HojaDeSprites("/Multimedia/Sprites/Sprites Personajes 3.png", 32, 32, Transparency.TRANSLUCENT);
     private HojaDeSprites hSPersonajes4 = new HojaDeSprites("/Multimedia/Sprites/Sprites Personajes 4.png", 32, 32, Transparency.TRANSLUCENT);
     private HojaDeSprites hSFondo1 = new HojaDeSprites("/Multimedia/Sprites/Sprites Fondo 1.png", 32, 32, Transparency.TRANSLUCENT);
     private HojaDeSprites hSFondo2 = new HojaDeSprites("/Multimedia/Sprites/Sprites Fondo 2.png", 32, 32, Transparency.TRANSLUCENT);
     private HojaDeSprites hSFondo3 = new HojaDeSprites("/Multimedia/Sprites/Sprites Fondo 3.png", 32, 32, Transparency.TRANSLUCENT);
     private HojaDeSprites hSFondo4 = new HojaDeSprites("/Multimedia/Sprites/Sprites Fondo 4.png", 32, 32, Transparency.TRANSLUCENT);*/

    @Override
    public void update() {
    }

    @Override
    public void paint(Graphics g) {
        mapManager.paint(g);
        /*g.drawImage(classicCharacters.getSprite(enemigo1).getImagen(), 0, 0, 32, 32, null);
        g.drawImage(classicCharacters.getSprite(enemigo2).getImagen(), 32, 0, 32, 32, null);
        g.drawImage(classicCharacters.getSprite(enemigo3).getImagen(), 64, 0, 32, 32, null);
        g.drawImage(classicCharacters.getSprite(enemigo4).getImagen(), 96, 0, 32, 32, null);*/
    }

}
