package com.example.lazar.domaci.Init;

import com.example.lazar.domaci.R;
import com.example.lazar.domaci.db.model.Auction;
import com.example.lazar.domaci.db.model.Item;
import com.example.lazar.domaci.db.model.User;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;




/**
 * Created by Lazar on 5/22/2017.
 */

public class Tool{


    public static ArrayList<Item> items = new ArrayList<Item>();
    public static ArrayList<User> users = new ArrayList<User>();
    public static ArrayList<Auction> auctions = new ArrayList<Auction>();


    public static  void InitBase(){

        User user1 = new User();

        user1.setName("Marko");
        user1.setEmail("Markovic");
        user1.setPassword("123");
        user1.setPhone("555333");
        user1.setPicture("images1");
        user1.setAddress("markova bb");
        users.add(user1);


        User user = new User();
        Item item = new Item();
        Auction auction = new Auction();
        Auction auction4 = new Auction();
        user.setName("Pera");
        user.setEmail("Peric");
        user.setPassword("123");
        user.setPhone("555333");
        user.setPicture("images1");
        user.setAddress("perina bb");



        item.setName("Knjiga");
        item.setDescription("Kao nova- ne korisceno");
        item.setPicture(R.drawable.slika_2);
        item.setSold(false);


        auction.setStartPrice(200);
        String dateString = "25-01-2017";
        String dateString1 = "25-02-2017";
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = format.parse(dateString);
            Date date1 = format.parse(dateString1);
            auction.setStartDate(date);
            auction.setEndDate(date1);
            auction.setItem(item);
            auction.setActive(true);
            auction.setUser(user);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        auction4.setStartPrice(300);
        String dateString5 = "30-03-2017";
        String dateString6 = "30-04-2017";
        DateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = format2.parse(dateString5);
            Date date1 = format2.parse(dateString6);
            auction4.setStartDate(date);
            auction4.setEndDate(date1);
            auction4.setItem(item);
            auction4.setActive(false);
            auction4.setUser(user1);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        auctions.add(auction);
        auctions.add(auction4);
        item.setAuction(auctions);
        items.add(item);


        Item item1 = new Item();
        Auction auction1 = new Auction();

        item1.setName("gitara");
        item1.setDescription("Kao nova- ne korisceno");
        item1.setPicture(R.drawable.images2);
        item1.setSold(false);


        auction1.setStartPrice(600);
        String dateString3 = "25-01-2017";
        String dateString4 = "25-02-2017";
        DateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date3 = format1.parse(dateString3);
            Date date4 = format1.parse(dateString4);
            auction1.setStartDate(date3);
            auction1.setEndDate(date4);
            auction1.setItem(item1);
            auction1.setActive(true);
            auction1.setUser(user);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        auctions.add(auction1);
        item1.setAuction(auctions);

        users.add(user);


    }

}



