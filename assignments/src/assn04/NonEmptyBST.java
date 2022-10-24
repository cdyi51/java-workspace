package assn04;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	private EmptyBST<T> empty;
	public NonEmptyBST(T element) {
		empty = new EmptyBST<T>();
		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element) {
		T current = element;
		T root = _element;
		if (root==null) {
			root = current;
		}
		if (current.compareTo(root)==-1) {
			if (_left.isEmpty()) {
				NonEmptyBST<T> newLeft = new NonEmptyBST<T>(current);
				_left = newLeft;
			}
			else {_left.insert(current);}
		}
		if (current.compareTo(root)==1) {
			if (_right.isEmpty()) {
				NonEmptyBST<T> newRight = new NonEmptyBST<T>(current);
				_right = newRight;
			}
			else {_right.insert(current);}
		}
		//if current==root, nothing happens. so extra if statement is not needed.
		return this;
	}

	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		T root = _element;
		if(root==element) {
			BST<T> result = removeRoot(this);
			return result;
		}
		if(_left.isEmpty() && _right.isEmpty()) {
			return empty;
		}
		if(!(_left.isEmpty())) {
			if (element.compareTo(root) == -1) {
				if (_left.getElement().equals(element)) {
					_left = removeHelper(_left);
				} else {
					_left.remove(element);
				}
			}
		}
		if(!(_right.isEmpty())) {
			if (element.compareTo(root) == 1) {
				if (_right.getElement().equals(element)) {
					_right = removeHelper(_right);
				} else {
					_right.remove(element);
				}
			}
		}
		return this;
	}

	public BST<T> removeHelper(BST<T> child) {
			// case 1: if child has no children, then you just delete the child
			if(child.getLeft().isEmpty() && child.getRight().isEmpty()) {
				child = empty;
				return child;
			}
			//case 2: if child has one child, check which side and then repoint
			if(!(child.getLeft().isEmpty()) && child.getRight().isEmpty()) {
				child = child.getLeft();
				return child;
			}
			if(!(child.getRight().isEmpty()) && child.getLeft().isEmpty()) {
				child = child.getRight();
				return child;
			}
			// case 3: if child has two children, traverse the left side and find the rightmost element, then
			//replace child value with rightmost el and then delete rightmost el
			if(!(child.getLeft()).isEmpty() && !(child.getRight().isEmpty())) {
				BST<T> prevCurrent = child;
				BST<T> current = prevCurrent.getLeft();
				Boolean isLeft = true;
				// special case: if first left doesn't have a right child, then it is the rightmost element.
				if(!(current.getLeft().isEmpty()) && current.getRight().isEmpty()) {
					child.setElement(current.getElement());
					child.setLeft(current.getLeft());
					return child;
				}
				while(true) {
					if (!(current.isEmpty()) && !(current.getRight().isEmpty())) {
						prevCurrent = current;
						current = prevCurrent.getRight();
						isLeft = false;
					} else {
						T rightMost = current.getElement();
						child.setElement(rightMost);
						if (isLeft) {
							prevCurrent.setLeft(empty);
						} else {
							prevCurrent.setRight(empty);
						}
						break;
					}
				}

			}
			return child;
		}

		// ik this method is more or less the same as removeHelper, but it helped me to think of root as a separate case.
	public BST<T> removeRoot(BST<T> rootBst) {
		//case1: root is leaf
		if(_left.isEmpty() && _right.isEmpty()) {
			return empty;
		}
		//case2: root has one child. determine whether it is left or right, then replace root with child
		if(!(_left.isEmpty()) && _right.isEmpty()) {
			rootBst = _left;
			return rootBst;
		}
		if(!(_right.isEmpty()) && _left.isEmpty()) {
			rootBst = _right;
			return rootBst;
		}
		//case 3: root has elements on both sides. traverse the left side and find the rightmost element. "replace" root
		// with rightmost el, then disconnect original rightmost el from tree.
		if(!(_left.isEmpty()) && !(_right.isEmpty())) {
			BST<T> parent = rootBst;
			BST<T> toRemove = _left;
			Boolean isLeft = true;
			while(true) {
				if (!(toRemove.isEmpty()) && !(toRemove.getRight().isEmpty())) {
					parent = toRemove;
					toRemove = parent.getRight();
					isLeft = false;
				} else {
					T rightMost = toRemove.getElement();
					rootBst.setElement(rightMost);
					if (isLeft) {
						parent.setLeft(empty);
					} else {
						parent.setRight(empty);
					}
					break;
				}
			}
		}
		return rootBst;
	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		if(_element==null) {return;}
		System.out.print(_element + " ");
		if(!(_left.isEmpty())) {
			_left.printPreOrderTraversal();
		}
		if(!(_right.isEmpty())) {
			_right.printPreOrderTraversal();
		}
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		if(this == null) {return;}
		_left.printPostOrderTraversal();
		_right.printPostOrderTraversal();
		System.out.print(_element + " ");
	}

	// TODO: printBreadthFirstTraversal
	@Override
	public void printBreadthFirstTraversal() {
		Queue<BST<T>> q = new LinkedList<BST<T>>();
		System.out.print(_element + " ");
		if(!(_left.isEmpty())) {
			q.add(_left);
		}
		if(!(_right.isEmpty())) {
			q.add(_right);
		}
		while(q.size()>0) {
			if(!(q.element().getLeft().isEmpty())) {
				q.add(q.element().getLeft());
			}
			if(!(q.element().getRight().isEmpty())) {
				q.add(q.element().getRight());
			}
			System.out.print(q.poll().getElement() + " ");
		}
	}

	@Override
	public int getHeight() {
		return Math.max(_left.getHeight(), _right.getHeight())+1;
	}

	@Override
	public void setRight(BST<T> bst) {
		_right = bst;
	}

	@Override
	public void setLeft(BST<T> bst) {
		_left = bst;
	}
	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
	@Override
	public void setElement(T val) {
		_element = val;
	}

}
