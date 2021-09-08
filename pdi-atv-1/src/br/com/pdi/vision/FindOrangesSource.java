/**
 * Raique Carvalho Queiroz
 * Faculdade Área1
 * Computação Gráfica
 * Atividade avaliativa 1
 */
package br.com.pdi.vision;

import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class FindOrangesSource {

    BufferedImage image, imageSource;
    int width, heigth, widthSource, heigthSource, red, green, blue, redRange, greenRange, blueRange;
    int thresholderRed, thresholderGreen, thresholderBlue;
    List<Integer> redList = new ArrayList<>();
    List<Integer> greenList = new ArrayList<>();
    List<Integer> blueList = new ArrayList<>();

    public void run() throws IOException {

        File input = new File("D:\\Faculdade\\PDI\\image1.jfif");
        // source is my reference to what is an orange >> Best source was source3.png
        File source = new File("D:\\Faculdade\\PDI\\source3.png");

        image = ImageIO.read(input);
        width = image.getWidth();
        heigth = image.getHeight();

        imageSource = ImageIO.read(source);
        widthSource = imageSource.getWidth();
        heigthSource = imageSource.getHeight();

        // Fill lists to calculate average RGB colors from an orange source

        for(int i = 0; i < heigthSource; i++) {
            for (int j = 0; j < widthSource; j++) {
                Color pixel = new Color(imageSource.getRGB(j, i));
                red = pixel.getRed();
                green = pixel.getGreen();
                blue = pixel.getBlue();
                redList.add(red);
                greenList.add(green);
                blueList.add(blue);
            }
        }

        // Get average RGB colors

        thresholderRed = (int)calculateAverage(redList);
        thresholderGreen = (int)calculateAverage(greenList);
        thresholderBlue = (int)calculateAverage(blueList);


        // Get an range of shaders of orange from our source
        // I tried 3 different ways to get this info
        // The best one gets the difference between the max rgb color and the avarege RGB colors

        //redRange = Collections.max(redList,null)-Collections.min(redList,null);
        //greenRange = Collections.max(greenList,null)-Collections.min(greenList,null);
        //blueRange = Collections.max(blueList,null)-Collections.min(blueList,null);

        redRange = Collections.max(redList,null)-thresholderRed;
        greenRange = Collections.max(greenList,null)-thresholderGreen;
        blueRange = Collections.max(blueList,null)-thresholderBlue;

        //redRange = (int)avgRed-Collections.min(redList,null);
        //greenRange = (int)avgGreen-Collections.min(greenList,null);
        //blueRange = (int)avgBlue-Collections.min(blueList,null);

        //replace to white what is considered a shade of orange

        for(int i = 0; i < heigth; i++){
            for(int j = 0; j < width; j++){
                Color pixel = new Color(image.getRGB(j, i));
                red = pixel.getRed();
                green = pixel.getGreen();
                blue = pixel.getBlue();

                if((red == thresholderRed && green == thresholderGreen && blue == thresholderBlue) ||
                    (red >= thresholderRed-redRange && red <= thresholderRed+redRange) &&
                            (green >= thresholderGreen-greenRange && green <= thresholderGreen+greenRange) &&
                            (blue >= thresholderBlue-blueRange && blue <= thresholderBlue+blueRange))
            {
                    image.setRGB(j,i,Color.WHITE.getRGB());
                }else{
                    image.setRGB(j,i,Color.BLACK.getRGB());
                }
            }
        }
        File output = new File("D:\\Faculdade\\PDI\\image1_bw.jpg");
        ImageIO.write(image,"jpg",output);
    }

    private double calculateAverage(List <Integer> listPixels) {
        Integer sum = 0;
        for (Integer pixel : listPixels) {
            sum += pixel;
        }
        return sum.doubleValue()/listPixels.size();
    }

    public static void main(String[] args) throws IOException {
        new FindOrangesSource().run();
    }
}
