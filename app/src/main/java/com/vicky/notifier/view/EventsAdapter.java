package com.vicky.notifier.view;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vicky.notifier.R;
import com.vicky.notifier.event.BulkEventDetails;
import com.vicky.notifier.event.EventDetails;

public class EventsAdapter extends Adapter<EventsAdapter.EventViewHolder> {
    private BulkEventDetails bulkEventDetails;

    public class EventViewHolder extends ViewHolder {
        protected TextView day;
        protected TextView event;
        protected TextView month;

        public EventViewHolder(View itemView) {
            super(itemView);
            this.day = (TextView) itemView.findViewById(R.id.day);
            this.month = (TextView) itemView.findViewById(R.id.month);
            this.event = (TextView) itemView.findViewById(R.id.event);
        }
    }

    public EventsAdapter(BulkEventDetails bulkEventDetails) {
        this.bulkEventDetails = bulkEventDetails;
    }

    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.events_layout, parent, false));
    }

    public void onBindViewHolder(EventViewHolder holder, int position) {
        EventDetails eventDetails = (EventDetails) this.bulkEventDetails.get(position);
        holder.day.setText(eventDetails.getDay());
        holder.month.setText(eventDetails.getMonth());
        holder.event.setText(eventDetails.getEvent());
    }

    public int getItemCount() {
        return this.bulkEventDetails.size();
    }
}
