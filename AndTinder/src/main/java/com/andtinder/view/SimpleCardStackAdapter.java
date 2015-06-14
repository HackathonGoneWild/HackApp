package com.andtinder.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andtinder.R;
import com.andtinder.model.CardModel;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public final class SimpleCardStackAdapter extends CardStackAdapter {

	public SimpleCardStackAdapter(Context mContext) {
		super(mContext);
	}

	@Override
	public View getCardView(int position, CardModel model, View convertView, ViewGroup parent) {
		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			if(model.getDistance()==null) {
				convertView = inflater.inflate(R.layout.std_card_inner, parent, false);
			}
			else{
				convertView = inflater.inflate(R.layout.peer_inner, parent, false);
			}
			assert convertView != null;
		}
		((ImageView) convertView.findViewById(R.id.image)).setImageDrawable(model.getCardImageDrawable());
		((TextView) convertView.findViewById(R.id.title)).setText(model.getTitle());
		((TextView) convertView.findViewById(R.id.price)).setText(model.getDescription()+"$");
		try {
			((TextView) convertView.findViewById(R.id.rating)).setText(model.getRating());
		}catch(NullPointerException e){
			((TextView) convertView.findViewById(R.id.description)).setText(model.getPrice());
			((TextView) convertView.findViewById(R.id.distance)).setText(String.valueOf(model.getDistance() + "km"));
		}

	//	(
	//
		YoYo.with(Techniques.SlideInUp)
				.duration(1600)
				.playOn(convertView.findViewById(R.id.image));

		return convertView;
	}
}
