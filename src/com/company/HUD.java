package com.company;


import java.awt.*;
import java.util.LinkedList;

public class HUD {

    public void tick(){

    }
    public void render( Graphics g ){
        int borderW = Game.WIDTH - 2 * Game.BORDER;
        int borderH = Game.HEIGHT - 2 * Game.BORDER;
        g.setColor(Color.gray);
        g.fillRect( Game.BORDER , Game.BORDER , borderW, borderH);

        int alt = 1;

        for( int i = 0; i < 8; i++ ){
            for ( int  j = 0; j < 8; j++){
                if ( alt == -1 ){
                    drawTile( g, Color.gray, i, j );
                } else {
                    drawTile( g, Color.WHITE, i, j );
                }
                alt *= -1;
            }
            alt *= -1;
        }
    }
    private void drawTile( Graphics g, Color color, int x, int y ){
        g.setColor( color );
        g.fillRect( Game.BORDER + x * Game.TILE, Game.BORDER + y * Game.TILE, Game.TILE, Game.TILE );
    }



}

