package org.psjava.ds.graph;

import ch.usi.si.codelounge.jsicko.Contract;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MutableGraphTest {

    @Test
    public void emptyDequeTest() throws Throwable {
        Executable textFixture = () -> {
            MutableGraph<String, UndirectedEdge> mutableGraph = new MutableGraph<>();
            String v1 = "a";
            String v2 = "b";
            String v3 = "c";
            UndirectedEdge<String> e1 = SimpleUndirectedEdge.create(v2, v3);
            mutableGraph.insertVertex(v1);
            mutableGraph.insertVertex(v2);
            mutableGraph.addEdge(v3, e1);
        };
        assertThrows(Contract.PreconditionViolation.class, textFixture);
    }

    @Test
    public void fullGraphTest() throws Throwable {
        MutableGraph<String, UndirectedEdge> mutableGraph = new MutableGraph<>();
        String v1 = "a";
        String v2 = "b";
        UndirectedEdge<String> e1 = SimpleUndirectedEdge.create(v1, v2);
        mutableGraph.insertVertex(v1);
        mutableGraph.insertVertex(v2);
        mutableGraph.addEdge(v1, e1);
        mutableGraph.addEdge(v2, e1);
        mutableGraph.getEdges(v1);
    }

}
