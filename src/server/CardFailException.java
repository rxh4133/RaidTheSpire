package server;

public class CardFailException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public CardFailException(String m) {
		super(m);
	}
}
