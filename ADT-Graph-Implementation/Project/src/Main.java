import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int source=0,destination=0,i=0,ordered_relation=0;
        Graph my_graph;
        try {
            Scanner s = new Scanner(new File("input.txt"));
            source= Integer.parseInt(s.next());
            ordered_relation= Integer.parseInt(s.next());
            my_graph= new Graph(source);
            while (i<ordered_relation) {
                source= Integer.parseInt(s.next());
                destination= Integer.parseInt(s.next());
                my_graph.insert(source-1,destination-1);
                i++;
            }
            my_graph.find_populer();
            //System.out.println(my_graph);
        } catch (IOException e) {
            System.out.println("Error accessing input file!");
        }
    }
}
