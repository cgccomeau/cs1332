/**
 * Your implementation of an ArrayList.
 *
 * @author Charlie Comeau
 * @userid ccomeau7
 * @GTID 903359699
 * @version 1.0
 * @param <T> generic type of ArrayList class
 */
public class ArrayList<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * The initial capacity of the array list.
     *
     * DO NOT CHANGE THIS VARIABLE.
     */
    public static final int INITIAL_CAPACITY = 9;

    /**
     * Constructs a new ArrayList.
     *
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object array to T[] to get the generic typing.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the element to the index specified.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Adding to index {@code size} should be amortized O(1),
     * all other adds are O(n).
     *
     * @param index the index where you want the new element
     * @param data the data to add to the list
     * @throws java.lang.IndexOutOfBoundsException if index is negative
     * or index > size
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index cannot be "
                    + "greater than ArrayList size!");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative!");
        }
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data "
                    + "into data structure");
        }

        if (size == backingArray.length) {
            T[] tempBackingArray = (T[]) new Object[backingArray.length * 2];
            for (int i = 0, j = 0; i < size + 1;) {
                if (i == index) {
                    tempBackingArray[i] = data;
                    i++;
                } else {
                    tempBackingArray[i] = backingArray[j];
                    j++;
                    i++;
                }
            }
            backingArray = tempBackingArray;
            size++;
        } else if (index == size) {
            backingArray[size++] = data;
        } else {
            T placeholder = backingArray[index];
            for (int i = index; i < size + 1; i++) {
                if (i == index) {
                    backingArray[i] = data;
                } else {
                    T placeholder2 = backingArray[i];
                    backingArray[i] = placeholder;
                    placeholder = placeholder2;
                }
            }
            size++;
        }
    }

    /**
     * Adds the given data to the front of your array list.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @param data the data to add to the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        addAtIndex(0, data);
    }

    /**
     * Adds the given data to the back of your array list.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        addAtIndex(size, data);
    }

    /**
     * Removes and returns the element at {@code index}.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * This method should be O(1) for index {@code size - 1} and O(n) in
     * all other cases.
     *
     * @param index the index of the element
     * @return the object that was formerly at that index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size
     */
    public T removeAtIndex(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index cannot be "
                    + "greater than ArrayList size!");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative!");
        }

        T result = backingArray[index];
        if (index == 0) {
            removeFromFront();
        } else if (index == size - 1) {
            removeFromBack();
        } else {
            for (int i = index; i < size - 1; i++) {
                backingArray[i] = backingArray[i + 1];
            }
            backingArray[size - 1] = null;
            size--;
        }
        return result;
    }

    /**
     * Removes and returns the first element in the list.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @return the data from the front of the list or null if the list is empty
     */
    public T removeFromFront() {
        T result = backingArray[0];
        for (int i = 0; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        if (size != 0) {
            backingArray[size - 1] = null;
            size--;
        }
        return result;
    }

    /**
     * Removes and returns the last element in the list.
     *
     * Must be O(1).
     *
     * @return the data from the back of the list or null if the list is empty
     */
    public T removeFromBack() {
        if (size != 0) {
            T result = backingArray[size - 1];
            backingArray[size - 1] = null;
            size--;
            return result;
        } else {
            return null;
        }
    }

    /**
     * Returns the element at the given index.
     *
     * Must be O(1).
     *
     * @param index the index of the element
     * @return the data stored at that index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size
     */
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index cannot be greater "
                    + "than ArrayList size!");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative!");
        }
        return backingArray[index];
    }

    /**
     * Finds the index at which the given data is located in the ArrayList.
     *
     * If there are multiple instances of the data in the ArrayList, then return
     * the index of the last instance.
     *
     * Be sure to think carefully about whether value or reference equality
     * should be used.
     *
     * Must be O(n), but consider which end of the ArrayList to start from.
     *
     * @param data the data to find the last index of
     * @return the last index of the data or -1 if the data is not in the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public int lastIndexOf(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data "
                    + "into data structure!");
        }

        for (int i = size - 1; i >= 0; i--) {
            if (backingArray[i].equals(data)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Returns a boolean value representing whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list. Resets the backing array to a new array of the initial
     * capacity.
     *
     * Must be O(1).
     */
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the size of the list as an integer.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the backing array for this list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array for this list
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
}
