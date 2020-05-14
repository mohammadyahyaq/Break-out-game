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

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GLCanvas;

public class Main {

    public static Renderer render;

    public static void main(String[] args) {
        init();
    }

    public static void init() {
            GLCanvas canvas = new GLCanvas();
            render = new Renderer();
            canvas.addGLEventListener(render);
            canvas.addMouseMotionListener(new PaddleMotionListener());
            canvas.addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent ke) {
                    
                }

                public void keyPressed(KeyEvent ke) {
                    char pressed = ke.getKeyChar();
                    if (pressed == KeyEvent.VK_ESCAPE) {
                        render.setIsPaused(!render.isIsPaused());
                    }
                }

                public void keyReleased(KeyEvent ke) {
                    
                }
            });
            Frame frame = new Frame("OpenGL Program");
            frame.add(canvas);
            frame.setSize(1000, 1000);
            frame.setLocationRelativeTo(null); // this will center the widow
            frame.setResizable(false); // this statment gonna make the window unresizable
            
            final Animator animator = new Animator(canvas);
            // Now should add a listener to make the close button!!!
            frame.addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosing(WindowEvent e) {
                    // Run this on another thread than the AWT event queue to
                    // make sure the call to Animator.stop() completes before
                    // exiting
                    new Thread(new Runnable() {

                        public void run() {
                            //animator.stop();
                            System.exit(0);
                        }
                    }).start();
                }
            });
            animator.start(); // we use this part if we want to make an animation

            frame.setVisible(true);
    }
}
