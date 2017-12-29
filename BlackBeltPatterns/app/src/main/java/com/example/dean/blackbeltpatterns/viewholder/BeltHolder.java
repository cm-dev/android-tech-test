package com.example.dean.blackbeltpatterns.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dean.blackbeltpatterns.R;

/**
 * Created by Dean on 23/12/2017.
 */

public class BeltHolder extends RecyclerView.ViewHolder {

    private TextView mBeltNameTextView;

    public BeltHolder(View itemView) {
        super(itemView);
        mBeltNameTextView = itemView.findViewById(R.id.title_txt);
    }

    public TextView getBeltNameTextView() {
        return mBeltNameTextView;
    }

    public void setBeltNameTextview(TextView mBeltNameTextView) {
        this.mBeltNameTextView = mBeltNameTextView;
    }
}

