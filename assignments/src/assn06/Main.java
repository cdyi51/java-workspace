package assn06;

public class Main {
    public static void main(String[] args) {

        // Create a new empty tree.
        SelfBalancingBST<Integer> avl_bst = new AVLTree<Integer>();

        if(true) {
            avl_bst = avl_bst.insert(1);
            avl_bst = avl_bst.insert(2);
            avl_bst = avl_bst.insert(6);
            avl_bst = avl_bst.insert(10);
            System.out.println("Max: " + avl_bst.findMax());
            System.out.println("Min: " + avl_bst.findMin());
            avl_bst = avl_bst.remove_sub(10);
            System.out.println("Max: " + avl_bst.findMax());
            avl_bst = avl_bst.remove_sub(1);
            System.out.println("Min: " + avl_bst.findMin());
            boolean doesContain = avl_bst.contains(12);
            System.out.println(doesContain);


        }

//        if (true) {
//            //avl_bst = avl_bst.insert(20);
//            avl_bst.insert(20);
//            avl_bst.show_tree();
//            avl_bst = avl_bst.insert(11);
//            avl_bst.show_tree();
//            avl_bst = avl_bst.insert(50);
//            avl_bst.show_tree();
//            avl_bst = avl_bst.insert(4);
//            avl_bst.show_tree();
//            avl_bst = avl_bst.insert(6);
//            avl_bst.show_tree();
//            avl_bst = avl_bst.insert(15);
//            avl_bst.show_tree();
//            avl_bst = avl_bst.insert(3);
//            avl_bst.show_tree();
//            avl_bst = avl_bst.insert(16);
//            avl_bst.show_tree();
//            avl_bst = avl_bst.insert(17);
//            avl_bst.show_tree();
//            //avl_bst = avl_bst.insert(2);
//            avl_bst.show_tree();
//            //System.out.println("----");
//        }


        // Insert 50 random integers.
        // Note how we need to capture the result of insert back into
        // the variable avl_bst because the post insertion root that is
        // returned might change because of the insertion

        if (false) {
            for (int i = 0; i < 5; i++) {
                avl_bst = avl_bst.insert((int) (Math.random() * 100));
            }

            System.out.println(avl_bst.height());
//
//         Now insert 50 integers in increasing order which would
//         cause a simple BST to become very tall but for our
//         self-balancing tree won't be too bad.
//
            for (int i = 0; i < 50; i++) {
                avl_bst = avl_bst.insert(i);
            }

            System.out.println(avl_bst.height());
        }
    }
}
