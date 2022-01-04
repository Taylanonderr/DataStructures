import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        int [][] dimensional={ {1,2,3,4,5,6},{7,8,9,10,11,12},{13,14,15,16,17,18}};
        System.out.println(dimensional.length);
        System.out.println(dimensional.length);
        Traverse array=new Traverse(dimensional);
        Iterator<Integer> it=array.collection.iterator();
        while(it.hasNext()) {
            System.out.print(it.next() + " ");
        }
    }
}
