package com.vicky.notifier.view;

import com.vicky.notifier.event.BulkEventDetails;
import com.vicky.notifier.event.EventDetails;
import com.vicky.notifier.tools.Today;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vignesh Sivakumar on 13-01-2017.
 */
public class EventViewDetails {

    private final BulkEventDetails bulkEventDetails;
    private final Today today;

    private Map<Integer, Integer> viewTypeMap = new HashMap<Integer, Integer>();

    private Map<Integer, String> periodTextMap = new HashMap<Integer, String>();
    private Map<Integer, EventDetails> eventDetailsMap = new HashMap<Integer, EventDetails>();

    public EventViewDetails(BulkEventDetails bulkEventDetails) {
        this.bulkEventDetails = bulkEventDetails;
        today = new Today();
        initEventView();
    }

    public int getEventViewType(int position) {
        return viewTypeMap.get(position);
    }

    public EventDetails getEventDetails(int position) {
        return eventDetailsMap.get(position);
    }

    public String getPeriodText(int position) {
        return periodTextMap.get(position);
    }

    public int getItemCount() {
        return viewTypeMap.size();
    }

    private void initEventView() {
        viewTypeMap.put(0, EventsAdapter.PERIOD_TYPE);
        viewTypeMap.put(1, EventsAdapter.DUMMY_TYPE);
        periodTextMap.put(0, "Next Three Months");
        int offset = 2;
        int offsetIndex = 1;
        for(int i = 0; i < bulkEventDetails.size(); ++i) {
            EventDetails eventDetails = bulkEventDetails.get(i);
            if(Integer.parseInt(eventDetails.getYear()) == today.getYear() &&
                    eventDetails.getMonthIndex() > today.getMonth() + 2) {
                if(offsetIndex == 1) {
                    if(offset % 2 != 0) {
                        viewTypeMap.put(offset++, EventsAdapter.DUMMY_TYPE);
                    }
                    periodTextMap.put(offset, "Year " + today.getYear());
                    viewTypeMap.put(offset++, EventsAdapter.PERIOD_TYPE);
                    viewTypeMap.put(offset++, EventsAdapter.DUMMY_TYPE);
                    ++offsetIndex;
                }
            } else if(Integer.parseInt(eventDetails.getYear()) > today.getYear()) {
                if(offsetIndex == 2) {
                    if (offset % 2 != 0) {
                        viewTypeMap.put(offset++, EventsAdapter.DUMMY_TYPE);
                    }
                    periodTextMap.put(offset, "Year " + (today.getYear() + 1));
                    viewTypeMap.put(offset++, EventsAdapter.PERIOD_TYPE);
                    viewTypeMap.put(offset++, EventsAdapter.DUMMY_TYPE);
                    ++offsetIndex;
                }
            }
            eventDetailsMap.put(offset, eventDetails);
            viewTypeMap.put(offset++, EventsAdapter.EVENT_TYPE);
        }
    }
}
