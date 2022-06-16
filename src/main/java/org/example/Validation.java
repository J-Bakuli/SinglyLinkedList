package org.example;

public class Validation {
    private static final String NULL_MAX_DATA_NUMBER_EXCEPTION = "Max data number should not be null";
    private static final String MAX_DATA_NUMBER_WITHIN_BOUNDARIES = "Max data number is greater than list size";
    private static final String EMPTY_LIST = "The list is empty";

    public static void checkNullListData(SinglyLinkedList<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException(EMPTY_LIST);
        }
    }

    public static void checkMaxNumberWithinBoundaries(SinglyLinkedList<Integer> list, int number) {
        if (number > list.size() || number < 0) {
            throw new IllegalArgumentException(MAX_DATA_NUMBER_WITHIN_BOUNDARIES);
        }
    }

    public static void checkMaxNumberIsZero(int number) {
        if (number == 0) {
            throw new IllegalArgumentException(NULL_MAX_DATA_NUMBER_EXCEPTION);
        }
    }
}
