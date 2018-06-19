package fi.solita.reminders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    private TextInputEditText inputDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("Add reminder");

        final TextInputLayout inputLayoutDescription = findViewById(R.id.inputLayoutDescription);
        inputDescription = (TextInputEditText) findViewById(R.id.inputDescription);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "Back pressed");
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        intent.putExtra(MainActivity.EXTRA_DESCRIPTION, inputDescription.getText().toString());
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
