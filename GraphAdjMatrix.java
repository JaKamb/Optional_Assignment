import java.util.ArrayList;

public class GraphAdjMatrix implements Graph{
    int matrix[][];
    int size;

    public GraphAdjMatrix(int size){
        this.matrix = new int[size][size];
        this.size = size;
    }
    public void addEdge(int v1, int v2){
        matrix[v1][v2] = 1;
    }
    public void addEdge(int v1, int v2, int w){
        matrix[v1][v2] = w;
    }
    public int getEdge(int v1, int v2){
        return matrix[v1][v2];
    }
    public int createSpanningTree(){
        ArrayList<Edge> edges = new ArrayList<>(size * size);
        ArrayList<Edge> TBU = new ArrayList<>();
        int w = 0;
        DisjointSet d = new DisjointSet(size);

        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] != 0) {
                    edges.add(new Edge(i, j, matrix[i][j]));
                }
            }
        }

        edges.sort(Edge::compareTo);
        for (Edge edge : edges ){
            if (d.find(edge.a[0]) != d.find(edge.a[1])){
                d.union(edge.a[0], edge.a[1]);
                TBU.add(edge);
                w += edge.a[2];
            }
        }

        matrix = new int[size][size];
        for (Edge edge : TBU){
            matrix[edge.a[0]][edge.a[1]] = edge.a[2];
        }

        return w;
    }
    public static void main(String args[]){
        GraphAdjMatrix g = new GraphAdjMatrix(10);
        g.addEdge(1, 4, 20);
        g.addEdge(3, 4, 14);
        g.addEdge(3, 5, 3);
        g.addEdge(5, 5, 7);
        g.addEdge(7, 2);
        g.addEdge(1, 8, 35);
        g.addEdge(8, 7, 8);
        System.out.println(g.createSpanningTree());
    }
    public class Edge implements Comparable{
        int a[];

        public Edge(int v1, int v2, int weight){
            this.a = new int[]{v1,v2,weight};
        }
        @Override
        public int compareTo(Object o) {
            Edge other = (Edge)o;
            return(a[2]-((Edge)o).a[2]);
        }
    }   
}