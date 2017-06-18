package com.example.lazar.domaci.fragment;


import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lazar.domaci.MainActivity;
import com.example.lazar.domaci.R;
import com.example.lazar.domaci.addBid;
import com.example.lazar.domaci.db.DatabaseHelper;
import com.example.lazar.domaci.db.ItemDao;
import com.example.lazar.domaci.db.UserDao;
import com.example.lazar.domaci.db.model.Auction;
import com.example.lazar.domaci.db.model.Bid;
import com.example.lazar.domaci.db.model.Item;
import com.example.lazar.domaci.db.model.User;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {


    private static int NOTIFICATION_ID = 1;
    Auction auction;
    User user;
    Item item;

    public DetailsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);



        TextView textView = (TextView)view.findViewById(R.id.item_name);

        long  itemID =  getActivity().getIntent().getLongExtra("item_id",-1);
        final long  userId =  getActivity().getIntent().getLongExtra("trenutni_user",-1);

        item= getItemById(itemID);

        auction = getActiveAuction(item);

        user = getUserById(auction.getUser().getId());

        getActivity().setTitle(item.getName());

        textView.setText(item.getName());



        TextView description= (TextView) view.findViewById(R.id.item_description);
        description.setText(item.getDescription());


        ImageView imageView= (ImageView)view.findViewById(R.id.imageViewPhoto);
        imageView.setImageResource(item.getPicture());

        final double current = getCurrentPrice(auction);


       FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.bid_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"cena je RSD valuti",Toast.LENGTH_LONG).show();
                final int resoult = 1;
                Intent intent = new Intent(getActivity(), addBid.class);
                intent.putExtra("auction_id", auction.getId());
                intent.putExtra("current_price", current);
                intent.putExtra("user_id",userId);
                startActivityForResult(intent,resoult);

            }
        });
        return view;}

    public Item getItemById(long itemId){

        DatabaseHelper dh = new DatabaseHelper(getActivity());

        try {
            ItemDao itemDao = new ItemDao(dh.getConnectionSource());
            item = itemDao.queryForId(itemId);


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return item;

    }


    private Auction getActiveAuction(Item item){


        for(Auction auction: item.getAuction()
                ){
            if(auction.isActive()){

                return auction;
            }
        }

        return null;
    }

    private double getCurrentPrice(Auction auction){

        ArrayList<Double> prices = new ArrayList<Double>();

        if (auction.getBid().isEmpty()){

            return auction.getStartPrice();
        }
        else{

            for(Bid bid: auction.getBid()){
                prices.add(bid.getPrice());
            }

            return Collections.max(prices);
        }
    }

    private User getUserById(long i) {

        DatabaseHelper dh = new DatabaseHelper(getActivity());

        try {
            UserDao userDao = new UserDao(dh.getConnectionSource());
            user = userDao.queryForId(i);


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;


    }
    }







