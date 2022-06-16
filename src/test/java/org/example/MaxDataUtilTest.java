package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaxDataUtilTest {

    private SinglyLinkedList<Integer> list;

    @BeforeEach
    public void init() {
        list = new SinglyLinkedList<>();
        list.addFirst(122);
        list.addLast(99);
        list.addLast(15);
        list.addLast(5);
        list.addFirst(1);
    }

    @AfterEach
    public void destroy() {
        list.clear();
    }

    @Test
    void getMaxData() {
        List<Integer> expected = new ArrayList<>(Arrays.asList(15, 99, 122));
        Assertions.assertIterableEquals(expected, MaxDataUtil.getMaxData(list, 3));
    }

    @Test
    void getMaxData_checkMaxNumberIsZero() {
        assertThrows(IllegalArgumentException.class, () -> MaxDataUtil.getMaxData(list, 0));
    }

    @Test
    void getMaxData_checkNullListData() {
        list.clear();
        assertThrows(IllegalArgumentException.class, () -> MaxDataUtil.getMaxData(list, 3));
    }

    @Test
    void getMaxData_checkMaxNumberWithinBoundaries_greaterNumber() {
        assertThrows(IllegalArgumentException.class, () -> MaxDataUtil.getMaxData(list, 1222));
    }

    @Test
    void getMaxData_checkMaxNumberWithinBoundaries_negativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> MaxDataUtil.getMaxData(list, -14));
    }
}