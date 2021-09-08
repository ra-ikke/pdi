package br.com.pdi.vision;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Vector;

public class ColorDetection {
    private int pixelRgb [] = new int [3];
    private Vector<String> colorName = new Vector<String>();
    private Vector<Double> distances = new Vector<Double>();
    private String file = "D:\\Faculdade\\PDI\\imagem1.jfif";
    private int [][] storedRgb;

    ColorDetection(int rgb[])
    {
        pixelRgb = rgb.clone(); //clones array
        /*read file and extract names + r g b values from the file*/
        readFile();

        /*Find distance of the given pixel to all the ones in file*/
        for(int i=0; i<storedRgb.length; i++)
            distances.add(colorDistance(storedRgb[i]));

        /*Select the pixel which is closest to the given pixel and display it and its name*/
        double min = Collections.min(distances);
        int i = distances.indexOf(min);
        /*JFrame stuff*/
 //       ColorFrame.changeColor(storedRgb[i][0],storedRgb[i][1], storedRgb[i][2]);
  //      ColorFrame.changeText("(" + storedRgb[i][0] + ", " + storedRgb[i][1] + ", " + storedRgb[i][2] + ")", colorName.elementAt(i).toUpperCase());

    }

    public void readFile()
    {
        /*read file and store it in a string*/
        String [] result;
        String string = "";
        try{
            InputStream ips=new FileInputStream(file);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String line;
            while ((line=br.readLine())!=null){
                string+=line + "\n";
            }
            br.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        /*Split everything on basis of words separated by spaces*/
        result = string.split("\\s");
        /*Initialise 2d array*/
        storedRgb = new int[result.length/4][];
        /*Parse R G B and Color name to arrays/vector */
        for(int i = 0; i < result.length; i+=4){
            storedRgb[i/4] = new int [3];
            storedRgb[i/4][0] = Integer.parseInt(result[i]);
            storedRgb[i/4][1] = Integer.parseInt(result[i+1]);
            storedRgb[i/4][2] = Integer.parseInt(result[i+2]);
            colorName.add(result[i+3]);
        }
    }
    /*Calculate color distance of one color to another*/
    public double colorDistance(int otherRgb[])
    {
        long rmean = ( (long)pixelRgb[0] + (long)otherRgb[0] ) / 2;
        long r = (long)pixelRgb[0] - (long)otherRgb[0];
        long g = (long)pixelRgb[1] - (long)otherRgb[1];
        long b = (long)pixelRgb[2] - (long)otherRgb[2];
        return Math.sqrt((((512+rmean)*r*r)>>8) + 4*g*g + (((767-rmean)*b*b)>>8));
    }
}