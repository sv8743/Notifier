package com.vicky.notifier.activity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vicky.notifier.R;
import com.vicky.notifier.actions.model.EventActionFactory;
import com.vicky.notifier.actions.model.EventActionMessage;
import com.vicky.notifier.actions.model.EventType;
import com.vicky.notifier.actions.model.constants.EventActionMessageConstants;
import com.vicky.notifier.database.DatabaseConstants;

import java.util.Date;

public class AddEventActivity extends AppCompatActivity {

    private NotifierDateChangeListener notifierDateChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        final CalendarView calendarView = (CalendarView) findViewById(R.id.event_date);
        notifierDateChangeListener = new NotifierDateChangeListener();
        calendarView.setOnDateChangeListener(notifierDateChangeListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                EditText event = (EditText) findViewById(R.id.event);
                EventActionMessage eventActionMessage = new EventActionMessage(EventType.ADD_EVENT);
                eventActionMessage.addToMessageMap(EventActionMessageConstants.EVENT_ACTION_DAY, notifierDateChangeListener.getDay());
                eventActionMessage.addToMessageMap(EventActionMessageConstants.EVENT_ACTION_MONTH, notifierDateChangeListener.getMonth());
                eventActionMessage.addToMessageMap(EventActionMessageConstants.EVENT_ACTION_YEAR, notifierDateChangeListener.getYear());
                eventActionMessage.addToMessageMap(EventActionMessageConstants.EVENT_ACTION_EVENT, event.getText().toString());
                try {
                    View view = findViewById(R.id.action_done);
                    EventActionFactory.getEventAction(view.getContext(), eventActionMessage).performAction();
                    Toast.makeText(view.getContext(), "Yay! Event added.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(view.getContext(), MainActivity.class));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class NotifierDateChangeListener implements CalendarView.OnDateChangeListener {

        private int day;
        private String month;
        private int year;

        public NotifierDateChangeListener() {
            Date today = new Date();
            day = Integer.parseInt((String) DateFormat.format("dd", today));
            month = (String) DateFormat.format("MMM", today);
            year = Integer.parseInt((String) DateFormat.format("yyyy", today));

            TextView date = (TextView) findViewById(R.id.date_view);
            date.setText(month + " " + day + ", " + year);
        }

        @Override
        public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
            TextView date = (TextView) findViewById(R.id.date_view);
            date.setText(DatabaseConstants.MONTH_NAMES[month] + " " + day + ", " + year);
            this.day = day;
            this.month = DatabaseConstants.MONTH_NAMES[month];
            this.year = year;
        }

        public int getDay() {
            return day;
        }

        public String getMonth() {
            return month;
        }

        public int getYear() {
            return year;
        }
    }
}
