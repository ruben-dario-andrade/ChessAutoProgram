package com.company;

enum PIECETYPE{
    MOVE,
    PASSANT,
    CASTLE
}

public class ValidMove {

    private int uniqueID;
    private int x, y;
    PIECETYPE piecetype;

    public ValidMove( int uniqueID, int x, int y, PIECETYPE piecetype ){
        this.uniqueID = uniqueID;
        this.x = x;
        this.y = y;
        this.piecetype = piecetype;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public PIECETYPE getPiecetype() {
        return piecetype;
    }
}
