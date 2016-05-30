package epacman.maps;

import java.awt.Rectangle;
import epacman.sprites.Sprite;

public class Tile {
    
    private final Sprite sprite;
    private final int id;       //Identificador único de cada Tile
    private boolean solido;
    
    public Tile(final Sprite sprite, final int id, final boolean solido){
        this.sprite = sprite;
        this.id = id;
        this.solido = solido;
    }

    public int getId() {
        return id;
    }
    public boolean isSolido() {
        return solido;
    }
    public void setSolido(final boolean solido) {
        this.solido = solido;
    }
    public Sprite getSprite() {
        return sprite;
    }
    
    public Rectangle getLimites(final int x, final int y){
        return new Rectangle(x, y, sprite.getAncho(), sprite.getAlto());
    }
    
}
