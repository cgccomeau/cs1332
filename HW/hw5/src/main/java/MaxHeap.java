import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a max heap.
 *
 * @author Charlie Comeau
 * @userid ccomeau7
 * @GTID 903359699
 * @version 1.0
 * @param <T> generic type for MaxHeaps
 */
public class MaxHeap<T extends Comparable<? super T>> {

    // DO NOT ADD OR MODIFY THESE INSTANCE/CLASS VARIABLES.
    public static final int INITIAL_CAPACITY = 13;

    private T[] backingArray;
    private int size;

    /**
     * Creates a Heap with an initial capacity of INITIAL_CAPACITY
     * for the backing array.
     *
     * Use the constant field provided. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * The data in the backingArray should be in the same order as it appears
     * in the passed in ArrayList before you start the Build Heap Algorithm.
     *
     * The backingArray should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null!");
        }
        backingArray = (T[]) new Comparable[2 * data.size() + 1];
        for (int i = 1; i <= data.size(); i++) {
            if (data.get(i - 1) == null) {
                throw new IllegalArgumentException("data cannot be null!");
            }
            backingArray[i] = data.get(i - 1);
            size++;
        }
        downHeap(size / 2);
    }

    /**
     * private recursive helper method to properly downHeap
     * @param index the index where the method is currently at
     */
    private void downHeap(int index) {
        if (index == 0 || index > size / 2) {
            return;
        }
        boolean heaped = false;
        T leftChild = backingArray[index * 2];
        T rightChild = null;
        T maxChild = leftChild;
        if (index * 2 + 1 <= size) {
            rightChild = backingArray[index * 2 + 1];
        }
        if (rightChild != null && rightChild.compareTo(leftChild) > 0) {
            maxChild = rightChild;
        }
        if (backingArray[index].compareTo(maxChild) < 0) {
            heaped = true;
            T temp = backingArray[index];
            backingArray[index] = maxChild;
            if (maxChild == leftChild) {
                backingArray[index * 2] = temp;
            } else {
                backingArray[index * 2 + 1] = temp;
            }
        }
        if (heaped && maxChild == leftChild) {
            downHeap(index * 2);
        } else if (heaped && maxChild == rightChild) {
            downHeap(index * 2 + 1);
        }
        downHeap(--index);
    }

    /**
     * Adds an item to the heap. If the backing array is full and you're trying
     * to add a new item, then double its capacity.
     *
     * @throws IllegalArgumentException if the item is null
     * @param item the item to be added to the heap
     */
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("cannot insert null data into "
                    + "data structure!");
        }
        if (size == backingArray.length - 1) {
            T[] tempBackingArray =
                    (T[]) new Comparable[2 * backingArray.length];
            for (int i = 1; i < backingArray.length; i++) {
                tempBackingArray[i] = backingArray[i];
            }
            backingArray = tempBackingArray;
            add(item);
        } else {
            backingArray[++size] = item;
            upHeap(size);
        }
    }

    /**
     * private recursive helper method to properly upHeap
     * @param index the index where the method is currently at
     */
    private void upHeap(int index) {
        if (index < 2) {
            return;
        }
        T parent = backingArray[index / 2];
        if (backingArray[index].compareTo(parent) > 0) {
            T temp = parent;
            backingArray[index / 2] = backingArray[index];
            backingArray[index] = temp;
        }
        upHeap(index / 2);
    }

    /**
     * Removes and returns the max item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * @throws java.util.NoSuchElementException if the heap is empty
     * @return the removed item
     */
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is already empty!");
        }
        T result = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size--] = null;
        downHeap(1);
        System.out.println();
        return result;
    }

    /**
     * Returns the maximum element in the heap.
     *
     * @return the maximum element, null if the heap is empty
     */
    public T getMax() {
        return backingArray[1];
    }

    /**
     * Returns if the heap is empty or not.
     *
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap and rests the backing array to a new array of capacity
     * {@code INITIAL_CAPACITY}.
     */
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return number of items in the heap
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the heap
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

}
