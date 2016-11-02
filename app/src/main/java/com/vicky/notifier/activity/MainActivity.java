package com.vicky.notifier.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.vicky.notifier.R;
import com.vicky.notifier.actions.model.EventActionFactory;
import com.vicky.notifier.actions.model.EventActionMessage;
import com.vicky.notifier.actions.model.EventType;
import com.vicky.notifier.database.util.EventsDBUtil;
import com.vicky.notifier.exception.NotifierException;
import com.vicky.notifier.view.BulkEventDetails;
import com.vicky.notifier.view.EventsAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), AddEventActivity.class));
            }
        });
        EventsDBUtil.init(this);
        try {
            initRecyclerView();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView() throws NotifierException {
        RecyclerView eventList = (RecyclerView) findViewById(R.id.events_list);
        eventList.setHasFixedSize(true);
        EventActionMessage eventActionMessage = new EventActionMessage(EventType.GET_ALL_EVENT);
        BulkEventDetails bulkEventDetails = (BulkEventDetails) EventActionFactory.getEventAction(this, eventActionMessage).performAction();
        eventList.setAdapter(new EventsAdapter(bulkEventDetails));
        eventList.setLayoutManager(getLayoutManager());
    }

    private GridLayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 2);
    }

}
