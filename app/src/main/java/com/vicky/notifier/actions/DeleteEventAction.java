package com.vicky.notifier.actions;

import android.content.Context;

import com.vicky.notifier.actions.model.EventAction;
import com.vicky.notifier.database.util.EventsDBUtil;
import com.vicky.notifier.exception.NotifierException;

/**
 * Created by Vignesh Sivakumar on 23-10-2016.
 */
public class DeleteEventAction extends EventAction {

    private final String eventID;

    public DeleteEventAction(Context context, String eventID) {
        super(context);
        this.eventID = eventID;
    }

    @Override
    public Object performAction() throws NotifierException {
        EventsDBUtil.deleteEvent(eventID);
        return null;
    }
}
