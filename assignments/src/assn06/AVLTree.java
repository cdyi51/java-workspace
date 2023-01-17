package assn06;

//import java.util.LinkedList;
//import java.util.Queue;

import javax.swing.event.CellEditorListener;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;
    
    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    /**
     *
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
    
     private AVLTree<T> rotateLeft(AVLTree<T> node) {
         // You should implement left rotation and then use this 
         // method as needed when fixing imbalances.
    	 // TODO
         AVLTree<T> rightChild = node._right;
         AVLTree<T> change = rightChild._left;

         rightChild.setLeft(node);
         node.setRight(change);

         updateHeight(node);
         node.updateSize();
         updateHeight(rightChild);
         rightChild.updateSize();

         return rightChild;
     }
    
    /**
     *
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */ 
     
     private AVLTree<T> rotateRight(AVLTree<T> node) {
         // You should implement right rotation and then use this 
         // method as needed when fixing imbalances.
    	 // TODO
         AVLTree<T> leftChild = node._left;
         AVLTree<T> change = leftChild._right; //for generic use: leftChild's right subtree will be set to the left of original root. if null, a null tree is appointed to root.

         leftChild.setRight(node);
         node.setLeft(change);

         updateHeight(node);
         node.updateSize();
         updateHeight(leftChild);
         leftChild.updateSize();

         return leftChild;
     }

    @Override
    public boolean isEmpty() {
         return size() == 0;
    }

    @Override
    public int height() {
         return _height;
    }

    @Override
    public int size() {
        return _size;
    }

//    @Override
//    public SelfBalancingBST<T> insert(T element) {
//         SelfBalancingBST<T> temp = insert_sub(element);
//         return temp;
//    }
    @Override
    public SelfBalancingBST<T> insert(T element) {
    	// TODO

        if (isEmpty()) {
            _left = new AVLTree<>();
            _right = new AVLTree<>();
            _value = element;
            _height++;
            _size++;
            return this;
        }
        else if (element.compareTo(_value)<0) {
            if (_left.isEmpty()) {
                AVLTree<T> newLeft = new AVLTree<T>();
                newLeft._left = new AVLTree<>();
                newLeft._right = new AVLTree<>();
                newLeft._value = element;
                _left = newLeft;
                updateHeight(_left);
                _left.updateSize();
            }
            else {
                setLeft(_left.insert(element));
            }
        }
        else if (element.compareTo(_value)>0) {
            if (_right.isEmpty()) {
                AVLTree<T> newRight = new AVLTree<T>();
                newRight._left = new AVLTree<>();
                newRight._right = new AVLTree<>();
                newRight._value = element;
                _right = newRight;
                updateHeight(_right);
                _right.updateSize();
            }
            else {
                setRight(_right.insert(element));
            }
        }
        updateHeight(this);
        AVLTree<T> temp = rotator();
        temp.updateSize();
        return temp;
    }


    public void updateHeight(AVLTree<T> tree) {
        int maxHeight = Math.max(
                getHeight(tree._left),
                getHeight(tree._right)
        );
         tree._height = maxHeight + 1;
    }

    public int getHeight(AVLTree<T> node) {
        //if node isn't null, return node height. otherwise, return -1 (height of null node)
         return node!=null ? node._height : -1;
    }

    public int getBalanceFactor(AVLTree<T> tree) {
         // if tree is not null, return the difference of left and right subtrees. else, return 0.
         return tree != null ? getHeight(tree._left) - getHeight(tree._right) : 0;
    }

    public AVLTree<T> rotator() {
         // if bst is balanced
         if(getBalanceFactor(this) >=-1 && getBalanceFactor(this)<= 1) {
             return this;
         }
         // if current tree is left heavy
         else if(getBalanceFactor(this)>1) {
             // check if its left child is right heavy. if so, perform left right rotation.
             if(getBalanceFactor(_left) < 0)
                 setLeft(rotateLeft(_left)); // rotate about root left
             return rotateRight(this); // now rotate new root to the right and return
         }
         // if current tree is right heavy
         else if(getBalanceFactor(this)<-1){
             // check if its right child is left heavy. if so, you need to perform a right left rotation.
             if(getBalanceFactor(_right) > 0)
                 setRight(rotateRight(_right));
             return rotateLeft(this);
         }

         return this;
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
         SelfBalancingBST<T> temp = remove_sub(element);

         if (temp == null) {
             _value = null;
             _left = null;
             _right = null;
             _size = 0;
             _height = -1;
             return this;
         }
        return temp;
    }
    @Override
    public SelfBalancingBST<T> remove_sub(T element) {
        // TODO
        // if left or right is empty, element is not in the tree, so return null.
        if(isEmpty()){
            return this;
        }
        if(element.compareTo(_value)<0) {// if element is less than value, check next left node
            if(!(getLeft().isEmpty())) {
                setLeft(getLeft().remove_sub(element));
            }
        }
        else if(element.compareTo(_value)>0) { // if element is greater than value, check next right node
            if(!(getRight().isEmpty())) {
                setRight(getRight().remove_sub(element));
            }
        }
        else { // once node to remove is found:
            // case 1: node has one child or is a leaf node
            if(_left.isEmpty()) {
                //_right._size--;
                return getRight();
            }
            else if(_right.isEmpty()) {
                //_size--;
                return getLeft();
            }
            // case 2: node has two children. find the max on the node's left subtree, and set that value to the node
            T successor = getRight().findMin();
            setValue(successor);// replace node to remove with predecessor
            setRight(getRight().remove_sub(successor));// delete the original predecessor
            //updateSize();
        }
        updateHeight(this);
        updateSize();
        return rotator();
    }
    @Override
    public void updateSize() {
         int leftSize;
         leftSize = _left == null? 0: _left.size();
         int rightSize;
         rightSize = _right == null? 0: _right.size();

         _size =leftSize + rightSize + 1;
    }

    @Override
    public T findMin() {
    	// TODO
        if(isEmpty()) {
            return null;
        }
        AVLTree<T> current = this;
        while(!(current.getLeft().isEmpty())) {
            current = (AVLTree<T>) current.getLeft();
        }
        return current.getValue();
    }

    @Override
    public T findMax() {
    	// TODO
        if(isEmpty()) {
            return null;
        }
        AVLTree<T> current = this;
        while(!(current.getRight().isEmpty())) {
            current = (AVLTree<T>) current.getRight();
        }
        return current.getValue();
    }

    @Override
    public boolean contains(T element) {
    	// TODO
        if(element==_value) {
            return true;
        }
        if(element.compareTo(_value) <0 && !(_left.isEmpty()) && _left.contains(element)) {
            return true;
        }
        if(element.compareTo(_value) >0 && !(_right.isEmpty()) && _right.contains(element)) {
            return true;
        }
        return false;
    }

//    public void printBreadthFirstTraversal() {
//        Queue<AVLTree<T>> q = new LinkedList<AVLTree<T>>();
//        System.out.print(_value + " ");
//        if(!(_left==null)) {
//            q.add(_left);
//        }
//        if(!(_right==null)) {
//            q.add(_right);
//        }
//        while(q.size()>0) {
//            if(!(q.element().getLeft()==null)) {
//                q.add(q.element()._left);
//            }
//            if(!(q.element().getRight()==null)) {
//                q.add(q.element()._right);
//            }
//            System.out.print(q.poll().getValue() + " ");
//        }
//    }
    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }

         return _right;
    }

    @Override
    public void setRight(SelfBalancingBST<T> tree) {
        _right = (AVLTree<T>) tree;
    }

    @Override
    public void setLeft(SelfBalancingBST<T> tree) {
        _left = (AVLTree<T>) tree;
    }
    @Override
    public void setValue(T val) {
        _value = val;
    }

    @Override
    public void show_tree() {
         show_tree_sub();
         System.out.printf("size = %d%n", _size);
         System.out.println("----");
    }
    private void show_tree_sub() {
        if (_left != null) {
            _left.show_tree_sub();
        }
        System.out.printf(" (%d) ", _height);
        //System.out.printf("%i  %i%n", (int) _height, (int) _value);
        System.out.println(_value);
        if (_right != null) {
            _right.show_tree_sub();
        }


    }

}