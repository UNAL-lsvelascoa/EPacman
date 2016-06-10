package epacman.sprites;

import epacman.common.ResourcesLoader;
import java.awt.image.BufferedImage;

public class SpritesSheet {

    private final int sheetPixelWidth;
    private final int altoHojaEnPixeles;
    private final int sheetSpriteWidth;
    private final int altoHojaEnSprites;
    private final int spriteWidth;
    private final int spriteHeight;

    final private Sprite[] sprites;

    private static final ResourcesLoader LOADER = new ResourcesLoader();

    public SpritesSheet(final String uri, final int spriteWidth, final int spriteHeight, final int transparency) {
        BufferedImage image;
        image = LOADER.loadImage(uri, transparency);

        sheetPixelWidth = image.getWidth();
        altoHojaEnPixeles = image.getHeight();
        sheetSpriteWidth = sheetPixelWidth / spriteWidth;
        altoHojaEnSprites = altoHojaEnPixeles / spriteHeight;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        sprites = new Sprite[sheetSpriteWidth * altoHojaEnSprites];
        rellenarSprites(image);
    }

    private void rellenarSprites(final BufferedImage imagen) {

        for (int i = 0; i < altoHojaEnSprites; i++) {
            for (int j = 0; j < sheetSpriteWidth; j++) {
                final int posicionX = j * spriteWidth;
                final int posicionY = i * spriteHeight;
                sprites[j + i * sheetSpriteWidth] = new Sprite(imagen.getSubimage(posicionX, posicionY, spriteWidth, spriteHeight));
            }
        }
    }

    public Sprite getSprite(final int indice) {
        return sprites[indice];
    }

    public Sprite getSprite(final int x, final int y) {
        return sprites[x + y * sheetSpriteWidth];
    }

}
