package com.example.lazar.domaci.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Lazar on 6/5/2017.
 */

@DatabaseTable(tableName ="auctions")
public class Auction implements Serializable {



   // public static final String TABLE_NAME_AUCTION = "auction";
/*
    public static final String FIELD_NAME_ID="id";
    public static final String FIELD_NAME_START_PRICE= "startPrice";
    public static final String FIELD_NAME_START_DATE= "startDate";
    public static final String FIELD_NAME_END_DATE= "endDate";
    public static final String FIELD_NAME_USER= "user_id";
    public static final String FIELD_NAME_ITEM= "item";
    public static final String FIELD_NAME_BIDS= "bid";
    public static final String FIELD_NAME_Active= "active";

*/
    @DatabaseField(generatedId = true)
    private Long id;;

    @DatabaseField
    private double startPrice;

    @DatabaseField
    private Date startDate;

    @DatabaseField
    private Date endDate;

    @DatabaseField(foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    Item item;

    @DatabaseField(foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    User user;



    @ForeignCollectionField(eager = true)
    private Collection<Bid> bid;

    @DatabaseField
    private boolean active;


    public Auction(long id, double startPrice, Date startDate, Date endDate, User user, Item item, Collection<Bid> bid, boolean active) {
        this.id = id;
        this.startPrice = startPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.item = item;
        this.bid = bid;
        this.active = active;
    }
    public Auction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Collection<Bid> getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid.add(bid);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
