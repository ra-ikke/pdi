package br.com.pdi.vision;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GrayScaleConversion {

    BufferedImage image;
    int width, heigth, red, green, blue, gray;

    public void run() throws IOException {
        File input = new File("D:\\Faculdade\\PDI\\imagem1.jfif");
        image = ImageIO.read(input);
        width = image.getWidth();
        heigth = image.getHeight();

        for(int i = 0; i < heigth; i++){
            for(int j = 0; j < width; j++){
                Color pixel = new Color(image.getRGB(j, i));
                red = pixel.getRed();
                green = pixel.getGreen();
                blue = pixel.getBlue();

                gray = (red + green + blue)/3;

                Color newPixel = new Color(gray, gray, gray);

                image.setRGB(j,i,newPixel.getRGB());

            }
        }
        File output = new File("D:\\Faculdade\\PDI\\imagem1_gray.jpg");
        ImageIO.write(image,"jpg",output);
    }

    public static void main(String[] args) throws IOException {
        new GrayScaleConversion().run();
    }
}
