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

import com.sun.opengl.util.j2d.TextRenderer;
import java.awt.Font;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class Renderer implements GLEventListener {

    private GL gl;
    private Ball ball;
    private Ball[] extraBalls;
    private Ball[] ghostBalls;
    private Paddle paddle;
    private Item item;
    private Graphics[] bricks;
    private float row;
    private float column;
    private TextRenderer rendrer;
    public static int score;
    private boolean isPaused;
    private boolean isLosed;
    private boolean isWon;

    @Override
    public void init(GLAutoDrawable drawable) {
// this method gonna called once, after the program starts
        gl = drawable.getGL();

        this.ball = new Ball(0.02f, gl, false);
        this.extraBalls = new Ball[4];
        this.ghostBalls = new Ball[4];
        this.paddle = new Paddle(gl);
        this.bricks = new Graphics[28];
        this.item = new Item(gl);
        this.row = 0.95f;
        this.column = -0.9f;
        this.rendrer = new TextRenderer(new Font("SansSerif", Font.BOLD, 36));
        for (int i = 0; i < bricks.length; i++) {
            this.bricks[i] = new Graphics(gl, i);
        }

        gl.glColor3f(0.3f, 0.2f, 0);
        gl.glOrtho(-1000, 1000, -1000, 1000, 0, 0); // this gonna set the start cordinates and the last cordinate
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        if (isPaused) {
            gl.glClearColor(0.678f, 0.941f, 0.478f, 1); // this gonna make the background gray
            gl.glClear(GL.GL_COLOR_BUFFER_BIT);
            rendrer.beginRendering(drawable.getWidth(), drawable.getHeight());
            // optionally set the color
            rendrer.setColor(0.3f, 0.4f, 0.3f, 0.8f);
            rendrer.draw("Press Esc to continue...", 275, 500);
            // ... more draw commands, color changes, etc.
            rendrer.endRendering();
            waitFrame();
        } else if (isLosed) {
            gl.glClearColor(0.678f, 0.5f, 0.5f, 1); // this gonna make the background gray
            gl.glClear(GL.GL_COLOR_BUFFER_BIT);
            rendrer.beginRendering(drawable.getWidth(), drawable.getHeight());
            // optionally set the color
            rendrer.setColor(0.3f, 0.4f, 0.3f, 0.8f);
            rendrer.draw("You Lost, Please try again :(", 250, 500);
            // ... more draw commands, color changes, etc.
            rendrer.endRendering();
            waitFrame();
        } else if (Graphics.destroyedNum == 28) {
            isWon = true;
            gl.glClearColor(0.678f, 0.941f, 0.478f, 1); // this gonna make the background gray
            gl.glClear(GL.GL_COLOR_BUFFER_BIT);
            rendrer.beginRendering(drawable.getWidth(), drawable.getHeight());
            // optionally set the color
            rendrer.setColor(0.3f, 0.4f, 0.3f, 0.8f);
            rendrer.draw("Congratulations, You won!", 275, 500);
            // ... more draw commands, color changes, etc.
            rendrer.endRendering();
            waitFrame();
        } else {
            // we use this method to draw on the canvas!
            gl.glClearColor(0.9f, 0.91f, 0.9f, 1); // this gonna make the background gray
            gl.glClear(GL.GL_COLOR_BUFFER_BIT);
            this.row = 0.95f;
            for (int i = 0; i < bricks.length; i++) {

                if (i % 7 == 0) {
                    column = -0.9f;
                    row -= 0.18f;
                }
                // if the brick destroyed it will not draw again
                if (!bricks[i].isDestroyed()) {
                    bricks[i].Draw(column, row, 0.2f, 0.05f, 1.0f, 0.6f, 0.0f);
                } else {
                    bricks[i].getItem().draw(); // generate an item
                }
                column += 0.25;
            }

            if (extraBalls[0] != null) { // for extra balls
                for (int i = 0; i < extraBalls.length; i++) {
                    extraBalls[i].draw();
                }
            }

            if (ghostBalls[0] != null) { // for ghost balls
                for (int i = 0; i < ghostBalls.length; i++) {
                    ghostBalls[i].draw();
                }
            }
        }
        this.isLosed = this.isLosedChecker(); // we will check the losing state in each iteration
        if ((!isWon && !isLosed && !isPaused)) {
            this.ball.draw(); // we should draw the ball
            this.paddle.draw();
        }
        //For Scoring system
        rendrer.beginRendering(drawable.getWidth(), drawable.getHeight());
        // optionally set the color
        rendrer.setColor(1.0f, 0.2f, 0.2f, 0.8f);
        rendrer.draw("Score is : " + score, 0, 940);
        // ... more draw commands, color changes, etc.
        rendrer.endRendering();
        waitFrame();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // we use this method when we want to resize the window

        GLU glu = new GLU();

        gl.glMatrixMode(GL.GL_PROJECTION); // this gonna make the shape not affected by the size of the window

        gl.glLoadIdentity();
        //glu.gluPerspective(45.0f, (float) width / (float) height, 0.0f, 10.0f);

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {

    }

    public Ball getBall() {
        return ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public Item getItem() {
        return item;
    }

    public Graphics[] getBricks() {
        return bricks;
    }

    public Ball[] getExtraBalls() {
        return extraBalls;
    }

    public Ball[] getGhostBalls() {
        return ghostBalls;
    }

    /**
     * This method gonna wait to make sure the frame rate always 60 FPS
     */
    private void waitFrame() {
        long before = System.currentTimeMillis();
        long after = before;
        while (after - before < 16) {
            after = System.currentTimeMillis();
        }
    }

    private void pauseScreen() {

    }

    public boolean isIsPaused() {
        return isPaused;
    }

    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    private boolean isLosedChecker() {
        boolean noExtraBalls = true;
        if (extraBalls[0] != null) {
            for (int i = 0; i < extraBalls.length; i++) {
                if (!extraBalls[i].isOut()) {
                    noExtraBalls = false;
                }
            }
        } else {
            noExtraBalls = true;
        }

        boolean ballNotExist = ball.isOut();

        if (ballNotExist && noExtraBalls) {
            return true;
        } else {
            return false;
        }
    }

}
