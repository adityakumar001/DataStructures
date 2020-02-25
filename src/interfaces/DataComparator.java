package interfaces;

public interface DataComparator<T> {

    /*Returns a positive number when the type is of greater value compared to the caller object
     * a negative integer if type has lesser value or return zero if both are equal.*/
    int compare(T value1, T value2);
}
