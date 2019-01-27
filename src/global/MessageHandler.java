package global;

public abstract class MessageHandler {
	public void handleMessage(Message toBeHandled) {
		System.out.println("Incoming message: " + toBeHandled);
	}
}
