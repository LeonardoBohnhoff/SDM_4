package org.psjava.ds.deque;

public class Node<T> {
    public T v;
    public Node<T> n;
    public Node<T> p;

    public Node(T v, Node<T> p, Node<T> n) {
        this.v = v;
        this.p = p;
        this.n = n;
    }
    
    public Node() {}
}
