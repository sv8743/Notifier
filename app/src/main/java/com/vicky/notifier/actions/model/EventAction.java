package com.vicky.notifier.actions.model;

import android.content.Context;

import com.vicky.notifier.exception.NotifierException;
import com.vicky.notifier.event.EventDetails;

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

}
