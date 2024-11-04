package app.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ApiException extends RuntimeException {

    private final int status;
    private final String message;
    private final String timestamp;

    public ApiException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }
}
