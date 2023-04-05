package org.sda.bms.repository.exception;

public class EntityDeleteFailedException extends RuntimeException {
    public EntityDeleteFailedException(String message) {
        super(message);
    }

    public EntityDeleteFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
