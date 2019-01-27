package global;

import java.io.Serializable;

public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	public String text;
	public Object data;
	
	public Message() {
		
	}
	
	public Message(String t, Object d) {
		text = t;
		data = d;
	}
	
	public boolean textEquals(String equals) {
		return text.equals(equals);//null pointer vulnerable
	}
	
	@Override
	public String toString() {
		return "Message["+text+"][" + data + "]";
	}
}
