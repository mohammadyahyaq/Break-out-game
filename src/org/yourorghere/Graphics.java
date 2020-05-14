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
import static org.yourorghere.Renderer.score;


public class Graphics {
    
    private GL gl;
    private float x ,  y ,  width ,  height;
    private boolean destroyed = false;
    private Item item;
    private int index;
    public static int destroyedNum = 0;
    public Graphics(GL gl, int index) {
        this.gl = gl;
        this.item = new Item(gl);
        this.index = index;
    }
    
    
    
    public void Draw(float x , float y , float width , float height, float red, float green, float blue){
       
         
        if(!this.destroyed){
            this.x = x;  
            this.y = y;  
            this.width = width;   
            this.height = height;
            this.item.setMovementx(x + (width/2)); // x axis for the item in this brick
            this.item.setMovementy(y + (height/2)); // y axis for the item in this brick
            
            gl.glPushMatrix();
            gl.glColor3f(red,green,blue);        
            gl.glBegin(GL.GL_QUADS);
                gl.glVertex2d(x, y);
                gl.glVertex2d(x + width, y);
                gl.glVertex2d(x + width, y + height);
                gl.glVertex2d(x, y + height);
            gl.glEnd();
            gl.glPopMatrix();
         }
        
        
        
        this.collide(); 
    }
    
    


    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
    
    
    public void collide(){
        
        float ballX = Main.render.getBall().getMovementx();
        float ballY = Main.render.getBall().getMovementy();

        if (ballX >= this.x && ballX <= this.x + this.width && ballY >= this.y && ballY <= this.y + this.height) {
            this.destroyed = true;
            destroyedNum++;
            if (ballX > this.x + width/2) {
                System.out.println("Right");
                Main.render.getBall().setxToRight(true);
            } else {
                System.out.println("Left");
                Main.render.getBall().setxToRight(false);
            }
            if (Main.render.getBall().isxToRight()) {
                ballX += Ball.xSpeed;
            } else {
                ballX -= Ball.xSpeed;
            }
            Main.render.getBall().setyToTop(!Main.render.getBall().isyToTop()); // we flip the movement
            if (Main.render.getBall().isyToTop()) {
                ballY += Ball.ySpeed;
            } else {
                ballY -= Ball.ySpeed;
            }
            Renderer.score++;
            if (score >=1) {
                //Ball.xSpeed = Ball.xSpeed + Ball.xSpeed*0.05f;
                Ball.ySpeed = Ball.ySpeed + Ball.ySpeed*0.05f;
            }
            //(Math.random() * ((1 - 0) + 1)) + 0 == 1
            if(((int)(Math.random() * ((10 - 1) + 1)) + 1 ) == 5){
                Main.render.getBricks()[this.index].getItem().generateItem(); // generate an item
            }
        }
        
        // this for loop if there an extra balls to collide
        for (int i = 0; i < 4; i++) {
            Ball[] balls = Main.render.getExtraBalls();
            if(balls[0] != null){
             float eballX = balls[i].getMovementx();
             float eballY = balls[i].getMovementy();
            
            if (eballX >= this.x && eballX <= this.x + this.width && eballY >= this.y && eballY <= this.y + this.height) {
                this.destroyed = true;
                destroyedNum++;
                if (eballX > this.x + width / 2) {
                    System.out.println("Right");
                    Main.render.getBall().setxToRight(true);
                } else {
                    System.out.println("Left");
                    Main.render.getBall().setxToRight(false);
                }
                if (Main.render.getBall().isxToRight()) {
                    eballX += Ball.xSpeed;
                } else {
                    eballX -= Ball.xSpeed;
                }
                //Main.render.getBall().setyToTop(!Main.render.getBall().isyToTop()); // we flip the movement
                balls[i].setyToTop(!balls[i].isyToTop());
                if (Main.render.getBall().isyToTop()) {
                    eballY += Ball.ySpeed;
                } else {
                    eballY -= Ball.ySpeed;
                }
                Renderer.score++;
                //(Math.random() * ((1 - 0) + 1)) + 0 == 1
                if (((int) (Math.random() * ((10 - 1) + 1)) + 1) == 5) {

                    Main.render.getBricks()[this.index].getItem().generateItem(); // generate an item
                }
            }
            }
        }
        
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public Item getItem() {
        return item;
    }
    
    
    
    
}
