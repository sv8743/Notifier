package com.vicky.notifier.actions.model;

import com.vicky.notifier.actions.model.constants.EventActionMessageConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Vignesh Sivakumar on 27-10-2016.
 */
public class EventActionMessage {

    private EventType eventType;
    private Map<String, Object> messageMap = new HashMap<String, Object>();

    public EventActionMessage(EventType eventType) {
        this.eventType = eventType;
    }

    public void addToMessageMap(String key, Object value) {
        messageMap.put(key, value);
    }

    public EventType getEventType() {
        return eventType;
    }

    public Integer getDay() {
        return (Integer) messageMap.get(EventActionMessageConstants.EVENT_ACTION_DAY);
    }

    public String getMonth() {
        return (String) messageMap.get(EventActionMessageConstants.EVENT_ACTION_MONTH);
    }

    public Integer getYear() {
        return (Integer) messageMap.get(EventActionMessageConstants.EVENT_ACTION_YEAR);
    }

    public String getEvent() {
        return (String) messageMap.get(EventActionMessageConstants.EVENT_ACTION_EVENT);
    }

    public String getEventID() {
        return (String) messageMap.get(EventActionMessageConstants.EVENT_ACTION_EVENT_ID);
    }

    public Map<String, String> getEventValues() {
        return (Map<String, String>) messageMap.get(EventActionMessageConstants.EVENT_ACTION_VALUES);
    }
}
