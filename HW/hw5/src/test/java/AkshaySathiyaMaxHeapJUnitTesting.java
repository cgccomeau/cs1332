import org.junit.Before;
import org.junit.Test;
import org.junit.AfterClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Sample unit tests for max heaps (CS 1332 HW06).
 *
 * @author Akshay Sathiya
 * @version 1.0
 */
public class AkshaySathiyaMaxHeapJUnitTesting {

    private static final int TIMEOUT = 200;
    private static int numTestsPassed = 0;
    private static final int NUMTESTS = 12;
    private MaxHeap<Integer> maxHeap;

    @Before
    public void setUp() {
        // create an empty maxheap
        maxHeap = new MaxHeap<>();
    }

    @Test (timeout = TIMEOUT)
    public void testNoParamConstructor() {
        // check to see if the size of the maxheap is 0
        assertEquals(0, maxHeap.size());

        // check to see if the max heap is empty
        assertTrue(maxHeap.isEmpty());

        // check to see if its backing array was initialized properly
        assertArrayEquals(new Integer[13], maxHeap.getBackingArray());

        // report that test was passed
        System.out.println("No-param constructor test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testOneParamConstructorBuildHeap() {
        // create and add Integers to an ArrayList of Integers
        ArrayList<Integer> heapData = new ArrayList<>();
        heapData.add(12);
        heapData.add(13);
        heapData.add(14);
        heapData.add(15);
        heapData.add(16);
        heapData.add(17);

        // create a new maxheap using the one-param constructor
        maxHeap = new MaxHeap<>(heapData);

        // check to see if the size is 6
        assertEquals(6, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        /*
        Expected heap

                    17
                  /    \
                 16    14
                /  \   /
               15  13 12
         */
        // make an array representing the maxheap's expected backing array
        Integer[] expectedHeap = new Integer[13];
        expectedHeap[1] = 17;
        expectedHeap[2] = 16;
        expectedHeap[3] = 14;
        expectedHeap[4] = 15;
        expectedHeap[5] = 13;
        expectedHeap[6] = 12;

        /*
        check to see if the maxheap's backing array is the same as the maxheap's
            expected backing array
         */
        assertArrayEquals(expectedHeap, maxHeap.getBackingArray());

        // report that test was passed, increment numTestsPassed
        System.out.println("One-param constructor build heap test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testOneParamConstructorBuildHeapExceptionBehavior() {
        /*
        create a new constructor with null data, should throw an
            IllegalArgumentException

        if an IllegalArgumentException is thrown, report that test has been
            passed and increment numTestsPassed
         */
        try {
            maxHeap = new MaxHeap<>(null);
        } catch (IllegalArgumentException e) {
            System.out.println("One-param constructor build heap exception "
                    + "behavior test passed!");
            numTestsPassed++;
        }

    }

    @Test (timeout = TIMEOUT)
    public void testAdd() {
        // add 6 Integers to the maxheap
        maxHeap.add(12);
        maxHeap.add(13);
        maxHeap.add(14);
        maxHeap.add(15);
        maxHeap.add(16);
        maxHeap.add(17);

        // check to see if the size is 6
        assertEquals(6, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        /*
        Expected heap

                    17
                  /    \
                 15    16
                /  \   /
               12  14 13
         */
        // make an array representing the maxheap's expected backing array
        Integer[] expectedHeap = new Integer[13];
        expectedHeap[1] = 17;
        expectedHeap[2] = 15;
        expectedHeap[3] = 16;
        expectedHeap[4] = 12;
        expectedHeap[5] = 14;
        expectedHeap[6] = 13;

        /*
        check to see if the maxheap's backing array is the same as the maxheap's
            expected backing array
         */
        assertArrayEquals(expectedHeap, maxHeap.getBackingArray());

        // report that the test was passed, increment numTestsPassed
        System.out.println("Add test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testAddExceptionBehavior() {
        /*
        attempt to add null data to the max heap, should throw an
            IllegalArgumentException

        if an IllegalArgumentExcetpion is thrown, report that the test has been
            passed and increment numTestsPassed
         */
        try {
            maxHeap.add(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Add exception behavior test passed!");
            numTestsPassed++;
        }
    }

    @Test (timeout = TIMEOUT)
    public void testRemove() {
        // add 6 Integers to the maxheap
        maxHeap.add(12);
        maxHeap.add(13);
        maxHeap.add(14);
        maxHeap.add(15);
        maxHeap.add(16);
        maxHeap.add(17);

        /*
        Expected heap

                    17
                  /    \
                 15    16
                /  \   /
               12  14 13
         */

        // check to see if the capacity of the backing array is 13
        assertEquals(13, maxHeap.getBackingArray().length);

        // check to see if the size is 6
        assertEquals(6, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        // remove from the heap, check if removed data is 17
        assertEquals(new Integer(17), maxHeap.remove());

        // check to see if the size is 5
        assertEquals(5, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        // remove from the heap, check if removed data is 16
        assertEquals(new Integer(16), maxHeap.remove());

        // check to see if the size is 4
        assertEquals(4, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        // remove from the heap, check if removed data is 15
        assertEquals(new Integer(15), maxHeap.remove());

        // check to see if the size is 3
        assertEquals(3, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        // remove from the heap, check if removed data is 14
        assertEquals(new Integer(14), maxHeap.remove());

        // check to see if the size is 2
        assertEquals(2, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        // remove from the heap, check if removed data is 13
        assertEquals(new Integer(13), maxHeap.remove());

        // check to see if the size is 1
        assertEquals(1, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        // remove from the heap, check if removed data is 12
        assertEquals(new Integer(12), maxHeap.remove());

        // check to see if the size is 0
        assertEquals(0, maxHeap.size());

        // check to see that the maxheap is empty
        assertTrue(maxHeap.isEmpty());

        // check to see if the capacity of the backing array is still 13
        assertEquals(13, maxHeap.getBackingArray().length);

        // create and add Integers to an ArrayList of Integers
        ArrayList<Integer> heapData = new ArrayList<>();
        heapData.add(12);
        heapData.add(13);
        heapData.add(14);
        heapData.add(15);
        heapData.add(16);
        heapData.add(17);

        // create a new maxheap using the one-param constructor
        maxHeap = new MaxHeap<>(heapData);

        /*
        Expected heap

                    17
                  /    \
                 16    14
                /  \   /
               15  13 12
         */

        // check to see if the capacity of the backing array is 13
        assertEquals(13, maxHeap.getBackingArray().length);

        // check to see if the size is 6
        assertEquals(6, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        // check to see if the size is 6
        assertEquals(6, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        // remove from the heap, check if removed data is 17
        assertEquals(new Integer(17), maxHeap.remove());

        // check to see if the size is 5
        assertEquals(5, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        // remove from the heap, check if removed data is 16
        assertEquals(new Integer(16), maxHeap.remove());

        // check to see if the size is 4
        assertEquals(4, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        // remove from the heap, check if removed data is 15
        assertEquals(new Integer(15), maxHeap.remove());

        // check to see if the size is 3
        assertEquals(3, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        // remove from the heap, check if removed data is 14
        assertEquals(new Integer(14), maxHeap.remove());

        // check to see if the size is 2
        assertEquals(2, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        // remove from the heap, check if removed data is 13
        assertEquals(new Integer(13), maxHeap.remove());

        // check to see if the size is 1
        assertEquals(1, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        // remove from the heap, check if removed data is 12
        assertEquals(new Integer(12), maxHeap.remove());

        // check to see if the size is 0
        assertEquals(0, maxHeap.size());

        // check to see that the maxheap is empty
        assertTrue(maxHeap.isEmpty());

        // check to see if the capacity of the backing array is still 13
        assertEquals(13, maxHeap.getBackingArray().length);

        // report that test has been passed, increment numTestsPassed
        System.out.println("Remove test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testRemoveExceptionBehavior() {
        /*
        attempt to remove from an empty maxheap, should throw a
            NoSuchElementException

        if a NoSuchElementException is thrown, then report that the test has
            been passed, increment numTestsPassed
         */
        try {
            maxHeap.remove();
        } catch (NoSuchElementException e) {
            System.out.println("Remove exception behavior test passed!");
            numTestsPassed++;
        }
    }

    @Test (timeout = TIMEOUT)
    public void testGetMax() {
        // add 6 Integers to the maxheap
        maxHeap.add(12);
        maxHeap.add(13);
        maxHeap.add(14);
        maxHeap.add(15);
        maxHeap.add(16);
        maxHeap.add(17);

        /*
        Expected heap

                    17
                  /    \
                 15    16
                /  \   /
               12  14 13
         */

        // check to see if the max value is 17
        assertEquals(new Integer(17), maxHeap.getMax());

        // remove from the heap, check if removed data is 17
        assertEquals(new Integer(17), maxHeap.remove());

        // check to see if the max value is 16
        assertEquals(new Integer(16), maxHeap.getMax());

        // remove from the heap, check if removed data is 16
        assertEquals(new Integer(16), maxHeap.remove());

        // check to see if the max value is 15
        assertEquals(new Integer(15), maxHeap.getMax());

        // remove from the heap, check if removed data is 15
        assertEquals(new Integer(15), maxHeap.remove());

        // check to see if the max value is 14
        assertEquals(new Integer(14), maxHeap.getMax());

        // remove from the heap, check if removed data is 14
        assertEquals(new Integer(14), maxHeap.remove());

        // check to see if the max value is 13
        assertEquals(new Integer(13), maxHeap.getMax());

        // remove from the heap, check if removed data is 13
        assertEquals(new Integer(13), maxHeap.remove());

        // check to see if the max value is 12
        assertEquals(new Integer(12), maxHeap.getMax());

        // remove from the heap, check if removed data is 12
        assertEquals(new Integer(12), maxHeap.remove());

        // check to see if the max value is null (heap is empty)
        assertNull(maxHeap.getMax());

        // create and add Integers to an ArrayList of Integers
        ArrayList<Integer> heapData = new ArrayList<>();
        heapData.add(12);
        heapData.add(13);
        heapData.add(14);
        heapData.add(15);
        heapData.add(16);
        heapData.add(17);

        // create a new maxheap using the one-param constructor
        maxHeap = new MaxHeap<>(heapData);

        /*
        Expected heap

                    17
                  /    \
                 16    14
                /  \   /
               15  13 12
         */

        // check to see if the max value is 17
        assertEquals(new Integer(17), maxHeap.getMax());

        // remove from the heap, check if removed data is 17
        assertEquals(new Integer(17), maxHeap.remove());

        // check to see if the max value is 16
        assertEquals(new Integer(16), maxHeap.getMax());

        // remove from the heap, check if removed data is 16
        assertEquals(new Integer(16), maxHeap.remove());

        // check to see if the max value is 15
        assertEquals(new Integer(15), maxHeap.getMax());

        // remove from the heap, check if removed data is 15
        assertEquals(new Integer(15), maxHeap.remove());

        // check to see if the max value is 14
        assertEquals(new Integer(14), maxHeap.getMax());

        // remove from the heap, check if removed data is 14
        assertEquals(new Integer(14), maxHeap.remove());

        // check to see if the max value is 13
        assertEquals(new Integer(13), maxHeap.getMax());

        // remove from the heap, check if removed data is 13
        assertEquals(new Integer(13), maxHeap.remove());

        // check to see if the max value is 12
        assertEquals(new Integer(12), maxHeap.getMax());

        // remove from the heap, check if removed data is 12
        assertEquals(new Integer(12), maxHeap.remove());

        // check to see if the max value is null (heap is empty)
        assertNull(maxHeap.getMax());

        // report that test has been passed, increment numTestsPassed
        System.out.println("Get max test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testIsEmpty() {
        // check to see if the maxheap is empty
        assertTrue(maxHeap.isEmpty());

        // add 6 Integers to the maxheap
        maxHeap.add(12);
        maxHeap.add(13);
        maxHeap.add(14);
        maxHeap.add(15);
        maxHeap.add(16);
        maxHeap.add(17);

        /*
        check to see if the maxheap is not empty, remove all 6 Integers from the
            maxheap, one by one
         */
        for (int i = 0; i < 6; i++) {
            assertFalse(maxHeap.isEmpty());
            maxHeap.remove();
        }

        // check to see if the maxheap is empty
        assertTrue(maxHeap.isEmpty());

        // report that the test has been passed, increment numTestsPassed
        System.out.println("Is empty test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testClear() {
        // add 6 Integers to the maxheap
        maxHeap.add(12);
        maxHeap.add(13);
        maxHeap.add(14);
        maxHeap.add(15);
        maxHeap.add(16);
        maxHeap.add(17);

        // check to see that the maxheap's size is not 0
        assertNotEquals(0, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        // check to see if the capacity of the backing array is 13
        assertEquals(13, maxHeap.getBackingArray().length);

        // clear the maxheap
        maxHeap.clear();

        // check to see that the maxheap's size is 0
        assertEquals(0, maxHeap.size());

        // check to see that the maxheap is empty
        assertTrue(maxHeap.isEmpty());

        // check to see if the capacity of the backing array is 13
        assertEquals(13, maxHeap.getBackingArray().length);

        // report that the test has been passed and increment numTestsPassed
        System.out.println("Clear test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testSize() {
        // check to see that the size of the maxheap is 0
        assertEquals(0, maxHeap.size());

        /*
        add 6 Integers to the maxheap, check to see that size is being
            incremented with each add
         */
        maxHeap.add(12);
        assertEquals(1, maxHeap.size());

        maxHeap.add(13);
        assertEquals(2, maxHeap.size());

        maxHeap.add(14);
        assertEquals(3, maxHeap.size());

        maxHeap.add(15);
        assertEquals(4, maxHeap.size());

        maxHeap.add(16);
        assertEquals(5, maxHeap.size());

        maxHeap.add(17);
        assertEquals(6, maxHeap.size());

        /*
        remove all 6 Integers from the maxheap, check to see that size is being
            decremented with each remove
         */
        for (int i = 0; i < 6; i++) {
            assertEquals(6 - i, maxHeap.size());
            maxHeap.remove();
        }

        // check to see that the size of the maxheap is 0
        assertEquals(0, maxHeap.size());

        /*
        check that the size remains the same after peeking at the max value of
            the maxheap
         */
        maxHeap.getMax();
        assertEquals(0, maxHeap.size());

        // report that test has been passed, increment numTestsPassed
        System.out.println("Size test passed!");
        numTestsPassed++;
    }

    @Test (timeout = TIMEOUT)
    public void testGetBackingArray() {
        // create and add Integers to an ArrayList of Integers
        ArrayList<Integer> heapData = new ArrayList<>();
        heapData.add(12);
        heapData.add(13);
        heapData.add(14);
        heapData.add(15);
        heapData.add(16);
        heapData.add(17);

        // create a new maxheap using the one-param constructor
        maxHeap = new MaxHeap<>(heapData);

        // check to see if the size is 6
        assertEquals(6, maxHeap.size());

        // check to see that the maxheap is not empty
        assertFalse(maxHeap.isEmpty());

        /*
        Expected heap

                    17
                  /    \
                 16    14
                /  \   /
               15  13 12
         */
        // make an array representing the maxheap's expected backing array
        Integer[] expectedHeap = new Integer[13];
        expectedHeap[1] = 17;
        expectedHeap[2] = 16;
        expectedHeap[3] = 14;
        expectedHeap[4] = 15;
        expectedHeap[5] = 13;
        expectedHeap[6] = 12;

        /*
        check to see if the maxheap's backing array is the same as the maxheap's
            expected backing array
         */
        assertArrayEquals(expectedHeap, maxHeap.getBackingArray());

        // remove from the maxheap
        maxHeap.remove();

        /*
        Expected heap

                    16
                  /    \
                 15    14
                /  \
               12  13
         */
        // make an array representing the maxheap's expected backing array
        expectedHeap[1] = 16;
        expectedHeap[2] = 15;
        expectedHeap[3] = 14;
        expectedHeap[4] = 12;
        expectedHeap[5] = 13;
        expectedHeap[6] = null;

        /*
        check to see if the maxheap's backing array is the same as the maxheap's
            expected backing array
         */
        assertArrayEquals(expectedHeap, maxHeap.getBackingArray());

        // remove from the maxheap
        maxHeap.remove();

        /*
        Expected heap

                    15
                  /    \
                 13    14
                /
               12
         */
        // make an array representing the maxheap's expected backing array
        expectedHeap[1] = 15;
        expectedHeap[2] = 13;
        expectedHeap[3] = 14;
        expectedHeap[4] = 12;
        expectedHeap[5] = null;

        /*
        check to see if the maxheap's backing array is the same as the maxheap's
            expected backing array
         */
        assertArrayEquals(expectedHeap, maxHeap.getBackingArray());

        // report that test has been passed, increment numTestsPassed
        System.out.println("Get backing array test passed!");
        numTestsPassed++;
    }

    @AfterClass
    public static void takeDown() {
        // report that all tests were passed or however many tests were passed
        if (numTestsPassed == NUMTESTS) {
            System.out.println("\nAll tests passed! Great job!");
        } else {
            System.out.println("\n" + numTestsPassed + " tests passed out of "
                    + NUMTESTS);
        }
    }

}
