package interfaces;

public interface List<T> extends DataStructure<T> {

    void sort(DataComparator<T> comparisonHelper);

    void insert(T type, int n);

    /**
     * Deletes the nth element from the list.
     */
    T delete(int n);

    /**
     * Deletes the last element from the list.
     */
    T delete();

    T get(int n);
}
