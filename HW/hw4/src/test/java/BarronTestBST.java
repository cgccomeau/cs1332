import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Sample JUnit test cases for BST.
 *
 * @author Emma Barron
 * @userid ebarron6
 * @GTID 903375456
 * @version 1.0
 */
public class BarronTestBST {
    private BST<Integer> bst;
    public static final int TIMEOUT = 200;

    @Before
    public void setup() {
        bst = new BST<Integer>();
    }

    // tests the BST constructor we implemented with (Collections<T>)
    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testConstructor() {
        // test with null
        BST bst3 = new BST(null);
        // test with actual values
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(13);
        arr.add(10);
        arr.add(17);
        arr.add(0);
        arr.add(12);
        arr.add(11);
        arr.add(14);
        // <13, 10, 17, 0, 12, 11, 14>

        BST bst2 = new BST(arr);
        /*
                 13
               /    \
              10     17
             /  \    /
            0   12  14
                /
               11
         */
        assertEquals(13, bst2.getRoot().getData());
        assertEquals(10, bst2.getRoot().getLeft().getData());
        assertEquals(0, bst2.getRoot().getLeft().getLeft().getData());
        assertEquals(12, bst2.getRoot().getLeft().getRight().getData());
        assertEquals(11, bst2.getRoot().getLeft().getRight().getLeft().getData());
        assertEquals(17, bst2.getRoot().getRight().getData());
        assertEquals(14, bst2.getRoot().getRight().getLeft().getData());

        assertNull(bst2.getRoot().getRight().getRight());

        assertEquals(7, bst2.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAdd() {
        bst.add(null);
        /*
                      2
                     / \
                    1   3
                         \
                         100
                        /   \
                       12   1000
        */
        bst.add(2);
        bst.add(1);
        bst.add(3);
        assertEquals(3, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 3, bst.getRoot().getRight().getData());

        bst.add(100);
        bst.add(1000);
        bst.add(12);
        assertEquals(6, bst.size());
        assertEquals((Integer) 100,
                bst.getRoot().getRight().getRight().getData());
        assertEquals((Integer) 12,
                bst.getRoot().getRight().getRight().getLeft().getData());
        assertEquals((Integer) 1000,
                bst.getRoot().getRight().getRight().getRight().getData());

        // try adding duplicate
        bst.add(100);
        assertEquals(6, bst.size());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemove() {
        // remove data that doesn't exist
        // throws no such element
        bst.remove(-5);

        /*
                      2
                     / \
                   -1   3
                   / \   \
                -10   0   4
                       \   \
                        1   5
        */
        // ensure the data from the tree is returned
        Integer testingInt1 = new Integer(5);
        Integer testingInt2 = new Integer(3);
        Integer testingInt3 = new Integer(2);
        Integer testingInt4 = new Integer(1);

        bst.add(testingInt2);
        bst.add(-1);
        bst.add(testingInt3);
        bst.add(4);
        bst.add(testingInt1);
        bst.add(-10);
        bst.add(0);
        bst.add(testingInt4);
        assertEquals(8, bst.size());

        // check remove with no children
        /*
                      2                    2
                     / \                  / \
                   -1   3               -1   3
                   / \   \     ->       / \   \
                -10   0   4           -10  0    4
                       \   \                \
                        1   5                1
        */
        assertSame(testingInt1, bst.remove(5));
        assertEquals(7, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) (-1), bst.getRoot().getLeft().getData());
        assertEquals((Integer) 3, bst.getRoot().getRight().getData());
        assertEquals((Integer) (-10), bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 0,
                bst.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 1,
                bst.getRoot().getLeft().getRight().getRight().getData());
        assertEquals((Integer) 4, bst.getRoot().getRight().getRight().getData());

        // check remove with single child
        /*
                      2                    2
                     / \                  / \
                   -1   3               -1   4
                   / \   \     ->       / \
                -10   0   4           -10  0
                       \                    \
                        1                    1
        */
        assertSame(testingInt2, bst.remove(3));
        assertEquals(6, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) (-1), bst.getRoot().getLeft().getData());
        assertEquals((Integer) 4, bst.getRoot().getRight().getData());
        assertEquals((Integer) (-10), bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 0,
                bst.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 1,
                bst.getRoot().getLeft().getRight().getRight().getData());

        // remove with two children
        /*
                      2                    1
                     / \                  / \
                   -1   4               -1   4
                   / \         ->       / \
                -10   0               -10  0
                       \
                        1
        */
        assertSame(testingInt3, bst.remove(2));
        assertEquals(5, bst.size());
        assertSame(testingInt4, bst.getRoot().getData());
        assertEquals((Integer) (-1), bst.getRoot().getLeft().getData());
        assertEquals((Integer) 4, bst.getRoot().getRight().getData());
        assertEquals((Integer) (-10), bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 0,
                bst.getRoot().getLeft().getRight().getData());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testNullRemove() {
        // try removing null from normal tree
        bst.add(1);
        bst.add(2);
        bst.add(0);
        bst.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveFromEmptyTree() {
        bst.remove(1);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetContains() {
        // try to get a null object from a normal bst
        bst.add(1);
        bst.add(2);
        bst.add(0);
        bst.get(null);
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
        */
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);

        assertFalse(bst.contains(-10));
        assertFalse(bst.contains(50));

        assertTrue(bst.contains(58));
        assertEquals((Integer) 58, bst.get(58));
        assertEquals((Integer) 58, bst.remove(58));
        assertFalse(bst.contains(58));

        assertTrue(bst.contains(12));
        assertEquals((Integer) 12, bst.get(12));
        assertTrue(bst.contains(94));
        assertEquals((Integer) 94, bst.get(94));
        assertTrue(bst.contains(24));
        assertEquals((Integer) 24, bst.get(24));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testNullContains() {
        bst.add(1);
        bst.add(2);
        bst.add(0);
        bst.contains(null);

        // test contains with empty tree
        bst.clear();
        assertFalse(bst.contains(0));
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetDifferent() {
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
        */

        Integer testingInteger1 = new Integer(12);
        Integer testingInteger2 = new Integer(58);
        Integer testingInteger3 = new Integer(24);

        bst.add(testingInteger3);
        bst.add(1);
        bst.add(7);
        bst.add(testingInteger1);
        bst.add(94);
        bst.add(testingInteger2);
        bst.add(73);

        // We want to make sure the data we retrieve is the one from the tree,
        // not the data that was passed in.
        assertSame(testingInteger1, bst.get(new Integer(12)));
        assertSame(testingInteger2, bst.get(58));
        assertSame(testingInteger3, bst.get(24));

        // test when element is not found in real tree
        bst.get(8);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetFromEmptyTree() {
        bst.get(4);
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder() {
        // test if root == null
        assertEquals(new ArrayList<Integer>(), bst.levelorder());

        // test with full tree
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
                            / \
                           68  77
        */

        List<Integer> levelorder = new ArrayList<>();
        levelorder.add(24);
        levelorder.add(1);
        levelorder.add(94);
        levelorder.add(7);
        levelorder.add(58);
        levelorder.add(12);
        levelorder.add(73);
        levelorder.add(68);
        levelorder.add(77);

        bst = new BST(levelorder);

        // Should be [24, 1, 94, 7, 58, 12, 73, 68, 77]
        assertEquals(levelorder, bst.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPreorder() {
        // test if root == null
        assertEquals(new ArrayList<Integer>(), bst.levelorder());
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
                            / \
                           68  77
        */

        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> preorder = new ArrayList<>();
        preorder.add(24);
        preorder.add(1);
        preorder.add(7);
        preorder.add(12);
        preorder.add(94);
        preorder.add(58);
        preorder.add(73);
        preorder.add(68);
        preorder.add(77);

        // Should be [24, 1, 7, 12, 94, 58, 73, 68, 77]
        assertEquals(preorder, bst.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInorder() {
        // test if root == null
        assertEquals(new ArrayList<Integer>(), bst.levelorder());
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
                            / \
                           68  77
        */

        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> inorder = new ArrayList<>();
        inorder.add(1);
        inorder.add(7);
        inorder.add(12);
        inorder.add(24);
        inorder.add(58);
        inorder.add(68);
        inorder.add(73);
        inorder.add(77);
        inorder.add(94);

        // Should be [1, 7, 12, 24, 58, 68, 73, 77, 94]
        assertEquals(inorder, bst.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorder() {
        // test if root == null
        assertEquals(new ArrayList<Integer>(), bst.levelorder());
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
                            / \
                           68  77
        */

        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> postorder = new ArrayList<>();
        postorder.add(12);
        postorder.add(7);
        postorder.add(1);
        postorder.add(68);
        postorder.add(77);
        postorder.add(73);
        postorder.add(58);
        postorder.add(94);
        postorder.add(24);

        // Should be [12, 7, 1, 68, 77, 73, 58, 94, 24]
        assertEquals(postorder, bst.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testBasicIsBST() {
        /*
                    50
                  /    \   should return false
                75      25
        */

        BSTNode<Integer> root = new BSTNode<>(50);
        root.setLeft(new BSTNode<>(75));
        root.setRight(new BSTNode<>(25));

        assertEquals(false, BST.isBST(root));

        /*
                    50
                  /    \   should return true
                25      75
        */

        root = new BSTNode<>(50);
        root.setLeft(new BSTNode<>(25));
        root.setRight(new BSTNode<>(75));

        assertEquals(true, BST.isBST(root));
    }

    @Test(timeout = TIMEOUT)
    public void testIsBST() {
        // test with empty BST
        assertTrue(BST.isBST(bst.getRoot()));

        // test with one node (only root)
        bst.add(10);
        assertTrue(BST.isBST(bst.getRoot()));
        // test with 2 roots
        bst.add(12);
        assertTrue(BST.isBST(bst.getRoot()));
        // test with 3 roots, leftmost incorrect
        bst.getRoot().setLeft(new BSTNode<>(20));
        assertFalse(BST.isBST(bst.getRoot()));

        bst.clear();
        /*
                      2
                     / \
                   -1   3
                   / \   \
                -10   0   4
                       \   \
                        1   5
        */
        bst.add(2);
        bst.add(-1);
        bst.add(3);
        bst.add(4);
        bst.add(5);
        bst.add(-10);
        bst.add(0);
        bst.add(1);
        assertEquals(8, bst.size());

        assertTrue(BST.isBST(bst.getRoot()));

        // test hardest case
        bst.getRoot().getLeft().getRight().setLeft(new BSTNode<>(-9));
        /*
                      2
                     / \
                   -1   3
                   / \   \
                -10   0   4
                     / \   \
                    -9  1   5
        */
        assertFalse(BST.isBST(bst.getRoot().getLeft()));
        assertTrue(BST.isBST(bst.getRoot().getLeft().getRight()));
        assertTrue(BST.isBST(bst.getRoot().getRight()));
        assertFalse(BST.isBST(bst.getRoot()));
    }

    @Test(timeout = TIMEOUT)
    public void testConstructorAndClear() {
        /*
                     24
                    /
                   1
                    \
                     7
        */

        List<Integer> toAdd = new ArrayList<>();
        toAdd.add(24);
        toAdd.add(1);
        toAdd.add(7);
        bst = new BST<>(toAdd);
        assertEquals(3, bst.size());

        assertEquals((Integer) 24, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 7, bst.getRoot().getLeft().getRight().getData());

        bst.clear();
        assertEquals(null, bst.getRoot());
        assertEquals(0, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        assertEquals(-1, bst.height());
        /*
                     24
                    /
                   1
                    \
                     7
        */

        bst.add(24);
        assertEquals(0, bst.height());
        bst.add(1);
        assertEquals(1, bst.height());
        bst.add(7);
        assertEquals(3, bst.size());

        assertEquals(2, bst.height());
        /*
                     24
                    / \
                   1   36
                  / \  / \
                  0  7 32 64
                          /
                         48
        */
        bst.add(36);
        bst.add(64);
        bst.add(0);
        bst.add(48);
        bst.add(32);
        assertEquals(3, bst.height());
    }
}
