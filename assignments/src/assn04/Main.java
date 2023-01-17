package assn04;

import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    /*
    This is a basic example of how to create a BST and add values to it.
    You should add more examples and use this class to debug your code
    */
    BST<Integer> bst = new NonEmptyBST<Integer>(26);
//    bst = bst.insert(25);
//    bst = bst.insert(10);
//    bst = bst.insert(40);
//    bst = bst.insert(50);
//    bst = bst.insert(30);
//    bst = bst.insert(27);
//    bst = bst.insert(28);
//    bst = bst.insert(29);
    bst.printPreOrderTraversal();
    System.out.println("\n");

    bst = bst.remove(26);

    bst.printPreOrderTraversal();
    System.out.println("\n");

//    ArrayList<Integer> list = new ArrayList<>();
//    for(int i=1; i < 1000; i++) {
//      int min = 0;
//      int max = 100;
//      list.add((int)Math.floor(Math.random()* (max - min) + 1));
//    }

  }

}
