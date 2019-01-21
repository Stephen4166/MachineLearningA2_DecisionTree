package decision_tree;

class Node {
	String name;
	Attr attr;
	int depth;
	int index;
	Node left;
	Node right;
	int posi = 0;
	int nega = 0;
	
	public Node () {
		
	}
	
	public Node (int depth) {
		this.name = null;
		this.depth = depth;
	}
	
	public int getLeaf(Node root) {
		if (root.name.equals("1") || root.name.equals("0")) {
			return 1;
		}
			return getLeaf(root.left) + getLeaf(root.right);
	}
	
	public int getNodeNum(Node root) {
		if (root.name.equals("1") || root.name.equals("0")) {
			return 1;
		}
		return 1 + getNodeNum(root.left) + getNodeNum(root.right);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (index != other.index)
			return false;
		return true;
	}
	
	
}

public class Tree {
	Node root;
	
	public Tree () {
		root = new Node (0);
	}
	
    
	public static Node clone (Node root) {
		if (root == null) {
			System.out.println("Clone Error!");
			return null;
		}
		Node newRoot = new Node ();
		
		newRoot.attr = root.attr;
		newRoot.depth = root.depth;
		newRoot.index = root.index;
		newRoot.name = root.name;
		newRoot.posi = root.posi;
		newRoot.nega = root.nega;
		
		if (root.left != null) {
			newRoot.left = clone (root.left);
		}
		if (root.right != null) {
			newRoot.right = clone (root.right);
		}
		
		return newRoot;
	}
	
	
    void printTree (Node root) {
    	if (root == null) {
    		System.out.println("Error!");
    		return;
    	}
    	
    	if (root.name.equals("1") || root.name.equals("0")) {
    		System.out.println(root.name);
    		return;
    	}
    	
    	System.out.print(root.name + " = 0 : ");
    	if (root.right.name.equals("1") || root.right.name.equals("0")) {
    		System.out.print(root.right.name);
    	} else {
    		System.out.print("\n");
    		for (int i=0; i<root.right.depth; i++) {
    			System.out.print("| ");
    		}
    		printTree (root.right);
    	}
    	
    	System.out.print("\n");
    	for (int i=0; i<root.depth; i++) {
    		System.out.print ("| ");
    	}
    	System.out.print(root.name + " = 1 : ");
    	if (root.left.name.equals("1") || root.left.name.equals("0")) {
    		System.out.print(root.left.name);
    	} else {
    		System.out.print("\n");
    		for (int i=0; i<root.left.depth; i++) {
    			System.out.print("| ");
    		}
    		printTree (root.left);
    	}
    }
	
}
