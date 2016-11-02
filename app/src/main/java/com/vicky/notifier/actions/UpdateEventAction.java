package com.vicky.notifier.actions;

import android.content.Context;

import com.vicky.notifier.actions.model.EventAction;
import com.vicky.notifier.database.util.EventsDBUtil;
import com.vicky.notifier.exception.NotifierException;

import java.util.Map;

/**
 * Created by Vignesh Sivakumar on 23-10-2016.
 */
public class UpdateEventAction extends EventAction {

    private final Map<String, String> updateMap;
    private final String eventID;

    public UpdateEventAction(Context context, String eventID, Map<String, String> values) {
        super(context);
        this.updateMap = values;
        this.eventID = eventID;
    }

    @Override
    public Object performAction() throws NotifierException {
        EventsDBUtil.updateEvent(eventID, updateMap);
        return null;
    }
}
