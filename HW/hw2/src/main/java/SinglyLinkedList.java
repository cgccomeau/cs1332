/**
 * Your implementation of a circular singly linked list.
 *
 * @author Charlie Comeau
 * @userid ccomeau7
 * @GTID 903359699
 * @version 1.0
 * @param <T> generic type of Singly Circular Linked List class
 */
public class SinglyLinkedList<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private int size;

    /**
     * Adds the element to the index specified.
     *
     * Adding to indices 0 and {@code size} should be O(1), all other cases are
     * O(n).
     *
     * @param index the requested index for the new element
     * @param data the data for the new element
     * @throws java.lang.IndexOutOfBoundsException if index is negative or
     * index > size
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index cannot be negative!");
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("index cannot be "
                    + "larger than size!");
        }
        if (data == null) {
            throw new IllegalArgumentException("cannot insert null data "
                    + "into data structure!");
        }

        LinkedListNode<T> curr = new LinkedListNode<>(data);

        if (isEmpty()) {
            head = curr;
            curr.setNext(curr);
            size++;
        } else if (index == 0) {
            LinkedListNode<T> temp = head.getNext();
            head.setNext(curr);
            curr.setNext(temp);
            T tempData = head.getData();
            head.setData(curr.getData());
            curr.setData(tempData);
            size++;
        } else if (index == size) {
            LinkedListNode<T> temp = head.getNext();
            head.setNext(curr);
            curr.setNext(temp);
            T tempData = head.getData();
            head.setData(curr.getData());
            curr.setData(tempData);
            head = curr;
            size++;
        } else {
            LinkedListNode<T> temp = head;
            for (int i = 1; i < index; i++) {
                temp = temp.getNext();
            }
            LinkedListNode<T> temp2 = temp.getNext();
            temp.setNext(curr);
            curr.setNext(temp2);
            size++;
        }

    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1) for all cases.
     *
     * @param data the data for the new element
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("cannot insert null data "
                    + "into data structure!");
        } else {
            addAtIndex(0, data);
        }
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1) for all cases.
     *
     * @param data the data for the new element
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("cannot insert null data "
                    + "into data structure!");
        } else {
            addAtIndex(size, data);
        }
    }

    /**
     * Removes and returns the element from the index specified.
     *
     * Removing from index 0 should be O(1), all other cases are O(n).
     *
     * @param index the requested index to be removed
     * @return the data formerly located at index
     * @throws java.lang.IndexOutOfBoundsException if index is negative or
     * index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index cannot be negative!");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("index cannot be "
                    + "larger than size!");
        }

        LinkedListNode<T> curr = head;

        if (isEmpty()) {
            return null;
        } else if (size == 1) {
            T removeData = head.getData();
            head = null;
            size--;
            return removeData;
        } else if (index == 0) {
            T removeData = head.getData();
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
            size--;
            return removeData;
        } else {
            T removeData = head.getNext().getData();
            for (int i = 1; i < index; i++) {
                curr = curr.getNext();
                removeData = curr.getNext().getData();
            }
            curr.setNext(curr.getNext().getNext());
            size--;
            return removeData;
        }
    }

    /**
     * Removes and returns the element at the front of the list. If the list is
     * empty, return {@code null}.
     *
     * Must be O(1) for all cases.
     *
     * @return the data formerly located at the front, null if empty list
     */
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        }
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the element at the back of the list. If the list is
     * empty, return {@code null}.
     *
     * Must be O(n) for all cases.
     *
     * @return the data formerly located at the back, null if empty list
     */
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        }
        return removeAtIndex(size - 1);
    }

    /**
     * Removes the last copy of the given data from the list.
     *
     * Must be O(n) for all cases.
     *
     * @param data the data to be removed from the list
     * @return the removed data occurrence from the list itself (not the data
     * passed in), null if no occurrence
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("cannot insert null data "
                    + "into data structure!");
        }

        LinkedListNode<T> curr = head;
        LinkedListNode<T> prev = null;
        boolean front = false;

        if (isEmpty()) {
            return null;
        }

        if (curr == head && curr.getData().equals(data)) {
            prev = head;
            front = true;
        }

        while (curr.getNext() != head) {
            if (curr.getNext().getData().equals(data)) {
                prev = curr;
            }
            curr = curr.getNext();
        }

        if (prev == null) {
            return null;
        } else if (prev == head && front) {
            return removeFromFront();
        } else {
            LinkedListNode<T> temp = prev.getNext();
            prev.setNext(prev.getNext().getNext());
            size--;
            return temp.getData();
        }
    }

    /**
     * Returns the element at the specified index.
     *
     * Getting index 0 should be O(1), all other cases are O(n).
     *
     * @param index the index of the requested element
     * @return the object stored at index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size
     */
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index cannot be negative!");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("index cannot be "
                    + "larger than size!");
        }
        LinkedListNode<T> result = head;
        for (int i = 0; i < index; i++) {
            result = result.getNext();
        }
        return result.getData();
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length {@code size} holding all of the objects in
     * this list in the same order
     */
    public Object[] toArray() {
        Object[] result = new Object[size];
        LinkedListNode<T> curr = head;
        for (int i = 0; i < size; i++) {
            result[i] = curr.getData();
            curr = curr.getNext();
        }
        return result;
    }

    /**
     * Returns a boolean value indicating if the list is empty.
     *
     * Must be O(1) for all cases.
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Clears the list of all data.
     *
     * Must be O(1) for all cases.
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    /**
     * Returns the head node of the linked list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return node at the head of the linked list
     */
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}
