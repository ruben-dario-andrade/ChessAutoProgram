package com.company;


import java.awt.*;
import java.util.LinkedList;

public class Handler {


    private GameObject[][] objects = new GameObject[8][8];

    private int state = 1;

    private int optionsX = -1;

    private int optionsY = -1;

    private Engine engine;

    public Handler(){

        this.engine = new Engine( this, 0, 1 );
    }

    public void tick(){
        engine.tick();
        for ( int i = 0; i < 8; i++ ){
            for( int j = 0; j < 8; j++ ){
                if( objects[i][j] != null ) {
                    GameObject tempObject = objects[i][j];
                    tempObject.tick();
                }
            }
        }

    }

    public void render( Graphics g ){
        for ( int i = 0; i < 8; i++ ){
            for( int j = 0; j < 8; j++ ){
                if( objects[i][j] != null ) {
                    GameObject tempObject = objects[i][j];
                    tempObject.render( g );
                }
            }
        }
        if ( engine.getMoveState()  ){
            showOptions( g, optionsX, optionsY);
        }
    }

    public void createPieces(){
        /* Add Pawns */
        for( int i = 0; i < 8; i++){
            addObject( new Pawn( i , 6, ID.Pawn, Game.getImage(0),1,this), i, 6 );
        }
        for( int i = 0; i < 8; i++){
            addObject( new Pawn( i , 1, ID.Pawn, Game.getImage(1),-1,this), i, 1 );
        }
        /* Add Queens */
        addObject( new Queen( 3, 7, ID.Queen, Game.getImage(10), 1, this ), 3, 7);
        addObject( new Queen( 3, 0, ID.Queen, Game.getImage(11), -1, this ), 3, 0);
        /* Add Knights */
        addObject( new Knight(1,7,ID.Knight,Game.getImage(4), 1, this), 1, 7);
        addObject( new Knight(6,7,ID.Knight,Game.getImage(4), 1, this), 6, 7);
        addObject( new Knight(1,0,ID.Knight,Game.getImage(5), -1, this), 1, 0);
        addObject( new Knight(6,0,ID.Knight,Game.getImage(5), -1, this), 6, 0);
        /* Add Bishops */
        addObject( new Bishop(2, 7,ID.Bishop,Game.getImage(6), 1, this), 2, 7);
        addObject( new Bishop(5, 7,ID.Bishop,Game.getImage(6), 1, this), 5, 7);
        addObject( new Bishop(2, 0,ID.Bishop,Game.getImage(7), -1, this), 2, 0);
        addObject( new Bishop(5, 0,ID.Bishop,Game.getImage(7), -1, this), 5, 0);
        /* Add Rooks */
        addObject( new Rook(0,7,ID.Rook,Game.getImage(2),1,this),0, 7);
        addObject( new Rook(7,7,ID.Rook,Game.getImage(2),1,this),7, 7);
        addObject( new Rook(0,0,ID.Rook,Game.getImage(3),-1,this),0, 0);
        addObject( new Rook(7,0,ID.Rook,Game.getImage(3),-1,this),7, 0);

        addObject( new King(4,7,ID.King,Game.getImage(8),1,this),4, 7);
        addObject( new King(4,0,ID.King,Game.getImage(9),-1,this),4, 0);
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

    public void addObject( GameObject object, int x , int y ){
        this.objects[x][y] = object;
    }

    public void removeObject( int x, int y ){
        this.objects[x][y] = null;
    }

    public GameObject getObject( int x, int y){
        if( x > 7 || x < 0 || y > 7 || y < 0 ){
            return null;
        }
        return this.objects[x][y];
    }



    private void showOptions( Graphics g, int x,  int y ){
        g.setColor( Color.blue );
        if ( getObject(x, y) == null ){
            return;
        }
        if ( getObject(x, y).getWhite() != engine.getState() ){
            return;
        }
        LinkedList<ValidMove> validMoves = getObject(x, y).getValidMoves();
        for ( int i = 0; i < validMoves.size(); i++ ){
            g.drawRect( (validMoves.get(i).getX() + 1 ) * Game.TILE, (validMoves.get(i).getY() + 1) * Game.TILE, Game.TILE, Game.TILE );
        }
        g.drawRect( (x + 1) * Game.TILE, (y + 1) * Game.TILE, Game.TILE, Game.TILE );
    }

    public void setMoveState(int x, int y, boolean moveState)
    {
        engine.setMoveState( moveState );
        optionsX = x;
        optionsY = y;
    }

    public void placeObject( int x, int y, GameObject piece ){
        objects[x][y] = piece;
    }

    public int getState() {
        return state;
    }

    public Engine getEngine(){ return engine; }

}