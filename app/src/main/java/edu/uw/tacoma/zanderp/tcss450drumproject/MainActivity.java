package edu.uw.tacoma.zanderp.tcss450drumproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;


import edu.uw.tacoma.zanderp.tcss450drumproject.Authenticate.SignInActivity;
import edu.uw.tacoma.zanderp.tcss450drumproject.drums.Drums;
import edu.uw.tacoma.zanderp.tcss450drumproject.view.ViewRecordingsActivity;

/**
 * The main menu activity of the app. Redirects to drum playing screen. Gives menu option to
 * log out of the application.
 */
public class MainActivity extends AppCompatActivity {
    private Button btnPlayDrums;
    private Button btnCustomize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnPlayDrums = (Button)findViewById(R.id.play_drums);
        btnCustomize = (Button)findViewById(R.id.button2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Starts the drum play activity.
     * @param view the view
     */
    public void playDrums(View view){
        Intent drumset = new Intent(this, Drums.class);
        drumset.putExtra("custom",false);
        startActivity(drumset);
    }

    public void customDrums(View view){
        Intent custom = new Intent(this, Drums.class);
        custom.putExtra("custom",true);
        startActivity(custom);
    }

    public void viewMyRecordings(View view) {
        Intent viewMyRecordings = new Intent(this, ViewRecordingsActivity.class);
        viewMyRecordings.putExtra("all", false);
        startActivity(viewMyRecordings);
    }

//    public void viewUsers(View view) {
//        Intent viewUsers = new Intent(this, )
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            SharedPreferences sharedPreferences =
                    getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(getString(R.string.LOGGEDIN), false)
                    .apply();
            Intent i = new Intent(this, SignInActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            Toast.makeText(this,"Logout Successful!", Toast.LENGTH_LONG)
                    .show();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
