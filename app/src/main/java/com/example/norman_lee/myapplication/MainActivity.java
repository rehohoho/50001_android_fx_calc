package com.example.norman_lee.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    Button buttonConvert;
    Button buttonSetExchangeRate;
    EditText editTextValue;
    TextView textViewResult;
    TextView textViewExchangeRate;
    ExchangeRate exchangeRate;

    public final String TAG = "MainActivity";
    private SharedPreferences mPreferences;
    private final String sharedPrefFile = "com.example.android.mainsharedprefs";
    public static final String RATE_KEY = "Rate_Key";
    public static final String EDIT_TEXT_KEY = "Edit_Text_Key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO 4.5 Get a reference to the sharedPreferences object
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        //TODO 4.6 Retrieve the value using the key, and set a default when there is none
        String exchangeRateStr = mPreferences.getString(RATE_KEY, "2.95");
        String editTextStr = mPreferences.getString(EDIT_TEXT_KEY, "2.95");

        //TODO 3.13 Get the intent, retrieve the values passed to it, and instantiate the ExchangeRate class
        //TODO 3.13a See ExchangeRate class --->
        Intent inIntent = getIntent();
        String inHome = inIntent.getStringExtra("home");
        String inForeign = inIntent.getStringExtra("foreign");
        if (inHome == null) {
            exchangeRate = new ExchangeRate(exchangeRateStr);
        } else {
            exchangeRate = new ExchangeRate(inHome, inForeign);
        }
        textViewExchangeRate = findViewById(R.id.textViewExchangeRate);
        textViewExchangeRate.setText(String.format("%s", exchangeRate.getExchangeRate()));

        //TODO 2.1 Use findViewById to get references to the widgets in the layout
        buttonConvert = findViewById(R.id.buttonConvert);
        editTextValue = findViewById(R.id.editTextValue);
        textViewResult = findViewById(R.id.textViewResult);

        //TODO 2.2 Assign a default exchange rate of 2.95 to the textView
        editTextValue.setText(editTextStr);

        //TODO 2.3 Set up setOnClickListener for the Convert Button
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 2.4 Display a Toast & Logcat message if the editTextValue widget contains an empty string
                String exchangeRateStr = editTextValue.getText().toString();
                if (exchangeRateStr.equals("")) {
                    Toast.makeText(getApplicationContext(), "empty editText", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "empty editText");
                }
                //TODO 2.5 If not, calculate the units of B with the exchange rate and display it
                //TODO 2.5a See ExchangeRate class --->
                else {
                    final BigDecimal res = exchangeRate.calculateAmount(exchangeRateStr);
                    textViewResult.setText(String.format("%s", res));
                }
            }
        });

        //TODO 3.1 Modify the Android Manifest to specify that the parent of SubActivity is MainActivity
        //TODO 3.2 Get a reference to the Set Exchange Rate Button
        buttonSetExchangeRate = findViewById(R.id.buttonSetExchangeRate);
        //TODO 3.3 Set up setOnClickListener for this
        buttonSetExchangeRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 3.4 Write an Explicit Intent to get to SubActivity
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                startActivity(intent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //TODO 4.1 Go to res/menu/menu_main.xml and add a menu item Set Exchange Rate
    //TODO 4.2 In onOptionsItemSelected, add a new if-statement and code accordingly

    //TODO 5.1 Go to res/menu/menu_main.xml and add a menu item Open Map App
    //TODO 5.2 In onOptionsItemSelected, add a new if-statement
    //TODO 5.3 code the Uri object and set up the intent

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
        if (id == R.id.action_set_exchange_rate) {
            Intent intent = new Intent(getApplicationContext(), SubActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_map) {
            Uri.Builder builder = new Uri.Builder()
                .scheme("geo").opaquePart("0.0").appendQueryParameter("q", getString(R.string.main_default_location));
            Uri geoLocation = builder.build();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(geoLocation);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    //TODO 4.3 override the methods in the Android Activity Lifecycle here
    //TODO 4.4 for each of them, write a suitable string to display in the Logcat

    /**
     * Open app: create, start, resume
     * Change app: pause, stop
     * Rotate: pause, stop, destroy, create, start, resume
     *   Refires same intent (fx rate same, keeps user data, resets UI)
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    //TODO 4.7 In onPause, get a reference to the SharedPreferences.Editor object
    //TODO 4.8 store the exchange rate using the putString method with a key
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");

        SharedPreferences.Editor editor = mPreferences.edit(); // builder
        editor.putString(RATE_KEY, exchangeRate.getExchangeRate().toString());
        editor.putString(EDIT_TEXT_KEY, editTextValue.getText().toString());
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
