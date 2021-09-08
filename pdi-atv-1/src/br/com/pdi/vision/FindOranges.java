package br.com.pdi.vision;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FindOranges {

    BufferedImage image;

    int width, heigth, red, green, blue;

    Color orange = new Color(230,95,0);

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

                if((red == orange.getRed() && green == orange.getGreen() && blue == orange.getBlue()) ||
                    (red >= orange.getRed()-90 && red <= orange.getRed()+90) &&
                            (green >= orange.getGreen()-90 && green <= orange.getGreen()+90) &&
                            (blue >= orange.getBlue()-60 && blue <= orange.getBlue()+60))
            {
                    image.setRGB(j,i,Color.WHITE.getRGB());
                }else{
                    image.setRGB(j,i,Color.BLACK.getRGB());
                }
            }
        }
        File output = new File("D:\\Faculdade\\PDI\\imagem1_bw.jpg");
        ImageIO.write(image,"jpg",output);
    }

    public static void main(String[] args) throws IOException {
        new FindOranges().run();
    }
}
