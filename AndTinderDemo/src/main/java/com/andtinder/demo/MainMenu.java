package com.andtinder.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.shamanland.fab.FloatingActionButton;

/**
 * Created by tomer on 6/13/15.
 */
public class MainMenu extends HackathonActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        ImageView pin = (ImageView) findViewById(R.id.pin);
        ImageView peer = (ImageView) findViewById(R.id.shop);
        pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PeerActivity.class));

            }
        });
        peer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CategoryPicker.class));
            }
        });

    }
}
