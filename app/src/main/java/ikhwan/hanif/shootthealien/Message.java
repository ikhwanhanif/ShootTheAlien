package ikhwan.hanif.shootthealien;

public class Message {
    private String messageId; // Unique identifier for the message
    private String senderId;
    private String senderName;
    private String content;
    private String timestamp;

    // Default constructor (required for Firestore)
    public Message() {
    }

    public Message(String messageId, String senderId, String senderName, String content, String timestamp) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.senderName = senderName;
        this.content = content;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
