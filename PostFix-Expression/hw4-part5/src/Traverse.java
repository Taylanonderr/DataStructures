import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Traverse implements Iterable {
    private int[][] array2D;
    private int pos = 0;
    public List<Integer> collection;

    public Traverse(int anArray[][]) {
        array2D = anArray;
        //collection = new ArrayList<>(); // diamond op in jdk7
        collection = new ArrayList();

        int maxIterations = array2D.length + array2D[0].length;
        spirally_array(array2D, 0, 0, true,0,array2D.length,array2D[0].length);
    }
    public void spirally_array ( int[][] arr2D, int row_pos, int col_pos, boolean direction, int position,int row_limit ,int column_limit){

        if (row_pos<= row_limit && col_pos+1<arr2D[0].length && direction == true) {
            if (collection.contains(arr2D[row_pos][col_pos+1])){
                collection.add(arr2D[row_pos][col_pos]);
                direction = false;
                row_pos++;
            }else{
            collection.add(arr2D[row_pos][col_pos]);
            col_pos++;
            }
        } else if (col_pos + 1 == arr2D[0].length && row_pos< arr2D.length && direction == true) {
            collection.add(arr2D[row_pos][col_pos]);
            if(row_pos==row_limit-1  || collection.contains(arr2D[row_pos+1][col_pos] )) {
                direction = false;
                col_pos--;
                row_limit--;
            }
            else {
                row_pos++;

            }

        } else if (direction == false && col_pos > 0 && !collection.contains(arr2D[row_pos][col_pos-1] )) {

            collection.add(arr2D[row_pos][col_pos]);
            col_pos--;
            if(col_pos==0) {
                row_limit--;
            }

        } else if (direction == false && row_pos > 0) {

            if (row_pos == 0 || collection.contains(arr2D[row_pos-1][col_pos])){
                collection.add(arr2D[row_pos][col_pos]);
                direction = true;
                if(collection.contains(arr2D[row_pos][col_pos+1]))
                    row_pos++;
                else
                    col_pos++;
            }
            else {
                collection.add(arr2D[row_pos][col_pos]);
                row_pos--;

            }
        }
        position++;
        if (position >= arr2D.length * arr2D[0].length)
            return;
        else
            spirally_array(array2D, row_pos, col_pos, direction, position,row_limit,column_limit);
    }
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return pos < collection.size();
            }

            @Override
            public Integer next() throws NoSuchElementException {
                if (hasNext()) {
                    return collection.get(pos++);
                } else {
                    throw new NoSuchElementException();
                }
            }

        };
    }
}


