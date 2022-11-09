package assn06;

public interface SelfBalancingBST<T extends Comparable<T>> {

    /**
     * Returns true if the tree is empty
     */
    boolean isEmpty();

    /**
     * Returns height of the tree.
     */
    int height();
    
    /**
     * Returns the number of elements in the tree.
     *
     * @return the number of elements in the tree
     */
    int size();

    /**
     * Inserts element into tree and returns resulting
     * tree after insertion. Depending on implementation,
     * this may or may not be the same object that you
     * started with.
     *
     * @param element to be added to the tree
     * @return resulting tree after insertion
     **/
    SelfBalancingBST<T> insert(T element);

    /**
     * Removes element from tree and returns resulting
     * tree after removal. Depending on implementation,
     * this may or may not be the same object that you
     * started with. If element is not in the tree, the
     * tree should remain unchanged and return itself.
     **
     * @param element to be removed from the tree
     * @return resulting tree after removal
     **/
    SelfBalancingBST<T> remove(T element);

    /**
     * Returns the smallest value in the tree.
     * Return null if called on an empty tree.
     *
     * @return the smallest value in the tree
     */
    T findMin();

    /**
     * Returns the largest value in the tree.
     * Return null if called on an empty tree.
     *
     * @return the smallest value in the tree.
     */
    T findMax();

    /**
     * Returns true if the tree contains the specified element.
     *
     * @param element whose presence in this tree is to be tested
     * @return true if this tree contains the specified element
     */
    boolean contains(T element);

    /**
     * Returns value at the top of the tree.
     * If a tree is empty, its value is null.
     */
    T getValue();

    /**
     * Returns the left child of the tree.
     * Throws a RuntimeException if the tree is empty.
     */

    SelfBalancingBST<T> getLeft();

    /**
     * Returns the right child of the tree.
     * Throws a RuntimeException if the tree is empty.
     */

    SelfBalancingBST<T> getRight();

}