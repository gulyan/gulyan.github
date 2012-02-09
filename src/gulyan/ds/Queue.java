package gulyan.ds;

public class Queue<T> implements OList<T> {
	
	Stack<T> s1, s2;

	Queue() {
		s1 = new Stack<T>();
		s2 = new Stack<T>();
	}
	
	private void dump() {
		if(s2.isEmpty()) {
			while(!s1.isEmpty())
				s2.push(s1.pop());
		}
	}
	
	@Override
	public void push(T e) {
		s1.push(e);
	}
	
	@Override
	public T pop() {
		dump();
		return s2.pop();
	}

	@Override
	public T top() {
		dump();
		return s2.top();
	}

	@Override
	public boolean isEmpty() {
		return s1.isEmpty() && s2.isEmpty();
	}

	@Override
	public void clear() {
		s1.clear();
		s2.clear();
	}

}
