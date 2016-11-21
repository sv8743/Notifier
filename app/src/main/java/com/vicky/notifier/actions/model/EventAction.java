package com.vicky.notifier.actions.model;

import android.content.Context;

import com.vicky.notifier.database.DatabaseConstants;
import com.vicky.notifier.database.DatabaseHelper;
import com.vicky.notifier.exception.NotifierError;
import com.vicky.notifier.exception.NotifierException;
import com.vicky.notifier.view.EventDetails;

import java.util.Arrays;

/**
 * Created by Vignesh Sivakumar on 23-10-2016.
 */
public class EventAction {

    protected EventDetails eventDetails = null;
    protected final Context context;

    public EventAction(Context context) {
        this.context = context;
    }

    public Object performAction() throws NotifierException {
        throw new RuntimeException("Method not implemented");
    }

    protected void checkPreConditions() throws NotifierException {
        if(eventDetails == null) {
            throw new NotifierException(NotifierError.NULL_EVENT_DETAILS);
        } else {
            String eventID = eventDetails.getEventID();
            if(eventID == null) {
                throw new NotifierException(NotifierError.NULL_EVENT_ID);
            }
            int day = eventDetails.getDayAsInt();
            if(day < 1 || day > 31) {
                throw new NotifierException(NotifierError.EVENT_DAY_RANGE_ERROR);
            }

            String month = eventDetails.getMonth();
            if(!Arrays.asList(DatabaseConstants.MONTH_NAMES).contains(month)) {
                throw new NotifierException(NotifierError.INVALID_EVENT_MONTH);
            }
        }
    }
}
