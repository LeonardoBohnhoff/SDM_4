package org.psjava.ds.graph;

import ch.usi.si.codelounge.jsicko.Contract;
import org.psjava.ds.HashSetFactory;
import org.psjava.ds.array.DynamicArray;
import org.psjava.ds.map.MutableMap;
import org.psjava.goods.GoodMutableMapFactory;
import org.psjava.util.Assertion;

import java.util.Set;

// TODO use GraphV2
@Deprecated
public class MutableGraph<V, E> implements Graph<V, E>, Contract {

    public MutableGraph() {}

    public static <V, E> MutableGraph<V, E> create() {
        return new MutableGraph<V, E>();
    }

    private Set<V> vertices = HashSetFactory.INSTANCE.create();
    private MutableMap<V, DynamicArray<E>> edgeMap = GoodMutableMapFactory.getInstance().create();

    @Ensures("vertex_contained_in_set_and_map")
    public void insertVertex(V v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
            edgeMap.add(v, new DynamicArray<E>());
        }
    }

    @Requires("graph_contains_vertex_02")
    public void addEdge(V from, E edge) {
        //Assertion.ensure(edgeMap.containsKey(from), "Given vertex is not in the graph");
        edgeMap.get(from).addToLast(edge);
    }

    @Override
    @Pure
    public Set<V> getVertices() {
        return vertices;
    }

    @Override
    @Requires("graph_contains_vertex_01")
    public Iterable<E> getEdges(V v) {
        return edgeMap.get(v);
    }

    @Override
    public String toString() {
        return GraphToString.toString(this);
    }

    // jSicko additions

    @Pure
    public boolean graph_contains_vertex_01(V v) {
        return vertices.contains(v);
    }
    
    @Pure
    public boolean graph_contains_vertex_02(V from, E edge) {
        return vertices.contains(from);
    }

    @Pure
    public boolean vertex_contained_in_set_and_map(V v) {
        return vertices.contains(v) && edgeMap.containsKey(v);
    }
}
