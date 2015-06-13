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
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class MainActivity extends HackathonActivity {

    /**
     * This variable is the container that will host our cards
     */
	private CardContainer mCardContainer;
	int id = 0;
//	ProgressBar pb;
	@TargetApi(Build.VERSION_CODES.KITKAT)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.mainlayout);
		//pb = (ProgressBar) findViewById(R.id.loader);
		Intent intent = new Intent(getIntent());
		String key = intent.getStringExtra("key");
		System.out.println(key);
		mCardContainer = (CardContainer) findViewById(R.id.layoutview);
		fetchData(key);



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

		final Resources r = getResources();

		final SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);
		Parse.initialize(this, "5RS0RQgQ3O3fOpbjmD0oC9vuFbaCP3IskXl0C1UR", "MsR7b8iDRHQL7wHM5pL0aQ95dnKHQEe0xAveTGdQ");
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Items");
		query.whereEqualTo("category", key);
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> items, ParseException e) {
				if (e == null) {
					for (int i = 0;i<10;i++) {
						Drawable image = LoadImageFromWebOperations(items.get(i).getString("image"));
						final CardModel card = new CardModel(items.get(i).getString("name"), items.get(i).getString("price"), image);
						card.setId(i);
						System.out.println(items.get(i).getString("image"));
						card.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
							@Override
							public void onLike() {
								Toast.makeText(getApplicationContext(), "Liked!" + card.getId(), Toast.LENGTH_SHORT).show();
							}

							@Override
							public void onDislike() {
								Toast.makeText(getApplicationContext(), "Disliked!", Toast.LENGTH_SHORT).show();
							}
						});
						adapter.add(card);
					}
					//	pb.setVisibility(View.INVISIBLE);
					mCardContainer.setAdapter(adapter);
				} else {
					System.out.println(e);
				}
			}
		});
	}
}
