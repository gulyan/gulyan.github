/**
 * 
 */
package gulyan.cpl;

import java.io.Reader;
import java.util.NoSuchElementException;

/**
 * @author Alexandru Guduleasa
 *
 */
public class Lexer {

	private enum State {
		INIT, DIGIT, TOKEN
	}
	
	private Reader input;
	private StringBuilder token;
	private State state;
	
	public Lexer(Reader in) {
		this.input = in;
		this.token = new StringBuilder();
		this.state = State.INIT;
	}
	
	/**
	 * Get the next token from the input
	 * @return the token string or null if no more tokens are available
	 */
	public String getNextToken() {
		int i;
		char ch;
		switch(state) {
		case INIT:
			try {
				i = input.read();
				ch = (char)i;
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			if(i < 0)
				return null;
			else if(ch == '(')
				return "(";
			else if(ch == ')')
				return ")";
			else if(ch == '*')
				return "*";
			else if(ch == '+')
				return "+";
			else if(ch == '-')
				return "-";
			else if(ch == '/')
				return "/";
			else if(ch == ' ')
				return getNextToken();
			else if(Character.isDigit(ch)) {
				state = State.DIGIT;
				token.append(ch);
				return getNextToken();
			}
			break;
		case DIGIT:
			try {
				i = input.read();
				ch = (char)i;
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			if(i < 0 || ch == ' ') {
				state = State.INIT;
				String rVal = token.toString();
				token = new StringBuilder();
				return rVal;
			}
			else if(Character.isDigit(ch)) {
				token.append(ch);
				return getNextToken();
			}
			else if(ch == '(' || ch == ')' ||
					ch == '*' || ch == '+' || ch == '-' || ch == '/') {
				state = State.TOKEN;
				String rVal = token.toString();
				token = new StringBuilder();
				token.append(ch);
				return rVal;
			}
			break;
		case TOKEN:
			state = State.INIT;
			String rVal = token.toString();
			token = new StringBuilder();
			return rVal;
		}
		throw new NoSuchElementException();
	}
	
}
