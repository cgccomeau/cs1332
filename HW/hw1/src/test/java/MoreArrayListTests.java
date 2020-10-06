import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


/**
 * Still need to test get() and lastIndexOf()
 *
 * @author samin36
 * @version 1.0
 */
public class MoreArrayListTests {

    public static final int TIMEOUT = 100;
    private ArrayList<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialArray() {
        assertEquals("size variable is incorrectly initialized", 0,
                list.size());
        assertArrayEquals("Backing array is not correctly initialized",
                new Object[ArrayList.INITIAL_CAPACITY],
                list.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddToFrontWithNull() {
        list.addToFront("a");
        list.addToFront("b");
        list.addToFront("c");
        list.addToFront(null);
        list.addToFront("d");
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontChecksCapacity() {
        assertEquals("Size of backing array before adding to the front was not "
                + "zero", 0, list.size());
        for (int i = 1; i <= ArrayList.INITIAL_CAPACITY; i++) {
            list.addToFront("z" + i + "z");
        }
        assertEquals("Size of backing array does not equal initial capacity",
                ArrayList.INITIAL_CAPACITY, list.size());

        Object[] expectedArray = {"z9z", "z8z", "z7z", "z6z", "z5z",
                "z4z", "z3z", "z2z", "z1z"};

        assertArrayEquals("addToFront did not add elements correctly",
                expectedArray, list.getBackingArray());

        list.addToFront(
                "z" + String.valueOf(ArrayList.INITIAL_CAPACITY + 1) + "z");
        assertEquals("Length of backing array was not doubled when adding to "
                        + "the front", ArrayList.INITIAL_CAPACITY * 2,
                list.getBackingArray().length);
        assertEquals("Size was not incremented properly after exceeding"
                        + " capacity", ArrayList.INITIAL_CAPACITY + 1,
                list.size());

        expectedArray = new Object[ArrayList.INITIAL_CAPACITY * 2];
        for (int i = 1; i <= list.size(); i++) {
            expectedArray[i - 1] = "z" + (list.size() - (i - 1)) + "z";
        }
        assertArrayEquals("addToFront did not add elements correctly when at "
                        + "full capacity", expectedArray,
                list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        assertEquals("Size was not zero before adding to the front", 0,
                list.size());
        for (int i = 1; i <= 4; i++) {
            list.addToFront("z" + i + "z");
        }

        assertEquals("Size was not incremented properly after adding to the "
                + "front", 4, list.size());
        Object[] expectedArray = new Object[ArrayList.INITIAL_CAPACITY];
        expectedArray[0] = "z4z";
        expectedArray[1] = "z3z";
        expectedArray[2] = new String("z2z");
        expectedArray[3] = "z1z";
        assertArrayEquals("Add to front did not add elements correctly",
                expectedArray, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddToBackWithNull() {
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack("c");
        list.addToBack(null);
        list.addToBack("d");
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackChecksCapacity() {
        assertEquals("Size of backing array before adding to the back was not "
                + "zero", 0, list.size());
        for (int i = 1; i <= list.getBackingArray().length; i++) {
            list.addToBack("z" + i + "z");
        }
        assertEquals("Size of backing array does not equal initial capacity",
                ArrayList.INITIAL_CAPACITY, list.size());

        Object[] expectedArray = {"z1z", "z2z", "z3z", "z4z", "z5z",
                "z6z", "z7z", "z8z", "z9z"};

        assertArrayEquals("addToBack did not add elements correctly",
                expectedArray, list.getBackingArray());

        list.addToBack(
                "z" + String.valueOf(ArrayList.INITIAL_CAPACITY + 1) + "z");


        assertEquals("Length of backing array was not doubled when adding to "
                        + "the back", ArrayList.INITIAL_CAPACITY * 2,
                list.getBackingArray().length);
        assertEquals("Size was not incremented properly after exceeding "
                + "capacity", ArrayList.INITIAL_CAPACITY + 1, list.size());

        expectedArray = new Object[ArrayList.INITIAL_CAPACITY * 2];
        for (int i = 1; i <= list.size(); i++) {
            expectedArray[i - 1] = new String("z" + i + "z");
        }
        assertArrayEquals("addToBack did not add elements correctly when at "
                        + "full capacity", expectedArray,
                list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        assertEquals("Size was not zero before adding to the back", 0,
                list.size());
        for (int i = 1; i <= 4; i++) {
            list.addToBack("z" + i + "z");
        }

        assertEquals("Size was not incremented properly after adding to the "
                + "back", 4, list.size());
        Object[] expectedArray = new Object[ArrayList.INITIAL_CAPACITY];
        expectedArray[0] = "z1z";
        expectedArray[1] = "z2z";
        expectedArray[2] = new String("z3z");
        expectedArray[3] = "z4z";
        assertArrayEquals("Add to back did not add elements correctly",
                expectedArray, list.getBackingArray());

    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexChecksCapacity() {
        assertEquals("Size was not zero before calling addAtIndex method", 0,
                list.size());
        for (int i = 1; i <= list.getBackingArray().length; i++) {
            list.addAtIndex(i - 1, "z" + i + "z");
        }
        assertEquals("The length (not size of list) of backing array was "
                        + "changed before capacity" + " was reached",
                ArrayList.INITIAL_CAPACITY, list.getBackingArray().length);
        list.addAtIndex(0, "z0z");
        list.addAtIndex(list.size(), "z10z");
        assertEquals("The backing array was not resized after reaching "
                        + "capacity in addAtIndex", ArrayList.INITIAL_CAPACITY * 2,
                list.getBackingArray().length);
        assertEquals("The size of the list was not incremented properly in "
                + "addAtIndex", 11, list.size());
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexThrowsIBEForNegativeIndex() {
        assertEquals("Size was not zero before calling addAtIndex method", 0,
                list.size());
        list.addAtIndex(0, "z1z"); // "z1z"
        list.addAtIndex(list.size(), "z2z"); // "z1z", "z2z"
        list.addAtIndex(1, "z3z"); // "z1z", "z3z", "z2z"
        list.addAtIndex(-9, "z(-1)z");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexThrowsIBEForIndexGreaterThanSize() {
        assertEquals("Size was not zero before calling addAtIndex method", 0,
                list.size());
        list.addAtIndex(0, "z1z"); // "z1z"
        list.addAtIndex(list.size(), "z2z"); // "z1z", "z2z"
        list.addAtIndex(1, "z3z"); // "z1z", "z3z", "z2z"
        list.addAtIndex(list.size() + 9, "z4z");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddAtIndexThrowsIllegalArgumentException() {
        assertEquals("Size was not zero before calling addAtIndex method", 0,
                list.size());
        list.addAtIndex(0, "z1z"); // "z1z"
        list.addAtIndex(list.size(), "z2z"); // "z1z", "z2z"
        list.addAtIndex(1, "z3z"); // "z1z", "z3z", "z2z"
        list.addAtIndex(2, null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
        assertEquals("Size was not zero before calling addAtIndex", 0,
                list.size());
        list.addAtIndex(0, "z2z"); // "z2z"
        list.addAtIndex(0, "z1z"); // "z1z", "z2z"
        list.addAtIndex(list.size(), "z3z"); // "z1z", "z2z", "z3z"
        list.addAtIndex(list.size(), "z4z"); // "z1z", "z2z", "z3z", "z4z"
        list.addAtIndex(2, "z5z"); // "z1z", "z2z", "z5z", "z3z", "z4z"

        assertEquals("Size was not incremented properly in addAtIndex", 5,
                list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "z1z";
        expected[1] = "z2z";
        expected[2] = "z5z";
        expected[3] = "z3z";
        expected[4] = "z4z";

        assertArrayEquals("addAtIndex did not add elements in correct order",
                expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        assertEquals("Size was not zero before calling removeFromFront", 0,
                list.size());
        assertNull("removeFromFront did not return null when trying to remove"
                + " from empty list", list.removeFromFront());

        list.addToFront("z3z"); // "z3z"
        list.addToBack("z2z"); // "z3z", "z2z"
        list.addAtIndex(1, "z1z"); // "z3z", "z1z", "z2z"

        assertEquals("Size was not incremented properly", 3, list.size());

        // "z1z", "z2z"
        assertEquals("removeFromFront did not return the item removed (might "
                        + "have used reference equality instead of value)",
                new String("z3z"), list.removeFromFront());

        assertEquals("Size was not decremented in removeFromFront", 2,
                list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = new String("z1z");
        expected[1] = new String("z2z");
        assertArrayEquals("Elements may not have been shifted after call to "
                        + "removeFromFront", expected,
                list.getBackingArray());

        expected = new Object[ArrayList.INITIAL_CAPACITY];
        while (list.size() != 0) {
            list.removeFromFront();
        }

        assertArrayEquals(
                "Removing all elements using removeFromFront did not work",
                expected, list.getBackingArray());

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        assertEquals("Size was not zero before calling removeFromBack", 0,
                list.size());
        assertNull("removeFromBack did not return null when trying to remove"
                + " from empty list", list.removeFromBack());

        list.addToFront("z3z"); // "z3z"
        list.addToBack("z2z"); // "z3z", "z2z"
        list.addAtIndex(1, "z1z"); // "z3z", "z1z", "z2z"

        assertEquals("Size was not incremented properly", 3, list.size());

        // "z3z", "z1z"
        assertEquals("removeFromBack did not return the item removed (might "
                        + "have used reference equality instead of value)",
                new String("z2z"), list.removeFromBack());

        assertEquals("Size was not decremented in removeFromBack", 2,
                list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = new String("z3z");
        expected[1] = new String("z1z");
        assertArrayEquals("Elements may not have been set to null after call "
                        + "to removeFromBack", expected,
                list.getBackingArray());

        expected = new Object[ArrayList.INITIAL_CAPACITY];
        while (list.size() != 0) {
            list.removeFromBack();
        }

        assertArrayEquals(
                "Removing all elements using removeFromBack did not work",
                expected, list.getBackingArray());

    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexThrowsIBEForNegativeIndex() {
        assertEquals("Size was not zero before calling removeFromIndex", 0,
                list.size());
        list.addAtIndex(0, "z1z");
        list.addAtIndex(list.size(), "z2z");
        list.addToBack("z3z");
        list.addToFront("z4z");

        list.removeAtIndex(-9);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexThrowsIBEForInvalidSizeIndex() {
        assertEquals("Size was not zero before calling removeFromIndex", 0,
                list.size());
        list.addAtIndex(0, "z1z");
        list.addAtIndex(list.size(), "z2z");
        list.addToBack("z3z");
        list.addToFront("z4z");

        list.removeAtIndex(list.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        assertEquals("Size was not zero before calling removeFromIndex", 0,
                list.size());
        list.addAtIndex(0, "z1z");
        list.addAtIndex(list.size(), "z2z");
        list.addToBack("z3z");
        list.addToFront(new String("z4z"));
        list.addAtIndex(3, "z5z"); // "z4z", "z1z", "z2z", "z5z, "z3z"

        assertEquals("Size was not updated properly after adding elements", 5,
                list.size());

        assertEquals("removeAtIndex did not return the correct value", "z2z",
                list.removeAtIndex(2)); // "z4z", "z1z",
        // "z5z, "z3z"

        assertEquals("Size was not decremented/updated properly after calling"
                + " removeAtIndex", 4, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "z4z";
        expected[1] = "z1z";
        expected[2] = "z5z";
        expected[3] = "z3z";
        assertArrayEquals("removeAtIndex did not remove correctly", expected,
                list.getBackingArray());

        list.removeAtIndex(0); // "z1z", "z5z", "z3z"
        list.removeAtIndex(list.size() - 1); // "z1z", "z5z"
        expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = new String("z1z");
        expected[1] = new String("z5z");
        assertArrayEquals("removeAtIndex did not remove correctly", expected,
                list.getBackingArray());

    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexOnEmptyList() {
        assertEquals("Size was not zero before calling removeFromIndex", 0,
                list.size());
        list.addAtIndex(0, "z1z");
        list.addAtIndex(list.size(), "z2z");
        list.addToBack("z3z");

        while (list.size() != 0) {
            Object expected = list.get(0);
            assertEquals("Incorrect element return from removeAtIndex",
                    expected, list.removeAtIndex(0));
        }
        list.removeAtIndex(0);
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertTrue("isEmpty did not return true on an empty list",
                list.isEmpty());

        list.addToFront("z1z");
        list.addToBack("z2z");
        list.addAtIndex(1, "z3z");

        assertFalse("isEmpty returned true on an non-empty list",
                list.isEmpty());

        while (list.size() != 0) {
            list.removeFromBack();
        }
        assertTrue("isEmpty did not return true on an empty list",
                list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testIsClear() {
        assertEquals("Size was not zero before calling clear", 0, list.size());

        for (int i = 1; i <= ArrayList.INITIAL_CAPACITY * 2; i++) {
            list.addAtIndex(i - 1, "z" + i + "z");
        }

        assertEquals("Size was not incremented properly", 18, list.size());

        list.clear();

        assertEquals("Size was not set to zero after calling clear", 0,
                list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        assertArrayEquals("backingArray was not reset to initial capacity "
                        + "after calling clear", expected,
                list.getBackingArray());
    }
}
