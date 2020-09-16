package com.company;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class MouseInput extends MouseAdapter {

    private Handler handler;
    private boolean pressed = false;
    private int holdX = -1;
    private int holdY = -1;
    private int holdUniqueID = 0;


    public MouseInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int x, y, currentX, currentY , i, j;
        x = e.getX();
        y = e.getY();
        currentX = Game.clamp(-1 + x / Game.TILE, 0 ,  7);
        currentY = Game.clamp( -1 + y / Game.TILE, 0 , 7);
        boolean valid = false;
        if ( !pressed ) {
            for ( i = 0; i < 8; i++ ) {
                for ( j = 0; j < 8; j++ ) {
                    GameObject tempObject = handler.getObject(i, j);
                    if (tempObject != null) {
                        if (tempObject.getBounds().intersects(new Rectangle(x, y, 2, 2))) {
                            handler.setMoveState(currentX, currentY, true);
                            holdUniqueID = tempObject.getUniqueID();
                            holdX = tempObject.getX();
                            holdY = tempObject.getY();
                            if ( handler.getState() == tempObject.getWhite() ) {
                                pressed = true;
                            }
                            //pressed = true;
                        }
                    }
                }
            }
        } else {
            for ( i = 0; i < 8; i++ ) {
                for( j = 0; j < 8; j++ ){
                    GameObject tempObject = handler.getObject( i , j );
                    if (tempObject != null) {
                        if ( tempObject.getX() == holdX && tempObject.getY() == holdY ){
                            valid = handler.getEngine().checkValid( currentX, currentY, i, j );
                            if (valid){
                                handler.getEngine().movePiece(currentX, currentY, i ,j );
                            }
                            i = 8;
                        }
                    }
                }
            }
            pressed = false;
            handler.setMoveState(currentX, currentY, false);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
    }


}
