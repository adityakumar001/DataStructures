package datastructures;

import interfaces.DataComparator;

@SuppressWarnings("unchecked")
public class DynamicArray<T> extends AbstractList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] defaultArray;
    private DataComparator<T> comparator;

    public DynamicArray() {
        size = 0;
        defaultArray = new Object[DEFAULT_CAPACITY];
    }

    @SafeVarargs
    public DynamicArray(T... values) {
        size = 0;
        defaultArray = new Object[values.length + 1];
        for (T value : values) {
            insert(value);
        }
    }

    public DynamicArray(T data) {
        defaultArray = new Object[10];
        defaultArray[0] = data;
        size++;
    }

    @Override
    public T delete() {
        if (size == 0) throw new IndexOutOfBoundsException("List is empty !!");
        T data = (T) defaultArray[size - 1];
        defaultArray[--size] = null;
        return data;
    }

    @Override
    public void sort(DataComparator<T> comparisonHelper) {
        this.comparator = comparisonHelper;
        sorted = true;
        quickSort(0, size - 1);
    }

    private int partition(int beg, int end) {
        int loc = beg - 1;
        int comparison;
        for (int i = beg; i < end; i++) {
            comparison = comparator.compare((T) defaultArray[i], (T) defaultArray[end]);
            if (comparison < 0) {
                swap(++loc, i);
            }
        }
        swap(++loc, end);
        return loc;
    }

    private void swap(int from, int to) {
        Object temp = defaultArray[from];
        defaultArray[from] = defaultArray[to];
        defaultArray[to] = temp;
    }

    private void quickSort(int beg, int end) {
        if (beg < end) {
            int partition = partition(beg, end);
            System.out.println(this);
            quickSort(beg, partition - 1);
            quickSort(partition + 1, end);
        }
    }

    @Override
    public T delete(int n) {

        T data = (T) defaultArray[n];
        if (size - n + 1 >= 0) System.arraycopy(defaultArray, n + 1, defaultArray, n + 1 - 1,
                size - n + 1);
        --size;
        return data;

    }

    @Override
    public T get(int n) {
        return (T) defaultArray[n];
    }

    @Override
    public T delete(T type) {
        int loc = search(type);

        return loc > -1 ? delete(loc) : null;
    }

    @Override
    public void insert(T data) {
        if (spaceAvailable()) {
            defaultArray[size++] = data;
        }
    }

    @Override
    public void insert(T data, int n) {
        if (spaceAvailable()) {
            if (size - n >= 0) System.arraycopy(defaultArray, n, defaultArray, n + 1, size - n);
            ++size;
            defaultArray[n] = data;
        }
    }

    private boolean spaceAvailable() {
        if (size == defaultArray.length - 1) {
            increaseArraySize(defaultArray.length + 1);
            return true;
        } else return true;
    }

    private void increaseArraySize(int minimumCapacity) {
        if (minimumCapacity <= MAX_CAPACITY) {
            int newCapacity = defaultArray.length + (defaultArray.length >> 1);
            if (newCapacity - defaultArray.length < 0) {
                Object[] tempArray = new Object[minimumCapacity];
                System.arraycopy(defaultArray, 0, tempArray, 0, size);
                defaultArray = tempArray;
            } else {
                Object[] tempArray = new Object[newCapacity];
                System.arraycopy(defaultArray, 0, tempArray, 0, size);
                defaultArray = tempArray;
            }
        } else throw new OutOfMemoryError("You can only have a max of " + MAX_CAPACITY + " elements.");
    }


    @Override
    public int search(T data) {
        if (sorted) {
            return binarySearch(data, 0, size - 1);
        } else {
            for (int i = 0; i < size; i++) {
                if (data.equals(defaultArray[i]))
                    return i;
            }
        }
        return -1;
    }

    public int noOfOccurrences(T data) {
        int firstOccurence = search(data);
        int count = 1;
        if (firstOccurence != 1)
            if (sorted) {
                while (data.equals(defaultArray[count + firstOccurence])) {
                    count++;
                }
                return count;
            } else {
                for (int i = firstOccurence + 1; i < size; i++) {
                    if (defaultArray[i].equals(data)) {
                        count++;
                    }
                }
                return count;
            }
        return -1;
    }

    private int binarySearch(T data, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            int comparison = comparator.compare((T) defaultArray[mid], data);
            if (comparison > 0) {
                left = mid + 1;
            } else if (comparison < 0) {
                right = mid - 1;
            } else return mid;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ");
        int i = 0;
        while (defaultArray[i] != null) {
            builder.append(defaultArray[i]).append(" ");
            i++;
        }
        builder.append(" ]");
        return builder.toString();
    }
}
