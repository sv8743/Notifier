package com.vicky.notifier.tools;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class TextViewBuilder {
    private final Context context;
    private int padding;
    private String text;
    private int textSize;

    public TextViewBuilder(Context context) {
        this.context = context;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextView build() {
        TextView textView = new TextView(this.context);
        textView.setText(this.text);
        textView.setTextSize(1, (float) this.textSize);
        textView.setTextColor(Color.BLACK);
        textView.setPadding(this.padding, this.padding, this.padding, this.padding);
        textView.setId(View.generateViewId());
        return textView;
    }
}
