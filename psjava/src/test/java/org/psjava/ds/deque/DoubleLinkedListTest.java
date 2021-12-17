package org.psjava.ds.deque;

import ch.usi.si.codelounge.jsicko.Contract;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DoubleLinkedListTest {

    @Test
    public void emptyDequeTest() throws Throwable {
        Executable textFixture = () -> {
            DoubleLinkedList<String> doubleLinkedList = new DoubleLinkedList<>();
            doubleLinkedList.size();
            doubleLinkedList.isEmpty();
            doubleLinkedList.removeFirst();
        };
        assertThrows(Contract.PreconditionViolation.class, textFixture);
    }

    @Test
    public void filledDequeTest() throws Throwable {
        DoubleLinkedList<String> doubleLinkedList = new DoubleLinkedList<>();
        doubleLinkedList.addToFirst("a");
        doubleLinkedList.addToFirst("b");
        doubleLinkedList.addToLast("c");
        doubleLinkedList.addToLast("d");
        doubleLinkedList.removeFirst();
        doubleLinkedList.removeLast();
        doubleLinkedList.size();
        doubleLinkedList.isEmpty();
        doubleLinkedList.clear();
    }

}
