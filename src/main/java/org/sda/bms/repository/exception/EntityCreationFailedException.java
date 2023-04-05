package org.sda.bms.repository.exception;

public class EntityCreationFailedException extends RuntimeException {
    public EntityCreationFailedException(String message) {
        super(message);
    }

    public EntityCreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
