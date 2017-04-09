package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by yijiez on 3/19/17.
 */
public class StronglyConnectedComponents {

    private static int numOfSCCs = 0;
    private static int curSize = 0;

    private void initGraph(String fileName, Graph graph) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNextInt()) {
                graph.addEdge(sc.nextInt(), sc.nextInt());
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void calculateFinishTime(Node node, List<Node> nodesByFinishTimeDesc) {
        node.setVisited(true);
        for (Node toNode : node.getTo()) {
            if (toNode.isVisited()) continue;
            calculateFinishTime(toNode, nodesByFinishTimeDesc);
        }
        nodesByFinishTimeDesc.add(0, node);
    }

    private void findSCC(Node node) {
        node.setVisited(true);
        curSize++;
        for (Node fromNode : node.getFrom()) {
            if (fromNode.isVisited()) continue;
            findSCC(fromNode);
        }
    }

    public static void main(String[] args) {
        String fileName = "/Users/yijiez/IdeaProjects/algorithm/src/graph/data/SCC.txt";
        StronglyConnectedComponents scc = new StronglyConnectedComponents();

        Graph graph = new Graph();
        scc.initGraph(fileName, graph);

        List<Node> nodesByFinishTimeDesc = new ArrayList<Node>();

        for (Node node : graph.getNodes().values()) {
            if (node.isVisited()) continue;
            scc.calculateFinishTime(node, nodesByFinishTimeDesc);
        }

        for (Node node : nodesByFinishTimeDesc) {
            System.out.print(node.value);
            node.setVisited(false);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(5);
        for (Node node : nodesByFinishTimeDesc) {
            if (node.isVisited()) continue;
            scc.findSCC(node);
            numOfSCCs++;
            if (pq.size() < 5) {
                pq.offer(curSize);
            } else {
                int minSize = pq.peek();
                if (minSize < curSize) {
                    pq.poll();
                    pq.offer(curSize);
                }
            }
            System.out.println("curSize: " + curSize);
            curSize = 0;
        }
        System.out.println("PQ result: ");
        while (pq.size() > 0) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();
        System.out.println("Number of SCCs: " + numOfSCCs);
    }
}
