package assn05;


public interface BinaryHeap<V,P extends Comparable<P>> {
    /**
     * Returns number of elements in heap
     * @return numbers of elements in heap
     */
    int size();

    /**
     * Create new hospital.Prioritized object and insert it into the heap
     * @param value
     * @param priority
     */
    void enqueue(V value, P priority);

    /**
     * Remove the element with the smallest priority from the heap
     * and return its value
     * @return the value of the removed element
     */
    V dequeue();

    /**
     * return the smallest value in the heap without removing it
     * @return the smallest value in the heap
     */
    V getMin();


    /**
     * Retrieves contents of heap as an array.
     * @return array of hospital.Prioritized objects in the order stored in the heap
     */
    Prioritized<V,P>[] getAsArray();
}
