package com.vicky.notifier.actions;

import android.content.Context;

import com.vicky.notifier.actions.model.EventAction;
import com.vicky.notifier.database.util.EventsDBUtil;
import com.vicky.notifier.exception.NotifierException;

/**
 * Created by Vignesh Sivakumar on 28-10-2016.
 */
public class EventDetailAction extends EventAction {

    private final String eventID;

    public EventDetailAction(Context context, String eventID) {
        super(context);
        this.eventID = eventID;
    }

    @Override
    public Object performAction() throws NotifierException {
        return EventsDBUtil.getEventDetail(eventID);
    }
}
