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

import javax.media.opengl.GL;

public class Paddle {

    private float movementx = 0.0f; // this variable for the shifting factor of x
    private GL gl;
    private float xMax, yMax; // we need those two variables to know the cordinates for each vertex in the paddle
    
    public Paddle(GL gl) {
        this.gl = gl;
        this.xMax = 0.1f; // this is the default value for xMax
        this.yMax = 0.025f; // this is the default value for yMax
    }

    public void draw() {
        gl.glPushMatrix();
            gl.glColor3f(0.1f, 0.1f, 0.6f);
            float shiftDown = 0.9f;
            gl.glBegin(GL.GL_QUADS);
                gl.glVertex2f(-xMax + movementx, -yMax - shiftDown);
                gl.glVertex2f(xMax + movementx, -yMax - shiftDown);
                gl.glVertex2f(xMax + movementx, yMax - shiftDown);
                gl.glVertex2f(-xMax + movementx, yMax - shiftDown);
            gl.glEnd();
        gl.glPopMatrix();
        
    }
    
        
    public void tallerPaddel(){
            this.xMax = 0.2f;
    }

    public float getMovementx() {
        return movementx;
    }

    public float getxMax() {
        return xMax;
    }

    public float getyMax() {
        return yMax;
    }

    // the mouse listener gonna use this method to movement
    public void setMovementx(float movementx) {
        this.movementx = movementx;
    }
    
    
}
