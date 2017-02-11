package com.vicky.notifier.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Vignesh Sivakumar on 27-10-2016.
 */
public class BulkEventDetails {

    private final List<EventDetails> eventsList = new ArrayList<EventDetails>();

    public void addEvent(EventDetails eventDetails) {
        eventsList.add(eventDetails);
    }

    public EventDetails get(int position) {
        return eventsList.get(position);
    }

    public int size() {
        return eventsList.size();
    }

    public void finalize() {
        Collections.sort(eventsList, new Comparator<EventDetails>() {
            @Override
            public int compare(EventDetails e1, EventDetails e2) {
                if(Integer.parseInt(e1.getYear()) > Integer.parseInt(e2.getYear())) {
                    return 1;
                } else if(Integer.parseInt(e1.getYear()) < Integer.parseInt(e2.getYear())){
                    return -1;
                } else {
                    if(e1.getMonthIndex() > e2.getMonthIndex()) {
                        return 1;
                    } else if(e1.getMonthIndex() < e2.getMonthIndex()) {
                        return -1;
                    } else {
                        if(e1.getDayAsInt() > e2.getDayAsInt()) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                }
            }
        });
    }
}
