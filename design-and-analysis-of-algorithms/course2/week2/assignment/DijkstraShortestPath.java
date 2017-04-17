package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

import graph.util.Edge;
import graph.util.Node;
import graph.util.Graph;

/**
 * Created by yijiez on 3/26/17.
 */
public class DijkstraShortestPath {

    private int source;
    private Graph<Integer> graph;

    public DijkstraShortestPath(Graph<Integer> graph, int source) {
        this.graph = graph;
        this.source = source;

        for(Node node : graph.getNodeMap().values()) {
            node.setDistance(Integer.MAX_VALUE);
        }
    }

    public void calculateShortestPath() {
        Node<Integer> from = graph.getNodeMap().get(source);
        from.setDistance(0);
        for (Edge edge : graph.getEdges(from.getValue())) {
            edge.getTo().setDistance(Integer.MAX_VALUE);
        }

        System.out.println("edges:");
        for(Node<Integer> node : graph.getNodeMap().values()) {
            System.out.println(graph.getEdges(node.getValue()));
        }

        for (int i = 0; i < graph.getNodeMap().size(); i++) {
            int value = from.getValue();
            System.out.println("from: " + from.getValue());
            System.out.println("edges: " + graph.getEdges(from.getValue()));
            for (Edge edge : graph.getEdges(from.getValue())) {
                Node<Integer> to = edge.getTo();
                System.out.println("to: " + to);
                if (to.isVisited()) continue;
                int currentDistance = from.getDistance() + edge.getWeight();
                System.out.println("currentDistance: " + currentDistance);
                System.out.println("toDistance: " + to.getDistance());
                if (currentDistance < to.getDistance()) {
                    to.setDistance(currentDistance);
                    to.setParent(from);
                    graph.getNodeMap().put(to.getValue(), to);
                }
            }
            from.setVisited(true);
            from = getNodeWithShortestDistance();
        }
    }

    public Node<Integer> getNodeWithShortestDistance() {
        Node<Integer> targetNode = null;
        int shortestDistance = Integer.MAX_VALUE;

        for (Node node : graph.getNodeMap().values()) {
            if (node.isVisited()) continue;
            if (node.getDistance() < shortestDistance) {
                targetNode = node;
                shortestDistance = node.getDistance();
            }
        }
        return targetNode;
    }

    public void calculateShortestPathWithHeap() {
        Node<Integer> sourceNode = graph.getNodeMap().get(source);
        sourceNode.setDistance(0);
        for (Edge edge : graph.getEdges(sourceNode.getValue())) {
            edge.getTo().setDistance(Integer.MAX_VALUE);
        }

        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
        priorityQueue.add(sourceNode);
        while (!priorityQueue.isEmpty()) {
            Node<Integer> from = priorityQueue.poll();
            from.setVisited(true);
            for (Edge edge : graph.getEdges(from.getValue())) {
                Node to = edge.getTo();
                if (to.isVisited()) continue;
                int currentDistance = from.getDistance() + edge.getWeight();
                if (currentDistance < to.getDistance()) {
                    priorityQueue.remove(to);
                    to.setDistance(currentDistance);
                    to.setParent(from);
                    priorityQueue.add(to);
                }
            }
        }
    }

    private static void initGraph(String fileName, Graph<Integer> graph) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int source = -1;
            while (sc.hasNext()) {
                String curStr = sc.next();
                if (curStr.contains(",")) {
                    String[] strs = curStr.split(",");
                    Node toNode = graph.getNodeMap().get(Integer.parseInt(strs[0]));
                    if (toNode == null) {
                        graph.addNode(Integer.parseInt(strs[0]));
                    }
                    graph.addEdge(source, Integer.parseInt(strs[0]), Integer.parseInt(strs[1]));
                } else {
                    Node fromNode = graph.getNodeMap().get(Integer.parseInt(curStr));
                    if (fromNode == null) {
                        graph.addNode(Integer.parseInt(curStr));
                    }
                    source = Integer.parseInt(curStr);
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fileName = "/Users/yijiez/IdeaProjects/algorithm/src/graph/data/testDijkstraData.txt";

        Graph<Integer> graph = null;
        DijkstraShortestPath dijkstraShortestPath = null;

        graph = new Graph();
        initGraph(fileName, graph);
        System.out.println("Start Dijkstra!");
        dijkstraShortestPath = new DijkstraShortestPath(graph, 1);
        dijkstraShortestPath.calculateShortestPath();
        for (Node node : graph.getNodeMap().values()) {
            System.out.println(String.format("Distance for Node: %d = %d", node.getValue(), node.getDistance()));
        }

        graph = new Graph();
        initGraph(fileName, graph);
        graph.printGraph();
        System.out.println("Start Dijkstra using heap!");
        dijkstraShortestPath = new DijkstraShortestPath(graph, 1);
        dijkstraShortestPath.calculateShortestPathWithHeap();
        for (Node node : graph.getNodeMap().values()) {
            System.out.println(String.format("Distance for Node: %d = %d", node.getValue(), node.getDistance()));
        }
    }
}
