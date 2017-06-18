package com.example.lazar.domaci;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.lazar.domaci.adapters.AuctionsListAdapter;
import com.example.lazar.domaci.db.model.Auction;

import java.util.ArrayList;

/**
 * Created by Lazar on 5/7/2017.
 */

public class AuctionsActivity extends AppCompatActivity {

    private ArrayList<Auction> auctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auctions);

        auctions = (ArrayList<Auction>) getIntent().getSerializableExtra("auctions");

        ListView listView = (ListView) findViewById(R.id.auction_list);
        AuctionsListAdapter adapter = new AuctionsListAdapter(AuctionsActivity.this, auctions);
        listView.setAdapter(adapter);
    }



}
