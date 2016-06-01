package epacman.sprites;

import java.awt.image.BufferedImage;
import epacman.tools.CargadorDeRecursos;

public class SpritesSheet {

    final private int anchoHojaEnPixeles, altoHojaEnPixeles,
            anchoHojaEnSprites, altoHojaEnSprites,
            anchoSprites, altoSprites;

    final private Sprite[] sprites;

    private static final CargadorDeRecursos cargador = new CargadorDeRecursos();

    public SpritesSheet(final String ruta, final int anchoSprites, final int altoSprites, final int transparente) {
        BufferedImage imagen;
        imagen = cargador.cargarImagenCompatible(ruta, transparente);

        anchoHojaEnPixeles = imagen.getWidth();
        altoHojaEnPixeles = imagen.getHeight();
        anchoHojaEnSprites = anchoHojaEnPixeles / anchoSprites;
        altoHojaEnSprites = altoHojaEnPixeles / altoSprites;
        this.anchoSprites = anchoSprites;
        this.altoSprites = altoSprites;

        sprites = new Sprite[anchoHojaEnSprites * altoHojaEnSprites];
        rellenarSprites(imagen);
    }

    private void rellenarSprites(final BufferedImage imagen) {

        for (int i = 0; i < altoHojaEnSprites; i++) {
            for (int j = 0; j < anchoHojaEnSprites; j++) {
                final int posicionX = j * anchoSprites;
                final int posicionY = i * altoSprites;
                sprites[j + i * anchoHojaEnSprites] = new Sprite(imagen.getSubimage(posicionX, posicionY, anchoSprites, altoSprites));
            }
        }
    }

    public Sprite getSprite(final int indice) {
        return sprites[indice];
    }

    public Sprite getSprite(final int x, final int y) {
        return sprites[x + y * anchoHojaEnSprites];
    }

}
