package com.example.lazar.domaci.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lazar.domaci.R;
import com.example.lazar.domaci.db.model.Item;

import java.util.List;

/**
 * Created by Lazar on 6/11/2017.
 */

public class ItemListAdapter  extends BaseAdapter {


    private Context mContext;
    private List<Item> items;

    public ItemListAdapter(Context context, List<Item> items) {


        this.mContext = context;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View viewItem, ViewGroup viewGroup) {
        View view;
        Item item = items.get(i);
        if(viewItem == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_items,null);

        }else{
            view = viewItem;
        }


        TextView titleText = (TextView) view.findViewById(R.id.item_title);
        TextView descText = (TextView) view.findViewById(R.id.item_description_);
        ImageView itemIcon = (ImageView) view.findViewById(R.id.imageView3);

        titleText.setText(item.getName());
        descText.setText(item.getDescription());
        itemIcon.setImageResource(item.getPicture());

        return view;



    }

}