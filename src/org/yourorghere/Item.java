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

import java.util.Random;
import javax.media.opengl.GL;

public class Item {

    private GL gl;

    private float movementx; // this variable for the shifting factor of x
    private float movementy; // this variable for the shifting factor of y
    private boolean generated = false;
    private float radius = 0.03f;
    public Item(GL gl) {
        this.gl = gl;
    }

    public void draw() {

        if (this.generated) {
            this.isCollide();
            gl.glPushMatrix();
            gl.glColor3f(0.0f, 0.5f, 0.0f);
            gl.glBegin(GL.GL_POLYGON);
            for (float i = 0; i < 2 * Math.PI; i += (float) Math.PI / 25) {
                gl.glVertex2f((float) Math.cos(i) * radius + movementx, (float) Math.sin(i) * radius + movementy);
            }
            gl.glEnd();
            gl.glPopMatrix();
        }

        movementy += -0.002f;    // here to make the item go down

    }
    /*
    
    float paddleLeft = Main.render.getPaddle().getMovementx() - 0.1f;
        float paddleRight = Main.render.getPaddle().getMovementx() + 0.1f;

        if (movementy <= -0.87f && movementy >= -0.88f) {

            if (this.movementx > paddleLeft && this.movementx < paddleRight) {
                this.deleteItem();
                Random randomObj = new Random();
                int random = randomObj.nextInt((6 - 1) + 1) + 1;
                //int random = ((int) (Math.random() * ((5 - 1) + 1)) + 1);
                //int random = 1;
    
    */
    

    private void isCollide() {
        float paddleLeft = Main.render.getPaddle().getMovementx() - Main.render.getPaddle().getxMax();
        float paddleRight = Main.render.getPaddle().getMovementx() + Main.render.getPaddle().getxMax();

        float paddleSurface = Main.render.getPaddle().getyMax() - 0.9f; // this will calculate the y cordinate of the paddle surface

        if (movementy <= paddleSurface + radius + 0.015 && movementy >= paddleSurface + radius - 0.015) {
            // I made 0.015 as an error factor
            if (movementx > paddleLeft && movementx < paddleRight) {
                this.deleteItem();
                Random randomObj = new Random();
                //int random = randomObj.nextInt((6 - 1) + 1) + 1;
                int random = ((int) (Math.random() * ((5 - 1) + 1)) + 1);
                //int random = 2;
                switch (random) {
                    case 1:
                        this.tallerPaddel();
                        break;
                    case 2:
                        this.smallBall();
                        break;
                    case 3:
                        this.multiBalls();
                        break;
                    case 4:
                        this.iiiusion();
                        break;
                    case 5:
                        doubleScore();
                        break;
                    case 6:
                        Slowness();
                        break;
                }

            }
        }
    }

    public void doubleScore() {

        Renderer.score = Renderer.score * 2;

    }

    public void multiBalls() {

        Main.render.getBall().multiBalls();

    }

    public void Slowness() {
        Ball.ySpeed = Ball.ySpeed * 0.5f;
    }

    public void smallBall() {

        Main.render.getBall().smallBall();

    }

    public void iiiusion() {

        Main.render.getBall().ghostBalls();

    }

    public void tallerPaddel() {

        Main.render.getPaddle().tallerPaddel();

    }

    public void generateItem() {

        this.generated = true;

    }

    public void deleteItem() {
        this.generated = false;
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

}
