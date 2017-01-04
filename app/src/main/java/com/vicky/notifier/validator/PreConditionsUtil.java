package com.vicky.notifier.validator;

import com.vicky.notifier.database.DatabaseConstants;
import com.vicky.notifier.event.EventDetails;
import com.vicky.notifier.exception.NotifierError;
import com.vicky.notifier.exception.NotifierException;

import java.util.Arrays;

/**
 * Created by Vignesh Sivakumar on 26-11-2016.
 */
public class PreConditionsUtil {

    public static void preCheck(EventDetails eventDetails) throws NotifierException {
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
