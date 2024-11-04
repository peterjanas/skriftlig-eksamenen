package app.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Message(int status, String message, String timestamp) {

    // Constructor without timestamp, sets timestamp to null
    public Message(int status, String message) {
        this(status, message, null);
    }

    // Constructor with timestamp, uses current time if timestamp is null
    public Message(int status, String message, String timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = (timestamp != null) ? timestamp : LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }
}
