package br.com.pdi.vision;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePixel {

    BufferedImage image;
    int width, heigth;

    public void run() throws IOException {
        File input = new File("D:\\Faculdade\\PDI\\source.png");
        image = ImageIO.read(input);
        width = image.getWidth();
        heigth = image.getHeight();

        for(int i = 0; i < heigth; i++){
            for(int j = 0; j < width; j++){
                Color pixel = new Color(image.getRGB(j, i));
                System.out.println("["+i+"]["+j+"]" +
                        " R: "+pixel.getRed()+" G:"+pixel.getGreen()+" B:"+pixel.getBlue());
            }
        }
    }

    public static void main(String[] args) {
        try{
            new ImagePixel().run();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
