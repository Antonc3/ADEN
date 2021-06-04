public class LLNode<E> {
	 LLNode<E> prev;
	 LLNode<E> next;
	 E data;

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	// TODO: Add a parameterized constructor that also inserts the node 
	//       in the correct location in the list
	public LLNode(E e, int ind, LLNode<E> nde) {
		LLNode<E> cur = nde;
		for(int i =0; i < ind; i++) {
			cur = cur.next;
		}
		this.data = e;
		this.prev = cur;
		this.next = cur.next;
		cur.next.prev = this;
		cur.next = this;
	}
}
