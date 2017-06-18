package com.example.lazar.domaci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.lazar.domaci.adapters.BidListAdapter;
import com.example.lazar.domaci.db.AuctionDao;
import com.example.lazar.domaci.db.DatabaseHelper;
import com.example.lazar.domaci.db.UserDao;
import com.example.lazar.domaci.db.model.Auction;
import com.example.lazar.domaci.db.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BidActivity extends AppCompatActivity {


    Auction auction;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_bid__list);

        Intent intent = getIntent();
        long auctionId= intent.getLongExtra("auction_id",-1);
        long userId= intent.getLongExtra("trenutni_user",-1);

        auction = getItemById(auctionId);
        user = getUserById(userId);

        ListView listView = (ListView) findViewById(R.id.bid_list);

        List bid = new ArrayList<>(auction.getBid());

        BidListAdapter adapter = new BidListAdapter(BidActivity.this, bid);
        listView.setAdapter(adapter);



    }

    private User getUserById(long i) {

        DatabaseHelper dh = new DatabaseHelper(BidActivity.this);

        try {
            UserDao userDao = new UserDao(dh.getConnectionSource());
            user = userDao.queryForId(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;


    }
    public Auction getItemById(long auctionId){

        DatabaseHelper dh = new DatabaseHelper(BidActivity.this);

        try {
            AuctionDao AuctionDao = new AuctionDao(dh.getConnectionSource());
            auction = AuctionDao.queryForId(auctionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return auction;

    }

    public void dugme_bid(View view) {

    }
}
