import java.awt.*;
import java.util.Comparator;

public class BMX_Comparison implements Comparator {
    /**
     * default constructure
     */
    public BMX_Comparison(){}

    /**
     *I translate decimal value to binary value and keep in int array after that
     * merge red green and blue color values and compare two 24bit binary value
     * @param o1 first compare object it is a pixel
     * @param o2 second compare object it is a pixel
     * @return value is compare result equal bigger or smaller value
     */
    @Override
    public int compare(Object o1, Object o2) {
        int[] red = new int[8];
        int[] green = new int[8];
        int[] blue = new int[8];
        int[] red2 = new int[8];
        int[] green2 = new int[8];
        int[] blue2 = new int[8];
        int[] first = new int[24];
        int[] second = new int[24];
        Color c = new Color((Integer) o1);
        int red_first = c.getRed();
        int green_first = c.getGreen();
        int blue_first = c.getBlue();
        Color c2 = new Color((Integer) o2);
        int red_second = c2.getRed();
        int green_second = c2.getGreen();
        int blue_second = c2.getBlue();
        int index= 0;


        while(red_first> 0){
            red[index++] = red_first%2;
            red_first = red_first/2;
        }
        for(int i=index;i<8;i++)
        {
            red[i]=0;
        }

        index=0;
        while(green_first > 0){
            green[index++] = green_first%2;
            green_first = green_first/2;
        }
        for(int i=index;i<8;i++)
        {
            green[i]=0;
        }

        index=0;
        while(blue_first > 0){
            blue[index++] = blue_first%2;
            blue_first = blue_first/2;
        }
        for(int i=index;i<8;i++)
        {
            blue[i]=0;
        }

        index=23;
        for(int i=7;i>-1;i--){
            first[index]=red[i];
            index--;
            first[index]=green[i];
            index--;
            first[index]=blue[i];
            index--;
        }


        index = 0;
        while(red_second> 0){
            red2[index++] = red_second%2;
            red_second = red_second/2;
        }
        for(int i=index;i<8;i++)
        {
            red2[i]=0;
        }

        index = 0;
        while(green_second > 0){
            green2[index++] = green_second%2;
            green_second = green_second/2;
        }
        for(int i=index;i<8;i++)
        {
            green2[i]=0;
        }

        index = 0;
        while(blue_second > 0){
            blue2[index++] = blue_second%2;
            blue_second = blue_second/2;
        }
        for(int i=index;i<8;i++)
        {
            blue2[i]=0;
        }

        index=23;
        for(int i=7;i>-1;i--){
            second[index]=red2[i];
            index--;
            second[index]=green2[i];
            index--;
            second[index]=blue2[i];
            index--;
        }

        for(index=23;index>-1;index--){
            if(first[index]>second[index])
                return 1;
            else if(first[index]<second[index])
                return -1;
        }


        return 0;
    }
}
