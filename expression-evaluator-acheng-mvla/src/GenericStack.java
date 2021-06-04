import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * 
 */

/**
 * @author Anton Cheng
 *
 */
public class GenericStack<E> {

	private ArrayList<E> stack;
	public GenericStack(){
		this.stack = new ArrayList<E>();
	}
	
	
	public int getSize() {
		return stack.size();
	}
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	public E peek() throws EmptyStackException{
		if(this.isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.get(stack.size()-1);
	}
	public E pop() throws EmptyStackException{
		if(this.isEmpty()) {
			throw new EmptyStackException();
		}
		E obj = stack.get(stack.size()-1);
		stack.remove(stack.size()-1);
		return obj;
	}
	public void push(E o) {
		stack.add(o);
	}
	public void print() {
		for(int i = 0; i < stack.size(); i++) {
			System.out.print(stack.get(i) + " ");
		}
		System.out.println();
	}
	/**
	 * You need to implement the following functions
	 * a) isEmpty() - returns true if the element is empty
	 * b) getSize() - returns the size of the Stack
	 * c) peek() - returns the object that is at the top of the stack
	 * d) pop() - gets the object at the top of stack, then removes it from 
	 *            the stack and returns the object
	 * e) push(o) - adds the object to the top of stack
	 */
	
}
