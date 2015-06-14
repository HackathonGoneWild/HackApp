package com.andtinder.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shamanland.fab.FloatingActionButton;
import com.shamanland.fab.ShowHideOnScroll;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomer on 6/12/15.
 */
public class CategoryPicker extends HackathonActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catergory_picker);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Fetchcategories(this);
    }

    private void Fetchcategories(final Context context) {
        final GridView lv = (GridView) findViewById(R.id.categories);
        Parse.initialize(this, "5RS0RQgQ3O3fOpbjmD0oC9vuFbaCP3IskXl0C1UR", "MsR7b8iDRHQL7wHM5pL0aQ95dnKHQEe0xAveTGdQ");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("categories");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> result, ParseException e) {
                List<String> items = new ArrayList<String>();
                for(ParseObject item : result){
                    items.add(item.getString("name"));
                }
                CatergoryListAdapter myAdapter = new CatergoryListAdapter(context, R.layout.catergory_item, items);
                ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(myAdapter);
                animationAdapter.setAbsListView(lv);
                lv.setAdapter(animationAdapter);
            }
        });
    }
}