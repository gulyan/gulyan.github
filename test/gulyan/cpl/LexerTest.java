package gulyan.cpl;

import static org.junit.Assert.*;

import java.io.StringReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LexerTest {
	
	Lexer lex;

	@Before
	public void setUp() throws Exception {
		lex = new Lexer(new StringReader("2"));
	}

	@After
	public void tearDown() throws Exception {
		lex = null;
	}

	@Test
	public final void testLexer() {
		assertNotNull(lex);
	}

	@Test
	public final void testGetNextToken() {
		String token = lex.getNextToken();
		assertNotNull(token);
	}
	
	@Test
	public final void test1() {
		String token = lex.getNextToken();
		assertEquals("2", token);
	}
	
	@Test
	public final void test2() {
		lex.getNextToken();
		String token = lex.getNextToken();
		assertNull(token);
	}
	
	@Test
	public final void test3() {
		lex = new Lexer(new StringReader("   2   "));
		String token = lex.getNextToken();
		assertEquals("2", token);
		token = lex.getNextToken();
		assertNull(token);
	}
	
	@Test
	public final void test4() {
		lex = new Lexer(new StringReader("   2  +1 "));
		String token;
		token = lex.getNextToken();
		assertEquals("2", token);
		token = lex.getNextToken();
		assertEquals("+", token);
		token = lex.getNextToken();
		assertEquals("1", token);
		token = lex.getNextToken();
		assertNull(token);
	}
	
	@Test
	public final void test5() {
		lex = new Lexer(new StringReader("   121  +59-11 "));
		String token;
		token = lex.getNextToken();
		assertEquals("121", token);
		token = lex.getNextToken();
		assertEquals("+", token);
		token = lex.getNextToken();
		assertEquals("59", token);
		token = lex.getNextToken();
		assertEquals("-", token);
		token = lex.getNextToken();
		assertEquals("11", token);
		token = lex.getNextToken();
		assertNull(token);
	}
	
	@Test
	public final void test6() {
		lex = new Lexer(new StringReader(" (((8 + 1) - (7 - 4)) / (11 - 9)) "));
		String token;
		String array[] = { "(", "(", "(", "8", "+", "1", ")", "-", "(",
				"7", "-", "4", ")", ")", "/", "(", "11", "-", "9", ")", ")"};
		for(String expected : array) {
			token = lex.getNextToken();
			assertNotNull(token);
			if(token == null)
				return;
			assertEquals(expected, token);
		}
		token = lex.getNextToken();
		assertNull(token);
	}

}
