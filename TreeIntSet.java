import java.util.*;

/*
 * In this implementation of IntSet, all items are stored in an ordered array.
 *
 * The effiency of each method is as follows:
 *  	add is O(n)  (to make sure it isn't a duplicate)
 *  	contains is O(log(n))
 *  	remove is O(log(n))  (to find the index of the element)
 *  	size is O(1)
 */
public class TreeIntSet implements IntSet{
	/**
    * Returns an iterator over the elements in this set.
    * The elements are returned in no particular order.
    *
    * @return  an Iterator over the elements in this set
    */
    public Iterator<Integer> iterator(){
        return new MyIterator();
    }
    
    private class MyIterator implements Iterator<Integer>{
        private int index = 0;

        public boolean hasNext(){
        return 0 <= index && index < size;
        }
        
        public Integer next(){  
        // Note: the 2 lines below are equivalent to the 1 line
        // return data[index++];
        index++;
        return data[index - 1];
        }
    }

	private class IntBinarySearchTree{
	   // --------------------------------------------------------------------
	   // Private classes
	   // --------------------------------------------------------------------


	   /**
	   * Data-only class to store Nodes.
	   * Only accessible within IntBinarySearchTree.
	   */
	   private static class Node {
	       public int data;
	       public Node left;
	       public Node right;
	       public Node parent;

	       public Node(int data) {
	           this.data = data;
	           this.left = null;
	           this.right = null;
	           this.parent = null;
	       }
	   }


	  
	   // --------------------------------------------------------------------
	   // Private state variables
	   // --------------------------------------------------------------------
	   private Node root;
	   private int count;
	   private ArrayList<Integer> heights = new ArrayList<Integer>();
	   private ArrayList<Integer> temp = new ArrayList<Integer>();


	   // --------------------------------------------------------------------
	   // Public methods
	   // --------------------------------------------------------------------
	   /**
	   * Construct an empty search tree.
	   */
	   public IntBinarySearchTree() {
	       root = null;
	   }


	   /**
	   * Construct a search tree from a given array of values.
	   *
	   * @param  inputArray  array of values to be used to construct the tree
	   */
	   public IntBinarySearchTree(int[] inputArray) {
	       // replace this with code that actually works
	       for (int i = 0; i < inputArray.length; i++){
	           root = other_insert(inputArray[i], root);
	       }
	   }
	   /**
	   * Sets up this tree to be a sample binary search tree. This is just for
	   * testing purposes.
	   */
	   public void setToSampleTree1() {
	       root = new Node(52);
	       root.left = new Node(29);
	       root.left.left = new Node(10);
	       root.left.right = new Node(37);
	       root.left.left.right = new Node(17);
	       root.right = new Node(75);
	       root.right.left = new Node(62);
	       root.right.right = new Node(92);
	       root.right.left.left = new Node(58);
	       root.right.left.right = new Node(68);
	   }
	  
	   /**
	   * Sets up this tree to be a sample binary search tree. This is just for
	   * testing purposes.
	   */
	   public void setToSampleTree2() {
	       root = new Node(5);
	       root.left = new Node(1);
	       root.left.right = new Node(4);
	       root.left.right.left = new Node(3);
	       root.left.right.left.left = new Node(2);
	   }
	  
	   /**
	   * Sets up this tree to be a sample binary search tree. This is just for
	   * testing purposes.
	   */
	   public void setToSampleTree3() {
	       // make your own sample tree with 12 Nodes
	       // it should be almost balanced, but not quite
	       // draw it out on paper first!


	   }
	  
	   /**
	   * Return the values as a string.
	   *
	   * The format is as follows: "12 (4 16 (13 _))"
	   * Left to right, inorder traversal:
	   *  Start with the root, each pair of children is enclosed in parentheses
	   *  Any empty spaces are represented with an underscore.
	   *
	   * sampleTree2 from above is represented by "5 (1 (_ 4 (3 (2 _) _)) _)"
	   * @return  String 
	   */
	   @Override
	   public String toString() {
	       return toStringRecursion(root);
	   }


	   /**
	   * Prints the tree on its side, root on the left.
	   */
	   public void print(){
	       printTree(root, 0);
	   }




	   // --------------------------------------------------------------------
	   // Private methods
	   // --------------------------------------------------------------------


	   // Hint: All recursive solutions rely on a call to a private method!


	   private String toStringRecursion(Node root) {
	   
	       String contents = "";


	       if (root == null) {
	           return contents;
	       }
	       contents += root.data;


	       // Conditionals are only here to keep nice spacing
	       // (underscores when a child does not exist)
	       if (root.left != null && root.right == null){
	           contents += " (" + toStringRecursion(root.left) + " _)";
	       }
	       else if (root.left == null && root.right != null){
	           contents += " (_ " + toStringRecursion(root.right) + ")";
	       }
	       else if (root.left != null && root.right != null) {
	           contents +=  " (" + toStringRecursion(root.left) + " " + toStringRecursion(root.right) +  ")";
	       }
	       return contents;
	   
	   }


	   // prints tree on its side, root on the left.
	   // right to left inorder traversal
	   private void printTree(Node tree, int depth){
	   
	       if (tree == null){
	           return;
	       }
	       printTree(tree.right, depth + 1);   // Right side printed on top


	       for (int i = 0; i < depth; i++){    // Manage indentations
	           System.out.print("   ");
	       }  
	       System.out.println(tree.data);      // Current data
	       printTree(tree.left, depth + 1);    // Left side on bottom
	   }


	   private Node other_insert(int data, Node current){
	       if (current == null){
	           return new Node(data);
	       }
	       if (current.data > data){
	       		Node child = other_insert(data, current.left);
	           current.left = child;

	           child.parent = current;
	       }
	       else if (current.data < data){
	       		Node child = other_insert(data, current.left);
	           current.left = child;

	           child.parent = current;
	       }
	       return current;
	   }


	   public void insert(int data){
	       root = other_insert(data, root);
	   }


	   private int other_height(Node current){
	       if (current == null){
	           return -1;
	       }
	   
	       int left = other_height(current.left);
	       int right = other_height(current.right);


	       if (left > right){
	           return left + 1;
	       }
	       else {
	           return right + 1;
	       }
	   }


	   public int height(){    
	       if (root == null){
	           return 0;
	       }
	       int left = other_height(root.left);
	       int right = other_height(root.right);


	       if (left > right){
	           return left + 2;
	       }
	       else {
	           return right + 2;
	       }
	   }


	   public boolean find(int data){
	       return other_find(root, data);
	   }


	   private boolean other_find(Node node, int data){
	       if (node == null){
	           return false;
	       }
	       if ((node.left == null && data < node.data) || (node.right == null && data > node.data)){
	            return false;
	       }
	       if (data < node.data){
	           return other_find(node.left, data);
	       }
	       else if (data > node.data){
	           return other_find(node.right, data);
	       }
	       return true;
	   }
	   
	   public boolean delete(int data){
	       if (!find(data)){
	           return false;
	       }
	       root = delete(root, data);
	       return true;
	   }
	   private Node delete(Node node, int data){
	       if (node == null)
	           return null;
	       // traverse to right if greater
	       if (node.data > data) {
	           node.left = delete(node.left, data);
	       }
	       // traverse to left if smaller
	       else if (node.data < data) {
	           node.right = delete(node.right, data);
	       }
	       // once we have reached the desired node, do the following:
	       else {
	           // if the node has two children
	           if (node.left != null && node.right != null) {
	               Node temp = node;
	               Node smallest = find_smallest(temp.right);
	               node.data = smallest.data;
	               node.right = delete(node.right, smallest.data);
	           }
	           else if (node.left != null) {
	               node = node.left;
	           }
	           else if (node.right != null) {
	               node = node.right;
	           }
	           else
	               node = null;
	       }
	       return node;
	   }
	   public int countNodes(){
	       otherCountNodes(root);
	       int temp = count;
	       count = 0;
	       return temp;
	   }
	  
	   public boolean isBalanced(){
	       otherBalance(root);
	       for (int i = 0; i < heights.size(); i++){
	           System.out.print(heights.get(i) + ", ");
	           if (heights.get(i) > 1){
	               return false;
	           }
	       }
	       heights.clear();
	       return true;
	   }


	   public int countLeaves(){
	       otherLeaves(root);
	       int temp = count;
	       count = 0;
	       return temp;
	   }


	   public boolean isFull(){
	       otherFull(root);
	       if (count > 0){
	           count = 0;
	           return false;
	       }
	       return true;
	   }
	   public int countBranches(){
	       return countNodes() - countLeaves();
	   }
	   public int sum(){
	       otherSum(root);
	       int temp = count;
	       count = 0;
	       return temp;
	   }
	   private void otherSum(Node node){
	       if (node != null){
	           otherSum(node.left);
	           count += node.data;
	           otherSum(node.right);
	       }
	   }

	   private void otherBST(){
	   		if (node != null){
	           otherBST(node.left);
	           temp.add(node.data);
	           otherBST(node.right);
	       }
	   }

	   public ArrayList<Integer> sortBST(){
	   		for (int i = 0; i < temp.length; i++){
	   			for (int j = 0; j < temp.length - 1; j++){
	   				if ()
	   			}
	   		}
	   }


	   private void otherFull(Node node){
	       if (node != null){
	           otherFull(node.left);
	           if ((node.left == null && node.right != null) || (node.left != null && node.right == null)){
	               count++;
	           }
	           otherFull(node.right);
	       }
	   }
	   private int childrenNum(Node node){
	       return
	           (node.left == null && node.right == null) ? 0
	           : (node.left == null || node.right == null) ? 1
	           : 2;
	   }


	   private void otherBalance(Node node){
	       if (node != null){
	           otherSum(node.left);
	           heights.add(Math.abs(other_height(node.right) - other_height(node.left)));
	           otherSum(node.right);
	       }
	   }


	   private void otherLeaves(Node node){
	       if (node != null){
	           otherLeaves(node.left);
	           if (node.left == null && node.right == null){
	               count++;
	           }
	           otherLeaves(node.right);
	       }
	   }
	   private void otherCountNodes(Node node){
	       if (node != null){
	           otherCountNodes(node.left);
	           count++;
	           otherCountNodes(node.right);
	       }
	   }


	   private Node find_smallest(Node node){
	       if (node.left == null){
	           return node;
	       }
	       return find_smallest(node.left);
	   }


	   private Node find_largest(Node node){
	       if (node.right == null){
	           return node;
	       }
	       return find_largest(node.right);
	   }





	   private Node find_parent(Node current_root, int data){
	       if (current_root == null || data == root.data){
	           return null;
	       }
	       else if ((current_root.left != null && current_root.left.data == data) || (current_root.right != null && current_root.right.data == data)){
	           return current_root;
	       }
	       if (current_root.left != null && data < current_root.data){
	           return find_parent(current_root.left, data);
	       }
	       else if (current_root.right != null){
	           return find_parent(current_root.right, data);
	       }
	       return null;
	   }
	}
    private int size;
    public IntBinarySearchTree tree;
    
	/**
 	* Construct an empty set.
 	*/
	public TreeIntSet() {
    	size = 0;
    	tree = new IntBinarySearchTree();
	}

	/**
 	* Adds an element to the set, ensuring no duplicate elements are added.
 	*
 	* @param element - the item to add to the set
 	* @return  - true if element was added, false if it already existed.
 	*/
	public boolean add(int element) {
    	if (contains(element)){
    		return false;
    	}
    	tree.insert(element);
    	size++;
    	return true;
	}

	/**
 	* Returns the index of the element, -1 if the element does not exist in the set
 	*
 	* @param element - item to look for
 	* @return  - index of element or -1 if it does not exist
 	*/
	public boolean contains(int element) {
    	return tree.find(element);
	}

	/**
 	* Removes element from set if it exists in the set already
 	*
 	* @param element - Item to remove from the set
 	* @return  - true if element was removed, false if it did not exist in the set
 	*/
	public boolean remove(int element) {

    	// Return false if element not in the set.
    	if (!tree.delete(element)) return false;

    	size--;
    	return true;
	}

	/**
 	* @return  - the number of elements currently in the set
 	*
 	* This is NOT the same as the length of the underlying array structure
 	*/
	public int size() {
    	return size;
	}
}