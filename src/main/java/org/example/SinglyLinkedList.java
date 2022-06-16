package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements Iterable<E>, java.io.Serializable {
    private static final String NULL_DATA_EXCEPTION = "Data should not be null";
    private static final String ABSENT_ELEMENT_EXCEPTION = "Element is absent";
    private static final String EMPTY_LIST = "The list is empty";
    private transient Link<E> first;
    private transient Link<E> last;
    private transient int size;

    /**
     * Private method for unlinking the first element. It is used in some methods below for removing data
     * @param f element in the form of Link</E>
     * @returns the first data of the list
     */
    private E unlinkFirst(Link<E> f) {
        final E data = f.getData();
        final Link<E> next = f.getNext();
        f.setData(null);
        f.setNext(null);
        first = next;
        if (next == null) {
            last = null;
        }
        size--;
        return data;
    }

    /**
     * Private method for unlinking the last element. It is used in some methods below for removing data
     * @param l as the last element in the form of Link</E>
     * @returns the first data of the list
     */
    private E unlinkLast(Link<E> l) {
        final E data = l.getData();
        Link<E> temp = first;
        Link<E> t = new Link<>();
        while (temp.getNext() != null) {
            t = temp;
            temp = temp.getNext();
        }
        last = t;
        last.setNext(null);
        size--;
        return data;
    }

    /**
     * Gets the first data from the list
     * @returns the first list data in E form
     * @throws NoSuchElementException if the first element is null
     */
    public E getFirst() {
        final Link<E> f = first;
        if (f == null) {
            throw new NoSuchElementException(NULL_DATA_EXCEPTION);
        }
        return f.getData();
    }

    /**
     * Gets the last data from the list
     * @returns the last list data in E form
     * @throws NoSuchElementException if the last element is null
     */
    public E getLast() {
        final Link<E> l = last;
        if (l == null) throw new NoSuchElementException(NULL_DATA_EXCEPTION);
        return l.getData();
    }

    /**
     * Adds the first data to the list
     * @param value in E form
     */
    public void addFirst(E value) {
        Link<E> e = new Link<>();
        e.setData(value);
        if (first == null) {
            first = e;
            last = e;
        } else {
            e.setNext(first);
            first = e;
        }
        size++;
    }

    /**
     * Adds the last data to the list
     * @param value in E form
     */
    public void addLast(E value) {
        Link<E> e = new Link<>();
        e.setData(value);
        if (last == null) {
            first = e;
        } else {
            last.setNext(e);
        }
        last = e;
        size++;
    }

    /**
     * Prints the list
     * @throws NoSuchElementException if the list is empty
     */
    public void printList() {
        if (!isEmpty()) {
            Link<E> e = first;
            while (e != null) {
                System.out.print(e.getData() + " ");
                e = e.getNext();
            }
        } else {
            throw new NoSuchElementException(EMPTY_LIST);
        }
    }

    /**
     * Adds value to the index position within the list
     * @params index in int form, value in E form
     * @throws NoSuchElementException if the list is empty
     */
    public void addByIndex(int index, E value) {
        Link<E> temp = first;
        Link<E> e = new Link<>();
        e.setData(value);

        if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        } else {
            while (temp != null) {
                if (indexOf(temp.getData()) == index - 1) {
                    e.setNext(temp.getNext());
                    temp.setNext(e);
                    size++;
                    return;
                }
                temp = temp.getNext();
            }
        }
    }

    /**
     * Gets the index of Object e from the list
     * @param e in Object form
     * @returns int index or -1 if there is no Object e in the list
     */
    public int indexOf(Object e) {
        int index = 0;
        if (e == null) {
            for (Link<E> x = first; x != null; x = x.getNext()) {
                if (x.getData() == null) return index;
                index++;
            }
        } else {
            for (Link<E> x = first; x != null; x = x.getNext()) {
                if (e.equals(x.getData())) return index;
                index++;
            }
        }
        return -1;
    }

    /**
     * Gets the first element data while removing it
     * @returns the first element data in E form
     * @throws NoSuchElementException if the first data is null
     */
    public E removeFirst() {
        final Link<E> f = first;
        if (f == null) {
            throw new NoSuchElementException(NULL_DATA_EXCEPTION);
        }
        return unlinkFirst(f);
    }

    /**
     * Gets the last element data while removing it
     * @returns the last element data in E form
     * @throws NoSuchElementException if the last data is null
     */
    public E removeLast() {
        final Link<E> l = last;
        if (l == null) {
            throw new NoSuchElementException(NULL_DATA_EXCEPTION);
        }
        return unlinkLast(l);
    }

    /**
     * Removes the element value from the list
     * @param data in E form
     * @throws NoSuchElementException if the data is null, if it is absent in the list or if the list is empty
     */
    public void removeObject(E data) {
        final Link<E> f = first;
        final Link<E> l = last;

        if (data == null) {
            throw new NoSuchElementException(NULL_DATA_EXCEPTION);
        }

        if (!contains(data)) {
            throw new NoSuchElementException(ABSENT_ELEMENT_EXCEPTION);
        }

        if (first == null) {
            throw new NoSuchElementException(EMPTY_LIST);
        }

        if (first == last && first.equals(data)) {
            first = null;
            last = null;
            size--;
            return;
        }

        if (first.getData().equals(data)) {
            first = first.getNext();
            size--;
            return;
        }

        Link<E> t = first;
        while (t.getNext() != null) {
            if (t.getNext().getData().equals(data)) {
                if (last == t.getNext()) {
                    last = t;
                }
                t.setNext(t.getNext().getNext());
                size--;
                return;
            }
            t = t.getNext();
        }
    }

    /**
     * Get the size of the list
     * @returns size in int form
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty
     * @returns a boolean value of whether the list is empty
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Checks if the list contains the data
     * @returns a boolean value of whether the list contains Object o
     */
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * Removes the data from the list
     */
    public void clear() {
        for (Link<E> x = first; x != null; ) {
            Link<E> next = x.getNext();
            x.setData(null);
            x.setNext(null);
            x = next;
        }
        first = last = null;
        size = 0;
    }

    // Positional Access Operations

    /**
     * Gets the data by the index
     * @param index in int form
     * @returns data in E form
     */
    public E get(int index) {
        checkElementIndex(index);
        return node(index).getData();
    }

    /**
     * Checks if the index is within the boundaries of list size
     * @param index in int form
     * @returns a boolean value
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * Provides a message with int index and int size data
     * @param index in int form
     * @returns a String message
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * Checks element index to be within the boundaries of list size
     * @param index in int form
     * @throws IndexOutOfBoundsException in case index is not within the list size
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * Gets Link<E> from int index data
     * @param index in int form
     * @returns Link<E>
     */
    private Link<E> node(int index) {
        if (index < (size >> 1)) {
            Link<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.getNext();
            return x;
        } else {
            return last;
        }
    }

    //Iterator operations

    /**
     * Gets iterator which allows to work with hasNext() and next() methods
     * @returns new SinglyListIterator in the form of Iterator<E>
     */
    @Override
    public Iterator<E> iterator() {
        return new SinglyListIterator();
    }

    /**
     * Private class for SinglyListIterator with Iterator<E> implemented with hasNext() and next() methods
     * @returns new SinglyListIterator in the form of Iterator<E>
     */
    private class SinglyListIterator implements Iterator<E> {
        int size = size();
        int index = 0;
        Link<E> currentLink = first;

        /**
         * Returns boolean of whether the next elements exists
         * @returns boolean
         */
        @Override
        public boolean hasNext() {
            return (index < size);
        }

        /**
         * Returns next value if it exists
         * @returns E of next value
         * @throws NoSuchElementException in case the element is absent
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException(ABSENT_ELEMENT_EXCEPTION);
            }
            E data = currentLink.getData();
            index++;
            currentLink = currentLink.getNext();
            return data;
        }
    }
}
