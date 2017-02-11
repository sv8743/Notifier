package com.vicky.notifier.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vicky.notifier.R;

/**
 * Created by Vignesh Sivakumar on 13-01-2017.
 */
public class EventViewHolder extends RecyclerView.ViewHolder {

    private final TextView day;
    private final TextView event;
    private final TextView month;
    private final boolean isDummy;

    public EventViewHolder(View itemView, boolean isDummy) {
        super(itemView);
        this.day = (TextView) itemView.findViewById(R.id.day);
        this.month = (TextView) itemView.findViewById(R.id.month);
        this.event = (TextView) itemView.findViewById(R.id.event);
        this.isDummy = isDummy;
    }

    public boolean isDummy() {
        return isDummy;
    }

    public void setDay(String text) {
        day.setText(text);
    }

    public void setEvent(String text) {
        event.setText(text);
    }

    public void setMonth(String text) {
        month.setText(text);
    }

}