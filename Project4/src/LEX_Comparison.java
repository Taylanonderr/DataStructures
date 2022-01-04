import java.awt.*;
import java.util.Comparator;

public class LEX_Comparison implements Comparator  {
    /**
     * default constructure
     */
    public LEX_Comparison(){}

    /**
     *According to red,green and blue values compare two pixel
     * @param o1 first compare object it is a pixel
     * @param o2 second compare object it is a pixel
     * @return value is compare result equal bigger or smaller value
     */
    @Override
    public int compare(Object o1, Object o2) {
        Color c = new Color((Integer) o1);
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        Color c2 = new Color((Integer) o2);
        int red2 = c2.getRed();
        int green2 = c2.getGreen();
        int blue2 = c2.getBlue();
        if(red>red2){
            return 1;
        }
        else if(red==red2 && green>green2){
            return 1;
        }
        else if(red==red2 && green==green2 && blue>blue2){
            return 1;
        }
        else if(red==red2 && green==green2 && blue==blue2){
            return 0;
        }
        return -1;
    }
}
