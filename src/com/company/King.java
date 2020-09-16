package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class King extends GameObject {


    public King(int x, int y, ID id, BufferedImage image, int white, Handler handler) {
        super(x, y, id, image, white, handler);
        this.weight = 1000;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x * (Game.WIDTH - 2 * Game.BORDER) / 8 + Game.BORDER - 1, y * (Game.HEIGHT - 2 * Game.BORDER) / 8 + Game.BORDER - 2, null);
    }

    public LinkedList<ValidMove> getValidMoves(){
        int i, len, tempX, tempY;

        LinkedList<ValidMove> validMoves = new LinkedList<ValidMove>();
        if( x + 1 < 8  && y + 1 < 8){
            validMoves.add( new ValidMove( this.getUniqueID(), x + 1, y + 1, PIECETYPE.MOVE ) );
        }
        if( x + 1 < 8  && y - 1 >= 0){
            validMoves.add( new ValidMove( this.getUniqueID(), x + 1, y - 1, PIECETYPE.MOVE ) );
        }
        if( x + 1 < 8){
            validMoves.add( new ValidMove( this.getUniqueID(), x + 1, y, PIECETYPE.MOVE ) );
        }
        if( y - 1 >= 0){
            validMoves.add( new ValidMove( this.getUniqueID(), x , y - 1, PIECETYPE.MOVE ) );
        }
        if( x - 1 >= 0  && y + 1 < 8){
            validMoves.add( new ValidMove( this.getUniqueID(), x - 1, y + 1, PIECETYPE.MOVE ) );
        }
        if( x - 1 >= 0  && y - 1 >= 0){
            validMoves.add( new ValidMove( this.getUniqueID(), x - 1, y - 1, PIECETYPE.MOVE ) );
        }
        if( x - 1 >= 0 ){
            validMoves.add( new ValidMove( this.getUniqueID(), x - 1, y, PIECETYPE.MOVE ) );
        }
        if( y + 1 < 8){
            validMoves.add( new ValidMove( this.getUniqueID(), x , y + 1, PIECETYPE.MOVE ) );
        }
        len = validMoves.size();
        for( i = 0; i < len; i++ ){
            tempX = (int)validMoves.get(i).getX();
            tempY = (int)validMoves.get(i).getY();
            if( handler.getObject( tempX, tempY ) != null){
                if ( handler.getObject( tempX, tempY ).getWhite() == this.getWhite() ){
                    validMoves.remove(i);
                    i -= 1;
                    len -= 1;
                }
            }
        }

        return validMoves;
    }
}