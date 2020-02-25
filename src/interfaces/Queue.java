package interfaces;

public interface Queue<T> {
    void enqueue(T type);

    T dequeue();

    int size();

    T peekFront();
}
