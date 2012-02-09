package gulyan.ds;

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
		T elem = last.elem;
		last = last.next;
		return elem;
	}

	@Override
	public T top() {
		return last.elem;
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
