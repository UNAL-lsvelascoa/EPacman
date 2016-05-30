package epacman.tools;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class CargadorDeRecursos {
    
    private Image imagen = null;
    private BufferedImage imagenAcelerada = null;
    private GraphicsConfiguration configuracionG;
    
    public BufferedImage cargarImagenCompatible(final String ruta, final int transparencia){
        try {
            imagen = ImageIO.read(getClass().getResource(ruta));
        } catch (IOException ex) {
            Logger.getLogger(CargadorDeRecursos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        configuracionG = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        imagenAcelerada = configuracionG.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null), transparencia);
        
        Graphics g = imagenAcelerada.getGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();
        
        return imagenAcelerada;
    }
    
}
