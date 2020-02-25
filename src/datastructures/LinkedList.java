package datastructures;

import interfaces.DataComparator;


public  class LinkedList<T> extends AbstractList<T> {


    private transient Node start = null, end = null;

    public LinkedList() {
        size = 0;
    }

    public LinkedList(T data) {
        size = 1;
        end = start = new Node(data);
    }

    @SafeVarargs
    public LinkedList(T... objects) {
        for (T object : objects) {
            insert(object);
        }

    }

    @Override
    public T get(int n) {
        if (size == 0) {
            throw new RuntimeException("List is empty !!");
        }
        if (n == 0) {
            return start.data;
        }
        if (n == size - 1) {
            return end.data;
        }
        if (!(n < size)) throw new IndexOutOfBoundsException("For index : " + n);
        Node temp;
        int count;
        if (n <= size / 2) {
            count = 0;
            temp = start;
            while (count++ < n) {
                temp = temp.next;
            }
            return temp.data;

        } else {
            count = size - 1;
            temp = end;
            while (count != n) {
                temp = temp.previous;
                count--;
            }
            return temp.data;
        }
    }

    public void insert(T data, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("N cannot be negative !! ");
        } else if (n > size) {
            throw new IndexOutOfBoundsException("For index : " + n);
        }
        if (n == size) {
            insert(data);
        } else if (n == 0) {
            Node temp = new Node(data);
            temp.next = start;
            start.previous = temp;
            start = temp;
            size++;
        } else {
            size++;
            Node newNode = new Node(data), temp, temp2;

            if (size - n < n) {
                temp = end;
                int count = size - 1;
                while (--count != n) {
                    temp = temp.previous;
                }
                temp2 = temp.previous;
                newNode.previous = temp2;
                newNode.next = temp;
                temp.previous = newNode;
                temp2.next = newNode;
            } else {
                temp = start;
                int count = 0;
                while (++count != n) {
                    temp = temp.next;
                }
                temp2 = temp.next;
                newNode.next = temp2;
                newNode.previous = temp;
                temp.next = newNode;
                temp2.previous = newNode;
            }
        }
    }

    public void insert(T... values) {
        for (T value : values) {
            insert(value);
        }
    }

    @Override
    public void insert(T data) {
        if (start == null) {
            start = new Node(data);
            end = start;
            size++;
            return;
        }
        end.next = new Node(data);
        end.next.previous = end;
        end = end.next;
        size++;
    }

    @Override
    public T delete(T type) {
        Node temp = start;
        while (!temp.data.equals(type)) {
            temp = temp.next;
        }
        T data = temp.data;
        Node temp2 = temp.next;
        temp2.previous = temp.previous;
        temp.previous.next = temp2;
        return data;
    }

    /**
     * This method will delete the last element of the linked list.
     */
    public T delete() {
        if (size == 0) {
            throw new RuntimeException("List is empty !!");
        }
        Node temp = end;
        end = end.previous;
        size--;
        return temp.data;
    }

    public T delete(int n) {
        if (n > size - 1) throw new IndexOutOfBoundsException();
        if (n == size - 1) {
            return delete();
        } else if (n == 0) {
            Node temp = start;
            start = start.next;
            start.previous = null;
            size--;
            return temp.data;
        } else {
            Node temp, temp2;
            T data;
            if (size - n < n) {
                temp = end;
                int count = --size;
                while (count-- != n) {
                    temp = temp.previous;
                }
                data = temp.data;
                temp2 = temp.previous;
                temp = temp.next;
                temp2.next = temp;
                temp.previous = temp2;
                return data;
            } else {
                temp = start;
                int count = 0;
                while (count++ != n) {
                    temp = temp.next;
                }
                data = temp.data;
                temp2 = temp.previous;
                temp = temp.next;
                temp2.next = temp;
                temp.previous = temp2;
                size--;
                return data;
            }
        }
    }

    @Override
    public int search(T data) {
        if (start.data.equals(data)) {
            return 0;
        } else if (end.data.equals(data)) {
            return size - 1;
        }
        Node temp = start.next;
        int count = 1;
        while (temp.next != null) {
            if (temp.data.equals(data)) {
                return count;
            }
            count++;
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public void sort(DataComparator<T> comparisonHelper) {
    }

    @Override
    public String toString() {
        if (start == null) {
            return "[]";
        }
        Node temp = start;
        StringBuilder buffer = new StringBuilder();
        buffer.append("[ ");
        while (temp != null) {
            buffer.append(temp.data).append(" ");
            temp = temp.next;
        }
        buffer.append("]");
        return buffer.toString();
    }

    public void clear() {
        start = end = null;
        size = 0;
    }

    private class Node {
        Node next = null, previous = null;
        T data;

        Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }
}

