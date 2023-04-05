package org.sda.bms.repository.exception;

public class EntityUpdateFailedException extends RuntimeException{
    public EntityUpdateFailedException(String message) {
        super(message);
    }

    public EntityUpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
