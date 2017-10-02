


public class BinarySearchTree<E extends Comparable<E>> {
	class Node {
		E value;
		Node leftChild = null;
		Node rightChild = null;
		Node(E value) {
			this.value = value;
		}
		@Override
		public boolean equals(Object obj) {
			if ((obj instanceof BinarySearchTree.Node) == false)
				return false;
			Node other = (BinarySearchTree.Node)obj;
			return other.value.compareTo(value) == 0 &&
					other.leftChild == leftChild && other.rightChild == rightChild;
		}
	}
	
	protected Node root = null;
	
	protected void visit(Node n) {
		System.out.println(n.value);
	}
	
	public boolean contains(E val) {
		return contains(root, val);
	}
	
	protected boolean contains(Node n, E val) {
		if (n == null) return false;
		
		if (n.value.equals(val)) {
			return true;
		} else if (n.value.compareTo(val) > 0) {
			return contains(n.leftChild, val);
		} else {
			return contains(n.rightChild, val);
		}
	}
	
	public boolean add(E val) {
		if (root == null) {
			root = new Node(val);
			return true;
		}
		return add(root, val);
	}
	
	protected boolean add(Node n, E val) {
		if (n == null) {
			return false;
		}
		int cmp = val.compareTo(n.value);
		if (cmp == 0) {
			return false; // this ensures that the same value does not appear more than once
		} else if (cmp < 0) {
			if (n.leftChild == null) {
				n.leftChild = new Node(val);
				return true;
			} else {
				return add(n.leftChild, val);
			} 	
		} else {
			if (n.rightChild == null) {
				n.rightChild = new Node(val);
				return true;
			} else {
				return add(n.rightChild, val);
			} 	
		}
	}	
	
	public boolean remove(E val) {
		return remove(root, null, val);
	}
	
	protected boolean remove(Node n, Node parent, E val) {
		if (n == null) return false;

		if (val.compareTo(n.value) == -1) {
				return remove(n.leftChild, n, val);
		} else if (val.compareTo(n.value) == 1) {
				return remove(n.rightChild, n, val);
		} else {
			if (n.leftChild != null && n.rightChild != null){
				n.value = maxValue(n.leftChild);
				remove(n.leftChild, n, n.value);
			} else if (parent == null) {
				root = n.leftChild != null ? n.leftChild : n.rightChild;
			} else if (parent.leftChild == n){
				parent.leftChild = n.leftChild != null ? n.leftChild : n.rightChild;
			} else {
				parent.rightChild = n.leftChild != null ? n.leftChild : n.rightChild;
			}
			return true;
		}
	}

	protected E maxValue(Node n) {
		if (n.rightChild == null) {
			return n.value;
	    } else {
	       return maxValue(n.rightChild);
	    }
	}

	
	/*********************************************
	 * 
	 * IMPLEMENT THE METHODS BELOW!
	 *
	 *********************************************/
	
	
	// Method #1.
	public Node findNode(E val) {
    if (val == null){
      return null;
    }

		return findNode(root, val); 
	}

  private Node findNode(Node current, E val){
    // we have reached the end of a branch
    if (current == null){
      return null;
    }

    // we have found the value
    if (current.value.compareTo(val) == 0){
      return current;
    }

    // traverse the tree
    if (current.value.compareTo(val) > 0){
      return findNode(current.leftChild, val);
    } else {
      return findNode(current.rightChild, val);
    }
  }
	
	// Method #2.
	protected int depth(E val) {
    if (val == null){
      return -1;
    }

		return findDepth(root, val, 0); 
	}

  private int findDepth(Node current, E val, int depth){
    if (current == null){
      return -1;
    }

    if (current.value.compareTo(val) == 0){
      return depth;
    }

    if (current.value.compareTo(val) > 0){
      return findDepth(current.leftChild, val, depth + 1);
    } else {
      return findDepth(current.rightChild, val, depth + 1);
    }
  }
	
	// Method #3.
	protected int height(E val) {
    if (val == null){
      return -1;
    }
	  	
		return findHeight(root, val); 
	}

  private int findHeight(Node current, E val){
    if (current == null){
      return -1;
    }

    if (current.value.compareTo(val) == 0){
      return countToNull(current, -1);
    }
  
    if (current.value.compareTo(val) > 0){
      return findHeight(current.leftChild, val);
    } else {
      return findHeight(current.rightChild, val);
    }
  } 

  private int countToNull(Node current, int height){
    if (current == null){
      return height;
    }

    int left = countToNull(current.leftChild, height + 1);
    int right = countToNull(current.rightChild, height + 1);

    if (left > 0){
      return left;
    } else {
      return right;
    }

  }


	// Method #4.
	protected boolean isBalanced(Node n) {
    if (n == null || !contains(n.value)){
      return false;
    }

    int left = countToNull(n.leftChild, -1);
    int right = countToNull(n.rightChild, -1);

    int difference = java.lang.Math.abs(left - right);

    if (difference == 0 ||
        difference == 1) {
      return true;
    }

		return false; 
	}
	
	// Method #5. .
	public boolean isBalanced() {
		return checkBalance(root); 
	}

  private boolean checkBalance(Node current){
    if (current == null){
      return true;
    }

    if (checkBalance(current.leftChild)){
      if (isBalanced(current)){
        return checkBalance(current.rightChild);

      }else  { 
        return false;
      }
    } else {
      return false;
    }

  }

}
