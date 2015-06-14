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

package com.andtinder.model;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class CardModel {
	public int	id;
	private String   title;
	private String   description;
	private String 	 rating;
	private String 	 price;
	private Number 	distance;
	private Drawable cardImageDrawable;
	private Drawable cardLikeImageDrawable;
	private Drawable cardDislikeImageDrawable;
	private String phone;
    private OnCardDismissedListener mOnCardDismissedListener = null;

    private OnClickListener mOnClickListener = null;

	public CardModel(String name, String price, Drawable image, String description, Number distance,String phone) {
		this.title = name;
		this.price = price;
		this.description = description;
		this.cardImageDrawable = image;
		this.distance = distance;
		this.phone = phone;
	}

	public interface OnCardDismissedListener {
        void onLike();
        void onDislike();
    }

	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}

    public interface OnClickListener {
        void OnClickListener();
    }


	public CardModel(String title, String description, Drawable cardImage,String rating) {
		this.title = title;
		this.rating = rating;
		this.description = description;
		this.cardImageDrawable = cardImage;
	}

	public CardModel(String title, String description, Bitmap cardImage) {
		this.title = title;
		this.description = description;
		this.cardImageDrawable = new BitmapDrawable(null, cardImage);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public String getRating() {
		return rating;
	}

	public String getPrice(){
		return this.price;
	}

	public Number getDistance(){
		return this.distance;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Drawable getCardImageDrawable() {
		return cardImageDrawable;
	}

	public void setCardImageDrawable(Drawable cardImageDrawable) {
		this.cardImageDrawable = cardImageDrawable;
	}

	public Drawable getCardLikeImageDrawable() {
		return cardLikeImageDrawable;
	}

	public void setCardLikeImageDrawable(Drawable cardLikeImageDrawable) {
		this.cardLikeImageDrawable = cardLikeImageDrawable;
	}

	public Drawable getCardDislikeImageDrawable() {
		return cardDislikeImageDrawable;
	}

	public void setCardDislikeImageDrawable(Drawable cardDislikeImageDrawable) {
		this.cardDislikeImageDrawable = cardDislikeImageDrawable;
	}

    public void setOnCardDismissedListener( OnCardDismissedListener listener ) {
        this.mOnCardDismissedListener = listener;
    }

    public OnCardDismissedListener getOnCardDismissedListener() {
       return this.mOnCardDismissedListener;
    }
	public String getPhone()
	{
		return this.phone;
	}
    public void setOnClickListener( OnClickListener listener ) {
        this.mOnClickListener = listener;
    }

    public OnClickListener getOnClickListener() {
        return this.mOnClickListener;
    }
}
