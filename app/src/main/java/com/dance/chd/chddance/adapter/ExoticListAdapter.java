package com.dance.chd.chddance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dance.chd.chddance.R;
import com.dance.chd.chddance.model.Dancer;
import com.dance.chd.chddance.view.fragment.ExoticDancerList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Andy on 11/5/2016.
 */

public class ExoticListAdapter extends RecyclerView.Adapter<ExoticListAdapter.ViewHolder> {
    private List<Dancer> dancerList;
    private Context context;
    private ExoticDancerList exoticDancerList;

    public ExoticListAdapter(List<Dancer> dancerList, ExoticDancerList exoticDancerList) {
        this.dancerList = dancerList;
        this.exoticDancerList = exoticDancerList;
    }

    @Override
    public ExoticListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext().getApplicationContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.exotic_dancer_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExoticListAdapter.ViewHolder holder, int position) {
        holder.editTextQuantity.setText(String.valueOf(dancerList.get(position).getQuantity()));
        holder.itemType.setText(dancerList.get(position).getName());
        if (dancerList.get(position).getDrawable() != 0) {
            Glide.with(context).load(dancerList.get(position).getDrawable()).into(holder.exoticImageView);
        }
    }

    @Override
    public int getItemCount() {
        return dancerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.exotic_item_thumb) ImageView exoticImageView;
        @BindView(R.id.exotic_item_type) TextView itemType;
        @BindView(R.id.exotic_item_quantity_layout) LinearLayout linearLayout;
        @BindView(R.id.exotic_item_add) ImageView add;
        @BindView(R.id.exotic_item_remove) ImageView remove;
        @BindView(R.id.exotic_item_quantity) EditText editTextQuantity;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.exotic_item_add)
        public void onAdd() {
            int quantity = dancerList.get(getAdapterPosition()).getQuantity();
            dancerList.get(getAdapterPosition()).setQuantity(quantity += 1);
            editTextQuantity.setText(String.valueOf(quantity));
            exoticDancerList.updateSubtotal();
        }

        @OnClick(R.id.exotic_item_remove)
        public void onRemove() {
            int quantity = dancerList.get(getAdapterPosition()).getQuantity();
            if (quantity - 1 < 0) {
                dancerList.get(getAdapterPosition()).setQuantity(quantity);
            } else {
                dancerList.get(getAdapterPosition()).setQuantity(quantity -= 1);
            }
            editTextQuantity.setText(String.valueOf(quantity));
            exoticDancerList.updateSubtotal();
        }
    }
}
