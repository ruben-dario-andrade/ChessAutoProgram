package com.company;

import java.util.LinkedList;

public class PseudoGame {

    private GameObject[][] objects = new GameObject[8][8];
    private LinkedList Tpieces;
    private LinkedList<ValidMove> newMoves;
    ValidMove validMove;
    int weight;
    int state;

    public PseudoGame( final LinkedList<GameObject> fPieces, final ValidMove validMove, int state ){
        int len, i, j;
        this.weight = 0;
        this.state = state;
        this.validMove = validMove;
        GameObject[][] objects = new GameObject[8][8];
        this.objects = objects;

        LinkedList pieces = (LinkedList)fPieces.clone();
        this.Tpieces = pieces;
        LinkedList<GameObject> tempPieces;
        LinkedList<ValidMove> newMoves = new LinkedList<>();
        LinkedList<ValidMove> tempMoves;
        /* moves piece */
        len = pieces.size();
        for( i = 0; i < len; i++ ){
            GameObject tempObject = (GameObject) pieces.get(i);
            this.addObject( tempObject, tempObject.getX(), tempObject.getY() );
        }

        for( i = 0; i < 8; i++ ){
            for( j = 0; j < 8; j++){
                GameObject tempObject = objects[i][j];
                if (tempObject != null){
                    if ( getObject(i,j).getUniqueID() == validMove.getUniqueID() ){
                    //System.out.println( "piece of move x: " + tempObject.getX() + " y: " + tempObject.getY());
                        this.movePiece(validMove.getX(), validMove.getY(), i, j);
                    //System.out.println( "piece after move x: " + tempObject.getX() + " y: " + tempObject.getY());
                    }
                }
            }
        }


        /* sums weight */
        for ( i = 0; i < len; i++){
            GameObject tempObject = (GameObject) pieces.get(i);
            this.weight += tempObject.getWeight() * state;
        }

        tempPieces = getPieces();
        this.Tpieces = tempPieces;
        len = getPieces().size();
        for ( i = 0; i < len; i++){
            tempMoves = (LinkedList<ValidMove>) tempPieces.get(i).getValidMoves().clone();
            int lenT = tempMoves.size();
            for ( j = 0; j < lenT; j++ ){
                newMoves.add( tempMoves.get(j) );
            }
        }
        this.newMoves = newMoves;

    }


    public LinkedList<GameObject> getPieces(){
        LinkedList<GameObject> pieces = new LinkedList<>();
        for ( int i = 0; i < 8; i++ ){
            for( int j = 0; j < 8; j++ ){
                if( this.getObject(i, j) != null ){
                    pieces.add( this.getObject(i, j) );
                }
            }
        }
        return pieces;
    }

    private void addObject( GameObject object, int x , int y ){
        this.objects[x][y] = object;
    }

    private void movePiece(int newX, int newY, int currentX, int currentY ){
        GameObject tempObject = this.getObject(currentX, currentY);
        if (tempObject == null){
            //System.out.println("Piece Not Found");
            return;
        }

        if ( state == -1 ){
            //System.out.println( "Destination: " + newX + " " + newY );
            //System.out.println( "Old:  " + currentX + " " + currentY );
        }
        this.addObject( tempObject,newX, newY );
        //tempObject.setX(newX);
        //tempObject.setY(newY);
        //tempObject.setHasMoved(true);
        this.removeObject( currentX, currentY);
        state *= -1;
    }

    private void removeObject( int x, int y ){
        this.objects[x][y] = null;
    }

    private GameObject getObject( int x, int y){
        if( x > 7 || x < 0 || y > 7 || y < 0 ){
            return null;
        }
        return this.objects[x][y];
    }


    public ValidMove getValidMove() {
        return validMove;
    }

    public LinkedList<ValidMove> getNewMoves() {
        return newMoves;
    }

    public int getWeight() {
        return weight;
    }

}
