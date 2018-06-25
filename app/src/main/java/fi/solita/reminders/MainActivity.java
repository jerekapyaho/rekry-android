package fi.solita.reminders;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    private ReminderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.remindersRecyclerView);
        recyclerView.setHasFixedSize(true);

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Reminder> reminders = new ArrayList<>();
        reminders.add(new Reminder("Buy some milk"));
        reminders.add(new Reminder("Walk the dog"));
        reminders.add(new Reminder("Mow the lawn"));

        adapter = new ReminderAdapter(reminders);
        recyclerView.setAdapter(adapter);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public static final String EXTRA_DESCRIPTION = "fi.solita.reminders.DESCRIPTION";

    static final int ADD_REMINDER_REQUEST = 1;

    private void addReminder() {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivityForResult(intent, ADD_REMINDER_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: requestCode = " + requestCode + ", resultCode = " + resultCode);
        if (requestCode == ADD_REMINDER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Log.d(TAG, "Returned from detail intent with result");
                String description = data.getStringExtra(EXTRA_DESCRIPTION);
                Reminder reminder = new Reminder(description);
                adapter.add(0, reminder);
            }
            else {
                Log.d(TAG, "resultCode = " + resultCode);
            }
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
}
