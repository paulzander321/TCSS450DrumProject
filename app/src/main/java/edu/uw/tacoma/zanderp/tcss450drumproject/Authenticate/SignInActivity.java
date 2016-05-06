package edu.uw.tacoma.zanderp.tcss450drumproject.authenticate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.uw.tacoma.zanderp.tcss450drumproject.MainActivity;
import edu.uw.tacoma.zanderp.tcss450drumproject.R;

/**
 * Screen for user to either create a new account or sign in to an existing one. Once they've done so they are
 * brought to the main menu of the application.
 */
public class SignInActivity extends AppCompatActivity implements LoginFragment.LoginInteractionListener {

    private SharedPreferences mSharedPreferences;
    private static final String SIGN_IN_URL = "http://cssgate.insttech.washington.edu/~zanderp/login.php";
    private static final String REGISTER_URL = "http://cssgate.insttech.washington.edu/~zanderp/register.php";
    private SignInActivity that = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mSharedPreferences = getSharedPreferences(getString(R.string.LOGIN_PREFS)
                , Context.MODE_PRIVATE);
        if (!mSharedPreferences.getBoolean(getString(R.string.LOGGEDIN), false)) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new LoginFragment())
                    .commit();
        } else {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void login(String userId, String pwd) {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            //Check if login credentials are correct with Asynch task
            String url = SIGN_IN_URL + "?email=" + userId + "&pwd=" + pwd;
            new RegisterTask().execute(new String[]{url});
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                        openFileOutput(getString(R.string.LOGIN_FILE)
                                , Context.MODE_PRIVATE));
                outputStreamWriter.write("email = " + userId + ";");
                outputStreamWriter.write("password = " + pwd);
                outputStreamWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "No network connection available. Cannot authenticate user",
                    Toast.LENGTH_SHORT) .show();
        }
    }

    @Override
    public void register(String username, String email, String pwd) {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String url = REGISTER_URL + "?username=" + username + "&email=" + email
                    + "&pwd=" + pwd;
            new RegisterTask().execute(new String[]{url});
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                        openFileOutput(getString(R.string.LOGIN_FILE)
                                , Context.MODE_PRIVATE));
                outputStreamWriter.write("email = " + email + ";");
                outputStreamWriter.write("password = " + pwd);
                outputStreamWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "No network connection available. Cannot authenticate user",
                    Toast.LENGTH_SHORT) .show();
        }
    }

    /**
     * AsyncTask meant to communicate with a web service to either register or check if
     * credentials are valid for sign in.
     */
    private class RegisterTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();
                    InputStream content = urlConnection.getInputStream();
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }
                } catch (Exception e) {
                    response = "Unable to connect to remote, Reason: "
                            + e.getMessage();
                }
                finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject reader = new JSONObject(result);
                String resultStatus = reader.getString("result");
                if (resultStatus.equals("success")) {
                    mSharedPreferences
                            .edit()
                            .putBoolean(getString(R.string.LOGGEDIN), true)
                            .apply();
                    Intent i = new Intent(that, MainActivity.class);
                    startActivity(i);
                    Toast.makeText(that, "Logged in successfully!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(that, reader.getString("error"), Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                Toast.makeText(that, result + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

}
