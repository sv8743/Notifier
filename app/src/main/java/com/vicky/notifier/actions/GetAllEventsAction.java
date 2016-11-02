package com.vicky.notifier.actions;

import android.content.Context;

import com.vicky.notifier.actions.model.EventAction;
import com.vicky.notifier.database.util.EventsDBUtil;

/**
 * Created by Vignesh Sivakumar on 28-10-2016.
 */
public class GetAllEventsAction extends EventAction {

    public GetAllEventsAction(Context context) {
        super(context);
    }

    @Override
    public Object performAction() {
        return EventsDBUtil.getAllEvents();
    }
}
