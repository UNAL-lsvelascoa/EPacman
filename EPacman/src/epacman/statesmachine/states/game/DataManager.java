package epacman.statesmachine.states.game;

import epacman.characters.Live;
import epacman.common.Constants;
import java.awt.Graphics;
import java.util.ArrayList;

public class DataManager {

    private static final ArrayList<Live> LIVES = new ArrayList<>();
    
    public DataManager(){
        LIVES.add(new Live(29, 1, Constants.URI_CLASSIC_SPRITES_PLAYER));
        LIVES.add(new Live(29, 3, Constants.URI_CLASSIC_SPRITES_PLAYER));
        LIVES.add(new Live(29, 5, Constants.URI_CLASSIC_SPRITES_PLAYER));
    }

    public void update() {
        for(Live live : LIVES){
            live.update();
        }
    }

    public void paint(Graphics g) {
        for(Live live : LIVES){
            live.paint(g);
        }
    }

}
