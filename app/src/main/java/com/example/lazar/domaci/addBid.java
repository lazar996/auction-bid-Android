package com.example.lazar.domaci;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.lazar.domaci.db.AuctionDao;
import com.example.lazar.domaci.db.BidDao;
import com.example.lazar.domaci.db.DatabaseHelper;
import com.example.lazar.domaci.db.UserDao;
import com.example.lazar.domaci.db.model.Auction;
import com.example.lazar.domaci.db.model.Bid;
import com.example.lazar.domaci.db.model.Item;
import com.example.lazar.domaci.db.model.User;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Lazar on 6/17/2017.
 */

public class addBid  extends AppCompatActivity{

    EditText priceEditText;
    Button newBid;
    Bid bid = new Bid();
    private static int NOTIFICATION_ID = 1;
    DatabaseHelper dh = new DatabaseHelper(addBid.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        priceEditText = (EditText) findViewById(R.id.txtinput);
        newBid = (Button) findViewById(R.id.btdone);

        Intent lastInent = getIntent();

        long auctionId = lastInent.getLongExtra("auction_id",-1);
        long userId = lastInent.getLongExtra("user_id", -1);
        final double currentPrice = lastInent.getDoubleExtra("current_price",1);

        final User user = findUserById(userId);
        final Auction auction = findAuctionById(auctionId);

        newBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double price = Double.parseDouble(priceEditText.getText().toString());
                if(price > currentPrice){

                    bid.setAuction(auction);
                    bid.setUser(user);
                    bid.setDateTime(new Date());
                    bid.setPrice(price);
                    auction.setBid(bid);
                    try {
                        BidDao bidDao = new BidDao(dh.getConnectionSource());
                        bidDao.createIfNotExists(bid);
                        Intent intent = new Intent();
                        intent.putExtra("new_current_price",price);
                        setResult(RESULT_OK, intent);
                        Toast.makeText(addBid.this,"uspesno",Toast.LENGTH_SHORT).show();

                        NotificationCompat.Builder builder = new NotificationCompat.Builder(addBid.this);
                        Bitmap bitmap = BitmapFactory.decodeResource(addBid.this.getResources(), R.drawable.auction);
                        builder.setSmallIcon(R.drawable.plus);
                        builder.setContentTitle(String.valueOf(price));
                        builder.setContentText("vasa cena je najveca");
                        builder.setLargeIcon(bitmap);
                        NotificationManager manager = (NotificationManager)addBid.this.getSystemService(Context.NOTIFICATION_SERVICE);
                        manager.notify(NOTIFICATION_ID, builder.build());
                        finish();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else
                    Toast.makeText(addBid.this,"Trenutna cena je veca",Toast.LENGTH_SHORT).show();


            }
        });
    }


    private User findUserById(long id){
        User user = new User();

        DatabaseHelper dh = new DatabaseHelper(addBid.this);

        try {
            UserDao userDao = new UserDao(dh.getConnectionSource());
            user = userDao.queryForId(id);



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    private Auction findAuctionById(long id){
        Auction auction= new Auction();

        DatabaseHelper dh = new DatabaseHelper(addBid.this);

        try {
            AuctionDao auctionDaoc= new AuctionDao(dh.getConnectionSource());
            auction = auctionDaoc.queryForId(id);



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return auction;
    }

}
