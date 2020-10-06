import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyJUnitTest {
    private ArrayList<String> list;

    public static final int TIMEOUT = 200;
    public static final int INITIAL_CAPACITY = 9;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialArray() {
        // make sure size is 0 at the start
        assertEquals("size initialized incorrectly / with incorrect value",
                0, list.size());

        // make sure capacity is correct
        assertArrayEquals("initial array is incorrect (either data type or capacity)",
                new Object[INITIAL_CAPACITY], list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
        list.addAtIndex(0, "A");
        list.addAtIndex(0, "B");
        list.addAtIndex(1, "C");
        list.addAtIndex(1, "D");
        list.addAtIndex(2, "E");
        list.addAtIndex(2, "F");
        list.addAtIndex(3, "G");
        list.addAtIndex(3, "H");
        list.addAtIndex(4, "I");
        list.addAtIndex(9, "J");

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 2];
        expected[0] = "B";
        expected[1] = "D";
        expected[2] = "F";
        expected[3] = "H";
        expected[4] = "I";
        expected[5] = "G";
        expected[6] = "E";
        expected[7] = "C";
        expected[8] = "A";
        expected[9] = "J";

        // make sure array is properly resized
        assertEquals("capacity not resized to correct value",
                18, list.getBackingArray().length);

        // make sure size is correct
        assertEquals("size does not have correct value",
                10, list.size());

        // compare lists
        assertArrayEquals("elements not added to correct locations",
                expected, list.getBackingArray());

        System.out.println("all addAtIndex() tests passed!");
    }

    @Test(timeout = TIMEOUT)
    public void testAddFromFront() {
        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        list.addToFront("4a");
        list.addToFront("5a");
        list.addToFront("6a");
        list.addToFront("7a");
        list.addToFront("8a");
        list.addToFront("9a");

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 2];
        expected[0] = "9a";
        expected[1] = "8a";
        expected[2] = "7a";
        expected[3] = "6a";
        expected[4] = "5a";
        expected[5] = "4a";
        expected[6] = "3a";
        expected[7] = "2a";
        expected[8] = "1a";
        expected[9] = "0a";
        assertArrayEquals(expected, list.getBackingArray());

        // make sure array is properly resized
        assertEquals("capacity not resized to correct value",
                18, list.getBackingArray().length);

        // make sure size is correct
        assertEquals("size does not have correct value",
                10, list.size());

        // compare lists
        assertArrayEquals("elements not added to correct locations",
                expected, list.getBackingArray());

        System.out.println("all addToFront() tests passed!");
    }

    @Test(timeout = TIMEOUT)
    public void testAddFromBack() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");
        list.addToBack("D");
        list.addToBack("E");
        list.addToBack("F");
        list.addToBack("G");
        list.addToBack("H");
        list.addToBack("I");
        list.addToBack("J");

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 2];
        expected[0] = "A";
        expected[1] = "B";
        expected[2] = "C";
        expected[3] = "D";
        expected[4] = "E";
        expected[5] = "F";
        expected[6] = "G";
        expected[7] = "H";
        expected[8] = "I";
        expected[9] = "J";
        assertArrayEquals(expected, list.getBackingArray());

        // make sure array is properly resized
        assertEquals("capacity not resized to correct value",
                18, list.getBackingArray().length);

        // make sure size is correct
        assertEquals("size does not have correct value",
                10, list.size());

        // compare lists
        assertArrayEquals("elements not added to correct locations",
                expected, list.getBackingArray());

        System.out.println("all addToBack() tests passed!");
    }

    @Test(timeout =  TIMEOUT)
    public void testRemoveAtIndex() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");
        list.addToBack("D");
        list.addToBack("E");
        list.addToBack("F");
        list.addToBack("G");
        list.addToBack("H");
        list.addToBack("I");
        list.addToBack("J");

        Object[] removed = new Object[3];
        removed[0] = list.removeAtIndex(0);
        removed[1] = list.removeAtIndex(4);
        removed[2] = list.removeAtIndex(7);

        Object[] expectedRemoved = {"A", "F", "J"};

        Object[] expected = new Object[INITIAL_CAPACITY * 2];
        expected[0] = "B";
        expected[1] = "C";
        expected[2] = "D";
        expected[3] = "E";
        expected[4] = "G";
        expected[5] = "H";
        expected[6] = "I";

        // make sure correct elements were removed
        assertArrayEquals("Incorrect elements removed", expectedRemoved, removed);

        // make sure size is correct
        assertEquals("Incorrect size value", INITIAL_CAPACITY - 2, list.size());

        // make sure final arrays are equal
        assertArrayEquals("elements are not in correct positions", expected, list.getBackingArray());

        System.out.println("all removeAtIndex() tests passed!");
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");
        list.addToBack("D");
        list.addToBack("E");
        list.addToBack("F");
        list.addToBack("G");
        list.addToBack("H");
        list.addToBack("I");
        list.addToBack("J");

        Object[] removed = new Object[3];
        removed[0] = list.removeFromFront();
        removed[1] = list.removeFromFront();
        removed[2] = list.removeFromFront();

        Object[] expectedRemoved = {"A", "B", "C"};

        Object[] expected = new Object[INITIAL_CAPACITY * 2];
        expected[0] = "D";
        expected[1] = "E";
        expected[2] = "F";
        expected[3] = "G";
        expected[4] = "H";
        expected[5] = "I";
        expected[6] = "J";

        // make sure correct elements were removed
        assertArrayEquals("Incorrect elements removed", expectedRemoved, removed);

        // make sure size is correct
        assertEquals("Incorrect size value", INITIAL_CAPACITY - 2, list.size());

        // make sure final arrays are equal
        assertArrayEquals("elements are not in correct positions", expected, list.getBackingArray());

        System.out.println("all removeFromFront() tests passed!");
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");
        list.addToBack("D");
        list.addToBack("E");
        list.addToBack("F");
        list.addToBack("G");
        list.addToBack("H");

        Object[] removed = new Object[3];
        removed[0] = list.removeFromBack();
        removed[1] = list.removeFromBack();
        removed[2] = list.removeFromBack();

        Object[] expectedRemoved = {"H", "G", "F"};

        Object[] expected = new Object[INITIAL_CAPACITY];
        expected[0] = "A";
        expected[1] = "B";
        expected[2] = "C";
        expected[3] = "D";
        expected[4] = "E";

        // make sure correct elements were removed
        assertArrayEquals("Incorrect elements removed", expectedRemoved, removed);

        // make sure size is correct
        assertEquals("Incorrect size value", INITIAL_CAPACITY - 4, list.size());

        // make sure final arrays are equal
        assertArrayEquals("elements are not in correct positions", expected, list.getBackingArray());

        System.out.println("all removeFromBack() tests passed!");
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");
        list.addToBack("D");
        list.addToBack("E");
        list.addToBack("F");
        list.addToBack("G");
        list.addToBack("H");

        Object[] received = new Object[5];
        received[0] = list.get(5);
        received[1] = list.get(4);
        received[2] = list.get(3);
        received[3] = list.get(2);
        received[4] = list.get(1);

        Object[] expectedReceived = {"F", "E", "D" , "C", "B"};
        Object[] expected = {"A", "B", "C" , "D", "E", "F", "G", "H", null};

        // make sure correct elements were received
        assertArrayEquals("Incorrect elements returned", expectedReceived, received);

        // make sure size did not change from the get() method
        assertEquals("Incorrect size value, size should not be changed",
                INITIAL_CAPACITY - 1, list.size());

        // make sure array did not get modified
        assertArrayEquals("get() should not modify the ArrayList", expected, list.getBackingArray());

        System.out.println("all get() tests passed!");
    }

    @Test(timeout = TIMEOUT)
    public void testLastIndexOf() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");
        list.addToBack("D");
        list.addToBack("A");
        list.addToBack("F");
        list.addToBack("G");
        list.addToBack("H");

        int[] received = new int[3];
        received[0] = list.lastIndexOf("A");
        received[1] = list.lastIndexOf("C");
        received[2] = list.lastIndexOf("L");

        int[] shouldBeReceived = {4, 2, -1};

        Object[] expected = {"A", "B", "C" , "D", "A", "F", "G", "H", null};

        // make sure correct values were received
        assertArrayEquals("Incorrect values returned", shouldBeReceived, received);

        // make sure size did not change from the get() method
        assertEquals("Incorrect size value, size should not be changed",
                INITIAL_CAPACITY - 1, list.size());

        // make sure array did not get modified
        assertArrayEquals("lastIndexOf() should not modify the ArrayList", expected, list.getBackingArray());

        System.out.println("all lastIndexOf() tests passed!");
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        // make sure inital array is empty
        assertEquals("Initial array is not empty", true, list.isEmpty());

        list.addToFront("A");

        // make sure new array is not empty
        assertEquals("Method returns empty when array is not empty",
                false, list.isEmpty());

        list.removeFromFront();
        assertEquals("Array should be empty", true, list.isEmpty());

        System.out.println("all isEmpty() tests passed!");
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");
        list.addToBack("D");
        list.addToBack("E");
        list.addToBack("F");
        list.addToBack("G");
        list.addToBack("H");
        list.addToBack("I");
        list.addToBack("J");

        list.clear();

        // make sure size is 0
        assertEquals("size not properly reset to 0", 0, list.size());

        // make sure the array is now of size INITIAL_CAPACITY with all null elements
        assertArrayEquals("Array must be of size INITIAL_CAPACITY"
                + " and have only null elements", new Object[INITIAL_CAPACITY], list.getBackingArray());

        System.out.println("all clear() tests passed!");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddIndexNegativeException() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");

        // should cause exception
        list.addAtIndex(-1, "L");

        System.out.println("ERROR: Exception not thrown when index < 0");
    }
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddIndexSizeException() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");

        // should cause exception
        list.addAtIndex(4, "L");

        System.out.println("ERROR: Exception not thrown when index > size");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddIndexDataException() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");

        // should cause exception
        list.addAtIndex(2, null);

        System.out.println("ERROR: Exception not thrown when data is null");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddFrontDataException() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");

        // should cause exception
        list.addToFront(null);

        System.out.println("ERROR: Exception not thrown when data is null");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddBackDataException() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");

        // should cause exception
        list.addToBack(null);

        System.out.println("ERROR: Exception not thrown when data is null");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveIndexNegativeException() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");

        // should cause exception
        list.removeAtIndex(-1);

        System.out.println("ERROR: Exception not thrown when index = -1");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveIndexSizeException() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");

        // should cause exception
        list.removeAtIndex(list.size());

        System.out.println("ERROR: Exception not thrown when index = size");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveIndexGreaterSizeException() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");

        // should cause exception
        list.removeAtIndex(list.size() + 1);

        System.out.println("ERROR: Exception not thrown when index > size");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetNegativeException() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");

        // should cause exception
        list.get(-1);

        System.out.println("ERROR: Exception not thrown when index = -1");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetSizeException() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");

        // should cause exception
        list.get(list.size());

        System.out.println("ERROR: Exception not thrown when index = size");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetGreaterSizeException() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");

        // should cause exception
        list.get(list.size() + 1);

        System.out.println("ERROR: Exception not thrown when index > size");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLastIndexDataException() {
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");

        // should cause exception
        list.lastIndexOf(null);

        System.out.println("ERROR: Exception not thrown when data = null");
    }
}
