package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

public abstract class GameObject {

    protected int x, y;
    protected ID id;
    protected int width;
    protected int height;
    protected int white;
    protected int hasMoved;
    protected BufferedImage image;
    protected int weight;
    protected Handler handler;
    private int uniqueID;

    public GameObject( int x, int y, ID id, BufferedImage image, int white, Handler handler ){
        Random rand = new Random();
        rand.setSeed(System.nanoTime());
        this.uniqueID = rand.nextInt(1000000000);
        this.x = x;
        this.y = y;
        this.white = white;
        this.handler = handler;
        //this.x = x * (Game.WIDTH - 2 * Game.BORDER)/8 + Game.BORDER;
        //this.y = y * (Game.HEIGHT - 2 * Game.BORDER)/8 + Game.BORDER;
        this.id = id;
        this.image = image;
        this.width = 60;
        this.height = 60;
        this.hasMoved = 0;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public Rectangle getBounds(){
        return new Rectangle(  x * (Game.WIDTH - 2 * Game.BORDER)/8 + Game.BORDER, y * (Game.HEIGHT - 2 * Game.BORDER)/8 + Game.BORDER, width, height );
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getUniqueID(){ return this.uniqueID; }

    public abstract LinkedList<ValidMove> getValidMoves();

    public int getWhite() {
        return white;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved += 1;
    }

    public int getWeight(){
        return this.weight;
    }
}
