/**
 * AndTinder v0.1 for Android
 *
 * @Author: Enrique López Mañas <eenriquelopez@gmail.com>
 * http://www.lopez-manas.com
 *
 * TAndTinder is a native library for Android that provide a
 * Tinder card like effect. A card can be constructed using an
 * image and displayed with animation effects, dismiss-to-like
 * and dismiss-to-unlike, and use different sorting mechanisms.
 *
 * AndTinder is compatible with API Level 13 and upwards
 *
 * @copyright: Enrique López Mañas
 * @license: Apache License 2.0
 */

package com.andtinder.demo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;
import com.dd.CircularProgressButton;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shamanland.fab.FloatingActionButton;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends HackathonActivity {

    /**
     * This variable is the container that will host our cards
     */
	private CardContainer mCardContainer;
	int id = 0;
	int i;
	int f = 9;
	CustomDialogClass cdd;
//	ProgressBar pb;
	@TargetApi(Build.VERSION_CODES.KITKAT)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.mainlayout);
		cdd=new CustomDialogClass(this);
		cdd.show();

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);
		new LongOperation().execute("");
		//pb = (ProgressBar) findViewById(R.id.loader);




	}
	public static Drawable LoadImageFromWebOperations(String url) {
		try {
			InputStream is = (InputStream) new URL(url).getContent();
			Drawable d = Drawable.createFromStream(is, "src name");
			return d;
		} catch (Exception e) {
			System.out.println(e);

			System.out.println("FAILED");
			return null;
		}
	}
	private void fetchData(String key){


	}
	private class LongOperation extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			final Resources r = getResources();
			Intent intent = new Intent(getIntent());
			String key = intent.getStringExtra("key");
			System.out.println(key);
			mCardContainer = (CardContainer) findViewById(R.id.layoutview);
			final SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(MainActivity.this);
			Parse.initialize(MainActivity.this, "5RS0RQgQ3O3fOpbjmD0oC9vuFbaCP3IskXl0C1UR", "MsR7b8iDRHQL7wHM5pL0aQ95dnKHQEe0xAveTGdQ");
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Items");
			query.whereEqualTo("category", key);
			final List<ParseObject> LikedObject = new ArrayList<ParseObject>();
			query.findInBackground(new FindCallback<ParseObject>() {
				public void done(final List<ParseObject> items, ParseException e) {
					// done down;oading
					if (e == null && f > 0) {
						for (i = 0;i<10;i++) {
							Drawable image = LoadImageFromWebOperations(items.get(i).getString("image"));
							final CardModel card = new CardModel(items.get(i).getString("name"), items.get(i).getString("price"), image, "Rating: "+items.get(i).getString("stars"));
							card.setId(i);
							System.out.println(items.get(i).getString("image"));
							Button learn = (Button) findViewById(R.id.learn);
							Button save = (Button) findViewById(R.id.done);
							save.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									GridView grid = new GridView(getApplicationContext());
									LinearLayout lin = (LinearLayout) findViewById(R.id.lin);
									grid.setColumnWidth(2);
									afterAdapter myAdapter = new afterAdapter(MainActivity.this, R.layout.after_select_item, LikedObject);
									ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(myAdapter);
									animationAdapter.setAbsListView(grid);
									grid.setAdapter(animationAdapter);
									lin.removeAllViews();
									lin.addView(grid);
								}
							});
							learn.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									try {
										Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(items.get(f).getString("href")));
										startActivity(browserIntent);
									}
									catch (Exception e){

									}
								}
							});
							card.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
								@Override
								public void onLike() {
									LikedObject.add(items.get(f));

									if (card.getId() == 9) {
										for (ParseObject item : LikedObject) {
											System.out.println(item.get("name") + " is whats left");

										}
										GridView grid = new GridView(getApplicationContext());
										LinearLayout lin = (LinearLayout) findViewById(R.id.lin);
										grid.setColumnWidth(2);
										afterAdapter myAdapter = new afterAdapter(MainActivity.this, R.layout.after_select_item, LikedObject);
										ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(myAdapter);
										animationAdapter.setAbsListView(grid);
										grid.setAdapter(animationAdapter);
										lin.removeAllViews();
										lin.addView(grid);
									}
									f--;

								}

								@Override
								public void onDislike() {

									if (card.getId() == 9) {
										for (ParseObject item : LikedObject ){
											System.out.println(item.get("name") + " is whats left");

										}
										Toast.makeText(getApplicationContext(), "Disliked!", Toast.LENGTH_SHORT).show();
										GridView grid = new GridView(getApplicationContext());
										LinearLayout lin = (LinearLayout) findViewById(R.id.lin);
										grid.setColumnWidth(2);
										LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
												LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
										grid.setLayoutParams(layoutParams);
										afterAdapter myAdapter = new afterAdapter(MainActivity.this, R.layout.after_select_item, LikedObject);
										ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(myAdapter);
										animationAdapter.setAbsListView(grid);
										grid.setAdapter(animationAdapter);
										lin.removeAllViews();
										lin.addView(grid);
									}
									f--;
								}
							});
							adapter.add(card);
						}
						//	pb.setVisibility(View.INVISIBLE);


						mCardContainer.setAdapter(adapter);
						cdd.hide();

					} else {
						System.out.println(e);
					}
				}
			});
			return "Executed";
		}

		@Override
		protected void onPostExecute(String result) {
		//cdd.hide();
			// might want to change "executed" for the returned string passed
			// into onPostExecute() but that is upto you
		}

		@Override
		protected void onPreExecute() {}

		@Override
		protected void onProgressUpdate(Void... values) {}
	}
}
