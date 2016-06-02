package epacman.sprites;

import epacman.tools.ResourcesLoader;
import java.awt.image.BufferedImage;

public class SpritesSheet {

    final private int anchoHojaEnPixeles, altoHojaEnPixeles,
            anchoHojaEnSprites, altoHojaEnSprites,
            anchoSprites, altoSprites;

    final private Sprite[] sprites;

    private static final ResourcesLoader loader = new ResourcesLoader();

    public SpritesSheet(final String ruta, final int anchoSprites, final int altoSprites, final int transparente) {
        BufferedImage image;
        image = loader.loadImage(ruta, transparente);

        anchoHojaEnPixeles = image.getWidth();
        altoHojaEnPixeles = image.getHeight();
        anchoHojaEnSprites = anchoHojaEnPixeles / anchoSprites;
        altoHojaEnSprites = altoHojaEnPixeles / altoSprites;
        this.anchoSprites = anchoSprites;
        this.altoSprites = altoSprites;

        sprites = new Sprite[anchoHojaEnSprites * altoHojaEnSprites];
        rellenarSprites(image);
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
