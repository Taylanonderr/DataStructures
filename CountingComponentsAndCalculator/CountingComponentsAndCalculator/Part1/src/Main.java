import java.awt.*;
import java.io.*;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) throws IOException {
        int row = 0, column = 0, i = 0, j = 0;
        BufferedReader oku = new BufferedReader(new FileReader(
                "test_file_part1.txt"));
        String yazi;

        while ((yazi = oku.readLine()) != null) {
            row++;
            column = yazi.length();
        }
        oku.close();
        column=(column+1)/2;
        Character[][] Map = new Character[row][(column+1)];
        FileInputStream fis = new FileInputStream("test_file_part1.txt");
        Character current;
        while (fis.available() > 0) {
            current = (char) fis.read();
            if(current!=' ' && current!='\n' ) {
                Map[j][i] = current;
                i++;
                i = i % (column+1);
            }
            if (current!=' ' && current=='\n') {
                i=0;
                j++;
            }
        }

        System.out.printf("Before components don't finding : \n");

        Matrix Map_component=new Matrix(Map,row,column);
        System.out.printf("\n");
        oku.close();
        int counter=Map_component.binary_image();
        System.out.printf("End of the situation.Finding components : \n");
        for (i = 0; i < row; i++) {
            for (j = 0; j < (column); j++) {
                System.out.printf("%c",Map_component.Map[i][j]);
            }
            System.out.printf("\n");
        }
        System.out.printf("\nCorresponds number are %d \n",counter);

    }
}
