package tree;

// TODO: Auto-generated Javadoc
/**
 * The Class BSTNode. This is a Generic Binary Search Tree, and includes the methods for operating on 
 * that tree
 *
 * @param <E> the element type
 */
public class BSTNode<E> {
	
	/** The data. */
	E data;
	
	/** The parent. */
	BSTNode<E> parent;
	
	/** The left. */
	BSTNode<E> left;
	
	/** The right. */
	BSTNode<E> right;
	

	/**
	 * Instantiates a new BST node.
	 *
	 * @param e the e
	 * @param parent the parent
	 */
	public BSTNode(E e, BSTNode<E> parent) {
		// TODO: Complete the constructor
		this.data = e;
		this.parent = parent;
		this.left = this.right = null;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public E getData() {
		
		return data;
	}
	
	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(E data) {
		this.data = data;
	}
	
	/**
	 * Gets the left child.
	 *
	 * @return the left child
	 */
	public BSTNode<E> getLeftChild() {
		return left;
	}

	/**
	 * Gets the right child.
	 *
	 * @return the right child
	 */
	public BSTNode<E> getRightChild() {
		// TODO: Complete this method
		return right;
	}
	
	/**
	 * Creates and initializes a new left child.
	 *
	 * @param element the new left child
	 */
	public void setLeftChild(E element) {
		// TODO: Complete this method
		this.left = new BSTNode<>(element,this);
	}

	/**
	 * Creates and initializes a new right child.
	 *
	 * @param element the new right child
	 */
	public void setRightChild(E element) {
		// TODO: Complete this method
		this.right = new BSTNode<>(element,this);
	}

}
