import java.util.Scanner;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

public class ascii{
    public static void main(String[] args) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(args[0]));
        } catch (IOException e) {
            System.out.println("Couldn't load image");
            return;
        }
        int height = img.getHeight();
        int width = img.getWidth();
        System.out.println("Image succesfully loaded, height: " + height + " , width: " + width);

        int bowOrwob = 0;
        if (args[2].equals("bow")) {
            bowOrwob = 255;
        }

        String art = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int integerpixel = img.getRGB(j, i);
                Color c = new Color(integerpixel);
                int value = abs(((c.getRed() + c.getGreen() + c.getBlue())/3)-bowOrwob);
                art += valToString(value) + valToString(value) + valToString(value);
                
            }
            art += "\n";
        }


       
        if (args[1].equals("f")) {
            try {
                FileWriter out = new FileWriter("out.txt");
                out.write(art);
                out.close();
            } catch (IOException e){
                System.out.println("couldn't write to file :(");
            }   
        } else if(args[1].equals("c")){
            System.out.print(art);
        }
        else {
            System.out.println("Unknown flag: " + args[1]);
        }
        


        
    }

    public static String valToString(int val) {
        int index = (int)(val/3.92);
        String map = " `^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";
        return(""+ map.charAt(index));
    }

    public static int abs(int x){
        if (x >= 0) {
            return x;
        } else {
            return -x;
        }
    }
} 
