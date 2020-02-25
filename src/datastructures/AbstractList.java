package datastructures;

import interfaces.List;
import interfaces.Queue;
import interfaces.Stack;

public abstract class AbstractList<T> implements Stack<T>, Queue<T>, List<T> {
    static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;
    transient int size;
    boolean sorted;

    public final int size() {
        return size;
    }

    @Override
    public void enqueue(T data) {
        insert(data);
    }

    @Override
    public T dequeue() {
        return delete(0);
    }

    @Override
    public T peekFront() {
        return get(0);
    }

    @Override
    public T pop() {
        return delete();
    }

    @Override
    public void push(T data) {
        insert(data);
    }

}
