package com.vicky.notifier.actions.model;

import android.content.Context;

import com.vicky.notifier.actions.AddEventAction;
import com.vicky.notifier.actions.DeleteEventAction;
import com.vicky.notifier.actions.EventDetailAction;
import com.vicky.notifier.actions.GetAllEventsAction;
import com.vicky.notifier.actions.UpdateEventAction;
import com.vicky.notifier.exception.NotifierError;
import com.vicky.notifier.exception.NotifierException;

/**
 * Created by Vignesh Sivakumar on 27-10-2016.
 */
public class EventActionFactory {

    public static EventAction getEventAction(Context context, EventActionMessage message) throws NotifierException {
        switch (message.getEventType()) {
            case ADD_EVENT:
                return new AddEventAction(context, message.getDay(), message.getMonth(), message.getYear(), message.getEvent());
            case UPDATE_EVENT:
                return new UpdateEventAction(context, message.getEventID(), message.getEventValues());
            case DELETE_EVENT:
                return new DeleteEventAction(context, message.getEventID());
            case GET_ALL_EVENTS:
                return new GetAllEventsAction(context);
            case GET_EVENT_DETAIL:
                return new EventDetailAction(context, message.getEventID());
            default:
                throw new NotifierException(NotifierError.INVALID_EVENT_TYPE);
        }
    }
}
