package principal.sprites;

import java.awt.image.BufferedImage;

public class Sprite {
    
    private final BufferedImage imagen;
    private final int ancho, alto;
    
    public Sprite(final BufferedImage imagen){
        this.imagen = imagen;
        this.ancho = imagen.getWidth();
        this.alto = imagen.getHeight();
    }

    public int getAlto() {
        return alto;
    }
    public int getAncho() {
        return ancho;
    }
    public BufferedImage getImagen() {
        return imagen;
    }
    
}
