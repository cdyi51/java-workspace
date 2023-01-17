package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MinBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;


    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MinBinHeapER() {
        _heap = new ArrayList<>();
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO: overloaded constructor

    public MinBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = new ArrayList<>();
        int size = initialEntries.length;
        // first create the heap, in-order
        for(int i=0; i<size; i++) {
            _heap.add(initialEntries[i]);
        }
        // start at the last non-leaf node
        int startIndex = (size / 2) - 1;
        for (int i = startIndex; i >= 0; i--) {
            bubbleDown(i);
        }
    }


    @Override
    public int size() {
        return _heap.size();
    }

    // TODO: enqueue
    @Override
    public void enqueue(V value, P priority) {
        Prioritized <V, P> patient = new Patient<>(value, priority);
        _heap.add(patient);
        bubbleUp(_heap.size()-1);
    }

    void bubbleUp(int index) {
        // if I'm currently at the top of the heap, then I'm done.
        if (index!=0) {
            Prioritized<V, P> child = _heap.get(index); // you need to compare current (child)'s priority to parent priority.
            Prioritized <V, P> parent = _heap.get(parentInd(index));
            // if child priority is less than parent priority, then:
            if (child.getPriority().compareTo(parent.getPriority()) < 0) {
                //set parent index to child, and set child to parent (swap)
                _heap.set(parentInd(index), child);
                _heap.set(index, parent);
                bubbleUp(parentInd(index));
            }
        }
    }
    // TODO: enqueue
    public void enqueue(V value) {
        Patient<V, P> patient = new Patient<>(value);
        enqueue(value, patient.getPriority());
    }

    // TODO: dequeue
    @Override
    public V dequeue() {
        if (_heap.size() == 0) {
            return null;
        } else if (_heap.size() == 1) {
            Prioritized<V,P> first = _heap.get(0);
            _heap.remove(0);
            return (first.getValue());
        } else {
            Prioritized<V, P> toDeq = _heap.get(0);
            _heap.set(0, _heap.get(_heap.size() - 1));
            _heap.remove(_heap.size() - 1);
            bubbleDown(0);
            return (toDeq.getValue());
        }
    }

    void bubbleDown(int index){
        Prioritized<V, P> parent = _heap.get(index);
        // if element is a leaf
        if (!hasLeftChild(index) && !hasRightChild(index)){
            return;
        }
        // if element only has a left child
        else if (!hasRightChild(index)) {
            Prioritized<V, P> leftChild = _heap.get(leftChildInd(index));
            // if the leftChild's priority is less than the parent's:
            if (leftChild.getPriority().compareTo(parent.getPriority()) <  0) {
                _heap.set(index, leftChild);
                _heap.set(leftChildInd(index), parent);
                bubbleDown(leftChildInd(index));
            }
            else {return;}
        }
        // element has both left and right children
        else {
            Prioritized leftChild = _heap.get(leftChildInd(index));
            Prioritized rightChild = _heap.get(rightChildInd(index));
            if ((leftChild.getPriority().compareTo(parent.getPriority())) < 0 || (rightChild.getPriority().compareTo(parent.getPriority())) < 0) {
                // checking which of these children has the lower priority and is the one that needs to be swapped
                if (leftChild.getPriority().compareTo(rightChild.getPriority()) < 0) {
                    _heap.set(index, leftChild);
                    _heap.set(leftChildInd(index), parent);
                    bubbleDown(leftChildInd(index));
                } else {
                    _heap.set(index, rightChild);
                    _heap.set(rightChildInd(index), parent);
                    bubbleDown(rightChildInd(index));
                }
            }
            // else: meaning if the priority of the parent is indeed greater than both its children, then you don't have to swap.
            // I'm done because this means there's nothing wrong with the successive subtrees either.
            else {return;}  // done
        }
    }

    // TODO: getMin
    @Override
    public V getMin() {
        if(_heap.size()==0)
            return null;
        else {
            return _heap.get(0).getValue();
        }
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }
    boolean hasLeftChild(int index){
        return(validIndex(leftChildInd(index)));}

    boolean hasRightChild(int index){
        return(validIndex(rightChildInd(index)));}

    static int leftChildInd(int index){
        return (2*index+1);}

    static int rightChildInd(int index){
        return (2*index+2);}

    static int parentInd(int index){
        return ((index-1)/2);}

    boolean validIndex(int index){
        if ((index >= 0) && (index <= _heap.size()-1)){
            return true;}
        else {return false;}}


}
