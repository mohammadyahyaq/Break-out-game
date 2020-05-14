/*
»”„ «··Â «·—Õ„‰ «·—ÕÌ„
CPCS-391 Final Group Project
==============BreakOut Video Game=================
Students names: 
1- Ammar Joharji (1742559)
2- Mohammed Algafili (1741679)
3- Abdulrahman Alharithi (1741096)
Note: PLEASE launch the game from the MainMenuScreen.java!
*/
package org.yourorghere;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PaddleMotionListener implements MouseMotionListener{

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Main.render.getPaddle().setMovementx(((float)(e.getX() - 500.0f) / 500));
    }
    
}
