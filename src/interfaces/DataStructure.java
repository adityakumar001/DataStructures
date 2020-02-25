package interfaces;

public interface DataStructure<T> {
    T delete(T data);

    void insert(T data);

    int search(T data);

}
