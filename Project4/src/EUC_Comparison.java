import java.awt.*;
import java.util.Comparator;

public class EUC_Comparison implements Comparator {
    /**
     * default constructure
     */
    public EUC_Comparison(){
    }

    /**
     *
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
        double o1_euc=0;
        double o2_euc=0;
        o1_euc=Math.sqrt((red*red)+(green*green)+(blue*blue));
        o2_euc=Math.sqrt((red2*red2)+(green2*green2)+(blue2*blue2));
        if(o1_euc>o2_euc){
            return 1;
        }
        else if(o1_euc==o2_euc){
            return 0;
        }
        return -1;
    }
}