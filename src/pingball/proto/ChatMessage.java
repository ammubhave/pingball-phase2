package pingball.proto;

public class ChatMessage extends Message {
    private final String message;
    private final String username;

    public ChatMessage(String username, String message) {
        this.message = message;
        this.username = username;
    }

    public String getMessage() {
        return message;
    }
    
    public String getUsername() {
        return username;
    }

    @Override
    protected String name() {
        return NAME;
    }

    @Override
    public String toLine() {
        return NAME + " " + message + " " + username;
    }

    static final String NAME = "chat";

    ChatMessage(String[] tokens) {
        try {
            this.message = tokens[1];
            this.username = tokens[2];
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid version number", e);
        }
    }

}
