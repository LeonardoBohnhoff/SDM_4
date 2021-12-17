package org.psjava.ds.deque;

import java.util.Iterator;

import ch.usi.si.codelounge.jsicko.Contract;
import org.psjava.ds.PSCollection;
import org.psjava.util.IterableToString;
import org.psjava.util.ReadOnlyIterator;

import static ch.usi.si.codelounge.jsicko.Contract.old;
import static ch.usi.si.codelounge.jsicko.ContractUtils.implies;

public class DoubleLinkedList<T> implements PSDeque<T>, PSCollection<T>, Contract {

    public static <T> DoubleLinkedList<T> create() {
        return new DoubleLinkedList<T>();
    }

    private final Node<T> head;
    private final Node<T> tail;

    public DoubleLinkedList() {
        head = new Node<T>(null, null, null);
        tail = new Node<T>(null, null, null);
        head.n = tail;
        tail.p = head;
    }

    @Override
    @Pure
    @Ensures("check_emptiness")
    public boolean isEmpty() {
        return head.n == tail;
    }

    @Override
    @Pure
    @Ensures("check_positivity")
    public int size() {
        int r = 0;
        Node<T> node = head;
        while (node.n != tail) {
            r++;
            node = node.n;
        }
        return r;
    }

    @Override
    @Ensures("check_references_at_head_after_insertion")
    public void addToFirst(T v) {
        add(head.n, v);
    }

    @Override
    @Ensures("check_references_at_tail_after_insertion")
    public void addToLast(T e) {
        add(tail, e);
    }

    @Override
    @Ensures("is_empty")
    public void clear() {
        head.n = tail;
        tail.p = head;
    }

    @Override
    @Pure
    public T getFirst() {
        return head.n.v;
    }

    @Override
    @Pure
    public T getLast() {
        return tail.p.v;
    }

    @Override
    @Requires("!is_empty")
    @Ensures("check_references_at_head_after_removal")
    public T removeFirst() {
        return remove(head.n);
    }

    @Override
    @Requires("!is_empty")
    @Ensures("check_references_at_tail_after_removal")
    public T removeLast() {
        return remove(tail.p);
    }

    private T remove(Node<T> node) {
        node.p.n = node.n;
        node.n.p = node.p;
        return node.v;
    }

    private void add(Node<T> node, T v) {
        Node<T> newNode = new Node<>(v, node.p, node);
        newNode.p.n = newNode;
        newNode.n.p = newNode;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReadOnlyIterator<T>() {
            Node<T> next = head.n;

            @Override
            public boolean hasNext() {
                return next != tail;
            }

            @Override
            public T next() {
                T r = next.v;
                next = next.n;
                return r;
            }
        };
    }

    @Override
    public String toString() {
        return IterableToString.toString(this);
    }

    // jSicko additions

    @Invariant
    @Pure
    public boolean positive_size() {
        return size() >= 0;
    }

    @Invariant
    @Pure
    public boolean head_and_tail_pointers() {
        return this.head.n != null && this.tail.p != null && this.head.p == null && this.tail.n == null;
    }

    @Pure
    public boolean check_positivity(int returns) {
        return returns >= 0;
    }

    @Pure
    public boolean is_empty() {
        return this.head.n == this.tail && this.tail.p == this.head;
    }

    @Pure
    public boolean check_emptiness(boolean returns) {
        return implies(returns == true, () -> is_empty(), () -> !is_empty());
    }

    @Pure
    public boolean check_references_at_head_after_insertion(T v) {
        //return this.head.n.n == old(this).head.n;
        return this.head == this.head.n.p && this.head.n == this.head.n.n.p;
    }

    @Pure
    public boolean check_references_at_tail_after_insertion(T e) {
        //return this.tail.p.p == old(this).tail.p;
        return this.tail == this.tail.p.n && this.tail.p == this.tail.p.p.n;
    }

    @Pure
    public boolean check_references_at_head_after_removal() {
        //return this.head.n == old(this).head.n.n ;
        return this.head == this.head.n.p;
    }

    @Pure
    public boolean check_references_at_tail_after_removal() {
        //return this.tail.p == old(this).tail.p.p;
        return this.tail == this.tail.p.n;
    }
}
