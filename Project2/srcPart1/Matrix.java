public class Matrix {
    public Character [][]Map;
    private int row;
    private int column;

    /**
     * Matrix class copy field constructure
     * @param matrix_map reading file map
     * @param row_map   for allocate map row size
     * @param column_map    for allocate map calumn size
     */
    public Matrix(Character[][]matrix_map,int row_map,int column_map){
        row=row_map;
        column=column_map;
        Map=new Character [row][column+1];
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                Map[i][j]=matrix_map[i][j];
                System.out.printf("%c",Map[i][j]);
            }
            System.out.printf("\n");
        }
    }

    /**
     * main method find and change components
     * @return  number of components
     */
    public int binary_image(){
        int i,j,flag=0;
        Character alphabet = 'A';
        int counter=0;
        int control=1;
        Matrix.Node coord_nodes=new Node();
        MatrixStack stack = new MatrixStack();
        for (i = 0; i < (row); i++) {
            for (j = 0; j < ((column)); j++) {
                control++;
                if (Map[i][j] == '1') {
                    control = 0;
                    flag = 0;
                    Map[i][j] = alphabet;
                    coord_nodes = new Matrix.Node(i, j);
                    stack.push(coord_nodes);
                    while (!stack.isEmpty()) {
                        if (j < column - 1 && (Map[i][j + 1] == '1' )) {
                            coord_nodes = new Matrix.Node(i, j + 1);
                            flag = 1;
                            Map[i][j + 1] = alphabet;
                            j=j+1;
                            stack.push(coord_nodes);
                        }
                        if (j > 0 && (Map[i][j - 1] == '1' )) {
                            coord_nodes = new Matrix.Node(i, j - 1);
                            flag = 1;
                            Map[i][j - 1] = alphabet;
                            j=j-1;
                            stack.push(coord_nodes);
                        }
                        if (i < row - 1 && (Map[i + 1][j] == '1' )) {
                            coord_nodes = new Matrix.Node(i + 1, j);
                            flag = 1;
                            Map[i + 1][j] = alphabet;
                            i=i+1;
                            stack.push(coord_nodes);
                        }
                        if (i > 0 && (Map[i - 1][j] == '1' )) {
                            coord_nodes = new Matrix.Node(i - 1, j);
                            flag = 1;
                            Map[i - 1][j] = alphabet;
                            i=i-1;
                            stack.push(coord_nodes);
                        }
                        control = 0;
                        if (j < column - 1 && (Map[i][j + 1] == '1')) {
                            control = 1;
                        }
                        if (j > 0 && (Map[i][j - 1] == '1')) {
                            control = 1;
                        }
                        if (i < row - 1 && (Map[i + 1][j] == '1')) {
                            control = 1;
                        }
                        if (i > 0 && (Map[i - 1][j] == '1')) {
                            control = 1;
                        }
                        if(control==0) {
                            i = ((Node) stack.peek()).x;
                            j = ((Node) stack.peek()).y;
                            Matrix.Node temp = ((Node) stack.pop());
                        }
                        if (flag == 1 && control==0) {
                            control = 1;
                        }
                        flag = 0;
                    }
                    counter++;
                    alphabet++;
                }
            }
        }
        return counter;
    }

    /**
     * inner class
     */
    public static class Node{
        public int x;
        public int y;
        Node(){}

        /**
         * inner class copy field constructure
         * @param x_c   x coordinate
         * @param y_c   y coordinate
         */
        Node(int x_c,int y_c){
            x=x_c;
            y=y_c;
        }

        /**
         * tostring method
         * @return  coordinates
         */
        @Override
        public String toString() {
            return x+" "+y;
        }
    }
    //public


}
