package com.vicky.notifier.exception;

/**
 * Created by Vignesh Sivakumar on 23-10-2016.
 */
public enum NotifierError {

    NULL_EVENT_DETAILS ("EventDetails is null"),
    NULL_EVENT_ID ("EventDetails.eventID cannot be null"),
    EVENT_DAY_RANGE_ERROR ("EventDetails.day must be between 1 and 31"),
    INVALID_EVENT_MONTH ("EventDetails.month has an invalid month name"),
    INVALID_EVENT_TYPE ("Invalid event type in action message");

    private final String message;

    NotifierError(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
