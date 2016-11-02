package com.vicky.notifier.exception;

public class NotifierException extends Exception {

    private final NotifierError error;

    public NotifierException(NotifierError error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error.message();
    }

    @Override
    public String toString() {
        return error.name() + ":" + error.message();
    }
}
