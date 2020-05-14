/*
ÈÓã Çááå ÇáÑÍãä ÇáÑÍíã
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

public class Ball {

    private GL gl;

    private float raduis;
    private boolean xToRight = true;
    private boolean yToTop = true;
    private float movementx = 0.0f; // this variable for the shifting factor of x
    private float movementy = 0.0f; // this variable for the shifting factor of y
    private long startTime = -1;
    private long finishTime = 0;
    private boolean isGhost;
    private boolean isOut;
    public static float xSpeed = 0.01f;
    public static float ySpeed = 0.01f;
    public Ball(float raduis, GL gl, boolean isGhost) {
        this.gl = gl;
        this.raduis = raduis;
        this.isGhost = isGhost;
        this.isOut = false;
    }

    public void draw() {
        if (!isOut) {
            if(startTime >= finishTime){
            this.raduis = 0.02f;
        }
        
        gl.glPushMatrix();
        if (!isGhost) {
            gl.glColor3f(0.5f, 0, 0.5f);
        } else {
            gl.glColor3f(0.5f, 0.4f, 0.5f);
        }
        gl.glBegin(GL.GL_POLYGON);
        for (float i = 0; i < 2 * Math.PI; i += (float) Math.PI / 25) {
            gl.glVertex2f((float) Math.cos(i) * raduis + movementx, (float) Math.sin(i) * raduis + movementy);
        }
        gl.glEnd();
        gl.glPopMatrix();

        startTime += 15;
        updateX(); // now we should update the movement factor of x
        updateY(); // now we should update the movement factor of y
        }
    }

    private void updateX() {
        if (isCollide()) {
            xToRight = !xToRight; // we flip the movement
            if (xToRight) {
                movementx += xSpeed;
            } else {
                movementx -= xSpeed;
            }
            
            if (movementx >= 0) {
                xToRight = true;
            } else {
                xToRight = false;
            }
            
        } else if (xToRight) {
            if (movementx > 1) {
                xToRight = false;
                movementx -= xSpeed;
            } else {
                movementx += xSpeed;
            }
        } else {
            if (movementx < -1) {
                xToRight = true;
                movementx += xSpeed;
            } else {
                movementx -= xSpeed;
            }
        }
    }

    private void updateY() {
        if (isCollide()) {
            yToTop = !yToTop; // we flip the movement
            if (yToTop) {
                movementy += ySpeed;
            } else {
                movementy -= ySpeed;
            }
        } else if (yToTop) {
            if (movementy > 1) {
                yToTop = false;
                movementy -= ySpeed;
            } else {
                movementy += ySpeed;
            }
        } else {
            if (movementy < -1) {
                // here the ball is got out of the map
                isOut = true;
            } else {
                movementy -= ySpeed;
            }
        }
    }
    
    private boolean isCollide () {
        float paddleLeft = Main.render.getPaddle().getMovementx() - Main.render.getPaddle().getxMax();
        float paddleRight = Main.render.getPaddle().getMovementx() + Main.render.getPaddle().getxMax();
        
        float paddleSurface = Main.render.getPaddle().getyMax() - 0.9f; // this will calculate the y cordinate of the paddle surface
        
        if (movementy <= paddleSurface + raduis + 0.015 && movementy >= paddleSurface + raduis  - 0.015) {
            // I made 0.015 as an error factor
            if (movementx > paddleLeft && movementx < paddleRight) {
                // in this case the paddle is collide with the ball so we should update the refraction factor
                if (Main.render.getPaddle().getMovementx() > movementx) {
                    xToRight = false;
                }
                else {
                    xToRight = true;
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public void smallBall(){
        
        this.setRaduis(0.01f);
        this.startTime = System.currentTimeMillis();
        this.finishTime = System.currentTimeMillis() + 30000;
        
    }
    
    public void multiBalls(){
        
        for (int i = 0; i < 4; i++) {
            Main.render.getExtraBalls()[i] = new Ball(0.02f, gl, false);   
            Main.render.getExtraBalls()[i].setMovementx(movementx + i/0.25f * 0.1f);
            Main.render.getExtraBalls()[i].setMovementy(movementy + i/0.25f * 0.1f);
        }

    }
    
    public void ghostBalls(){
        
        for (int i = 0; i < 4; i++) {
            Main.render.getGhostBalls()[i] = new Ball(0.02f, gl, true);   
            Main.render.getGhostBalls()[i].setMovementx(movementx + i/0.25f * 0.2f);
            Main.render.getGhostBalls()[i].setMovementy(movementy + i/0.25f * 0.2f);
             Main.render.getGhostBalls()[i].gl.glColor3f(0.6f, 0.0f, 0.0f);
        }

    }

    public float getMovementx() {
        return movementx;
    }

    public void setMovementx(float movementx) {
        this.movementx = movementx;
    }

    public float getMovementy() {
        return movementy;
    }

    public void setMovementy(float movementy) {
        this.movementy = movementy;
    }

    public float getRaduis() {
        return raduis;
    }

    public void setRaduis(float raduis) {
        this.raduis = raduis;
    }

    public boolean isxToRight() {
        return xToRight;
    }

    public void setxToRight(boolean xToRight) {
        this.xToRight = xToRight;
    }

    public boolean isyToTop() {
        return yToTop;
    }

    public void setyToTop(boolean yToTop) {
        this.yToTop = yToTop;
    }

    public boolean isOut() {
        return isOut;
    }
}
