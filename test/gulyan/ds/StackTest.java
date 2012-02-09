package gulyan.ds;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StackTest {

	OList<Integer> l = null;
	
	@Before
	public void setUp() throws Exception {
		l = new Stack<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		l = null;
	}

	@Test
	public final void test1() {
		assertNotNull(l);
	}
	
	@Test
	public final void test2() {
		assertTrue(l.isEmpty());
	}
	
	@Test
	public final void test3() {
		l.push(2);
		assertFalse(l.isEmpty());
	}
	
	@Test
	public final void test4() {
		l.push(2);
		l.pop();
		assertTrue(l.isEmpty());
	}
	
	@Test
	public final void test5() {
		int x = 2;
		l.push(x);
		int a = l.top();
		int b = l.pop();
		assertEquals(x, a);
		assertEquals(x, b);
	}
	
	@Test
	public final void test6() {
		l.push(1);
		l.push(2);
		l.push(3);
		l.clear();
		assertTrue(l.isEmpty());
	}
	
	@Test
	public final void test7() {
		l.push(1);
		l.push(2);
		l.push(3);
		l.clear();
		int x = 8;
		l.push(x);
		assertFalse(l.isEmpty());
		int a = l.top();
		int b = l.pop();
		assertEquals(x, a);
		assertEquals(x, b);
	}
	
	@Test
	public final void test8() {
		l.push(1);
		l.push(2);
		l.push(3);
		int a = l.pop();
		int b = l.pop();
		int c = l.pop();
		assertTrue(l.isEmpty());
		assertEquals(3, a);
		assertEquals(2, b);
		assertEquals(1, c);
	}
	
	@Test
	public final void test9() {
		l.push(1);
		l.push(2);
		int a = l.pop();
		l.push(3);
		assertFalse(l.isEmpty());
		int b = l.pop();
		int c = l.pop();
		assertTrue(l.isEmpty());
		assertEquals(2, a);
		assertEquals(3, b);
		assertEquals(1, c);
	}

}
