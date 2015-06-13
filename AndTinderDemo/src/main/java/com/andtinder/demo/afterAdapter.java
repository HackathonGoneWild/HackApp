package com.andtinder.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.ParseObject;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by tomer on 6/12/15.
 */
public class afterAdapter extends ArrayAdapter<ParseObject> {

    Context mContext;
    int layoutResourceId;
    List<ParseObject> data = null;

    public afterAdapter(Context mContext, int layoutResourceId, List<ParseObject> data) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }
        String name = data.get(position).getString("name");
        String price = data.get(position).getString("price");
        String rating = data.get(position).getString("stars");
        String image = data.get(position).getString("image");
        final String link = data.get(position).getString("href");
        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView textName = (TextView) convertView.findViewById(R.id.textName);
        TextView textPrice = (TextView) convertView.findViewById(R.id.txtPrice);
        TextView textRating = (TextView) convertView.findViewById(R.id.txtRating);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        imageView.setImageDrawable(LoadImageFromWebOperations(image));
        textName.setText(name);
        textPrice.setText(price);
        textRating.setText(rating);
        RelativeLayout back = (RelativeLayout) convertView.findViewById(R.id.background);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                mContext.startActivity(browserIntent);
            }
        });
               //textViewItem.setText(objectItem);
        //System.out.println(objectItem.getString("name"));
        Resources res = mContext.getResources();


        return convertView;

    }


    private static Drawable LoadImageFromWebOperations(String url) {
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
}
