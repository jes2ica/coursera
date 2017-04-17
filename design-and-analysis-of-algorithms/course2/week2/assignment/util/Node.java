package graph.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yijiez on 3/27/17.
 */
public class Node<T> implements Comparable<Node<T>> {
    private T value;
    private List<Node<T>> fromNodes;
    private List<Node<T>> toNodes;
    private Node parent;
    private boolean visited;
    private int distance;
    private int ranking;

    public Node(T value) {
        this.value = value;
        this.fromNodes = new ArrayList<Node<T>>();
        this.toNodes = new ArrayList<Node<T>>();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<Node<T>> getFromNodes() {
        return fromNodes;
    }

    public void addFrom(Node<T> t) {
        fromNodes.add(t);
    }

    public List<Node<T>> getToNodes() {
        return toNodes;
    }

    public void addTo(Node<T> t) {
        toNodes.add(t);
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {return String.valueOf(value);}

    @Override
    public int compareTo(Node<T> n2) {
        return this.distance - n2.distance;
    }
}