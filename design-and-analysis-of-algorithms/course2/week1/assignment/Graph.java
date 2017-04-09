package graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yijiez on 3/19/17.
 */
public class Graph {

    Map<Integer, Node> nodes;

    public Graph() {
        nodes = new HashMap<Integer, Node>();
    }

    public Map<Integer, Node> getNodes() {
        return nodes;
    }

    public void printGraph() {
        for (Node vertex: nodes.values()) {
            System.out.print(vertex.toString() + ": ");
            for (Node node : vertex.to) {
                System.out.print(node.toString() + " ");
            }
            System.out.println("");
        }
    }

    public void addEdge(int source, int destination) {
        if (nodes.get(source) == null) {
            Node node = new Node(source);
            nodes.put(source, node);
        }

        if (nodes.get(destination) == null) {
            Node node = new Node(destination);
            nodes.put(destination, node);
        }

        Node sourceNode = nodes.get(source);
        Node destinationNode = nodes.get(destination);

        sourceNode.to.add(destinationNode);
        destinationNode.from.add(sourceNode);
    }
}

class Node {
    long value;
    long label;
    List<Node> from;
    List<Node> to;
    boolean isVisited;

    public Node(long value) {
        this.value = value;
        this.from = new ArrayList<Node>();
        this.to = new ArrayList<Node>();
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public List<Node> getFrom() { return from; }

    public List<Node> getTo() { return to; }

    @Override
    public String toString() {
        return String.format("[Node: value=%s,label=%s]", value, label);
    }
}