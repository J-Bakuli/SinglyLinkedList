package org.example;

public class Link<E> {
    private Link<E> next;
    private E data;

    public Link() {
    }

    public Link(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public Link<E> getNext() {
        return next;
    }

    public void setNext(Link<E> next) {
        this.next = next;
    }

    public void setData(E data) {
        this.data = data;
    }
}
