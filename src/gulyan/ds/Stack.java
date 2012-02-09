package gulyan.ds;

import java.util.NoSuchElementException;

public class Stack<T> implements OList<T> {
	
	private class Node {
		Node next;
		T elem;
		Node(Node next, T elem) {
			this.next = next;
			this.elem = elem;
		}
	}
	
	Node last;
	
	public Stack() {
		last = null;
	}

	@Override
	public void push(T e) {
		last = new Node(last, e);
	}

	@Override
	public T pop() {
		try {
			T elem = last.elem;
			last = last.next;
			return elem;
		}
		catch(NullPointerException e) {
			throw new NoSuchElementException("Stack is empty");
		}
	}

	@Override
	public T top() {
		try {
			return last.elem;
		}
		catch(NullPointerException e) {
			throw new NoSuchElementException("Stack is empty");
		}
	}

	@Override
	public boolean isEmpty() {
		return last == null;
	}

	@Override
	public void clear() {
		last = null;
	}

}
