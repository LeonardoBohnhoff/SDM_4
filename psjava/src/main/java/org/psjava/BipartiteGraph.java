package org.psjava;

import org.psjava.util.Assertion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ch.usi.si.codelounge.jsicko.Contract;


public class BipartiteGraph<V> implements Contract{

    private Set<V> lefts = new HashSet<>();
    private Set<V> rights = new HashSet<>();
    private List<BipartiteGraphEdge<V>> edges = new ArrayList<>();

	@Pure
	public boolean is_in_right(V v) {
		return rights.contains(v);
	}

	@Pure
	public boolean is_in_left(V v) {
		return lefts.contains(v);
	}

	@Pure
	public boolean not_in_right(V v) {
		return !is_in_right(v);
	}

	@Pure
	public boolean not_in_left(V v) {
		return !is_in_left(v);
	}

	@Requires("not_in_right")
	@Ensures("is_in_left")
    public void addLeft(V v) {
        // Assertion.ensure(!rights.contains(v), () -> "already in right side. vertex=" + v);
        lefts.add(v);
    }

	@Requires("not_in_left")
	@Ensures("is_in_right")
	public void addRight(V v) {
		// Assertion.ensure(!lefts.contains(v), () -> "already in left size. vertex=" + v);
		rights.add(v);
	}

	@Pure
	public boolean nodes_exist_in_left_and_right(final V left, final V right) {
		return is_in_right(right) && is_in_left(left);
	}

	@Requires("nodes_exist_in_left_and_right")
    public void addEdge(final V left, final V right) {
        // assertVertexExist(left, lefts);
        // assertVertexExist(right, rights);
		edges.add(new BipartiteGraphEdge<>(left, right));
    }

    // private void assertVertexExist(V v, Set<V> set) {
    //     Assertion.ensure(set.contains(v), () -> "vertex is not in graph. vertex=" + v);
    // }

    Collection<V> getLeftVertices() {
        return Collections.unmodifiableCollection(lefts);
    }

    Collection<V> getRightVertices() {
        return Collections.unmodifiableCollection(rights);
    }

    Collection<BipartiteGraphEdge<V>> getEdges() {
        return Collections.unmodifiableCollection(edges);
    }
}
