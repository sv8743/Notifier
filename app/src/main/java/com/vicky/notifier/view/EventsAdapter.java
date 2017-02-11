package com.vicky.notifier.view;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vicky.notifier.R;
import com.vicky.notifier.event.BulkEventDetails;
import com.vicky.notifier.event.EventDetails;

public class EventsAdapter extends Adapter<ViewHolder> {

    static final int PERIOD_TYPE = 0;
    static final int EVENT_TYPE = 1;
    static final int DUMMY_TYPE = 2;

    private EventViewDetails eventViewDetails;

    public EventsAdapter(BulkEventDetails bulkEventDetails) {
        this.eventViewDetails = new EventViewDetails(bulkEventDetails);
    }

    public int getItemViewType(int position) {
        return eventViewDetails.getEventViewType(position);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case EVENT_TYPE:
                return new EventViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.events_layout, parent, false), false);
            case PERIOD_TYPE:
                return new PeriodViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.period_layout, parent, false), false);
            case DUMMY_TYPE:
                return new PeriodViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.period_layout, parent, false), true);
        }
        return null;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if (viewHolder != null) {
            if (viewHolder instanceof PeriodViewHolder) {
                if(!((PeriodViewHolder) viewHolder).isDummy()) {
                    ((PeriodViewHolder) viewHolder).setPeriod(
                            eventViewDetails.getPeriodText(position));
                }
            } else {
                if(!((EventViewHolder) viewHolder).isDummy()) {
                    EventDetails eventDetails = eventViewDetails.getEventDetails(position);
                    if (eventDetails != null) {
                        ((EventViewHolder) viewHolder).setDay(eventDetails.getDay());
                        ((EventViewHolder) viewHolder).setMonth(eventDetails.getMonth());
                        ((EventViewHolder) viewHolder).setEvent(eventDetails.getEvent());
                    }
                }
            }
        }
    }

    public int getItemCount() {
        return eventViewDetails.getItemCount();
    }
}
