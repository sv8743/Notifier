package com.vicky.notifier.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vicky.notifier.R;

/**
 * Created by Vignesh Sivakumar on 13-01-2017.
 */
public class PeriodViewHolder extends RecyclerView.ViewHolder {

    private final TextView period;
    private final boolean isDummy;

    public PeriodViewHolder(View itemView, boolean isDummy) {
        super(itemView);
        this.period = (TextView) itemView.findViewById(R.id.period);
        this.isDummy = isDummy;
    }

    public boolean isDummy() {
        return isDummy;
    }

    public void setPeriod(String text) {
        period.setText(text);
    }
}
