package org.example;

public class Main {

    public static void main(String[] args) {
        SinglyLinkedList<Integer> numbers = new SinglyLinkedList<>();
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(4);
        numbers.addLast(5);
        numbers.addLast(6);
        numbers.addFirst(1);
        numbers.printList();
        System.out.println();

        System.out.println("Does list contain 100 value? " + numbers.contains(100));
        System.out.println("What is the index of 99? " + numbers.indexOf(99));
        System.out.println("First list element: " + numbers.getFirst());
        System.out.println("Last list element: " + numbers.getLast());

        numbers.removeObject(4);
        numbers.removeFirst();
        numbers.removeLast();
        numbers.addByIndex(1, 99);
        numbers.printList();
    }
}
