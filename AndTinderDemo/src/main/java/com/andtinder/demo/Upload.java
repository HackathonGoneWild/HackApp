package com.andtinder.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by tomer on 6/13/15.
 */
public class Upload extends HackathonActivity {
    EditText etPrice,etName,etDescription,etImage;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uplaod);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        init();
    }

    private  View.OnClickListener submitClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Parse.initialize(getApplicationContext(), "5RS0RQgQ3O3fOpbjmD0oC9vuFbaCP3IskXl0C1UR", "MsR7b8iDRHQL7wHM5pL0aQ95dnKHQEe0xAveTGdQ");
            ParseObject gameScore = new ParseObject("ItemsPeer");
            gameScore.put("name", etName.getText().toString());
            gameScore.put("description", etDescription.getText().toString());
            gameScore.put("price", etPrice.getText().toString());
            gameScore.put("image", etImage.getText().toString());
            if(gps()!=null) {
                gameScore.put("latitude", gps().getLatitude());
                gameScore.put("longtitude", gps().getLongitude());
                gameScore.saveInBackground();
            }
        }
    };

    private void init(){
        etPrice = (EditText) findViewById(R.id.Eprice);
        etName = (EditText) findViewById(R.id.name);
        etDescription = (EditText) findViewById(R.id.description);
        etImage = (EditText) findViewById(R.id.imageLink);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(submitClick);
    }

    private GPSTracker gps(){
        GPSTracker gps = new GPSTracker(Upload.this);
        if(gps.canGetLocation()){
            return gps;
        }
        else{
            Toast.makeText(getApplicationContext(),"TURN ON GPS BLACK KID MATHAFUCKA ASS BITCH",Toast.LENGTH_SHORT).show();
            gps.showSettingsAlert();
            return null;
        }
    }

}
