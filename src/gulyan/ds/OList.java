/**
 * 
 */
package gulyan.ds;

/**
 * Ordered list.
 * 
 * The elements are removed based on a strict rule.
 * 
 * 
 * @author Alexandru Guduleasa
 *
 * @param <T> element type
 */
public interface OList<T> {

	/**
	 * Add element to list
	 * @param e element
	 */
	void push(T e);
	
	/**
	 * Get next element from list and remove it from list
	 * @see #top top
	 * @return element
	 */
	T pop();
	
	/**
	 * Get next element from list
	 * @see #pop pop
	 * @return element
	 */
	T top();
	
	/**
	 * Test if list is empty
	 * @return true if list is empty, false otherwise
	 */
	boolean isEmpty();
	
	/**
	 * Remove all elements from list
	 */
	void clear();
	
}
