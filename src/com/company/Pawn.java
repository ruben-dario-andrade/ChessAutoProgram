package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class Pawn extends GameObject{

    private int moveCount;

    public Pawn( int x, int y, ID id, BufferedImage image, int white, Handler handler){
        super( x, y, id, image, white,handler);
        this.weight = 1;
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
        LinkedList<ValidMove> validMoves = new LinkedList<ValidMove>();
        ///alidMoves.add( new ValidMove( this.getUniqueID(), x, y, PIECETYPE.MOVE ) );
        if ( white == 1){
            //validMoves.add(new Point( x, y));
            if ( handler.getObject( x, y - 1) == null) {
                validMoves.add(new ValidMove( this.getUniqueID(), x, Game.clamp(y - 1, 0, 7), PIECETYPE.MOVE ));
            }
            if (hasMoved == 0 && handler.getObject(x, y - 2 ) == null && handler.getObject(x, y - 1) == null ) {
                validMoves.add(new ValidMove( this.getUniqueID(), x, Game.clamp(y - 2, 0, 7), PIECETYPE.MOVE ));
            }

            if (handler.getObject(x + 1, y - 1) != null){
                if ( handler.getObject(x + 1, y - 1).getWhite() == -1 ){
                    validMoves.add(new ValidMove( this.getUniqueID(),x + 1, y - 1, PIECETYPE.MOVE ) );
                }
            }
            if (handler.getObject(x - 1, y - 1) != null){
                if ( handler.getObject(x - 1, y - 1).getWhite() == -1 ){
                    validMoves.add(new ValidMove( this.getUniqueID(), x - 1, y - 1, PIECETYPE.MOVE ) );
                }
            }

        } else {
            //validMoves.add(new Point( x, y));
            if ( handler.getObject(x, y + 1) == null) {
                validMoves.add(new ValidMove(this.getUniqueID(), x, Game.clamp(y + 1, 0, 7), PIECETYPE.MOVE ) );
            }
            if (hasMoved == 0 && handler.getObject(x, y + 2 ) == null && handler.getObject(x, y + 1) == null) {
                validMoves.add(new ValidMove( this.getUniqueID(), x, Game.clamp(y + 2, 0, 7), PIECETYPE.MOVE ));
            }

            if (handler.getObject(x + 1, y + 1) != null){
                if ( handler.getObject(x + 1, y + 1).getWhite() == 1 ){
                    validMoves.add(new ValidMove(this.getUniqueID(), x + 1, y + 1, PIECETYPE.MOVE ) );
                }
            }
            if (handler.getObject(x - 1, y + 1) != null){
                if ( handler.getObject(x - 1, y + 1).getWhite() == 1 ){
                    validMoves.add(new ValidMove( this.getUniqueID(),x - 1, y + 1, PIECETYPE.MOVE ) );
                }
            }
        }
        //System.out.println("spook");
        return validMoves;
    }

}












