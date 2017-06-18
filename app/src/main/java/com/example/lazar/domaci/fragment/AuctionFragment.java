package com.example.lazar.domaci.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import android.widget.Toast;
import com.example.lazar.domaci.BidActivity;
import com.example.lazar.domaci.R;
import com.example.lazar.domaci.db.DatabaseHelper;
import com.example.lazar.domaci.db.ItemDao;
import com.example.lazar.domaci.db.UserDao;
import com.example.lazar.domaci.db.model.Auction;
import com.example.lazar.domaci.db.model.Item;
import com.example.lazar.domaci.adapters.AuctionsListAdapter;
import com.example.lazar.domaci.db.model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AuctionFragment extends Fragment {

    Item item;

    User user;

    public AuctionFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        long  itemID =  getActivity().getIntent().getLongExtra("item_id",-1);
        long  userID =  getActivity().getIntent().getLongExtra("user_id",-1);
        item = getItemById(itemID);

        user = getUserById(userID);



        List theList = new ArrayList(item.getAuction());
        View view = inflater.inflate(R.layout.fragment_auction, container, false);

        ListView listView = (ListView) view.findViewById(R.id.auctions_list_view);
        AuctionsListAdapter adapter = new AuctionsListAdapter(getActivity(), theList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Toast.makeText(getActivity(),"Lista Ponunda",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(),BidActivity.class );
                Auction auction= (Auction) adapterView.getItemAtPosition(position);
                intent.putExtra("auction_id",auction.getId());
                intent.putExtra("user_id",user);
                startActivity(intent);

            }
        });

        return view;
    }



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
