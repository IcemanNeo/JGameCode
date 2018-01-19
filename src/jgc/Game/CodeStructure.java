package jgc.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class CodeStructure extends JFrame implements Runnable, GameCommon {

    private BufferStrategy renderBuff;
    private Graphics2D brush;

    private double fps;
    private boolean gamePaused = false;

    //Construct the Game Window
    public CodeStructure() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(3);
        this.setTitle("Game Template");

        Canvas gameCanvas = new Canvas();
        gameCanvas.setSize(GAME_WIDTH, GAME_HEIGHT);
        gameCanvas.setLocation(0, 0);

        this.add(gameCanvas);
        this.setVisible(true);

        gameCanvas.createBufferStrategy(2); //Double Buff = 2; Triple Buff = 3;
        renderBuff = gameCanvas.getBufferStrategy();
    }

    @Override
    //Run the Game Thread / Loop
    public void run() {
        boolean done = false;
        long timeLeft, timeTaken, lastTick;

        while(!done) {

            lastTick = System.nanoTime(); //Set the Beginning Tick

            //Check to pause game?
            if(!gamePaused) {
                updateGame(); //Update Sprites
            }

            renderGame(); //Draw Sprites

            //Calculate Time Delay
            timeTaken = System.nanoTime() - lastTick;
            timeLeft = (OPTIMAL_TIME - timeTaken) / 1000000;

            //Set a Minimum Time Delay
            if (timeLeft < 10) {
                timeLeft = 10;
            }

            try {
                Thread.sleep(timeLeft); //CPU take a breath
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            fps = 1000000000.0 / (System.nanoTime() - lastTick); //Easy way to get FPS
        }
    }

    //Game Update - Sprites etc
    private void updateGame() {

    }

    //Game Drawing / Rendering
    private void renderGame() {
        do {
            try {
                brush = (Graphics2D) renderBuff.getDrawGraphics();

                brush.setColor(Color.BLACK);
                brush.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

                brush.setColor(Color.white);
                brush.drawString(String.valueOf(fps), 10, 10);

                //[ALL GAME DRAWING GOES HERE]

            } finally {
                brush.dispose();
            }

            renderBuff.show();

        } while(renderBuff.contentsLost());
    }
}