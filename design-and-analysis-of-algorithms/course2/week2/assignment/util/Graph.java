package graph.util;

import java.util.*;

/**
 * Created by yijiez on 3/27/17.
 */
public class Graph<T> {
    private Map<T, Node<T>> nodeMap;
    private Map<Node<T>, Set<Edge<T>>> edges;

    public Graph() {
        nodeMap = new HashMap<T, Node<T>>();
        edges = new HashMap<Node<T>, Set<Edge<T>>>();
    }

    public Node<T> getNode(T t) {
        return nodeMap.get(t);
    }

    public Map<T, Node<T>> getNodeMap() {
        return nodeMap;
    }

    public Node addNode(T t) {
        if (nodeMap.get(t) == null) {
            nodeMap.put(t, new Node<T>(t));
            edges.put(nodeMap.get(t), new HashSet<Edge<T>>());
        }
        return nodeMap.get(t);
    }

    public Set<Edge<T>> getEdges(T t) {
        return edges.get(nodeMap.get(t));
    }

    public void addEdge(T t1, T t2) {
        Node fromNode = nodeMap.get(t1);
        Node toNode = nodeMap.get(t2);
        edges.get(fromNode).add(new Edge<T>(fromNode, toNode, 0));
    }

    public void addEdge(T t1, T t2, int weight) {
        Node fromNode = nodeMap.get(t1);
        Node toNode = nodeMap.get(t2);
        edges.get(fromNode).add(new Edge<T>(fromNode, toNode, weight));
    }

    public void printGraph() {
        for (Node node: nodeMap.values()) {
            System.out.print(node.toString() + ": ");
            for (Edge edge : edges.get(node)) {
                System.out.print(edge.getTo() + " ");
            }
            System.out.println("");
        }
    }

    public void printGraphByNodes() {
        for (Node<T> node: nodeMap.values()) {
            System.out.print(node.toString() + ": ");
            for (Node<T> toNode : node.getToNodes()) {
                System.out.print(toNode.toString() + " ");
            }
            System.out.println("");
        }
    }
}
