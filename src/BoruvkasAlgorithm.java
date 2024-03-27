
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoruvkasAlgorithm {
//    public static List<Integer> vertNums = new ArrayList<>();
//    public static List<Integer> edgeNums = new ArrayList<>();
//    public static List<Long> times = new ArrayList<>();
//    public static List<Integer> countIt = new ArrayList<>();
    private int countIteration;
    private int vertNum;
    private int edgeNum;
    private  Edge[] edges;
    private Subset[] subsets;
    private int[] cheapest;

    public void start(Graph graph){
        vertNum = graph.getVertNum();
        edgeNum = graph.getEdgeNum();
        edges = graph.getEdges();
        // в начальном этапе кол-во компонент = кол-ву вершин
        subsets = new Subset[vertNum];
        cheapest = new int[vertNum];
        for (int i = 0; i < vertNum; i++) {
            subsets[i] = new Subset();
            subsets[i].setParent(i);
            subsets[i].setRank(0);
            cheapest[i] = -1;
        }

        boruvkaMST();
    }
    public void boruvkaMST() {
        // изначально в графе кол-во деревьев = кол-во  вершин
        int numTree = vertNum;
        // вес нашего остова с наименьшим  весом
        int MSTweight = 0;
        countIteration = 0;


        //Он будет работать до тех пор, пока MST не останется единственным деревом

        // Начало работы алгоритма
        long before = System.currentTimeMillis();
        while (numTree > 1) {
            countIteration++;
            Arrays.fill(cheapest, -1);

            //Перебираем все ребра, чтобы найти самое минимальное
            for (int i = 0; i < edgeNum; i++) {
                countIteration++;
                // мы смотрим принадлжат ли вершины  ребра разным компонентам,
                // то есть соединяет ли данное ребро две компоненты свзяности
                int set1 = find(subsets, edges[i].getSrc());
                int set2 = find(subsets, edges[i].getDest());

                // если две вершины уже принадлежат одной компоненте, то скипает текущее ребро, по алгоритму
                // мы его не рассматриваем
                if (set1 != set2) {

                    //Если они принадлежат к разным компонентам, проверяем, какой из них минимальный
                    if (cheapest[set1] == -1 || edges[cheapest[set1]].getWeight() > edges[i].getWeight()) {
                        cheapest[set1] = i;
                    }

                    if (cheapest[set2] == -1 || edges[cheapest[set2]].getWeight() > edges[i].getWeight()) {
                        cheapest[set2] = i;
                    }
                }
            }

            //Добавляем наим. ребра, полученные выше, в MST
            System.out.println(Arrays.toString(cheapest));
            for (int j = 0; j < vertNum; j++) {
                countIteration++;

                //Проверяем, существует ли минимальный из текущего набора
                if (cheapest[j] != -1) {
                    int set1 = find(subsets, edges[cheapest[j]].getSrc());
                    int set2 = find(subsets, edges[cheapest[j]].getDest());
                    if(set1 != set2){
                        MSTweight += edges[cheapest[j]].getWeight();
                        uniteSubsets(subsets, set1, set2);
                        numTree--;
                    }
                }
            }
        }

        long after = System.currentTimeMillis();
        System.out.println("Final weight of MST :" + MSTweight);
//        System.out.println("Количество итерация: " + countIteration + " Количество вершин: " + vertNum + " Количество ребер: " + edgeNum);
//        System.out.println(1.0 * countIteration );
//        System.out.println(edgeNum * Math.log(vertNum));
//        System.out.println(after - before + " ms");
//        vertNums.add(vertNum);
//        edgeNums.add(edgeNum);
//        times.add(after - before);
//        countIt.add(countIteration);



 //       System.out.println();
    }

    //Метод нахождения набора вершин рекурсивно
    private int find(Subset[] subsets, int vert) {
        if (subsets[vert].getParent() != vert) {
            subsets[vert].setParent(find(subsets, subsets[vert].getParent()));
        }
        return subsets[vert].getParent();
    }

    //Метод объединения подмножеств, он использует ранг для выбора родителя
    private void uniteSubsets(Subset[] subsets, int v1, int v2){

        if(subsets[v1].getRank() < subsets[v2].getRank()){
            subsets[v1].setParent(subsets[v2].getParent());
        }else if(subsets[v1].getRank() > subsets[v2].getRank()){
            subsets[v2].setParent(subsets[v1].getParent());
        }else{
            subsets[v2].setParent(subsets[v1].getParent());
            subsets[v1].setRank(subsets[v1].getRank() + 1);
        }
    }
}