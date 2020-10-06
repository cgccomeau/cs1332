import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Charlie Comeau
 * @userid ccomeau7
 * @GTID 903359699
 * @version 1.0
 * @param <T> for generic implementation of AVLs
 */
public class AVL<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it appears in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data into "
                    + "data structure!");
        }
        for (T t: data) {
            if (t == null) {
                throw new IllegalArgumentException("Cannot insert null data "
                        + "into data structure!");
            }
            add(t);
        }
    }

    /**
     * Adds the data to the AVL. Start by adding it as a leaf like in a regular
     * BST and then rotate the tree as needed.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors going up the tree,
     * rebalancing if necessary.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("cannot insert null data into "
                    + "data structure!");
        }
        root = add(data, root);
    }

    /**
     * recursive private helper method for adding
     * @param data the data to be added
     * @param curr the current node the method is on
     * @return the most recently accessed node
     */
    private AVLNode<T> add(T data, AVLNode<T> curr) {
        if (curr == null) {
            size++;
            curr = new AVLNode<>(data);
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(add(data, curr.getRight()));
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(add(data, curr.getLeft()));
        }
        updateNode(curr);
        return checkRotations(curr);
    }

    /**
     * private helper method that checks and executes AVL self-balancing
     * operations as needed
     * @param curr the current node the method is on
     * @return the most recently accessed node
     */
    private AVLNode<T> checkRotations(AVLNode<T> curr) {
        if (curr == null) {
            return curr;
        }
        if (curr.getBalanceFactor() < -1) {
            if (curr.getRight().getBalanceFactor() == 1) {
                return rotateRightLeft(curr);
            } else {
                return rotateLeft(curr);
            }
        } else if (curr.getBalanceFactor() > 1) {
            if (curr.getLeft().getBalanceFactor() == -1) {
                return rotateLeftRight(curr);
            } else {
                return rotateRight(curr);
            }
        }
        return curr;
    }

    /**
     * private helper method that executes a Left-Right rotation on node a
     * @param a the node that is to experience a LR rotation
     * @return the new root of the rotated subtree
     */
    private AVLNode<T> rotateLeftRight(AVLNode<T> a) {
        AVLNode<T> b = a.getLeft();
        AVLNode<T> c = b.getRight();
        a.setLeft(c);
        b.setRight(c.getLeft());
        c.setLeft(b);
        updateNode(b);
        updateNode(c);
        return rotateRight(a);
    }

    /**
     * private helper method that executes a Right-Left Rotation on node a
     * @param a the node that is to experience a RL rotation
     * @return the new root of the rotated subtree
     */
    private AVLNode<T> rotateRightLeft(AVLNode<T> a) {
        AVLNode<T> b = a.getRight();
        AVLNode<T> c = b.getLeft();
        a.setRight(c);
        b.setLeft(c.getRight());
        c.setRight(b);
        updateNode(b);
        updateNode(c);
        return rotateLeft(a);
    }

    /**
     * private helper method that executes a Left Rotation on node a
     * @param a the node that is to experience a L rotation
     * @return the new root of the rotated subtree
     */
    private AVLNode<T> rotateLeft(AVLNode<T> a) {
        AVLNode<T> b = a.getRight();
        a.setRight(b.getLeft());
        b.setLeft(a);
        updateNode(a);
        updateNode(b);
        return b;
    }

    /**
     * private helper method that executes a Right Rotation on node a
     * @param a the node that is to experience a R rotation
     * @return the new root of the rotated subtree
     */
    private AVLNode<T> rotateRight(AVLNode<T> a) {
        AVLNode<T> b = a.getLeft();
        a.setLeft(b.getRight());
        b.setRight(a);
        updateNode(a);
        updateNode(b);
        return b;
    }

    /**
     * private helper method that updates a Node's BF and Height after rotation
     * @param curr the current node to be updated
     */
    private void updateNode(AVLNode<T> curr) {
        int leftHeight = checkHeight(curr.getLeft());
        int rightHeight = checkHeight(curr.getRight());
        curr.setHeight(Math.max(leftHeight, rightHeight) + 1);
        calcBF(curr);
    }

    /**
     * private helper method that checks whether a node is null or not for
     * checking that node's height
     * @param curr the current node to be checked
     * @return node's height
     */
    private int checkHeight(AVLNode<T> curr) {
        if (curr == null) {
            return -1;
        } else {
            return curr.getHeight();
        }
    }

    /**
     * private helper method that calculates and updates a node's
     * Balance Factor
     * @param curr the current node to be updated
     */
    private void calcBF(AVLNode<T> curr) {
        if (curr == null) {
            return;
        }
        int leftHeight = checkHeight(curr.getLeft());
        int rightHeight = checkHeight(curr.getRight());
        int newBF = leftHeight - rightHeight;
        curr.setBalanceFactor(newBF);
    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf. In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the successor to replace the data,
     * not the predecessor. As a reminder, rotations can occur after removing
     * the successor node.
     *
     * Remember to recalculate heights going up the tree, rebalancing if
     * necessary.
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("AVLs cannot have null data!");
        }
        AVLNode<T> dummyNode = new AVLNode<>(null);
        root = remove(root, data, dummyNode);
        size--;
        return dummyNode.getData();
    }

    /**
     * private recursive helper method to help in removing
     * @param curr the current node the method is on
     * @param data the data of the desired node to be removed
     * @param dummyNode dummy node to capture the desired node's data
     * @return most recently accessed node
     */
    private AVLNode<T> remove(AVLNode<T> curr, T data, AVLNode<T> dummyNode) {
        if (curr == null) {
            throw new NoSuchElementException("AVL does not contain this data!");
        }

        if (data.compareTo(curr.getData()) == 0) {
            dummyNode.setData(curr.getData());
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else if (curr.getLeft() != null && curr.getRight() == null) {
                return curr.getLeft();
            } else if (curr.getLeft() == null && curr.getRight() != null) {
                return curr.getRight();
            } else {
                AVLNode<T> tempNode = new AVLNode<>(null);
                curr.setRight(successor(curr.getRight(), tempNode));
                curr.setData(tempNode.getData());
            }
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(remove(curr.getLeft(), data, dummyNode));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(remove(curr.getRight(), data, dummyNode));
        }
        updateNode(curr);
        return checkRotations(curr);
    }

    /**
     * private recursive helper node for finding a node's successor for removing
     * @param curr the current node the method is on
     * @param tempNode temporary node for capturing the successor node's data
     * @return the most recently accessed node
     */
    private AVLNode<T> successor(AVLNode<T> curr, AVLNode<T> tempNode) {
        AVLNode<T> result = curr;
        if (curr.getLeft() == null) {
            tempNode.setData(curr.getData());
            result = curr.getRight();
        } else {
            curr.setLeft(successor(curr.getLeft(), tempNode));
        }
        updateNode(curr);
        return checkRotations(result);
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot get null data from "
                    + "data structure!");
        }
        return get(root, data).getData();
    }

    /**
     * private recursive helper method for get
     * @param curr the current node the method is on
     * @param data data of the node to be desired
     * @return desired node
     */
    private AVLNode<T> get(AVLNode<T> curr, T data) {
        if (curr == null) {
            throw new java.util.NoSuchElementException("AVL tree does not "
                    + "contain this data!");
        }
        if (curr.getData().compareTo(data) < 0) {
            return get(curr.getRight(), data);
        } else if (curr.getData().compareTo(data) > 0) {
            return get(curr.getLeft(), data);
        } else {
            return curr;
        }
    }

    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("AVLs cannot contain "
                    + "null data!");
        }

        try {
            get(root, data);
        } catch (java.util.NoSuchElementException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns the data on branches of the tree with the maximum depth. If you
     * encounter multiple branches of maximum depth while traversing, then you
     * should list the remaining data from the left branch first, then the
     * remaining data in the right branch. This is essentially a preorder
     * traversal of the tree, but only of the branches of maximum depth.
     *
     * Your list should not duplicate data, and the data of a branch should be
     * listed in order going from the root to the leaf of that branch.
     *
     * Should run in worst case O(n), but you should not explore branches that
     * do not have maximum depth. You should also not need to traverse branches
     * more than once.
     *
     * Hint: How can you take advantage of the balancing information stored in
     * AVL nodes to discern deep branches?
     *
     * Example Tree:
     *                           10
     *                       /        \
     *                      5          15
     *                    /   \      /    \
     *                   2     7    13    20
     *                  / \   / \     \  / \
     *                 1   4 6   8   14 17  25
     *                /           \          \
     *               0             9         30
     *
     * Returns: [10, 5, 2, 1, 0, 7, 8, 9, 15, 20, 25, 30]
     *
     * @return the list of data in branches of maximum depth in preorder
     * traversal order
     */
    public List<T> deepestBranches() {
        List<T> result = new ArrayList<>();
        deepestBranchesHelper(root, result);
        return result;
    }

    /**
     * private recursive helper method for deepestBranches
     * @param curr the current node the method is on
     * @param result the List that stores all desired data
     */
    private void deepestBranchesHelper(AVLNode<T> curr, List<T> result) {
        if (curr == null) {
            return;
        }
        int leftHeight = checkHeight(curr.getLeft());
        int rightHeight = checkHeight(curr.getRight());
        int childrenHeightDifference = leftHeight - rightHeight;
        if (childrenHeightDifference > 0) {
            result.add(curr.getData());
            deepestBranchesHelper(curr.getLeft(), result);
        } else if (childrenHeightDifference == 0) {
            result.add(curr.getData());
            deepestBranchesHelper(curr.getLeft(), result);
            deepestBranchesHelper(curr.getRight(), result);
        } else {
            result.add(curr.getData());
            deepestBranchesHelper(curr.getRight(), result);
        }
    }

    /**
     * Returns a sorted list of data that are within the threshold bounds of
     * data1 and data2. That is, the data should be > data1 and < data2.
     *
     * Should run in worst case O(n), but this is heavily dependent on the
     * threshold data. You should not explore branches of the tree that do not
     * satisfy the threshold.
     *
     * Example Tree:
     *                           10
     *                       /        \
     *                      5          15
     *                    /   \      /    \
     *                   2     7    13    20
     *                  / \   / \     \  / \
     *                 1   4 6   8   14 17  25
     *                /           \          \
     *               0             9         30
     *
     * sortedInBetween(7, 14) returns [8, 9, 10, 13]
     * sortedInBetween(3, 8) returns [4, 5, 6, 7]
     * sortedInBetween(8, 8) returns []
     *
     * @param data1 the smaller data in the threshold
     * @param data2 the larger data in the threshold
     * @throws java.lang.IllegalArgumentException if data1 or data2 are null
     * or if data1 > data2
     * @return a sorted list of data that is > data1 and < data2
     */
    public List<T> sortedInBetween(T data1, T data2) {
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("Cannot search for null data "
                    + "in data structure!");
        }
        if (data1.compareTo(data2) > 0) {
            throw new IllegalArgumentException("parameter data is in "
                    + "the wrong order!");
        }

        List<T> result = new ArrayList<>();
        sortedInBetweenHelper(data1, data2, root, result);
        return result;
    }

    /**
     * private recursive helper method for sortedInBetween
     * @param data1 lower bound of data threshold
     * @param data2 upper bound of data threshold
     * @param curr current node that the method is on
     * @param result List that stores all desired data
     */
    private void sortedInBetweenHelper(T data1, T data2,
                                       AVLNode<T> curr, List<T> result) {
        if (curr == null) {
            return;
        }
        if (curr.getData().compareTo(data1) > 0
                && curr.getData().compareTo(data2) < 0) {
            sortedInBetweenHelper(data1, data2, curr.getLeft(), result);
            result.add(curr.getData());
            sortedInBetweenHelper(data1, data2, curr.getRight(), result);
        } else if (curr.getData().compareTo(data1) <= 0) {
            sortedInBetweenHelper(data1, data2, curr.getRight(), result);
        } else {
            sortedInBetweenHelper(data1, data2, curr.getLeft(), result);
        }
    }

    /**
     * Clears the tree.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * Since this is an AVL, this method does not need to traverse the tree
     * and should be O(1)
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return checkHeight(root);
    }

    /**
     * Returns the size of the AVL tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return number of items in the AVL tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    /**
     * Returns the root of the AVL tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the AVL tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
