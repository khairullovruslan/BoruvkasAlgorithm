import java.util.List;

public class Graph {

    private int vertNum;
    private int edgeNum;
    private Edge[] edges;



    public Graph(int vertNum, int edgeNum){
        this.vertNum = vertNum;
        this.edgeNum = edgeNum;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vertNum=" + vertNum +
                ", edgeNum=" + edgeNum +
                '}';
    }

    public Graph(){

    }


    public void setVertNum(int vertNum) {
        this.vertNum = vertNum;
    }

    public void setEdgeNum(int edgeNum) {
        this.edgeNum = edgeNum;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges.toArray(new Edge[0]);
    }



    public Edge[] getEdges() {
        return edges;
    }

    public int getVertNum() {
        return vertNum;
    }

    public int getEdgeNum() {
        return edgeNum;
    }

}