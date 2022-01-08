
public class Graph {
    private boolean adjMatrix[][];
    private int vertices;

    public Graph(int numVertices) {
        this.vertices = numVertices;
        adjMatrix = new boolean[vertices][vertices];
    }

    /**
     * insert method
     * @param i source
     * @param j destination
     */
    public void insert(int i, int j) {
        adjMatrix[i][j] = true;
    }

    /**
     * pairs transitivity properties fill in graph structure
     */
    public void transitivity(){
        int t,y,z;
        for(y=0;y<vertices;y++) {
            for (t = 0; t < vertices; t++) {
                for(z=0;z<vertices;z++) {
                    if(adjMatrix[t][y] == true && adjMatrix[y][z] == true)
                        adjMatrix[t][z] = true;
                }
            }
        }
    }

    /**
     *
     * @param i source
     * @param j destination
     * @return true or false to understand has pair or not
     */
    public boolean isEdge(int i, int j) {
        return adjMatrix[i][j];
    }

    /**
     * Find an integer which represents the number of people who are considered popular by every other person.
     */
    public void find_populer(){
        transitivity();
        int i,j,counter=0,popularity_num=0;
        for(i=0;i<vertices;i++){
            counter=0;
            for(j=0;j<vertices;j++){
                if(isEdge(j,i) && j!=i){
                    counter++;
                }
            }
            if(counter>=vertices-1 ){
                popularity_num++;
            }
        }
        System.out.println(popularity_num);         /*Program result*/
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < vertices; i++) {
            s.append(i+1 + ": ");
            for (boolean j : adjMatrix[i]) {
                s.append((j ? 1 : 0) + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}