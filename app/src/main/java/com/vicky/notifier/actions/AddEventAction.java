package com.vicky.notifier.actions;

import android.content.Context;

import com.vicky.notifier.actions.model.EventAction;
import com.vicky.notifier.database.util.EventsDBUtil;
import com.vicky.notifier.exception.NotifierException;
import com.vicky.notifier.tools.UniqueIDGenerator;
import com.vicky.notifier.view.EventDetails;

/**
 * Created by Vignesh Sivakumar on 23-10-2016.
 */
public class AddEventAction extends EventAction {

    public AddEventAction(Context context, int day, String month, String event) {
        super(context);
        String eventID = UniqueIDGenerator.getNewUUID();
        eventDetails = new EventDetails(eventID, day, month, event);
    }

    @Override
    public Object performAction() throws NotifierException {
        checkPreConditions();
        EventsDBUtil.insertEvent(eventDetails);
        return eventDetails.getEventID();
    }
}
