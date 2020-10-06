import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ZJamesTest {

    private ArrayQueue<String> s;
    private ArrayStack<String> a;
    private LinkedQueue<String> p;
    private LinkedStack<String> r;

    @Before
    public void setUp() {
        s = new ArrayQueue<String>();
        a = new ArrayStack<String>();
        p = new LinkedQueue<String>();
        r = new LinkedStack<String>();
    }


    //////////////////////
    // ArrayQueue Tests //
    //////////////////////
    @Test
    public void ArrayQueueConstructorTest() {
        assertEquals(0, s.size());
        assertEquals(s.INITIAL_CAPACITY, s.getBackingArray().length);

    }

    @Test
    public void ArrayQueueEnqueueTest() {
        s.enqueue("A");
        assertEquals(1, s.size());
        s.enqueue("B");
        assertEquals(2, s.size());
        s.enqueue("C");
        assertEquals(3, s.size());
        String[] arr = {"A", "B", "C", null, null, null, null, null, null};
        assertArrayEquals(s.getBackingArray(), arr);
        s.enqueue("D");
        s.enqueue("E");
        s.enqueue("F");
        s.enqueue("G");
        s.enqueue("H");
        s.enqueue("I");
        assertEquals(9, s.size());

        s.enqueue("J");
        String[] arr2 = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                null, null, null, null, null, null, null, null
        };
        assertArrayEquals(s.getBackingArray(), arr2);
        assertEquals(10, s.size());
        assertEquals(18, s.getBackingArray().length);
    }

    @Test (expected = IllegalArgumentException.class)
    public void ArrayQueueEnqueueExceptionTest() {
        s.enqueue(null);
    }
    @Test
    public void ArrayQueueDequeueTest() {
        s.enqueue("A");
        s.enqueue("B");
        s.enqueue("C");

        String[] arr1 = {null, "B", "C", null, null, null, null, null, null};
        String[] arr2 = {null, null, "C", null, null, null, null, null, null};
        String[] arr3 = {null, null, null, null, null, null, null, null, null};

        assertEquals("A", s.dequeue());
        assertEquals(2, s.size());
        assertArrayEquals(s.getBackingArray(), arr1);

        assertEquals("B", s.dequeue());
        assertEquals(1, s.size());
        assertArrayEquals(s.getBackingArray(), arr2);

        assertEquals("C", s.dequeue());
        assertEquals(0, s.size());
        assertArrayEquals(s.getBackingArray(), arr3);

        s.enqueue("A");
        s.enqueue("B");
        s.enqueue("C");
        s.enqueue("D");
        s.enqueue("E");
        s.enqueue("F");
        s.enqueue("G");
        s.enqueue("H");
        s.enqueue("I");

        assertEquals("A", s.dequeue());
        assertEquals("B", s.dequeue());

        String[] arr4 = {null, null, "C", "D", "E", "F", "G", "H", "I"};
        assertArrayEquals(s.getBackingArray(), arr4);

        s.enqueue("J");

        String[] arr5 = {"J", null, "C", "D", "E", "F", "G", "H", "I"};
        assertArrayEquals(s.getBackingArray(), arr5);
        assertEquals(8, s.size());

        assertEquals("C", s.dequeue());
        assertEquals(7, s.size());

        String[] arr6 = {"J", null, null, "D", "E", "F", "G", "H", "I"};
        assertArrayEquals(s.getBackingArray(), arr6);

        s.enqueue("K");
        s.enqueue("L");

        String[] arr7 = {"J", "K", "L", "D", "E", "F", "G", "H", "I"};
        assertArrayEquals(s.getBackingArray(), arr7);
        assertEquals(9, s.size());

        s.enqueue("M");
        String[] arr8 = {
                "D", "E", "F", "G", "H", "I", "J","K", "L", "M",
                null, null, null, null, null, null, null, null
        };
        assertArrayEquals(s.getBackingArray(), arr8);

        s.enqueue("O");
        String[] arr9 = {
                "D", "E", "F", "G", "H", "I", "J","K", "L", "M",
                "O", null, null, null, null, null, null, null
        };
        assertArrayEquals(s.getBackingArray(), arr9);

        assertEquals("D", s.dequeue());
        assertEquals("E", s.dequeue());

        String[] arr10 = {
                null, null, "F", "G", "H", "I", "J","K", "L", "M",
                "O", null, null, null, null, null, null, null
        };
        assertArrayEquals(s.getBackingArray(), arr10);

    }

    @Test (expected = NoSuchElementException.class)
    public void ArrayQueueDequeueExceptionTest() {
        s.dequeue();
    }

    @Test
    public void ArrayQueuePeekTest() {
        assertEquals(null, s.peek());
        s.enqueue("A");
        s.enqueue("B");
        s.enqueue("C");
        assertEquals("A", s.peek());
        assertEquals(3, s.size());


        assertEquals("A", s.dequeue());
        assertEquals(2, s.size());
        assertEquals("B", s.dequeue());
        assertEquals("C", s.peek());
        assertEquals(1, s.size());
        assertEquals("C", s.dequeue());
        assertEquals(0, s.size());
        assertEquals(null, s.peek());

        s.enqueue("A");
        s.enqueue("B");
        s.enqueue("C");
        s.enqueue("D");
        s.enqueue("E");
        s.enqueue("F");
        s.enqueue("G");
        s.enqueue("H");
        s.enqueue("I");
        s.enqueue("J");
        assertEquals("A", s.peek());
        assertEquals("A", s.dequeue());
        assertEquals(9, s.size());
        assertEquals("B", s.peek());
    }

    //////////////////////
    // ArrayStack Tests //
    //////////////////////

    @Test
    public void ArrayStackConstructorTest() {
        assertEquals(0, a.size());
        assertEquals(a.INITIAL_CAPACITY, a.getBackingArray().length);

    }

    @Test
    public void ArrayStackPushTest() {
        a.push("A");
        assertEquals(1, a.size());
        a.push("B");
        assertEquals(2, a.size());
        a.push("C");
        assertEquals(3, a.size());
        String[] arr = {"A", "B", "C", null, null, null, null, null, null};
        assertArrayEquals(a.getBackingArray(), arr);
        a.push("D");
        a.push("E");
        a.push("F");
        a.push("G");
        a.push("H");
        a.push("I");
        assertEquals(9, a.size());

        a.push("J");
        String[] arr2 = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                null, null, null, null, null, null, null, null};
        assertArrayEquals(a.getBackingArray(), arr2);
        assertEquals(10, a.size());
        assertEquals(18, a.getBackingArray().length);
    }

    @Test (expected = IllegalArgumentException.class)
    public void ArrayStackPushExceptionTest() {
        a.push(null);
    }
    @Test
    public void ArrayStackPopTest() {
        a.push("A");
        a.push("B");
        a.push("C");

        String[] arr1 = {"A", "B", "C", null, null, null, null, null, null};
        String[] arr2 = {"A", "B", null, null, null, null, null, null, null};
        String[] arr3 = {"A", null, null, null, null, null, null, null, null};
        String[] arr4 = {null, null, null, null, null, null, null, null, null};

        assertArrayEquals(a.getBackingArray(), arr1);

        assertEquals("C", a.pop());
        assertEquals(2, a.size());
        assertArrayEquals(a.getBackingArray(), arr2);

        assertEquals("B", a.pop());
        assertEquals(1, a.size());
        assertArrayEquals(a.getBackingArray(), arr3);

        assertEquals("A", a.pop());
        assertEquals(0, a.size());
        assertArrayEquals(a.getBackingArray(), arr4);

        a.push("A");
        a.push("B");
        a.push("C");
        a.push("D");
        a.push("E");
        a.push("F");
        a.push("G");
        a.push("H");
        a.push("I");

        String[] arr5 = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        String[] arr6 = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                null, null, null, null, null, null, null, null
        };

        assertArrayEquals(a.getBackingArray(), arr5);

        a.push("J");

        assertArrayEquals(a.getBackingArray(), arr6);

        assertEquals("J", a.pop());
        assertEquals(9, a.size());
        assertEquals("I", a.pop());
        assertEquals("H", a.pop());
        assertEquals("G", a.pop());
        assertEquals("F", a.pop());
        assertEquals("E", a.pop());
        assertEquals("D", a.pop());
        assertEquals("C", a.pop());
        assertEquals("B", a.pop());
        assertEquals("A", a.pop());


    }

    @Test (expected = NoSuchElementException.class)
    public void ArrayStackPopExceptionTest() {
        a.pop();
    }

    @Test
    public void ArrayStackPeekTest() {
        assertEquals(null, a.peek());
        a.push("A");
        a.push("B");
        a.push("C");
        assertEquals("C", a.peek());
        assertEquals(3, a.size());


        assertEquals("C", a.pop());
        assertEquals(2, a.size());
        assertEquals("B", a.pop());
        assertEquals("A", a.peek());
        assertEquals(1, a.size());
        assertEquals("A", a.pop());
        assertEquals(0, a.size());
        assertEquals(0, a.size());
        assertEquals(null, a.peek());

        a.push("A");
        a.push("B");
        a.push("C");
        a.push("D");
        a.push("E");
        a.push("F");
        a.push("G");
        a.push("H");
        a.push("I");
        a.push("J");
        assertEquals("J", a.peek());
        assertEquals("J", a.pop());
        assertEquals(9, a.size());
        assertEquals("I", a.peek());
    }

    ///////////////////////
    // Linked Queue Tests /
    ///////////////////////

    @Test
    public void LinkedQueueConstructorTest() {
        assertEquals(0, p.size());

    }

    @Test
    public void LinkedQueueEnqueueTest() {
        p.enqueue("A");
        assertEquals(1, p.size());
        p.enqueue("B");
        assertEquals(2, p.size());
        p.enqueue("C");
        assertEquals(3, p.size());
        p.enqueue("D");
        p.enqueue("E");
        p.enqueue("F");
        p.enqueue("G");
        p.enqueue("H");
        p.enqueue("I");
        assertEquals(9, p.size());

        p.enqueue("J");

        assertEquals("J", p.getTail().getData());
        assertEquals(10, p.size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void LinkedQueueEnqueueExceptionTest() {
        p.enqueue(null);
    }
    @Test
    public void LinkedQueueDequeueTest() {
        p.enqueue("A");
        p.enqueue("B");
        p.enqueue("C");

        assertEquals("A", p.dequeue());
        assertEquals(2, p.size());

        assertEquals("B", p.dequeue());
        assertEquals(1, p.size());

        assertEquals("C", p.dequeue());
        assertEquals(0, p.size());

        p.enqueue("A");
        p.enqueue("B");
        p.enqueue("C");
        p.enqueue("D");
        p.enqueue("E");
        p.enqueue("F");
        p.enqueue("G");
        p.enqueue("H");
        p.enqueue("I");

        assertEquals("A", p.dequeue());
        assertEquals("B", p.dequeue());
        assertEquals("I", p.getTail().getData());


        p.enqueue("J");
        assertEquals("J", p.getTail().getData());

        assertEquals(8, p.size());

        assertEquals("C", p.dequeue());
        assertEquals(7, p.size());


        p.enqueue("K");
        p.enqueue("L");

        assertEquals(9, p.size());

        p.enqueue("M");


    }

    @Test (expected = NoSuchElementException.class)
    public void LinkedQueueDequeueExceptionTest() {
        p.dequeue();
    }

    @Test
    public void LinkedQueuePeekTest() {
        assertEquals(null, p.peek());
        p.enqueue("A");
        p.enqueue("B");
        p.enqueue("C");
        assertEquals("A", p.peek());
        assertEquals(3, p.size());


        assertEquals("A", p.dequeue());
        assertEquals(2, p.size());
        assertEquals("B", p.dequeue());
        assertEquals("C", p.peek());
        assertEquals(1, p.size());
        assertEquals("C", p.dequeue());
        assertEquals(0, p.size());
        assertEquals(null, p.peek());

        p.enqueue("A");
        p.enqueue("B");
        p.enqueue("C");
        p.enqueue("D");
        p.enqueue("E");
        p.enqueue("F");
        p.enqueue("G");
        p.enqueue("H");
        p.enqueue("I");
        p.enqueue("J");
        assertEquals("A", p.peek());
        assertEquals("A", p.dequeue());
        assertEquals(9, p.size());
        assertEquals("B", p.peek());
    }

    //////////////////////
    // LinkedStack Tests /
    //////////////////////

    @Test
    public void LinkedStackConstructorTest() {
        assertEquals(0, r.size());

    }

    @Test
    public void LinkedStackPushTest() {
        r.push("A");
        assertEquals(1, r.size());
        r.push("B");
        assertEquals(2, r.size());
        r.push("C");
        assertEquals(3, r.size());
        String[] arr = {"A", "B", "C", null, null, null, null, null, null};
        //assertArrayEquals(r.getBackingArray(), arr);
        r.push("D");
        r.push("E");
        r.push("F");
        r.push("G");
        r.push("H");
        r.push("I");
        assertEquals(9, r.size());

        r.push("J");
        String[] arr2 = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                null, null, null, null, null, null, null, null};
        //assertArrayEquals(r.getBackingArray(), arr2);
        assertEquals(10, r.size());
        //assertEquals(18, r.getBackingArray().length);
    }

    @Test (expected = IllegalArgumentException.class)
    public void LinkedStackPushExceptionTest() {
        r.push(null);
    }
    @Test
    public void LinkedStackPopTest() {
        r.push("A");
        r.push("B");
        r.push("C");
        assertEquals("C", r.pop());
        assertEquals(2, r.size());
        assertEquals("B", r.pop());
        assertEquals(1, r.size());
        assertEquals("A", r.pop());
        assertEquals(0, r.size());
        assertEquals(0, r.size());
        r.push("A");
        r.push("B");
        r.push("C");
        r.push("D");
        r.push("E");
        r.push("F");
        r.push("G");
        r.push("H");
        r.push("I");
        r.push("J");
        assertEquals("J", r.pop());
        assertEquals(9, r.size());

    }

    @Test (expected = NoSuchElementException.class)
    public void LinkedStackPopExceptionTest() {
        r.pop();
    }

    @Test
    public void LinkedStackPeekTest() {
        assertEquals(null, r.peek());
        r.push("A");
        r.push("B");
        r.push("C");
        assertEquals("C", r.peek());
        assertEquals(3, r.size());


        assertEquals("C", r.pop());
        assertEquals(2, r.size());
        assertEquals("B", r.pop());
        assertEquals("A", r.peek());
        assertEquals(1, r.size());
        assertEquals("A", r.pop());
        assertEquals(0, r.size());
        assertEquals(0, r.size());
        assertEquals(null, r.peek());

        r.push("A");
        r.push("B");
        r.push("C");
        r.push("D");
        r.push("E");
        r.push("F");
        r.push("G");
        r.push("H");
        r.push("I");
        r.push("J");
        assertEquals("J", r.peek());
        assertEquals("J", r.pop());
        assertEquals(9, r.size());
        assertEquals("I", r.peek());
    }

    //////////////////////
    // TA - Made Tests  //
    //////////////////////

    private ArrayStack<Integer> arrayStack;
    private ArrayQueue<Integer> arrayQueue;
    private LinkedStack<Integer> linkedStack;
    private LinkedQueue<Integer> linkedQueue;

    public static final int TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void testArrayStackPush() {
        arrayStack = new ArrayStack<>();
        assertEquals(0, arrayStack.size());

        // [34, 29, 48, 59, _, _, _, _, _]
        arrayStack.push(34);
        arrayStack.push(29);
        arrayStack.push(48);
        arrayStack.push(59);

        assertEquals(4, arrayStack.size());

        Object[] backingArray = arrayStack.getBackingArray();

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPop() {
        arrayStack = new ArrayStack<>();
        assertEquals(0, arrayStack.size());

        // [34, 29, 48, 59, _, _, _, _, _]
        arrayStack.push(34);
        arrayStack.push(29);
        arrayStack.push(48);
        arrayStack.push(59);

        // [34, 29, 48, _, _, _, _, _, _]
        assertEquals((Integer) 59, arrayStack.pop());

        assertEquals(3, arrayStack.size());

        Object[] backingArray = arrayStack.getBackingArray();

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPeek() {
        arrayStack = new ArrayStack<>();
        assertEquals(0, arrayStack.size());

        // [34, 29, 48, 59, _, _, _, _, _]
        arrayStack.push(34);
        arrayStack.push(29);
        arrayStack.push(48);
        arrayStack.push(59);

        assertEquals((Integer) 59, arrayStack.peek());
        assertEquals(4, arrayStack.size());
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
        assertEquals(0, linkedStack.size());

        // 59 -> 48 -> 29 -> 34
        linkedStack.push(34);
        linkedStack.push(29);
        linkedStack.push(48);
        linkedStack.push(59);

        assertEquals((Integer) 59, linkedStack.peek());
        assertEquals(4, linkedStack.size());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueEnqueue() {
        arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());

        // [34, 29, 38, 59, _, _, _, _, _]
        arrayQueue.enqueue(34);
        arrayQueue.enqueue(29);
        arrayQueue.enqueue(48);
        arrayQueue.enqueue(59);

        assertEquals(4, arrayQueue.size());

        Object[] backingArray = arrayQueue.getBackingArray();

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueDequeue() {
        arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());

        // [34, 29, 38, 59, _, _, _, _, _]
        arrayQueue.enqueue(34);
        arrayQueue.enqueue(29);
        arrayQueue.enqueue(48);
        arrayQueue.enqueue(59);

        // [_, 29, 38, 59, _, _, _, _, _]
        assertEquals((Integer) 34, arrayQueue.dequeue());

        assertEquals(3, arrayQueue.size());

        Object[] backingArray = arrayQueue.getBackingArray();

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueuePeek() {
        arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());

        // [34, 29, 38, 59, _, _, _, _, _]
        arrayQueue.enqueue(34);
        arrayQueue.enqueue(29);
        arrayQueue.enqueue(48);
        arrayQueue.enqueue(59);

        assertEquals((Integer) 34, arrayQueue.peek());
        assertEquals(4, arrayQueue.size());
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

        // 34 -> 29 -> 48 -> 59
        linkedQueue.enqueue(34);
        linkedQueue.enqueue(29);
        linkedQueue.enqueue(48);
        linkedQueue.enqueue(59);

        assertEquals((Integer) 34, linkedQueue.peek());
        assertEquals(4, linkedQueue.size());
    }

}
