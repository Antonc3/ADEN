package tree;

import java.util.LinkedList;
import java.util.Queue;

// TODO: Auto-generated Javadoc
/**
 * The Class MyBST.
 *
 * @param <E> the element type
 */
public class MyBST<E extends Comparable<E>> {
	
	/** The root of the BST. */
	private BSTNode<E> root;
	
	/** The size of the BST. */
	int size;
	String str;
	/**
	 * Instantiates a new MyBST .
	 */
	public MyBST() {
		// TODO: Complete this constructor
		root = null;
		size = 0;
	}
	
	// Part 1 - code and validate the insert and search methods
	
	/**
	 * Gets the root.
	 *
	 * @return the root node of the Binary Search Tree
	 */
	public BSTNode<E> getRoot() {
		return root;
	}

	
	/**
	 * Gets the size of the Binary Search Tree.
	 *
	 * @return the size of the Binary Search Tree
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Insert.
	 *
	 * @param e the element to insert into the BST
	 * @return true, if successful; false if e already exists in the BST
	 */
	public boolean insert(E e) {
		if(root == null) {
			root = new BSTNode<>(e,null);
			size++;
			return true;
		}
		BSTNode<E> cur = root;
		while((cur.data.compareTo(e) > 0 &&cur.left != null) || (cur.data.compareTo(e) < 0 && cur.right != null)) {
			if(cur.data.compareTo(e) == 0) {
				return false;
			}
			if(cur.left != null) {
				if(cur.data.compareTo(e) > 0) {
					cur = cur.left;
					continue;
				}
			}
			if(cur.right!= null) {
				if(cur.data.compareTo(e) < 0) {
					cur = cur.right;
					continue;
				}
			}
		}
		if(cur.data.compareTo(e) == 0) { 
			return false;
		}
		if(cur.left == null && cur.data.compareTo(e) > 0) {
			cur.setLeftChild(e);
			size++;
			return true;
		}
		if(cur.right == null && cur.data.compareTo(e) < 0) {
			cur.setRightChild(e);
			size++;
			return true;
		}
		// TODO: Write the insert method. Refer to the pseudocode in the 
		//       slides to help you if needed.
		return false;
	}
	
	/**
	 * Search the BST.
	 *
	 * @param e the element to search for
	 * @return true, if the element was found in the list...
	 */
	public boolean search(E e) {
		if(root == null) {
			return false;
		}
		BSTNode<E> cur = root;
		while((cur.data.compareTo(e) > 0 &&cur.left != null) || (cur.data.compareTo(e) < 0 && cur.right != null)) {
			if(cur.data.compareTo(e) == 0) {
				return true;
			}
			if(cur.left != null) {
				if(cur.data.compareTo(e) > 0) {
					cur = cur.left;
					continue;
				}
			}
			if(cur.right!= null) {
				if(cur.data.compareTo(e) < 0) {
					cur = cur.right;
					continue;
				}
			}
		}
		if(cur.data.compareTo(e) == 0) {
			return true;
		}
		return false;
		// TODO: Write the search method. Refer to the pseudocode in the 
		//       slides to help you if needed.
}

	
	// Part 2: DO NOT WORK ON THIS until assigned...	
	/**
	 * Removes the.
	 *
	 * @param e the e
	 * @return the e
	 */
	public boolean remove(E e) {
		if(root == null) {
			return false;
		}
		BSTNode<E> cur = getMatchingNode(e);
		if(cur == null) {
			return false;
		}
		size--;
		BSTNode<E> par = cur.parent;
		if(cur.left == null && cur.right == null) {
			if(root.data == e) {
				root = null;
				return true;
			}
			if(par.left != null) {
				if(par.left.data == e) {
					par.left = null;
					return true;
				}
			}
			if(par.right != null) {
				if(par.right.data == e) {
					par.right = null;
					return true;
				}
			}
		}
		if(cur.left == null || cur.right == null) {
		
			BSTNode<E> child;
			if(cur.left != null) {
				child = cur.left;
			}
			else {
				child = cur.right;
			}
			if(root.data == e) {
				child.parent = null;
				root = child;
				return true;
			}
			if(par.left != null) {
				if(par.left.data == e) {
					par.left = child;
					child.parent = par;
					return true;
				}
			}
			if(par.right != null) {
				if(par.right.data == e) {
					par.right = child;
					child.parent = par;
					return true;
				}
			}
		}
//		System.out.println("Two children!");
		BSTNode<E> leftMost = findLeftMostNode(cur.right);
//		leftMost.parent = null;
		if(leftMost.parent.left != null) {
//			System.out.println("In leftmost.parent.left != null");
			if(leftMost.parent.left.data == leftMost.data) {
				leftMost.parent.left = leftMost.right;
				if(leftMost.right != null) {
					leftMost.right.parent = leftMost.parent;
				}
//				System.out.println("reset leftmost.parent.left to leftMost.right");
			}
		}
		if(leftMost.parent.right != null) {
			if(leftMost.parent.right.data == leftMost.data) {
				leftMost.parent.right = leftMost.right;
				if(leftMost.right != null) {
					leftMost.right.parent = leftMost.parent;
				}
			}
		}
//		System.out.println("reset parent and child 1");
		leftMost.left = cur.left;
		if(cur.left != null) {
			cur.left.parent = leftMost;
		}
		leftMost.right = cur.right;
		if(cur.right != null) {
			cur.right.parent = leftMost;
		}
//		System.out.println("Finished setting children and parent for leftmost");
		if(root.data == e) {
			leftMost.parent = null;
			root = leftMost;
			return true;
		}
		if(par.left != null) {
			if(par.left.data == e) {
				par.left = leftMost;
				leftMost.parent = par;
//				System.out.println("Made it!");
				return true;
			}
		}
		if(par.right != null) {
			if(par.right.data == e) {
				par.right = leftMost;
				leftMost.parent = par;
//				System.out.println("Made IT!!");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Preorder - traverse the BST using the preorder search algorithm.
	 * This should be written recursively, and will require two overloaded
	 * methods
	 */
	public String getStr() {
		return str;
	}
	public void preorder() {
		str = "";
		preOrderHelp(root);
	}
	private void preOrderHelp(BSTNode<E> cur) {
		if(cur == null)
			return;
		str+=cur.data+",";
		preOrderHelp(cur.left);
		preOrderHelp(cur.right);
	}
	/**
	 * Inorder - traverse the BST using the inorder search algorithm.
	 * This should be written recursively, and will require two overloaded
	 * methods
	 */
	public void inorder() {
		str = "";
		inOrderHelp(root);
	}
	private void inOrderHelp(BSTNode<E> cur) {
		if(cur == null)
			return;
		inOrderHelp(cur.left);
		str+=cur.data+",";
		inOrderHelp(cur.right);
	}
	
	/**
	 * Postorder - traverse the BST using the postorder search algorithm.
	 * This should be written recursively, and will require two overloaded
	 * methods
	 */
	
	public void postorder() {
		str = "";
		postOrderHelp(root);
	}
	private void postOrderHelp(BSTNode<E> cur) {
		if(cur == null)
			return;
		postOrderHelp(cur.left);
		postOrderHelp(cur.right);
		str+=cur.data+",";
	}
	private BSTNode<E> getMatchingNode(E e){
		BSTNode<E> cur = root;
		while(cur.data != e) {
			if(cur.data.compareTo(e)>0 && cur.left != null) {
				cur = cur.left;
				continue;
			}
			else if(cur.data.compareTo(e)>0 && cur.left == null) {
				return null;
			}if(cur.data.compareTo(e)<0 && cur.right != null) {
				cur = cur.right;
				continue;
			}
			else if(cur.data.compareTo(e)<0 && cur.right== null) {
				return null;
			}
		}
		return cur;
	}
	private BSTNode<E> findLeftMostNode(BSTNode<E> c){
		BSTNode<E> cur = c;
		while(cur.left != null ) {
			cur = cur.left;
		}
		return cur;
	}
	public void levelorder() {
		Queue<BSTNode<E>> levelQ = new LinkedList<BSTNode<E>>();
		levelQ.add(root);
		str = "";
		while(!levelQ.isEmpty()) {
			BSTNode<E> cur = levelQ.remove();
			if(cur == null) {
				continue;
			}
			levelQ.add(cur.left);
			levelQ.add(cur.right);
			str+=cur.data + ",";
		}
	}
}



