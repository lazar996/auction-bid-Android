package com.example.lazar.domaci.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Lazar on 6/5/2017.
 */
@DatabaseTable(tableName ="bids")
public class Bid implements Serializable {

//    public static final String TABLE_NAME_BID = "bid";


/*    public static final String FIELD_NAME_ID="id";
    public static final String FIELD_NAME_PRICE="price";
    public static final String FIELD_NAME_DATE_TIME="dateTime";
    public static final String FIELD_NAME_AUCTION="auction";
    public static final String FIELD_NAME_USER="user";*/

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private double price;

    @DatabaseField
    private Date dateTime;

    @DatabaseField(foreign = true)
     Auction auction;

    @DatabaseField(foreign = true)
    User user;




    public Bid(Long id, double price, Date dateTime, Auction auction, User user) {
        this.id = id;
        this.price = price;
        this.dateTime = dateTime;
        this.auction = auction;
        this.user = user;
    }
    public Bid() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
