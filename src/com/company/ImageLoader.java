package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageLoader {

    public static BufferedImage loadImage( String path ){
        try {
            File file = new File(path);
            if (file.exists()){

            } else {
                System.out.println("not spook");
            }
            FileInputStream fis = new FileInputStream(file);
            return ImageIO.read( fis );
        } catch ( Exception e){
            System.exit(1);
        }
        return null;
    }
}
