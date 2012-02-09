/**
 * 
 */
package gulyan.ds;

/**
 * @author gulyan
 *
 */
public interface OList<T> {

	void push(T e);
	
	T pop();
	
	T top();
	
	boolean isEmpty();
	
	void clear();
	
}
