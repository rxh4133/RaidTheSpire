package server;

public class ModifyValueException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public int modifier;
	
	public ModifyValueException(int modifier) {
		this.modifier = modifier;
	}

}
