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

public class ResourcesLoader {
    
    private Image image = null;
    private BufferedImage bfImage = null;
    private GraphicsConfiguration graphicsConfig;
    
    public BufferedImage cargarImagenCompatible(final String ruta, final int transparencia){
        try {
            image = ImageIO.read(getClass().getResource(ruta));
        } catch (IOException ex) {
            Logger.getLogger(ResourcesLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        graphicsConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        bfImage = graphicsConfig.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparencia);
        
        Graphics g = bfImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        
        return bfImage;
    }
    
}
