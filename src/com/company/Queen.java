package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Queen extends GameObject {


    public Queen(int x, int y, ID id, BufferedImage image, int white, Handler handler) {
        super(x, y, id, image,white, handler);
        this.weight = 10;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage( image,  x * (Game.WIDTH - 2 * Game.BORDER)/8 + Game.BORDER - 1,  y * (Game.HEIGHT - 2 * Game.BORDER)/8 + Game.BORDER - 2, null );
    }

    @Override
    public LinkedList<ValidMove> getValidMoves(){
        int i;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        boolean flag4 = true;
        boolean flag5 = true;
        boolean flag6 = true;
        boolean flag7 = true;
        boolean flag8 = true;
        LinkedList<ValidMove> validMoves = new LinkedList<ValidMove>();
        //validMoves.add( new ValidMove(this.getUniqueID(), x, y, PIECETYPE.MOVE));
        for ( i = 1; i < 8; i++ ){
            if ( x + i <= 7 && y + i <= 7 && flag1 ) {
                if ( handler.getObject(x + i, y + i) == null ){
                    validMoves.add( new ValidMove( this.getUniqueID(), x + i, y + i, PIECETYPE.MOVE));
                } else if ( handler.getObject(x + i, y + i).getWhite() != white ){
                    validMoves.add( new ValidMove( this.getUniqueID(),x + i, y + i, PIECETYPE.MOVE));
                    flag1 = false;
                } else {
                    flag1 = false;
                }
            }
            if ( x - i >= 0 && y + i <= 7 && flag2 ) {
                if ( handler.getObject(x - i, y + i) == null ){
                    validMoves.add( new ValidMove(this.getUniqueID(), x - i, y + i, PIECETYPE.MOVE));
                } else if ( handler.getObject(x - i, y + i).getWhite() != white ){
                    validMoves.add( new ValidMove(this.getUniqueID(), x - i, y + i, PIECETYPE.MOVE));
                    flag2 = false;
                } else {
                    flag2 = false;
                }
            }
            if ( x + i <= 7 && y - i >= 0 && flag3 ) {
                if ( handler.getObject(x + i, y - i) == null ){
                    validMoves.add( new ValidMove( this.getUniqueID(), x + i, y - i, PIECETYPE.MOVE));
                } else if ( handler.getObject(x + i, y - i).getWhite() != white ){
                    validMoves.add( new ValidMove( this.getUniqueID(), x+ i, y - i, PIECETYPE.MOVE));
                    flag3 = false;
                } else {
                    flag3 = false;
                }
            }
            if ( x - i >= 0 && y - i >= 0 && flag4 ) {
                if ( handler.getObject(x - i, y - i) == null ){
                    validMoves.add( new ValidMove(this.getUniqueID(), x - i, y - i, PIECETYPE.MOVE));
                } else if ( handler.getObject(x - i, y - i).getWhite() != white ){
                    validMoves.add( new ValidMove(this.getUniqueID(), x - i, y - i, PIECETYPE.MOVE));
                    flag4 = false;
                } else {
                    flag4 = false;
                }
            }
        }
        for ( i = 1; i < 8; i++ ){
            if ( x + i <= 7 && flag5 ) {
                if ( handler.getObject(x + i, y ) == null ){
                    validMoves.add( new ValidMove(this.getUniqueID(), x + i, y, PIECETYPE.MOVE));
                } else if ( handler.getObject(x + i, y ).getWhite() != white ){
                    validMoves.add( new ValidMove(this.getUniqueID(), x + i, y, PIECETYPE.MOVE));
                    flag5 = false;
                } else {
                    flag5 = false;
                }
            }
            if (  y + i <= 7 && flag6 ) {
                if ( handler.getObject(x , y + i) == null ){
                    validMoves.add( new ValidMove(this.getUniqueID(), x , y + i, PIECETYPE.MOVE));
                } else if ( handler.getObject(x , y + i).getWhite() != white ){
                    validMoves.add( new ValidMove(this.getUniqueID(), x , y + i, PIECETYPE.MOVE));
                    flag6 = false;
                } else {
                    flag6 = false;
                }
            }
            if (  y - i >= 0 && flag7 ) {
                if ( handler.getObject(x , y - i) == null ){
                    validMoves.add( new ValidMove(this.getUniqueID(), x , y - i, PIECETYPE.MOVE));
                } else if ( handler.getObject(x , y - i).getWhite() != white ){
                    validMoves.add( new ValidMove(this.getUniqueID(), x, y - i, PIECETYPE.MOVE));
                    flag7 = false;
                } else {
                    flag7 = false;
                }
            }
            if ( x - i >= 0  && flag8 ) {
                if ( handler.getObject(x - i, y ) == null ){
                    validMoves.add( new ValidMove(this.getUniqueID(), x - i, y, PIECETYPE.MOVE));
                } else if ( handler.getObject(x - i, y).getWhite() != white ){
                    validMoves.add( new ValidMove(this.getUniqueID(),x - i, y, PIECETYPE.MOVE));
                    flag8 = false;
                } else {
                    flag8 = false;
                }
            }
        }
        return validMoves;
    }
}
