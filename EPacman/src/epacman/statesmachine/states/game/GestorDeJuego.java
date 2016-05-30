package epacman.statesmachine.states.game;

import java.awt.Graphics;
import java.awt.Transparency;
import epacman.statesmachine.EstadoJuego;
import epacman.sprites.HojaDeSprites;

public class GestorDeJuego implements EstadoJuego {

    private GestorDeMapa gestorMapa;
    private int player = 1;
    private int enemigo1 = 20;
    private int enemigo2 = 30;
    private int enemigo3 = 40;
    private int enemigo4 = 50;
    private HojaDeSprites hSPersonajes1 = new HojaDeSprites("/Multimedia/Sprites/Sprites Personajes 1.png", 32, 32, Transparency.TRANSLUCENT);
    /*private HojaDeSprites hSPersonajes2 = new HojaDeSprites("/Multimedia/Sprites/Sprites Personajes 2.png", 32, 32, Transparency.TRANSLUCENT);
     private HojaDeSprites hSPersonajes3 = new HojaDeSprites("/Multimedia/Sprites/Sprites Personajes 3.png", 32, 32, Transparency.TRANSLUCENT);
     private HojaDeSprites hSPersonajes4 = new HojaDeSprites("/Multimedia/Sprites/Sprites Personajes 4.png", 32, 32, Transparency.TRANSLUCENT);
     private HojaDeSprites hSFondo1 = new HojaDeSprites("/Multimedia/Sprites/Sprites Fondo 1.png", 32, 32, Transparency.TRANSLUCENT);
     private HojaDeSprites hSFondo2 = new HojaDeSprites("/Multimedia/Sprites/Sprites Fondo 2.png", 32, 32, Transparency.TRANSLUCENT);
     private HojaDeSprites hSFondo3 = new HojaDeSprites("/Multimedia/Sprites/Sprites Fondo 3.png", 32, 32, Transparency.TRANSLUCENT);
     private HojaDeSprites hSFondo4 = new HojaDeSprites("/Multimedia/Sprites/Sprites Fondo 4.png", 32, 32, Transparency.TRANSLUCENT);*/

    @Override
    public void actualizar() {
        if (enemigo1 == 20) {
            enemigo1++;
        } else {
            enemigo1--;
        }
        if (enemigo2 == 30) {
            enemigo2++;
        } else {
            enemigo2--;
        }
        if (enemigo3 == 40) {
            enemigo3++;
        } else {
            enemigo3--;
        }
        if (enemigo4 == 50) {
            enemigo4++;
        } else {
            enemigo4--;
        }
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(hSPersonajes1.getSprite(enemigo1).getImagen(), 0, 0, 32, 32, null);
        g.drawImage(hSPersonajes1.getSprite(enemigo2).getImagen(), 32, 0, 32, 32, null);
        g.drawImage(hSPersonajes1.getSprite(enemigo3).getImagen(), 64, 0, 32, 32, null);
        g.drawImage(hSPersonajes1.getSprite(enemigo4).getImagen(), 96, 0, 32, 32, null);
    }

}
