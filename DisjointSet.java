public class DisjointSet {
    int parents[];

    public DisjointSet(int size){
        this.parents = new int[size];
        for (int i = 0; i < size; i++){
            parents[i] = -1;
        }
    }
    int find(int e){
        if (parents[e] < 0){
            return e;
        }
        else{
            parents[e] = find(parents[e]);
            return parents[e];
        }
    }
    void union(int e1, int e2){
        int set1 = find(e1);
        int set2 = find(e2);
        if(parents[set1] < parents[set2]){
            parents[set1] = parents[set1] + parents[set2];
            parents[set2] = set1;
        } else {
            parents[set2] = parents[set1] + parents[set2];
            parents[set1] = set2;
        }
    }
}