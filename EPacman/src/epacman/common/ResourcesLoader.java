package epacman.common;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ResourcesLoader {

    private Image image = null;
    private BufferedImage bfImage = null;
    private GraphicsConfiguration graphicsConfig;
    private static Clip sound;

    public BufferedImage loadImage(final String uri, final int transparency) {
        try {
            image = ImageIO.read(getClass().getResource(uri));
        } catch (IOException ex) {
            Logger.getLogger(ResourcesLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        graphicsConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        bfImage = graphicsConfig.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);

        Graphics g = bfImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bfImage;
    }

    public AudioInputStream loadSound(final String uri) {
        AudioInputStream audioStream = null;
        try {
            File audioFile = new File(uri);
            audioStream = AudioSystem.getAudioInputStream(audioFile);
        } catch (UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(ResourcesLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return audioStream;
    }

}
