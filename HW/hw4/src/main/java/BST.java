import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Your implementation of a binary search tree.
 *
 * @author Charlie Comesau
 * @userid ccomeau7
 * @GTID 903359699
 * @param <T> generic type for BST
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     *
     * Si/nce instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
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
     * Add the data as a leaf in the BST. Should traverse the tree to find the
     * appropriate location. If the data is already in the tree, then nothing
     * should be done (the duplicate shouldn't get added, and size should not be
     * incremented).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data into "
                    + "data structure!");
        }
        root = add(root, data);
    }

    /**
     * Recursive private helper method for add
     *
     * @param curr the current node the method is on
     * @param data the data to be added
     * @return the most recently accessed node
     */
    private BSTNode<T> add(BSTNode<T> curr, T data) {
        if (curr == null) {
            curr = new BSTNode<>(data);
            size++;
            return curr;
        }
        if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(add(curr.getLeft(), data));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(add(curr.getRight(), data));
        }
        return curr;
    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf (no children). In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the predecessor to replace the data.
     * You MUST use recursion to find and remove the predecessor (you will
     * likely need an additional helper method to handle this case efficiently).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in. Return the data that was stored in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot delete null data into "
                    + "data structure!");
        }
        BSTNode<T> dummyNode = new BSTNode<>(null);
        root = remove(root, data, dummyNode);
        size--;
        return dummyNode.getData();
    }

    /** Recursive private helper method to access node to be removed
     * @param curr the current node the method is on
     * @param data the data of the node to be removed
     * @param dummy the dummy node that is used to return
     *              the removed node's data
     * @return most recently accessed node
     */
    private BSTNode<T> remove(BSTNode<T> curr, T data, BSTNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("BST does not contain this data!");
        }
        if (data.compareTo(curr.getData()) == 0) {
            dummy.setData(curr.getData());
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else if (curr.getLeft() != null && curr.getRight() == null) {
                return curr.getLeft();
            } else if (curr.getLeft() == null && curr.getRight() != null) {
                return curr.getRight();
            } else {
                BSTNode<T> tempNode = new BSTNode<>(null);
                curr.setLeft(predecessor(curr.getLeft(), tempNode));
                curr.setData(tempNode.getData());
            }
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(remove(curr.getLeft(), data, dummy));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(remove(curr.getRight(), data, dummy));
        }
        return curr;
    }

    /** Recursive private helper method for finding the predecessor of the node
     * to be removed
     *
     * @param curr the current node the method is on
     * @param tempNode node to be removed
     * @return predecessor node of curr
     */
    private BSTNode<T> predecessor(BSTNode<T> curr, BSTNode<T> tempNode) {
        if (curr.getRight() == null && curr.getLeft() != null) {
            tempNode.setData(curr.getData());
            return curr.getLeft();
        }
        if (curr.getRight() == null) {
            tempNode.setData(curr.getData());
            return null;
        } else {
            curr.setRight(predecessor(curr.getRight(), tempNode));
        }
        return curr;
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
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
     * Recursive private helper method for searching for the specified node
     * @param curr the current node the method is on
     * @param data desired data of the desire node
     * @return desired node
     */
    private BSTNode<T> get(BSTNode<T> curr, T data) {
        if (curr == null) {
            throw new NoSuchElementException("BST does not contain this data!");
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
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data structure cannot "
                    + "contain null data!");
        }
        BSTNode<T> search = contains(root, data);
        return search != null;
    }

    /**
     * Recursive private helper method for searching for the specified node
     * @param curr the current node the method is on
     * @param data desired data of the desire node
     * @return desired node
     */
    private BSTNode<T> contains(BSTNode<T> curr, T data) {
        if (curr == null) {
            return null;
        } else if (curr.getData().compareTo(data) > 0) {
            return contains(curr.getLeft(), data);
        } else if (curr.getData().compareTo(data) < 0) {
            return contains(curr.getRight(), data);
        } else if (curr.getData().compareTo(data) == 0) {
            return curr;
        } else {
            return null;
        }
    }

    /**
     * Should run in O(n).
     *
     * @return a preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> preorderedList = new ArrayList<>();
        preorder(root, preorderedList);
        return preorderedList;
    }

    /**
     * Recursive private helper method for accessing all nodes and creating a
     * pre-ordered list
     * @param curr the current node the method is on
     * @param preorderedList the list that the node's data is being added to
     */
    private void preorder(BSTNode<T> curr, List<T> preorderedList) {
        if (curr == null) {
            return;
        }
        preorderedList.add(curr.getData());
        preorder(curr.getLeft(), preorderedList);
        preorder(curr.getRight(), preorderedList);
    }

    /**
     * Should run in O(n).
     *
     * @return an inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> inorderedList = new ArrayList<T>();
        inorder(root, inorderedList);
        return inorderedList;
    }

    /**
     * Recursive private helper method for accessing all nodes and creating
     * an in-ordered list.
     * @param curr the current node the method is on
     * @param inorderedList the list that the node's data is being added to
     */
    private void inorder(BSTNode<T> curr, List<T> inorderedList) {
        if (curr == null) {
            return;
        }
        inorder(curr.getLeft(), inorderedList);
        inorderedList.add(curr.getData());
        inorder(curr.getRight(), inorderedList);
    }

    /**
     * Should run in O(n).
     *
     * @return a postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> postorderedList = new ArrayList<>();
        postorder(root, postorderedList);
        return postorderedList;
    }

    /**
     * Recursive private helper method for accessing all nodes and creating a
     * post-ordered list
     * @param curr the current node the method is on
     * @param postorderedList the list that the node's data is being added to
     */
    private void postorder(BSTNode<T> curr, List<T> postorderedList) {
        if (curr == null) {
            return;
        }
        postorder(curr.getLeft(), postorderedList);
        postorder(curr.getRight(), postorderedList);
        postorderedList.add(curr.getData());
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * To do this, add the root node to a queue. Then, while the queue isn't
     * empty, remove one node, add its data to the list being returned, and add
     * its left and right child nodes to the queue. If what you just removed is
     * {@code null}, ignore it and continue with the rest of the nodes.
     *
     * Should run in O(n). This does not need to be done recursively.
     *
     * @return a level order traversal of the tree
     */
    public List<T> levelorder() {
        Queue<BSTNode<T>> levelOrderQueue = new LinkedList<>();
        List<T> levelOrderedList = new ArrayList<>();
        levelOrderQueue.add(root);
        while (!levelOrderQueue.isEmpty() && size != 0) {
            BSTNode<T> currNode = levelOrderQueue.poll();
            if (currNode != null) {
                levelOrderedList.add(currNode.getData());
            }
            if (currNode.getLeft() != null) {
                levelOrderQueue.add(currNode.getLeft());
            }
            if (currNode.getRight() != null) {
                levelOrderQueue.add(currNode.getRight());
            }
        }
        return levelOrderedList;
    }

    /**
     * This method checks whether a binary tree meets the criteria for being
     * a binary search tree.
     *
     * This method is a static method that takes in a BSTNode called
     * {@code treeRoot}, which is the root of the tree that you should check.
     *
     * You may assume that the tree passed in is a proper binary tree; that is,
     * there are no loops in the tree, the parent-child relationship is
     * correct, that there are no duplicates, and that every parent has at
     * most 2 children. So, what you will have to check is that the order
     * property of a BST is still satisfied.
     *
     * Should run in O(n). However, you should stop the check as soon as you
     * find evidence that the tree is not a BST rather than checking the rest
     * of the tree.
     *
     * @param <T> the generic typing
     * @param treeRoot the root of the binary tree to check
     * @return true if the binary tree is a BST, false otherwise
     */
    public static <T extends Comparable<? super T>> boolean isBST(
            BSTNode<T> treeRoot) {
        return isBST(treeRoot, null, null);
    }

    /**
     * Recursive private helper method for accessing all nodes and determining
     * if given BST is valid
     * @param curr the current node that the method is on
     * @param min current minimum data bound
     * @param max current maximum data bound
     * @param <T> generic tag for method
     * @return whether BST is valid or not
     */
    private static <T extends Comparable<? super T>> boolean isBST(
            BSTNode<T> curr, BSTNode<T> min, BSTNode<T> max) {
        if (curr == null) {
            return true;
        }

        if (min != null
                && min.getData().compareTo(curr.getData()) > 0) {
            return false;
        }

        if (max != null
                && max.getData().compareTo(curr.getData()) < 0) {
            return false;
        }

        return isBST(curr.getLeft(), min, curr)
                && isBST(curr.getRight(), curr, max);

    }

    /**
     * Clears the tree.
     *
     * Should run in O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Calculate and return the height of the root of the tree. A node's
     * height is defined as {@code max(left.height, right.height) + 1}. A leaf
     * node has a height of 0 and a null child should be -1.
     *
     * Should be calculated in O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (root == null) {
            return -1;
        } else {
            return height(root);
        }
    }

    /**
     * Recursive private helper method for accessing all nodes and calculating
     * the height of a given node
     * @param curr the current node the method is on
     * @return height of the tree
     */
    private int height(BSTNode<T> curr) {
        if (curr == null) {
            return -1;
        } else {
            int leftHeight = height(curr.getLeft());
            int rightHeight = height(curr.getRight());

            if (leftHeight > rightHeight) {
                return 1 + leftHeight;
            } else {
                return 1 + rightHeight;
            }
        }
    }

    /**
     * Returns the size of the BST.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the number of elements in the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the root of the BST.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
