package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SinglyLinkedListTest {

    private SinglyLinkedList<Integer> list;

    @BeforeEach
    public void init() {
        list = new SinglyLinkedList<>();
        list.addFirst(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addFirst(1);
    }

    @AfterEach
    public void destroy() {
        list.clear();
    }

    @Test
    void getFirst() {
        Assertions.assertEquals(1, list.getFirst());
    }

    @Test
    void getLast() {
        Assertions.assertEquals(5, list.getLast());
    }

    @Test
    void addFirst() {
        list.addFirst(0);
        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        Assertions.assertIterableEquals(expected, list);
    }

    @Test
    void addLast() {
        list.addLast(6);
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Assertions.assertIterableEquals(expected, list);
    }

    @Test
    void addByIndex_checkExistingIndex() {
        list.addByIndex(1, 33);
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 33, 2, 3, 4, 5));
        Assertions.assertIterableEquals(expected, list);
    }

    @Test
    void addByIndex_toThrowIndexOutOfBoundException_whenCheckAbsentIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.addByIndex(99, 33));
    }

    @Test
    void indexOf_checkExistingData() {
        Assertions.assertEquals(4, list.indexOf(5));
    }

    @Test
    void indexOf_checkAbsentData() {
        Assertions.assertEquals(-1, list.indexOf(99));
    }

    @Test
    void removeFirst() {
        list.removeFirst();
        List<Integer> expected = new ArrayList<>(Arrays.asList(2, 3, 4, 5));
        Assertions.assertIterableEquals(expected, list);
    }

    @Test
    void removeLast() {
        list.removeLast();
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Assertions.assertIterableEquals(expected, list);
    }

    @Test
    void removeObject_existingObject_firstData() {
        list.removeObject(1);
        List<Integer> expected = new ArrayList<>(Arrays.asList(2, 3, 4, 5));
        Assertions.assertIterableEquals(expected, list);
    }

    @Test
    void removeObject_existingObject_midData() {
        list.removeObject(3);
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 4, 5));
        Assertions.assertIterableEquals(expected, list);
    }

    @Test
    void removeObject_existingObject_repeatedData() {
        list.addLast(1);
        list.removeObject(1);
        List<Integer> expected = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 1));
        Assertions.assertIterableEquals(expected, list);
    }

    @Test
    void removeObject_toThrowNoSuchElementException_whenSuchDataIsAbsent() {
        assertThrows(NoSuchElementException.class, () -> list.removeObject(34));
    }

    @Test
    void removeObject_toThrowNoSuchElementException_whenListIsEmpty() {
        list.clear();
        assertThrows(NoSuchElementException.class, () -> list.removeObject(5));
    }

    @Test
    void size() {
        Assertions.assertEquals(5, list.size());
    }

    @Test
    void isEmpty_toReturnFalse_whenListIsNotEmpty() {
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    void isEmpty_toReturnTrue_whenListIsEmpty() {
        list.clear();
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    void contains_toReturnTrue_whenListHasSuchData() {
        Assertions.assertTrue(list.contains(2));
    }

    @Test
    void contains_toReturnFalse_whenListHasNoSuchData() {
        Assertions.assertFalse(list.contains(786));
    }

    @Test
    void clear_toReturnTrue_whenListIsEmptyAfterClearing() {
        list.clear();
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    void get_whenIndexExists() {
        Assertions.assertEquals(5, list.get(4));
    }

    @Test
    void get_toThrowIndexOutOfBoundsException_whenIndexIsOutOfBoundaries() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(99));
    }

    @Test
    void iterator_next_dataExists() {
        Assertions.assertEquals(1, list.iterator().next());
    }

    @Test
    void iterator_next_toThrowNoSuchElementException_whenAbsentData() {
        list.clear();
        assertThrows(NoSuchElementException.class, () -> list.iterator().next());
    }

    @Test
    void iterator_hasNext_toReturnTrue_whenDataExists() {
        Assertions.assertTrue(list.iterator().hasNext());
    }
}