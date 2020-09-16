package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Knight extends GameObject {

    public Knight(int x, int y, ID id, BufferedImage image, int white, Handler handler) {
        super(x, y, id, image, white, handler);
        this.weight = 4;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage( image,  x * (Game.WIDTH - 2 * Game.BORDER)/8 + Game.BORDER - 1,  y * (Game.HEIGHT - 2 * Game.BORDER)/8 + Game.BORDER - 2, null );
    }

    @Override
    public LinkedList<ValidMove> getValidMoves() {
        int i, tempX, tempY, size;
        LinkedList<ValidMove> validMoves = new LinkedList<ValidMove>();
        validMoves.add(new ValidMove(this.getUniqueID(), x + 1, y + 2, PIECETYPE.MOVE));
        validMoves.add(new ValidMove(this.getUniqueID(), x + 1, y - 2, PIECETYPE.MOVE));
        validMoves.add(new ValidMove(this.getUniqueID(), x - 1, y + 2, PIECETYPE.MOVE));
        validMoves.add(new ValidMove(this.getUniqueID(), x - 1, y - 2, PIECETYPE.MOVE));
        validMoves.add(new ValidMove(this.getUniqueID(), x + 2, y + 1, PIECETYPE.MOVE));
        validMoves.add(new ValidMove(this.getUniqueID(), x + 2, y - 1, PIECETYPE.MOVE));
        validMoves.add(new ValidMove(this.getUniqueID(), x - 2, y + 1, PIECETYPE.MOVE));
        validMoves.add(new ValidMove(this.getUniqueID(), x - 2, y - 1, PIECETYPE.MOVE));
        size = 8;
        for( i = 0; i < size; i++ ){
            tempX = (int)validMoves.get(i).getX();
            tempY = (int)validMoves.get(i).getY();
            if ( tempX < 0 || tempX > 7 || tempY < 0 || tempY > 7){
                validMoves.remove(i);
                i -= 1;
                size -= 1;
            }
        }
        for( i = 0; i < size; i++ ){
            tempX = (int)validMoves.get(i).getX();
            tempY = (int)validMoves.get(i).getY();
            if( handler.getObject( tempX, tempY ) != null){
                if ( handler.getObject( tempX, tempY ).getWhite() == this.getWhite() ){
                    validMoves.remove(i);
                    i -= 1;
                    size -= 1;
                }
            }
        }
        //validMoves.add( new ValidMove(this.getUniqueID(), x, y, PIECETYPE.MOVE));
        return validMoves;
    }
}
