package com.vicky.notifier.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Vignesh Sivakumar on 27-10-2016.
 */
public class BulkEventDetails {

    private final Map<Integer, EventDetails> eventsMap = new HashMap<>();
    private int position = 0;

    public void addEvent(EventDetails eventDetails) {
        eventsMap.put(position++, eventDetails);
    }

    public EventDetails get(int position) {
        return eventsMap.get(position);
    }

    public Iterator<EventDetails> iterator() {
        return eventsMap.values().iterator();
    }

    public int size() {
        return eventsMap.size();
    }
}
