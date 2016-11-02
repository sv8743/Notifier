package com.vicky.notifier.tools;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class CardViewBuilder {
    private int contentPadding;
    private final Context context;
    private int elevation;
    private int maxElevation;
    private int radius;
    private List<TextView> textViews;

    public CardViewBuilder(Context context) {
        this.textViews = new ArrayList();
        this.context = context;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setContentPadding(int contentPadding) {
        this.contentPadding = contentPadding;
    }

    public void setMaxElevation(int maxElevation) {
        this.maxElevation = maxElevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public void addTextView(TextView textView) {
        this.textViews.add(textView);
    }

    public CardView build() {
        CardView cardView = new CardView(this.context);
        cardView.setLayoutParams(new LayoutParams(-1, -2));
        cardView.setRadius((float) this.radius);
        cardView.setContentPadding(this.contentPadding, this.contentPadding, this.contentPadding, this.contentPadding);
        cardView.setMaxCardElevation((float) this.maxElevation);
        cardView.setElevation((float) this.elevation);
        cardView.setBackgroundColor(-1);
        cardView.setUseCompatPadding(Boolean.TRUE.booleanValue());
        cardView.setPreventCornerOverlap(Boolean.FALSE.booleanValue());
        cardView.setId(View.generateViewId());
        int aboveTextViewID = -1;
        for (TextView textView : this.textViews) {
            LayoutParams params = new LayoutParams(-1, -2);
            if (aboveTextViewID != -1) {
                params.addRule(3, aboveTextViewID);
            }
            aboveTextViewID = textView.getId();
            cardView.addView(textView, params);
        }
        return cardView;
    }
}
