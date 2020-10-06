import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

/**
 * Basic tests for the stack and queue classes.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class StacksQueuesStudentTests {

    private ArrayStack<Integer> arrayStack;
    private ArrayQueue<Integer> arrayQueue;
    private LinkedStack<Integer> linkedStack;
    private LinkedQueue<Integer> linkedQueue;

    public static final int TIMEOUT = 200;


    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testArrayStackPushIllegalArgumentException() {
        arrayStack = new ArrayStack<>();
        arrayStack.push(null);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPush() {
        arrayStack = new ArrayStack<>();
        assertEquals(0, arrayStack.size());

        // [0, 1, 2, 3, _, _, _, _, _]
        arrayStack.push(0);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);

        assertEquals(4, arrayStack.size());

        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.push(6);
        arrayStack.push(7);
        arrayStack.push(8);

        assertEquals(9, arrayStack.size());

        Object[] backingArray = arrayStack.getBackingArray();

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = i;
        }
        assertArrayEquals(expected, backingArray);

        arrayStack.push(9);

        Object[] resized = new Object[2 * expected.length];

        for (int i = 0; i < expected.length; i++) {
            resized[i] = expected[i];
        }
        resized[9] = 9;

        assertArrayEquals(resized, arrayStack.getBackingArray());
        assertEquals(18, arrayStack.getBackingArray().length);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPop() {
        arrayStack = new ArrayStack<>();
        assertEquals(0, arrayStack.size());

        // [0, 1, 2, 3, _, _, _, _, _]
        arrayStack.push(0);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.push(6);
        arrayStack.push(7);
        arrayStack.push(8);

        assertEquals(9, arrayStack.size());

        Object[] backingArray = arrayStack.getBackingArray();

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        for (int i = 0; i < backingArray.length; i++) {
            expected[i] = i;
        }

        // doubling backing array
        arrayStack.push(9);

        Object[] resized = new Object[2 * expected.length];
        for (int i = 0; i < expected.length; i++) {
            resized[i] = expected[i];
        }
        resized[expected.length] = 9;

        assertEquals(10, arrayStack.size());
        assertArrayEquals(arrayStack.getBackingArray(), resized);
        assertEquals((Integer) 9, arrayStack.pop());
        assertEquals(9, arrayStack.size());
        assertEquals(18, arrayStack.getBackingArray().length);


        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayStackPopIllegalArgumentException() {
        arrayStack = new ArrayStack<>();
        arrayStack.pop();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPeek() {
        arrayStack = new ArrayStack<>();
        assertEquals(null, arrayStack.peek());
        assertEquals(0, arrayStack.size());

        // [34, 29, 48, 59, _, _, _, _, _]
        arrayStack.push(34);
        arrayStack.push(29);
        arrayStack.push(48);
        arrayStack.push(59);

        assertEquals((Integer) 59, arrayStack.peek());
        assertEquals(4, arrayStack.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLinkedStackPushIllegalArgumentException() {
        linkedStack = new LinkedStack<>();
        linkedStack.push(null);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackPush() {
        linkedStack = new LinkedStack<>();
        assertEquals(0, linkedStack.size());

        // 59 -> 48 -> 29 -> 34
        linkedStack.push(34);
        linkedStack.push(29);
        linkedStack.push(48);
        linkedStack.push(59);

        assertEquals(4, linkedStack.size());

        LinkedNode<Integer> curr = linkedStack.getHead();
        assertNotEquals(null, curr);
        assertEquals((Integer) 59, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 48, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 29, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 34, curr.getData());

        curr = curr.getNext();
        assertEquals(null, curr);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedStackPopIllegalArgumentException() {
        linkedStack = new LinkedStack<>();
        linkedStack.pop();
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackPop() {
        linkedStack = new LinkedStack<>();
        assertEquals(0, linkedStack.size());

        // 59 -> 48 -> 29 -> 34
        linkedStack.push(34);
        linkedStack.push(29);
        linkedStack.push(48);
        linkedStack.push(59);

        // 48 -> 29 -> 34
        assertEquals((Integer) 59, linkedStack.pop());

        assertEquals(3, linkedStack.size());

        LinkedNode<Integer> curr = linkedStack.getHead();
        assertNotEquals(null, curr);
        assertEquals((Integer) 48, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 29, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 34, curr.getData());

        curr = curr.getNext();
        assertEquals(null, curr);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackPeek() {
        linkedStack = new LinkedStack<>();
        assertEquals(null, linkedStack.peek());
        assertEquals(0, linkedStack.size());

        // 59 -> 48 -> 29 -> 34
        linkedStack.push(34);
        linkedStack.push(29);
        linkedStack.push(48);
        linkedStack.push(59);

        assertEquals((Integer) 59, linkedStack.peek());
        assertEquals(4, linkedStack.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testArrayQueueEnqueueIllegalArgumentException() {
        arrayQueue = new ArrayQueue<>();
        arrayQueue.enqueue(null);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueEnqueue() {
        arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());

        // [0, 1, 2, 3, _, _, _, _, _]
        arrayQueue.enqueue(0);
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(3);

        assertEquals(4, arrayQueue.size());

        Object[] backingArray = arrayQueue.getBackingArray();

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = 0;
        expected[1] = 1;
        expected[2] = 2;
        expected[3] = 3;

        assertArrayEquals(expected, backingArray);

        assertEquals(arrayQueue.dequeue(), (Integer) 0);
        assertEquals(arrayQueue.dequeue(), (Integer) 1);
        // [_, _, 2, 3, _, _, _, _, _]
        expected[0] = null;
        expected[1] = null;
        assertArrayEquals(expected, arrayQueue.getBackingArray());
        assertEquals(arrayQueue.peek(), (Integer) 2);
        for (int i = 0; i < 5; i++) {
            arrayQueue.enqueue(1);
        }
        arrayQueue.enqueue(4);
        arrayQueue.enqueue(4);

        backingArray = arrayQueue.getBackingArray();
        // [4, 4, 2, 3, 1, 1, 1, 1, 1]
        expected[0] = 4;
        expected[1] = 4;
        expected[4] = 1;
        expected[5] = 1;
        expected[6] = 1;
        expected[7] = 1;
        expected[8] = 1;
        assertArrayEquals(expected, arrayQueue.getBackingArray());

        arrayQueue.enqueue(5);
        arrayQueue.enqueue(5);
        // [2, 3, 1, 1, 1, 1, 1, 4, 4, 5, 5, null, null, null, null, null, null, null]
        Object[] resize = new Object[2 * ArrayQueue.INITIAL_CAPACITY];
        assertEquals(18, arrayQueue.getBackingArray().length);
        resize[0] = 2;
        resize[1] = 3;
        resize[2] = 1;
        resize[3] = 1;
        resize[4] = 1;
        resize[5] = 1;
        resize[6] = 1;
        resize[7] = 4;
        resize[8] = 4;
        resize[9] = 5;
        resize[10] = 5;
        /*for (int i = 0; i < arrayQueue.getBackingArray().length; i++) {
            System.out.println(arrayQueue.getBackingArray()[i]);
        }*/
        assertEquals((Integer) 2, arrayQueue.peek());
        assertArrayEquals(resize, arrayQueue.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueDequeue() {
        arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());

        // [0, 1, 2, 3, _, _, _, _, _]
        arrayQueue.enqueue(0);
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(3);

        // [_, 1, 2, 3, _, _, _, _, _]
        assertEquals((Integer) 0, arrayQueue.dequeue());

        assertEquals(3, arrayQueue.size());

        Object[] backingArray = arrayQueue.getBackingArray();

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[1] = 1;
        expected[2] = 2;
        expected[3] = 3;

        assertArrayEquals(expected, backingArray);

        for (int i = 0; i < 5; i++) {
            arrayQueue.enqueue(0);
        }
        arrayQueue.enqueue(10);
        expected[0] = 10;
        expected[4] = 0;
        expected[5] = 0;
        expected[6] = 0;
        expected[7] = 0;
        expected[8] = 0;
        // [10, 1, 2, 3, 0, 0, 0, 0, 0]
        assertArrayEquals(expected, backingArray);
        assertEquals((Integer) 1, arrayQueue.dequeue());
        assertEquals((Integer) 2, arrayQueue.dequeue());
        assertEquals((Integer) 3, arrayQueue.dequeue());
        assertEquals((Integer) 0, arrayQueue.dequeue());
        assertEquals((Integer) 0, arrayQueue.dequeue());
        assertEquals((Integer) 0, arrayQueue.dequeue());
        assertEquals((Integer) 0, arrayQueue.dequeue());
        assertEquals((Integer) 0, arrayQueue.dequeue());
        for (int i = 1; i < arrayQueue.getBackingArray().length; i++) {
            expected[i] = null;
        }
        // [10, null, null, null, null, null, null, null, null]
        assertArrayEquals(expected, backingArray);
        assertEquals((Integer) 10, arrayQueue.dequeue());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueuePeek() {
        arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());
        assertEquals(null, arrayQueue.peek());

        // [34, 29, 38, 59, _, _, _, _, _]
        arrayQueue.enqueue(34);
        arrayQueue.enqueue(29);
        arrayQueue.enqueue(48);
        arrayQueue.enqueue(59);

        assertEquals((Integer) 34, arrayQueue.peek());
        assertEquals(4, arrayQueue.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLinkedQueueEnqueueIllegalArgumentException() {
        linkedQueue = new LinkedQueue<>();
        linkedQueue.enqueue(null);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueueEnqueue() {
        linkedQueue = new LinkedQueue<>();
        assertEquals(0, linkedQueue.size());

        // 34 -> 29 -> 48 -> 59
        linkedQueue.enqueue(34);
        linkedQueue.enqueue(29);
        linkedQueue.enqueue(48);
        linkedQueue.enqueue(59);

        assertEquals(4, linkedQueue.size());

        LinkedNode<Integer> curr = linkedQueue.getHead();
        assertNotEquals(null, curr);
        assertEquals((Integer) 34, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 29, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 48, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 59, curr.getData());
        assertSame(linkedQueue.getTail(), curr);

        curr = curr.getNext();
        assertEquals(null, curr);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedQueueDequeueIllegalArgumentException() {
        linkedQueue = new LinkedQueue<>();
        linkedQueue.dequeue();
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueueDequeue() {
        linkedQueue = new LinkedQueue<>();
        assertEquals(0, linkedQueue.size());

        // 34 -> 29 -> 48 -> 59
        linkedQueue.enqueue(34);
        linkedQueue.enqueue(29);
        linkedQueue.enqueue(48);
        linkedQueue.enqueue(59);

        // 29 -> 48 -> 59
        assertEquals((Integer) 34, linkedQueue.dequeue());

        assertEquals(3, linkedQueue.size());

        LinkedNode<Integer> curr = linkedQueue.getHead();
        assertNotEquals(null, curr);
        assertEquals((Integer) 29, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 48, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 59, curr.getData());
        assertSame(linkedQueue.getTail(), curr);

        curr = curr.getNext();
        assertEquals(null, curr);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueuePeek() {
        linkedQueue = new LinkedQueue<>();
        assertEquals(0, linkedQueue.size());
        assertEquals(null, linkedQueue.peek());

        // 34 -> 29 -> 48 -> 59
        linkedQueue.enqueue(34);
        linkedQueue.enqueue(29);
        linkedQueue.enqueue(48);
        linkedQueue.enqueue(59);

        assertEquals((Integer) 34, linkedQueue.peek());
        assertEquals(4, linkedQueue.size());
    }
}
