package epacman.control;

import epacman.common.Constants;

/**
 *
 * @author ErickSteven
 */
public class ControlManager {
    
    public static final Keyboard KEYBOARD = new Keyboard();
    
    public static int direction(){
        if(KEYBOARD.isLeft()){
            return Constants.LEFT;
        }
        if(KEYBOARD.isUp()){
            return Constants.UP;
        }
        if(KEYBOARD.isRight()){
            return Constants.RIGHT;
        }
        if(KEYBOARD.isDown()){
            return Constants.DOWN;
        }
        return -1;
    }
    
    public void action(){
        if(KEYBOARD.isExit()){
            System.exit(0);
        }
    }
    
}
