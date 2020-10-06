import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
/**
 * Some edge case testing for MaxHeap
 *
 * @author Sukhmai Kapur
 * @version 1.0
 */

public class SukhkyHeapTests {

    private static final int TIMEOUT = 200;
    private MaxHeap<Integer> maxHeap;

    @Before
    public void setUp() {
        maxHeap = new MaxHeap<>();
    }

    @Test(timeout = TIMEOUT)

    public void testresizeAdd() {

        /*
                   80
              /          \
            30            40
          /   \         /    \
        25     28      20      15
       /  \   /  \   /  \    /   \
      16  20 24  23 8   10
         */
        assertTrue(maxHeap.isEmpty());
        assertEquals(0, maxHeap.size());
        maxHeap.add(40);
        maxHeap.add(30);
        maxHeap.add(20);
        maxHeap.add(25);
        maxHeap.add(28);
        maxHeap.add(10);
        maxHeap.add(15);
        maxHeap.add(16);
        maxHeap.add(20);
        maxHeap.add(24);
        maxHeap.add(23);
        maxHeap.add(8);
        maxHeap.add(80);
        Integer[] expected = new Integer[MaxHeap.INITIAL_CAPACITY * 2];
        expected[1] = 80;
        expected[2] = 30;
        expected[3] = 40;
        expected[4] = 25;
        expected[5] = 28;
        expected[6] = 20;
        expected[7] = 15;
        expected[8] = 16;
        expected[9] = 20;
        expected[10] = 24;
        expected[11] = 23;
        expected[12] = 8;
        expected[13] = 10;
        assertArrayEquals(expected, maxHeap.getBackingArray());
        assertEquals(13, maxHeap.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBuildHeapContainsNull() {
        ArrayList<Integer> passedIn = new ArrayList<>();
        passedIn.add(25);
        passedIn.add(10);
        passedIn.add(30);
        passedIn.add(null);
        passedIn.add(44);
        maxHeap = new MaxHeap<>(passedIn);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBuildHeapISNull() {
        ArrayList<Integer> passedIn = null;
        maxHeap = new MaxHeap<>(passedIn);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddingNull() {
        maxHeap.add(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemovingEmpty() {
        maxHeap.remove();
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        maxHeap.add(20);
        maxHeap.add(21);
        maxHeap.clear();
        assertEquals(0, maxHeap.size());
        assertEquals(maxHeap.INITIAL_CAPACITY,
                maxHeap.getBackingArray().length);
    }

    @Test(timeout = TIMEOUT)
    public void testAddingAfterResizing() {
        /*
                   9
                /     \
              8        6
            /  \     /   \
         7      3  1      5
       /  \
     2     4
        */

        ArrayList<Integer> passedIn = new ArrayList<>();
        passedIn.add(1);
        passedIn.add(2);
        passedIn.add(3);

        maxHeap = new MaxHeap(passedIn);
        maxHeap.add(4);
        maxHeap.add(5);
        maxHeap.add(6);
        maxHeap.add(7);
        maxHeap.add(8);
        maxHeap.add(9);
        Integer[] expected = {null, 9, 8, 6, 7, 3, 1, 5, 2, 4,
                null, null, null, null};
        assertArrayEquals(expected, maxHeap.getBackingArray());
        assertEquals(9, maxHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testCorrectInitialSize() {
        ArrayList<Integer> passedIn = new ArrayList<>();
        passedIn.add(1);
        passedIn.add(2);
        passedIn.add(3);
        maxHeap = new MaxHeap(passedIn);

        assertEquals(3, maxHeap.size());
        assertEquals(7, maxHeap.getBackingArray().length);
        assertNull(maxHeap.getBackingArray()[0]);
    }

    @Test(timeout = TIMEOUT)
    public void testMax() {

        //tests if getMax returns null
        assertEquals(0, maxHeap.size());
        assertNull(maxHeap.getMax());

        //tests getMax without reordering
        maxHeap.add(1);
        assertEquals((Object) 1, maxHeap.getMax());

        //tests getMax with reordering
        maxHeap.add(2);
        maxHeap.add(3);
        assertEquals((Object) 3, maxHeap.getMax());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAndRemove() {
        /*
                   9                           8
                /     \                     /     \
              8        6                  7        6
            /  \     /   \     ->       /  \     /   \   ->
         7      3  2      5           4     3  2      5
       /  \                         /
     1     4                       1

                   7
                /     \
              4        6
            /  \     /   \
         1      3  2      5
        */

        maxHeap.add(1);
        maxHeap.add(2);
        maxHeap.add(3);
        maxHeap.add(4);
        maxHeap.add(5);
        maxHeap.add(6);
        maxHeap.add(7);
        maxHeap.add(8);
        maxHeap.add(9);

        assertEquals((Object) 9, maxHeap.remove());
        Integer[] expected = {null, 8, 7, 6, 4, 3, 2, 5, 1, null,
                null, null, null};
        assertArrayEquals(expected, maxHeap.getBackingArray());

        assertEquals((Object) 8, maxHeap.remove());
        Integer[] expected2 = {null, 7, 4, 6, 1, 3, 2, 5, null, null,
                null, null, null};
        assertArrayEquals(expected2, maxHeap.getBackingArray());
    }

    @Test (timeout = TIMEOUT)
    public void testRemoveRoot() {
        maxHeap.add(200);
        assertEquals((Object) 200, maxHeap.remove());
        assertNull(maxHeap.getBackingArray()[0]);
        assertTrue(maxHeap.isEmpty());
        assertEquals(0, maxHeap.size());
    }

    @Test (timeout = TIMEOUT)
    public void testEmptyList() {
        ArrayList<Integer> passedIn = new ArrayList<>();
        maxHeap = new MaxHeap<>(passedIn);

        assertEquals(1, maxHeap.getBackingArray().length);
        assertEquals(0, maxHeap.size());

        maxHeap.add(0);
        assertEquals(2, maxHeap.getBackingArray().length);
        assertEquals(1, maxHeap.size());
        assertEquals(0, maxHeap.getBackingArray()[1]);
        assertFalse(maxHeap.isEmpty());

        assertEquals((Object) 0, maxHeap.remove());
        assertEquals(2, maxHeap.getBackingArray().length);
        assertTrue(maxHeap.isEmpty());

    }

    @Test (timeout = TIMEOUT)
    public void testBasicRemove() {
        /*
                   45
               /      \                  10
             11       20     --->      /   \
           /   \     /               5     -10
          5    10 -10
        */
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(45);
        maxHeap.add(5);
        maxHeap.add(11);
        maxHeap.add(-10);

        assertEquals((Integer) 45, maxHeap.remove());
        assertEquals((Integer) 20, maxHeap.remove());
        assertEquals((Integer) 11, maxHeap.remove());
        assertEquals(3, maxHeap.size());

        Integer[] expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[1] = new Integer(10);
        expected[2] = new Integer(5);
        expected[3] = new Integer(-10);

        assertArrayEquals(expected, maxHeap.getBackingArray());
    }

    @Test (timeout = TIMEOUT)
    public void testBasicBuildHeap() {
        /*
                  5                       40
               /     \                 /     \
              10      7     --->     15       7
            /  \    /  \           /   \    /  \
           0   15 40    2        0     10  5    2
        */
        ArrayList<Integer> passedIn = new ArrayList<>();
        passedIn.add(5);
        passedIn.add(10);
        passedIn.add(7);
        passedIn.add(0);
        passedIn.add(15);
        passedIn.add(40);
        passedIn.add(2);
        maxHeap = new MaxHeap(passedIn);

        Integer[] expected = new Integer[15];
        expected[1] = 40;
        expected[2] = 15;
        expected[3] = 7;
        expected[4] = 0;
        expected[5] = 10;
        expected[6] = 5;
        expected[7] = 2;

        assertEquals(7, maxHeap.size());
        assertArrayEquals(expected, maxHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testBuildAndResize() {
        /*
                   40
                /      \
              10        15
            /   \     /   \
          0      5   7     2
        */
        ArrayList<Integer> passedIn = new ArrayList<>();
        passedIn.add(5);
        passedIn.add(10);
        passedIn.add(7);
        maxHeap = new MaxHeap(passedIn);
        maxHeap.add(0);
        maxHeap.add(15);
        maxHeap.add(40);
        maxHeap.add(2);

        Integer[] expected = new Integer[14];
        expected[1] = 40;
        expected[2] = 10;
        expected[3] = 15;
        expected[4] = 0;
        expected[5] = 5;
        expected[6] = 7;
        expected[7] = 2;

        assertEquals(7, maxHeap.size());
        assertArrayEquals(expected, maxHeap.getBackingArray());
    }
}
