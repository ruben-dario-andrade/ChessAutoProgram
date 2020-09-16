package com.company;

import java.util.LinkedList;

public class Computer {

    private Engine engine;
    private int state;

    public Computer( Engine engine, int state ){
        this.engine = engine;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void move( final LinkedList<GameObject> pieces ){
        int i, j, k, len, lenTemp, weight, tempWeight;
        LinkedList<ValidMove> possMoves = new LinkedList<>();
        len = pieces.size();
        //System.out.println( len );
        for( i = 0; i < len; i++ ){
            if ( pieces.get(i).getWhite() == state ){
                LinkedList<ValidMove> tempMoves = pieces.get(i).getValidMoves();;
                lenTemp = tempMoves.size();
                for ( j = 0; j < lenTemp; j++ ){
                    possMoves.add(tempMoves.get(j));
                }
            }
        }
        int x, y, holdV, xNew, yNew;
        lenTemp = possMoves.size();

        System.out.println(lenTemp);
        ValidMove tempMove =  possMoves.get(0);
        ValidMove move =  possMoves.get(0);

        LinkedList<GameObject> tempPieces = new LinkedList<>();
        tempPieces = (LinkedList<GameObject>) pieces.clone();
        weight = getMove( 3, move, tempPieces);
        tempWeight = 0;
        holdV = 0;


        System.out.println( "Valid moves to be checked: " + lenTemp);
        if (lenTemp > 0 ) {

            for (k = 1; k < lenTemp; k++) {
                tempMove = possMoves.get(k);
                System.out.println("Move " + k + " checked");
                tempWeight = getMove(3, tempMove, pieces);
                System.out.println("Weight is " + tempWeight);
                if (tempWeight > weight) {
                    holdV = k;
                    move = tempMove;
                    weight = tempWeight;
                }
            }

            System.out.println("Move " + holdV + " chosen");
            move = possMoves.get(holdV);
            //TODO make choice function
            int id = move.getUniqueID();
            for (i = 0; i < len; i++) {
                if (pieces.get(i).getUniqueID() == id) {
                    x = pieces.get(i).getX();
                    y = pieces.get(i).getY();
                    xNew = move.getX();
                    yNew = move.getY();
                    engine.movePiece(xNew, yNew, x, y);
                }
            }
        }


    }

    public int getMove( int depth, final ValidMove validMove, final LinkedList<GameObject> pieces){
        int i, len, mod;
        int weight = 0;
        LinkedList<GameObject> tempPieces = new LinkedList<>();
        tempPieces = (LinkedList<GameObject>) pieces.clone();
        PseudoGame pseudoGame = new PseudoGame(tempPieces, validMove, state);
        weight += pseudoGame.getWeight();
        if (depth == 0){
            return weight;
        }
        LinkedList<GameObject> newPieces = pseudoGame.getPieces();
        LinkedList<ValidMove> newMoves = pseudoGame.getNewMoves();
        len = newMoves.size();
        for ( i = 0; i < len; i++){
            weight += getMove( depth - 1, newMoves.get(i), newPieces );
        }
        return weight;
    }

}








