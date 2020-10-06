/**
 * This is a JUnit testerclass for Spring 2019 CS 1332 Homework 2, developed
 * by Akshay Sathiya
 *
 * @author Akshay Sathiya
 * @version 1.0
 */

import org.junit.Before;
import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class AkshaySathiyaJUnitTesting {

    private SinglyLinkedList<Double> myList;
    private static final int TIMEOUT = 200;
    private static final int NUMTESTS = 14;
    private static int numTestsPassed = 0;

    @Before
    public void setUp() {
        myList = new SinglyLinkedList<>();
    }

    @Test (timeout = TIMEOUT)
    public void testConstructor() {
        // Check to see if the head reference is null.
        assertNull(myList.getHead());

        // Check to see if the size of the linked list is 0.
        assertEquals(0, myList.size());

        // Report that test has been passed, increment numTestsPassed.
        System.out.println("Constructor test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testAddToFront() {
        // Check to see if the size of the linked list is 0.
        assertEquals(0, myList.size());

        // Add 5 Doubles to the linked list. Adding is done at the front.
        myList.addToFront(9.99);
        myList.addToFront(7.89);
        myList.addToFront(3.14);
        myList.addToFront(2.71);
        myList.addToFront(1.17);

        /*
        Attempt to add a node with null data to the linked list.
        Check to see if thrown exception is an IllegalArgumentException.
        Check to see if message is not empty String or null.
         */
        try {
            myList.addToFront(null);
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertNotEquals("", e.getMessage());
            assertNotNull(null, e.getMessage());
        }

        // Check to see if the size of the linked list is 5.
        assertEquals(5, myList.size());

        // Check to see if the order of the nodes is correct.
        LinkedListNode<Double> currentNode = myList.getHead();
        assertEquals(new Double(1.17), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(2.71), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(3.14), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(7.89), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(9.99), currentNode.getData());

        // Check to see if the linked list is of circular nature.
        currentNode = currentNode.getNext();
        assertEquals(new Double(1.17), currentNode.getData());

        // Report that the test has been passed, increment numTestsPassed.
        System.out.println("addToFront test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testAddToBack() {
        // Check to see if the size of the linked list is 0.
        assertEquals(0, myList.size());

        // Add 5 Doubles to the linked list. Adding is done at the back.
        myList.addToBack(9.99);
        myList.addToBack(7.89);
        myList.addToBack(3.14);
        myList.addToBack(2.71);
        myList.addToBack(1.17);

        /*
        Attempt to add a node with null data to the linked list.
        Check to see if thrown exception is an IllegalArgumentException.
        Check to see if message is not empty String or null.
         */
        try {
            myList.addToBack(null);
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertNotEquals("", e.getMessage());
            assertNotNull(null, e.getMessage());
        }

        // Check to see if the size of the linked list is 5.
        assertEquals(5, myList.size());

        // Check to see if the order of the nodes is correct.
        LinkedListNode<Double> currentNode = myList.getHead();
        assertEquals(new Double(9.99), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(7.89), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(3.14), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(2.71), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(1.17), currentNode.getData());

        // Check to see if the linked list is of circular nature.
        currentNode = currentNode.getNext();
        assertEquals(new Double(9.99), currentNode.getData());

        // Report that the test has been passed, increment numTestsPassed.
        System.out.println("addToBack test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testAddAtIndex() {
        // Check to see if the size of the linked list is 0.
        assertEquals(0, myList.size());

        // Add 5 Doubles to the linked list. Adding is done at various indices.
        myList.addAtIndex(0, 9.99);
        myList.addAtIndex(1, 7.89);
        myList.addAtIndex(1, 3.14);
        myList.addAtIndex(2, 2.71);
        myList.addAtIndex(3, 1.17);
        myList.addAtIndex(2, 1.17);

        /*
        Attempt to add a node with null data to the linked list.
        Check to see if thrown exception is an IllegalArgumentException.
        Check to see if message is not empty String or null.
         */
        try {
            myList.addAtIndex(2, null);
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertNotEquals("", e.getMessage());
            assertNotNull(null, e.getMessage());
        }

        /*
        Attempt to add a node to the linked list at a negative index.
        Check to see if thrown exception is an IndexOutOfBoundsException.
        Check to see if message is not empty String or null.
         */
        try {
            myList.addAtIndex(-2, null);
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertNotEquals("", e.getMessage());
            assertNotNull(null, e.getMessage());
        }

        /*
        Attempt to add a node to the linked list at a very high index.
        Check to see if thrown exception is an IndexOutOfBoundsException.
        Check to see if message is not empty String or null.
         */
        try {
            myList.addAtIndex(100, null);
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertNotEquals("", e.getMessage());
            assertNotNull(null, e.getMessage());
        }

        // Check to see if the size of the linked list is 6.
        assertEquals(6, myList.size());

        // Check to see if the order of the nodes is correct.
        LinkedListNode<Double> currentNode = myList.getHead();
        assertEquals(new Double(9.99), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(3.14), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(1.17), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(2.71), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(1.17), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(7.89), currentNode.getData());

        // Check to see if the linked list is of circular nature.
        currentNode = currentNode.getNext();
        assertEquals(new Double(9.99), currentNode.getData());

        // Report that the test has been passed, increment numTestsPassed.
        System.out.println("addAtIndex test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testRemoveFromFront() {
        // Check to see if the size of the linked list is 0.
        assertEquals(0, myList.size());

        // Attempt to remove from the empty linked list, should return null.
        assertNull(myList.removeFromFront());

        // Add 5 Doubles to the linked list. Adding is done at the back.
        myList.addToBack(9.99);
        myList.addToBack(7.89);
        myList.addToBack(3.14);
        myList.addToBack(2.71);
        myList.addToBack(1.17);

        // Check to see if the size of the linked list is 5.
        assertEquals(5, myList.size());

        /*
        Remove a node from the front of the linked list and check to see if the
        correct node was removed and the correct data was extracted.
         */
        assertEquals(new Double(9.99), myList.removeFromFront());

        // Check to see if the size of the linked list is 4.
        assertEquals(4, myList.size());

        /*
        Remove another node from the front of the linked list and check to see
        if the correct node was removed and the correct data was extracted.
         */
        assertEquals(new Double(7.89), myList.removeFromFront());

        // Check to see if the size of the linked list is 3.
        assertEquals(3, myList.size());

        // Check to see if the order of the nodes is correct.
        LinkedListNode<Double> currentNode = myList.getHead();
        assertEquals(new Double(3.14), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(2.71), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(1.17), currentNode.getData());

        // Check to see if the linked list is of circular nature.
        currentNode = currentNode.getNext();
        assertEquals(new Double(3.14), currentNode.getData());

        // Report that the test has been passed, increment numTestsPassed.
        System.out.println("removeAtFront test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testRemoveFromBack() {
        // Check to see if the size of the linked list is 0
        assertEquals(0, myList.size());

        // Attempt to remove from an empty list, should return null.
        assertNull(myList.removeFromBack());

        // Add 5 Doubles to the linked list. Adding is done at the front.
        myList.addToFront(9.99);
        myList.addToFront(7.89);
        myList.addToFront(3.14);
        myList.addToFront(2.71);
        myList.addToFront(1.17);

        // Check to see if the size of the linked list is 5
        assertEquals(5, myList.size());

        /*
        Remove a node from the back of the linked list and check to see if the
        correct node was removed and the correct data was extracted.
         */
        assertEquals(new Double(9.99), myList.removeFromBack());

        // Check to see if the size of the linked list is 4.
        assertEquals(4, myList.size());

        /*
        Remove another node from the back of the linked list and check to see if
        the correct node was removed and the correct data was extracted.
         */
        assertEquals(new Double(7.89), myList.removeFromBack());

        // Check to see if the size of the linked list is 3.
        assertEquals(3, myList.size());

        // Check to see if the order of the nodes is correct.
        LinkedListNode<Double> currentNode = myList.getHead();
        assertEquals(new Double(1.17), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(2.71), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(3.14), currentNode.getData());

        // Check to see if the linked list is of circular nature.
        currentNode = currentNode.getNext();
        assertEquals(new Double(1.17), currentNode.getData());

        // Report that the test has been passed, increment numTestsPassed.
        System.out.println("removeFromFront test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        // Check to see if the size of the linked list is 0.
        assertEquals(0, myList.size());

        // Add 5 Doubles to the linked list. Adding is done at various indices.
        myList.addAtIndex(0, 9.99);
        myList.addAtIndex(1, 7.89);
        myList.addAtIndex(1, 3.14);
        myList.addAtIndex(2, 2.71);
        myList.addAtIndex(3, 1.17);
        myList.addAtIndex(2, 1.17);
        // Final sequence is 9.99, 3.14, 1.17, 2.71, 1.17, 7.89

        // Check to see if the size of the linked list is 6.
        assertEquals(6, myList.size());

        /*
        Remove the node at index 2 of the linked list, and check to see if the
        correct node was removed and the correct data was extracted.
         */
        assertEquals(new Double(1.17), myList.removeAtIndex(2));

        // Check to see that the size of the linked list is 5.
        assertEquals(5, myList.size());

        /*
        Remove the node at index 0 of the linked list, and check to see if the
        correct node was removed and the correct data was extracted.
        This is essentially removing from the front via index.
         */
        assertEquals(new Double(9.99), myList.removeAtIndex(0));

        // Check to see that the size of the linked list is 4.
        assertEquals(4, myList.size());

        /*
        Remove the node at index 3 of the linked list, and check to see if the
        correct node was removed and the correct data was extracted.
        This is essentially removing from the back via index.
         */
        assertEquals(new Double(7.89), myList.removeAtIndex(3));

        // Check to see that the size of the linked list is 3.
        assertEquals(3, myList.size());

        /*
        Attempt to remove a node at a negative index.
        Check to see if thrown exception is an IndexOutOfBoundsException.
        Check to see if message is not empty String or null.
         */
        try {
            myList.removeAtIndex(-10);
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertNotEquals("", e.getMessage());
            assertNotNull(null, e.getMessage());
        }

        /*
        Attempt to remove a node at a very high index.
        Check to see if thrown exception is an IndexOutOfBoundsException.
        Check to see if message is not empty String or null.
         */
        try {
            myList.removeAtIndex(100);
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertNotEquals("", e.getMessage());
            assertNotNull(null, e.getMessage());
        }

        // Check to see if the size of the linked list is still 3.
        assertEquals(3, myList.size());

        // Check to see if the order of the nodes is correct.
        LinkedListNode<Double> currentNode = myList.getHead();
        assertEquals(new Double(3.14), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(2.71), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(1.17), currentNode.getData());

        // Check to see if the linked list is of circular nature.
        currentNode = currentNode.getNext();
        assertEquals(new Double(3.14), currentNode.getData());

        // Report that the test has been passed, increment numTestsPassed.
        System.out.println("removeAtIndex test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testRemoveLastOccurrence() {
        // Check to see if the size of the linked list is 0.
        assertEquals(0, myList.size());

        // Add 6 Doubles to the linked list. Adding is done at the back.
        myList.addToBack(4.50);
        myList.addToBack(9.99);
        myList.addToBack(7.89);
        myList.addToBack(9.99);
        myList.addToBack(2.71);
        myList.addToBack(9.99);

        // Remove the last occurrence of 9.99.
        myList.removeLastOccurrence(9.99);

        // Check to see if the size of the linked list is 5.
        assertEquals(5, myList.size());

        /*
        Check to see if the expected data arrangement matches the actual
        data arrangement of the linked list.
         */
        LinkedListNode<Double> currentNode = myList.getHead();
        assertEquals(new Double(4.50), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(9.99), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(7.89), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(9.99), currentNode.getData());

        currentNode = currentNode.getNext();
        assertEquals(new Double(2.71), currentNode.getData());

        /*
        Check to see that the next node is actually the first node. This means
        that the last occurrence of the node has actually been removed and
        the linked list retained its circular nature.
         */
        currentNode = currentNode.getNext();
        assertNotEquals(9.99, currentNode.getData());
        assertEquals(new Double(4.50), currentNode.getData());
        assertSame(myList.getHead(), currentNode);
        assertEquals(myList.getHead().getData(), currentNode.getData());

        // Report that the test has been passed, increment numTestsPassed.
        System.out.println("removeLastOccurrence test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testGet() {
        // Check to see that the size of the linked list is 0.
        assertEquals(0, myList.size());

        // Add 5 Doubles to the linked list. Adding is done at the back.
        myList.addToBack(9.99);
        myList.addToBack(7.89);
        myList.addToBack(3.14);
        myList.addToBack(2.71);
        myList.addToBack(1.17);

        // Check to see that the size of the linked list is 5.
        assertEquals(5, myList.size());

        // Get the data at index 0, and check if the retrieved data is correct.
        assertEquals(new Double(9.99), myList.get(0));

        // Get the data at index 1, and check if the retrieved data is correct.
        assertEquals(new Double(7.89), myList.get(1));

        // Get the data at index 2, and check if the retrieved data is correct.
        assertEquals(new Double(3.14), myList.get(2));

        // Get the data at index 3, and check if the retrieved data is correct.
        assertEquals(new Double(2.71), myList.get(3));

        // Get the data at index 4, and check if the retrieved data is correct.
        assertEquals(new Double(1.17), myList.get(4));

        /*
        Attempt to get the data of a node at a negative index.
        Check to see if exception thrown is an IndexOutOfBoundsException.
        Check to see if message is not empty String or null.
         */
        try {
            Double someData = myList.get(-2);
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertNotEquals("", e.getMessage());
            assertNotNull(e.getMessage());
        }

        /*
        Attempt to get the data of a node at a negative index.
        Check to see if exception thrown is an IndexOutOfBoundsException.
        Check to see if message is not empty String or null.
         */
        try {
            Double someData = myList.get(100);
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertNotEquals("", e.getMessage());
            assertNotNull(e.getMessage());
        }

        // Report that the test has been passed, increment numTestsPassed
        System.out.println("get test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testToArray() {
        // Check to see if the size of the linked list is 0.
        assertEquals(0, myList.size());

        /*
        Add 5 Doubles to the linked list, and create and array reflecting
        the expected arrangement of the data. Adding is done at the back.
         */
        Object[] expectedDataArrangement = new Object[5];
        myList.addToBack(9.99);
        expectedDataArrangement[0] = 9.99;
        myList.addToBack(7.89);
        expectedDataArrangement[1] = 7.89;
        myList.addToBack(3.14);
        expectedDataArrangement[2] = 3.14;
        myList.addToBack(2.71);
        expectedDataArrangement[3] = 2.71;
        myList.addToBack(1.17);
        expectedDataArrangement[4] = 1.17;

        // Check to see if the size of the linked list is 5.
        assertEquals(5, myList.size());

        /*
        Check to see if the expected data arrangement array is equal to
        the actual array representation of the linked list.
         */
        assertArrayEquals(expectedDataArrangement, myList.toArray());

        // Report that the test has been passed, increment numTestsPassed.
        System.out.println("toArray test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testIsEmpty() {
        // Check to see if size of the linked list is 0.
        assertEquals(0, myList.size());

        // Check to see if the linked list is empty.
        assertTrue(myList.isEmpty());

        // Add 5 Doubles to the linked list, adding is done at the front.
        myList.addToFront(9.99);
        myList.addToFront(7.89);
        myList.addToFront(3.14);
        myList.addToFront(2.71);
        myList.addToFront(1.17);

        // Check to see if the size of the linked list is 5.
        assertEquals(5, myList.size());

        // Check to see if the linked list is not empty.
        assertFalse(myList.isEmpty());

        /*
        Remove all 5 nodes from the linked list. Make sure that the linked list
        is not empty as long as there are still nodes to remove.
         */
        for (int i = 0; i < 5; i++) {
            assertFalse(myList.isEmpty());
            myList.removeFromFront();
        }

        // Check to see if the size of the list is 0.
        assertEquals(0, myList.size());

        // Check to see if the linked list is empty.
        assertTrue(myList.isEmpty());

        // Report that the test has been passed, increment numTestsPassed.
        System.out.println("isEmpty test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testClear() {
        // Check to see if the size of the linked list is 0.
        assertEquals(0, myList.size());

        // Check to see if the linked list is empty.
        assertTrue(myList.isEmpty());

        // Add 5 Doubles to the linked list, adding is done at the front.
        myList.addToFront(9.99);
        myList.addToFront(7.89);
        myList.addToFront(3.14);
        myList.addToFront(2.71);
        myList.addToFront(1.17);

        // Check to see if the size of the linked list is 5.
        assertEquals(5, myList.size());

        // Check to see if the linked list is not empty.
        assertFalse(myList.isEmpty());

        // Clear the list
        myList.clear();

        // Check to see if the size of the linked list is 0.
        assertEquals(0, myList.size());

        // Check to see if the linked list is empty.
        assertTrue(myList.isEmpty());

        // Report that the test has been passed, increment numTestsPassed.
        System.out.println("clear test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testSize() {
        // Check to see if the size of the linked list is 0.
        assertEquals(0, myList.size());

        // Check to see that the linked list is empty.
        assertTrue(myList.isEmpty());

        /*
        Add 5 Doubles to the linked list, adding is done at the front, back, and
        various indices. Check to see if the size increases by one every single
        time a node is added.
         */
        myList.addToFront(9.99);
        assertEquals(1, myList.size());
        myList.addToBack(1.17);
        assertEquals(2, myList.size());
        myList.addAtIndex(1, 7.89);
        assertEquals(3, myList.size());
        myList.addAtIndex(2, 3.14);
        assertEquals(4, myList.size());
        myList.addAtIndex(3, 2.71);
        assertEquals(5, myList.size());

        // Check to see if the size of the linked list is 5.
        assertEquals(5, myList.size());

        /*
        Remove the node at the front of the linked list, and check if the size
        is 4.
         */
        myList.removeFromFront();
        assertEquals(4, myList.size());

        /*
        Remove the node at the back of the linked list, and check if the size is
        3.
         */
        myList.removeFromBack();
        assertEquals(3, myList.size());

        /*
        Remove a node from index 1 of the linked list, and check if the size is
        2.
         */
        myList.removeAtIndex(2);
        assertEquals(2, myList.size());

        // Clear the linked list, and check if the size is 0.
        myList.clear();
        assertEquals(0, myList.size());

        // Check to see that the linked list is empty.
        assertTrue(myList.isEmpty());

        // Report that the test has been passed, increment numTestsPassed.
        System.out.println("size test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testGetHead() {
        // Check to see if the head reference points to null.
        assertNull(myList.getHead());

        /*
        Add 8 Doubles to the linked list, check to see if the head reference
        updates correctly or remains the same as nodes are added. Adding is done
        at various indices.
         */
        myList.addToFront(9.99);
        assertEquals(new Double(9.99), myList.getHead().getData());
        myList.addToBack(1.17);
        assertEquals(new Double(9.99), myList.getHead().getData());
        myList.addAtIndex(1, 7.89);
        assertEquals(new Double(9.99), myList.getHead().getData());
        myList.addAtIndex(2, 3.14);
        assertEquals(new Double(9.99), myList.getHead().getData());
        myList.addAtIndex(3, 2.71);
        assertEquals(new Double(9.99), myList.getHead().getData());
        myList.addToFront(7.89);
        assertEquals(new Double(7.89), myList.getHead().getData());
        myList.addToFront(3.33);
        assertEquals(new Double(3.33), myList.getHead().getData());
        myList.addToFront(4.82);
        assertEquals(new Double(4.82), myList.getHead().getData());
        // Sequence: 4.82, 3.33, 7.89, 9.99, 7.89, 3.14, 2.71, 1.17

        /*
        Remove a node from the front of the linked list and check to see if the
        head reference updates correctly.
         */
        myList.removeFromFront();
        assertEquals(new Double(3.33), myList.getHead().getData());
        // Sequence: 3.33, 7.89, 9.99, 7.89, 3.14, 2.71, 1.17

        /*
        Remove a node from the back of the linked list and check to see if the
        head reference stays the same.
         */
        myList.removeFromBack();
        assertEquals(new Double(3.33), myList.getHead().getData());
        // Sequence: 3.33, 7.89, 9.99, 7.89, 3.14, 2.71

        /*
        Remove a node from index 2, and check to see if the head reference stays
        the same.
         */
        myList.removeAtIndex(2);
        assertEquals(new Double(3.33), myList.getHead().getData());
        // Sequence: 3.33, 7.89, 7.89, 3.14, 2.71

        /*
        Remove a node from index 4, and check to see if the head reference stays
        the same.
         */
        myList.removeAtIndex(4);
        assertEquals(new Double(3.33), myList.getHead().getData());
        // Sequence: 3.33, 7.89, 7.89, 3.14, 2.71

        /*
        Remove a node from the front of the linked list and check to see if the
        head reference updates correctly.
         */
        myList.removeFromFront();
        assertEquals(new Double(7.89), myList.getHead().getData());
        // Sequence: 7.89, 7.89, 3.14, 2.71

        // Clear the linked list and check to see if the head reference is null
        myList.clear();
        assertNull(myList.getHead());

        // Report that the test has been passed, increment numTestsPassed.
        System.out.println("getHead test passed!");
        numTestsPassed++;
    }

    @AfterClass
    public static void takeDown() {
        System.out.println();
        if (numTestsPassed == NUMTESTS) {
            System.out.println("All tests passed!");
        } else {
            System.out.println(numTestsPassed + " out of " + NUMTESTS
                    + " passed.");
        }
    }
}
