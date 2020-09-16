package com.company;

import java.awt.*;
import java.util.LinkedList;

public class Engine {

    private Handler handler;

    private int state = 1;

    private boolean moveState = false;

    private int moveCount;

    private Computer computerWhite;

    private Computer computerBlack;

    private LinkedList<GameObject> pieces;

    public Engine( Handler handler, int white, int black ){
        this.handler = handler;
        this.moveCount = 0;

        if ( white == 1){
            computerWhite = new Computer( this, 1 );
        } else {
            computerWhite = null;
        }
        if ( black == 1 ){
            computerBlack = new Computer( this, -1 );
        } else {
            computerBlack = null;
        }
    }

    public void tick(){
        LinkedList<GameObject> pieces = handler.getPieces();
        LinkedList<GameObject> tempPieces = (LinkedList<GameObject>)pieces.clone();
        if ( computerWhite != null ){
            if( state == computerWhite.getState() ){
                computerWhite.move( tempPieces );
            }
        } else if (computerBlack != null ){
            if ( state == computerBlack.getState() ){
                computerBlack.move( tempPieces );
                //System.out.println("i get here");
            }
        }

    }

    private void incrementMoveCount(){
        this.moveCount += 1;
    }

    public boolean checkValid( int currentX, int currentY, int x, int y ){
        int i, size, tempX, tempY;
        GameObject tempObject = handler.getObject(x, y);
        if (tempObject == null ){
            return false;
        }
        if( currentX == x && currentY == y ){
            return false;
        }
        if (tempObject.getWhite() != handler.getState() ){
            return false;
        }
        LinkedList<ValidMove> tempList = tempObject.getValidMoves();
        size = tempList.size();
        for ( i = 0; i < size; i++ ){
            tempX = (int)tempList.get(i).getX();
            tempY = (int)tempList.get(i).getY();
            if( tempX == currentX && tempY == currentY ){
                return true;
            }
        }
        return false;
    }

    public boolean getMoveState() {
        return moveState;
    }

    public void setMoveState(boolean moveState) {
        this.moveState = moveState;
    }

    public void movePiece(int newX, int newY, int currentX, int currentY ){
        GameObject tempObject = handler.getObject(currentX, currentY);
        if (tempObject == null){
            System.out.println("Piece Not Found");
            return;
        }

        if ( state == -1 ){
        }
        handler.placeObject( newX, newY, tempObject );
        tempObject.setX(newX);
        tempObject.setY(newY);
        tempObject.setHasMoved(true);
        handler.removeObject( currentX, currentY);
        state *= -1;
    }

    public int getState(){
        return  state;
    }

}
