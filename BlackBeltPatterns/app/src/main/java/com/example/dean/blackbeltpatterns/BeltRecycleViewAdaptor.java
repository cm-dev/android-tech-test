package com.example.dean.blackbeltpatterns;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dean.blackbeltpatterns.belt.BeltDegree;
import com.example.dean.blackbeltpatterns.belt.BeltItem;
import com.example.dean.blackbeltpatterns.belt.IBelt;
import com.example.dean.blackbeltpatterns.viewholder.BeltHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dean on 23/12/2017.
 */

public class BeltRecycleViewAdaptor extends RecyclerView.Adapter<BeltHolder>{

    private final static int HEADER_VIEW = 0;
    private final static int BELT_VIEW = 1;

    private List<IBelt> belts = new ArrayList<>();

    public BeltRecycleViewAdaptor(ArrayList<IBelt> beltIn) {
        for (IBelt belt : beltIn ) {
            BeltDegree beltDegree = (BeltDegree) belt;
            belts.add(beltDegree);
            belts.addAll(beltDegree.getItems());
        }
    }

    @Override
    public BeltHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == HEADER_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.belt_header_item_layout,
                    parent,
                    false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.belt_item_layout,
                    parent,
                    false);
        }

        return new BeltHolder(view);
    }

    @Override
    public void onBindViewHolder(BeltHolder holder, int position) {
        if (belts.get(position) instanceof BeltDegree) {
            BeltDegree beltDegree = (BeltDegree) belts.get(position);
            holder.getBeltNameTextView().setText(beltDegree.getTitle());
        } else {
            BeltItem beltItem = (BeltItem) belts.get(position);
            holder.getBeltNameTextView().setText(beltItem.getName());
        }
    }

    public int getItemViewType(int position) {
        if (belts.get(position) instanceof BeltDegree) {
            return HEADER_VIEW;
        } else {
            return BELT_VIEW;
        }
    }

    @Override
    public int getItemCount() {
        return belts.size();
    }
}
