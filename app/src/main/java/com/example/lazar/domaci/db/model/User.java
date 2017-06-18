package com.example.lazar.domaci.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Lazar on 6/5/2017.
 */
@DatabaseTable(tableName = User.TABLE_NAME_USERS)
public class User implements Serializable {
    public static final String TABLE_NAME_USERS = "user";

   /* public static final String FIELD_NAME_ID= "id";
    public static final String FIELD_NAME_NAME="name";
    public static final String FIELD_NAME_EMAIL="email";
    public static final String FIELD_NAME_PASSWORD="password";
    public static final String FIELD_NAME_PICTURE="picture";
    public static final String FIELD_NAME_ADDRESS="address";
    public static final String FIELD_NAME_PHONE="phone";
    public static final String FIELD_NAME_AUCTION="auction";
    public static final String FIELD_NAME_BID="bid";*/

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String email;

    @DatabaseField
    private String password;

    @DatabaseField
    private String picture;

    @DatabaseField
    private String address;

    @DatabaseField
    private String phone;

    @ForeignCollectionField(eager = true)
    private Collection<Auction> auction;

    @ForeignCollectionField(eager=true)
    private Collection<Bid> bid;

    public User() {
    }


    public User(Long id, String name, String email, String password, String picture, String address, String phone, Collection<Auction> auction, Collection<Bid> bid) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.address = address;
        this.phone = phone;
        this.auction = auction;
        this.bid = bid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Collection<Auction> getAuction() {
        return auction;
    }

    public void setAuction(Collection<Auction> auction) {
        this.auction = auction;
    }

    public Collection<Bid> getBid() {
        return bid;
    }

    public void setBid(Collection<Bid> bid) {
        this.bid = bid;
    }
}

