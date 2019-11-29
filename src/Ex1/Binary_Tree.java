package Ex1;

public class Binary_Tree {
	class BTNode {
		function p;
		BTNode left,right;
		
		public BTNode(function p2,BTNode left,BTNode right) {
			this.p=p2;
			this.left = left;
			this.right = right;
		}
		
		@Override
		public String toString() {
			return this.p.toString();
		}
	}

	private BTNode root;
	private int size;
	
	/**
	 * Constructor
	 * Complexity: O(1)
	 */
	public Binary_Tree() {
		root = null;
		size = 0;
	}
	
	/**
	 * Constructor
	 * Complexity: O(1)
	 */
	public Binary_Tree(function p) {
		root = new BTNode(p, null, null);
		size = 1;
	}
	
	/**
	 * copy constructor
	 * Complexity: O(n)
	 */
	public Binary_Tree(Binary_Tree tree) {
		root = clone(tree.root);
		size = tree.size;
	}

	private BTNode clone(BTNode node) {
		if(node == null) return null;
		return new BTNode(node.p, clone(node.left), clone(node.right));
	}
	
	/**
	 * insert data to the tree
	 * Complexity: O(log n) , O(n) - worst case
	 */
	public void insert(function p) {
		root = insert(root, p);
	}
    //Randomally side - Not Good
	private BTNode insert(BTNode node, function p) {
		if(node == null) return new BTNode(p, null, null);
		double side = Math.random();
		if(side < 0.5) node.left = insert(node.left, p);
		else node.right = insert(node.right, p);
		return node;
	}
	
	/**
	 * String represents the tree
	 * Complexity: O(n)
	 */
	@Override
	public String toString() {
		String[] ans = new String[1];
		ans[0] = "[";
		toString(root,ans);
		return ans[0] + "]";
	}
	
	private void toString(BTNode node, String[] ans) {
		if(node != null) {
			toString(node.left,ans);
			ans[0] = ans[0] + node.p + ", ";
			toString(node.right,ans);			
		}
	}

	/**
	 * Print the tree PreOrder (root - left - right)
	 * Complexity: O(n)
	 */
	public void printPreOrder() {
		printPreOrder(root);
	}

	private void printPreOrder(BTNode node) {
		if(node !=null) {
			System.out.print(node + ", ");
			printPreOrder(node.left);
			printPreOrder(node.right);
		}
	}

	/**
	 * Print the tree InOrder (left - root - right)
	 * Complexity: O(n)
	 */
	public void printInOrder() {
		printInOrder(root);
	}

	private void printInOrder(BTNode node) {
		if(node !=null) {
			printInOrder(node.left);
			System.out.print(node + ", ");
			printInOrder(node.right);
		}
	}

	/**
	 * Print the tree PostOrder (left - right - root)
	 * Complexity: O(n)
	 */
	public void printPostOrder() {
		printPostOrder(root);
	}

	private void printPostOrder(BTNode node) {
		if(node !=null) {
			printPostOrder(node.left);
			printPostOrder(node.right);
			System.out.print(node + ", ");
		}
	}	
}
