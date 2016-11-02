package com.vicky.notifier.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.vicky.notifier.R;
import com.vicky.notifier.actions.model.EventActionFactory;
import com.vicky.notifier.actions.model.EventActionMessage;
import com.vicky.notifier.actions.model.EventType;
import com.vicky.notifier.actions.model.constants.EventActionMessageConstants;
import com.vicky.notifier.database.DatabaseConstants;

public class AddEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        final CalendarView calendarView = (CalendarView) findViewById(R.id.event_date);
        final NotifierDateChangeListener notifierDateChangeListener = new NotifierDateChangeListener();
        calendarView.setOnDateChangeListener(notifierDateChangeListener);

        Button addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText event = (EditText) findViewById(R.id.event);
                EventActionMessage eventActionMessage = new EventActionMessage(EventType.ADD_EVENT);
                eventActionMessage.addToMessageMap(EventActionMessageConstants.EVENT_ACTION_DAY, notifierDateChangeListener.getDay());
                eventActionMessage.addToMessageMap(EventActionMessageConstants.EVENT_ACTION_MONTH, notifierDateChangeListener.getMonth());
                eventActionMessage.addToMessageMap(EventActionMessageConstants.EVENT_ACTION_EVENT, event.getText().toString());
                try {
                    EventActionFactory.getEventAction(view.getContext(), eventActionMessage).performAction();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    class NotifierDateChangeListener implements CalendarView.OnDateChangeListener {

        private int day, month, year;

        @Override
        public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
            TextView date = (TextView) findViewById(R.id.date_view);
            date.setText(DatabaseConstants.MONTH_NAMES[month] + " " + day + ", " + year);
            this.day = day;
            this.month = month;
            this.year = year;
        }

        public int getDay() {
            return day;
        }

        public int getMonth() {
            return month;
        }

        public int getYear() {
            return year;
        }
    }
}
