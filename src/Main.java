import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BoruvkasAlgorithm boruvka = new BoruvkasAlgorithm();

        ArrayList<Graph> graphs = readTests();
        for (Graph graph : graphs) {
            boruvka.start(graph);
        }


    }

    public static Graph generateGraphs() {
        Random random = new Random();
        int countVert = random.nextInt(100, 1000);
        int countEdge = 0;
        List<Edge> edges = new ArrayList<>();
        int[] verts = new int[countVert];
        for (int j = 0; j < countVert; j++) {
            verts[j] = j;
        }
        for (int i = 0; i < countVert - 1; i++) {
            int countEdges = random.nextInt(1, countVert - i);
            Set<Integer> vertex = new HashSet<>();
            for (int j = 0; j < countEdges; j++) {
                int addVertex = random.nextInt(i + 1, countVert);
                vertex.add(addVertex);
            }
            for (Integer num : vertex) {
                int weight = random.nextInt(1, 10);
                edges.add(new Edge(i, num, weight));

            }
            countEdge += vertex.size();
        }
        Graph graph = new Graph(countVert, countEdge);
        graph.setEdges(edges);
        return graph;
    }
    public static void writeTestInFile(){
        /*
        Example
        #1
        Кол-во вершин;Кол-во ребер
        0-1-7 (0 - начало, 1 - конец, 7 - вес)
        .....
        #2
        * */
        try {
            File file = new File("tests1.txt"); // Путь к файлу
            FileWriter fileWriter = new FileWriter(file, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(int i = 0; i < 100; i++){
                Graph graph = generateGraphs();
                bufferedWriter.write("#" + i + ";" + graph.getVertNum() + ";" + graph.getEdgeNum());
                bufferedWriter.newLine();
                Edge[] edges = graph.getEdges();
                for (Edge edge: edges){
                    bufferedWriter.write(edge.getSrc() + "-" + edge.getDest() + "-" + edge.getWeight());
                    bufferedWriter.newLine();
                }


            }
            bufferedWriter.close();

        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
    public static ArrayList<Graph> readTests() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("tests.txt"));
        ArrayList<Graph> graphs = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        Graph currentGraph = new Graph();
        while (scanner.hasNext()){
            String value = scanner.nextLine();
            if (value.startsWith("#")){
                currentGraph.setEdges(edges);
                graphs.add(currentGraph);
                String[] data = value.split(";");
                currentGraph  = new Graph();
                edges = new ArrayList<>();
                currentGraph.setVertNum(Integer.parseInt(data[1]));
                currentGraph.setEdgeNum(Integer.parseInt(data[2]));
            }
            else {
                String[] data = value.split("-");
                edges.add(new Edge(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
            }

        }

        return graphs;



    }
}