package com.example.lazar.domaci.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.lazar.domaci.R;
import com.example.lazar.domaci.db.model.Auction;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Created by Lazar on 5/22/2017.
 */

public class AuctionsListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Auction> auctions;

    public AuctionsListAdapter(Context context, List<Auction> auctions){

        this.mContext = context;
        this.auctions = auctions;
    }




    @Override
    public int getCount() {
        return auctions.size();
    }

    @Override
    public Object getItem(int i) {
        return auctions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view1, ViewGroup viewGroup) {

        View view = view1;
        Auction auction = auctions.get(i);
        if(view == null){

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.auctions_view, null);
        }else {

            view = view1;
        }


        TextView auctionPrice= (TextView)view.findViewById(R.id.auction_price);
        TextView auctionName = (TextView)view.findViewById(R.id.auction_name);
        TextView auctionDate = (TextView)view.findViewById(R.id.auction_date);
        TextView auctionEndDate = (TextView) view.findViewById(R.id.auction_end);

        Format formater = new SimpleDateFormat("dd-MM-yyyy");
        String date1 = formater.format(auction.getStartDate());
        auctionPrice.setText(String.valueOf(auction.getStartPrice()));
        String date2 = formater.format(auction.getEndDate());
        auctionEndDate.setText(date2);
        auctionName.setText(auction.getItem().getName());
        auctionDate.setText(date1);

        return view;



}
}
