package gulyan.ds;

/*
 * Implement class Queue using 2 stacks s1 and s2.
 * The queue use the following rules:
 * - all push operations are done on s1
 * - all pop and top operations are done on s2
 * - if s2 is empty pop all the elements from s1 and push them in s2
 * - queue is empty if both stacks are empty
 * 
 * Properties of stack:
 * isEmpty(nil)
 * isEmpty(push(e, L)) = false
 * top(push(e, L)) = e
 * pop(push(e, L)) = L
 * 
 * Properties of queue
 * isEmpty(nil)
 * isEmpty(push(e, L)) = false
 * top(push(e, nil)) = e
 * top(push(e, push(x, L))) = top(push(x, L))
 * pop(push(e, nil)) = nil
 * pop(push(e, push(x, L))) = push(e, pop(push(x, L)))
 * 
 * Let Q be (S1, S2)
 * Operators:
 * isEmpty((S1, S2)) = isEmpty(S1) AND isEmpty(S2)
 * push(e, (S1, S2)) = (push(e, S1), S2)
 * top((S1, nil)) = top((nil, dump(S1, nil)))
 * top((S1, push(e, S2))) = e
 * pop((S1, nil)) = pop((nil, dump(S1, nil)))
 * pop((S1, push(e, S2))) = (S1, S2)
 * dump(nil, S2) = S2
 * dump(push(e, S1), S2) = dump(S1, push(e, S2))
 * 
 * Proof:
 * 
 * Theorem:
 * dump(push*(?, push(a, nil)), S2) = push(a, S3)
 * where push*(?, S) = S or push(?, push*(?, S))
 * dump(push*(?, push(a, nil)), S2)
 * 		= dump(push*(?, push(a, nil)), push*(?, S2))
 * 		= dump(push(a, nil), push*(?, S2))
 * 		= dump(push(a, nil), S3)
 * 		= push(a, S3)
 * 
 * isEmpty((nil, nil)) = true
 * isEmpty((nil, nil)) = isEmpty(nil) AND isEmpty(nil) = true
 * 
 * isEmpty(push(e, (S1, S2))) = false
 * isEmpty(push(e, (S1, S2)))
 * 		= isEmpty((push(e, S1), S2))
 * 		= isEmpty(push(e, S1)) AND ?
 * 		= false AND ? = false
 * 
 * top(push(e, (nil, nil))) = e
 * top(push(e, (nil, nil)))
 * 		= top((push(e, nil), nil))
 * 		= top((nil, dump(push(e, nil), nil)))
 * 		= top((nil, dump(nil, push(e, nil))))
 * 		= top((nil, push(e, nil))) = e
 * 
 * top(push(e, push(x, (S1, S2)))) = top(push(x, (S1, S2)))
 * top(push(e, push(x, (S1, S2))))
 * 		= top(push(e, (push(x, S1), S2)))
 * 		= top((push(e, push(x, S1)), S2))
 * Case 1 S2 = push(y, S2')
 * top(push(e, push(x, (S1, S2))))
 * 		= top((push(e, push(x, S1)), push(y, S2')))
 * 		= y
 * top(push(x, (S1, S2)))
 * 		= top(push(x, (S1, push(y, S2'))))
 * 		= y
 * Case 2 S2 = nil
 * top(push(e, push(x, (S1, S2))))
 * 		= top((push(e, push(x, S1)), nil))
 * 		= top((nil, dump(push(e, push(x, S1)), nil)))
 * 		= top((nil, dump(push(x, S1), push(e, nil))))
 * top(push(x, (S1, S2)))
 * 		= top((push(x, S1), nil))
 * 		= top((nil, dump(push(x, S1), nil)))
 * push(x, S1) = push*(?, push(a, nil))	-- has at least one element
 * top((nil, dump(push*(?, push(a, nil)), ?))
 * 		= top((nil, push(a, S3))
 * 		= a
 * 
 * pop(push(e, (nil,nil))) = (nil, nil)
 * pop(push(e, (nil,nil)))
 * 		= pop((push(e, nil), nil))
 * 		= pop((nil, dump(push(e, nil), nil)))
 * 		= pop((nil, dump(nil, push(e, nil))))
 * 		= pop((nil, push(e, nil)))
 * 		= (nil, nil)
 * 
 * pop(push(e, push(x, (S1, S2)))) = push(e, pop(push(x, (S1, S2))))
 * pop(push(e, push(x, (S1, S2))))
 * 		= pop(push(e, (push(x, S1), S2)))
 * 		= pop((push(e, push(x, S1)), S2))
 * push(e, pop(push(x, (S1, S2))))
 * 		= push(e, pop((push(x, S1), S2)))
 * Case 1 S2 = push(y, S2')
 * pop((push(e, push(x, S1)), S2))
 * 		= pop((push(e, push(x, S1)), push(y, S2')))
 * 		= (push(e, push(x, S1)), S2')
 * push(e, pop((push(x, S1), S2)))
 * 		= push(e, pop((push(x, S1), push(y, S2'))))
 * 		= push(e, (push(x, S1), S2'))
 * 		= (push(e, push(x, S1)), S2')
 * Case 2 S2 = nil
 * pop((push(e, push(x, S1)), S2))
 * 		= pop((push(e, push(x, S1)), nil))
 * 		= pop((nil, dump(push(e, push(x, S1)), nil)))
 * 		= pop((nil, dump(push(x, S1), push(e, nil))))
 * 		= pop((nil, dump(S1, push(x, push(e, nil)))))
 * push(e, pop((push(x, S1), S2)))
 * 		= push(e, pop((push(x, S1), nil)))
 * 		= push(e, pop((nil, dump(push(x, S1), nil))))
 * 		= push(e, pop((nil, dump(S1, push(x, nil)))))
 * 		= pop((push(e, nil), dump(S1, push(x, nil))))
 * 		= pop((nil, dump(S1, push(x, push(e, nil)))))
 * 
 */

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
