package com.andtinder.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by tomer on 6/12/15.
 */
public class CatergoryListAdapter extends ArrayAdapter<String> {

    Context mContext;
    int layoutResourceId;
    List<String> data = null;

    public CatergoryListAdapter(Context mContext, int layoutResourceId, List<String> data) {
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
        String objectItem = data.get(position);
        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView textViewItem = (TextView) convertView.findViewById(R.id.catName);
        //textViewItem.setText(objectItem);
        //System.out.println(objectItem.getString("name"));
        final String category = objectItem;
        ImageView image = (ImageView) convertView.findViewById(R.id.catImage);
        Resources res = mContext.getResources();
            if(category.equals("shirts")){
                    image.setImageDrawable(res.getDrawable(R.drawable.shirt));
                }
           else if(category.equals( "laptops")){
                image.setImageDrawable(res.getDrawable(R.drawable.laptop));
                }
            else if(category.equals( "cases")){
                image.setImageDrawable(res.getDrawable(R.drawable.cases));
                }
            else if(category.equals( "guitars")){
                image.setImageDrawable(res.getDrawable(R.drawable.guitar));
                }
            else  if(category.equals( "books")){
                image.setImageDrawable(res.getDrawable(R.drawable.book));
                }
            else  if(category.equals( "cameras")){
                image.setImageDrawable(res.getDrawable(R.drawable.camera));
                }
        else if(category.equals("watches")) {
                image.setImageDrawable(res.getDrawable(R.drawable.watch));
            }
            else if(category.equals("shoes")) {
                image.setImageDrawable(res.getDrawable(R.drawable.shoes));
            }
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(mContext, MainActivity.class);
                view.putExtra("key", category);
                mContext.startActivity(view);
            }
        });

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
