import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Vertex {

    private int num;
    private Set<Edge> edges;

    public Vertex(int num) {
        this.num = num;
        edges = new HashSet<>();
    }

    public int getNum() {
        return num;
    }

    public boolean addEdge(Edge edge){
        return edges.add(edge);
    }

    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

}