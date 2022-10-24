package assn04;


public class Main {
  public static void main(String[] args) {
    /*
    This is a basic example of how to create a BST and add values to it.
    You should add more examples and use this class to debug your code
    */
    BST<Integer> bst = new NonEmptyBST<Integer>(78);
      bst = bst.insert(31);
      bst = bst.insert(13);
      bst = bst.insert(10);
      bst = bst.insert(12);
      bst = bst.insert(38);
      bst = bst.insert(40);
      bst = bst.insert(39);
      bst = bst.insert(47);
      bst = bst.insert(84);
      bst = bst.insert(100);
      bst = bst.insert(101);

      bst = bst.remove(100);

      System.out.print("PreOrder: ");
      bst.printPreOrderTraversal();
      System.out.println("\n");

    System.out.print("Breadth First: ");
      bst.printBreadthFirstTraversal();
      System.out.println("\n");

    System.out.print("Post Order: ");
      bst.printPostOrderTraversal();
      System.out.println("\n");
  }

}
