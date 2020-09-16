package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1200002121321L;

    public static final int WIDTH = 600, HEIGHT = WIDTH, BORDER = 60, TILE = (WIDTH - 2 * BORDER) / 8;
    private int state = -1;

    private Thread thread;
    private boolean running = false;
    private HUD hud;
    private static LinkedList<BufferedImage> pieceImages = new LinkedList<BufferedImage>();


    private Handler handler;


    public Game(){
        handler = new Handler();
        hud = new HUD();
        this.addKeyListener( new KeyInput( handler ) );
        this.addMouseListener( new MouseInput( handler ));
        new Window( WIDTH, HEIGHT, "Spooky Game", this );
        //handler.addObject( new Piece( 1 , 1, ID.TestPiece, handler), 1, 1 );
        //handler.addObject( new Piece( 2 , 2, ID.TestPiece, handler), 2, 2 );
        handler.createPieces();
        Random r = new Random();


    }

    public synchronized void start(){
        ImageLoader imageLoader = new ImageLoader();
        pieceImages.add(ImageLoader.loadImage(".\\src\\res\\images\\PawnWhite.png"));
        pieceImages.add(ImageLoader.loadImage(".\\src\\res\\images\\PawnBlack.png"));
        pieceImages.add(ImageLoader.loadImage(".\\src\\res\\images\\RookWhite.png"));
        pieceImages.add(ImageLoader.loadImage(".\\src\\res\\images\\RookBlack.png"));
        pieceImages.add(ImageLoader.loadImage(".\\src\\res\\images\\KnightWhite.png"));
        pieceImages.add(ImageLoader.loadImage(".\\src\\res\\images\\KnightBlack.png"));
        pieceImages.add(ImageLoader.loadImage(".\\src\\res\\images\\BishopWhite.png"));
        pieceImages.add(ImageLoader.loadImage(".\\src\\res\\images\\BishopBlack.png"));
        pieceImages.add(ImageLoader.loadImage(".\\src\\res\\images\\KingWhite.png"));
        pieceImages.add(ImageLoader.loadImage(".\\src\\res\\images\\KingBlack.png"));
        pieceImages.add(ImageLoader.loadImage(".\\src\\res\\images\\QueenWhite.png"));
        pieceImages.add(ImageLoader.loadImage(".\\src\\res\\images\\QueenBlack.png"));
        thread = new Thread(this );
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch ( Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while( running ){
            long now = System.nanoTime();
            delta += ( now - lastTime ) / ns;
            lastTime = now;
            while ( delta >= 1) {
                tick();
                delta--;
            }
            if ( running )
                render();
            frames++;
            if( System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println( "FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        hud.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null ){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor( Color.BLACK );
        g.fillRect(0, 0, WIDTH, HEIGHT);

        hud.render( g );
        handler.render( g );

        g.dispose();
        bs.show();
    }

    public static int clamp( int var, int min, int max ){
        if ( var >= max)
            return max;
        else if ( var <= min )
            return min;
        else
            return var;
    }

    public static BufferedImage getImage( int i ){
        return pieceImages.get(i);
    }



    public static void main(String[] args) {
        new Game();
    }
}
