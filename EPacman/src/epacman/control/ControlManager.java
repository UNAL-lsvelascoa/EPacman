package epacman.control;

/**
 *
 * @author ErickSteven
 */
public class ControlManager {
    
    public static final Keyboard KEYBOARD = new Keyboard();
    
    public void action(){
        if(KEYBOARD.isExit()){
            System.exit(0);
        }
    }
    
}
