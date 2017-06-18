package com.example.lazar.domaci.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lazar.domaci.R;
import com.example.lazar.domaci.db.DatabaseHelper;
import com.example.lazar.domaci.db.UserDao;
import com.example.lazar.domaci.db.model.Bid;
import com.example.lazar.domaci.db.model.User;

import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Lazar on 6/16/2017.
 */

public class BidListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Bid> bids;

    public BidListAdapter(Context context, List<Bid> bids){
        this.mContext = context;
        this.bids = bids;
    }

    @Override
    public int getCount() {
        return bids.size();
    }

    @Override
    public Object getItem(int position) {
        return bids.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        Bid bid = bids.get(position);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_bid,null);
        }else{
            view = convertView;
        }

        TextView bidDate = (TextView) view.findViewById(R.id.vreme_bid);
        TextView bidPrice = (TextView) view.findViewById(R.id.cena_bid);
        TextView bidUser= (TextView) view.findViewById(R.id.user_bid);

        Format formater = new SimpleDateFormat("dd-MM-yyy");
        String date1 = formater.format(bid.getDateTime());
        User user = findUserById(bid.getUser().getId());
        bidDate.setText(date1);
        bidPrice.setText(String.valueOf(bid.getPrice()));
        bidUser.setText(String.valueOf(user.getName()));

        return view;
    }

    private User findUserById(long id){
        User user = new User();

        DatabaseHelper dh = new DatabaseHelper(mContext);

        try {
            UserDao userDao = new UserDao(dh.getConnectionSource());
            user = userDao.queryForId(id);



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}


