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

@DatabaseTable(tableName = "item")
public class Item implements Serializable{

   // public static final String TABLE_NAME_ITEM = "item";

  /*  public  static final String FIELD_NAME_ID ="id";
    public  static final String FIELD_NAME_NAME ="name";
    public  static final String FIELD_NAME_DESCRIPTION ="description";
    public  static final String FIELD_NAME_PICTURE ="picture";
    public  static final String FIELD_NAME_SOLD ="sold";
    public  static final String FIELD_NAME_AUCTION ="auction";
*/
    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String description;

    @DatabaseField
    private int picture;

    @DatabaseField
    private boolean sold;

    @ForeignCollectionField(eager = true)
    private Collection<Auction> auction;


    public Item(Long id, String name, String description, int picture, boolean sold, Collection<Auction> auction) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.sold = sold;
        this.auction = auction;
    }
    public Item() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Collection<Auction> getAuction() {
        return auction;
    }

    public void setAuction(Collection<Auction> auction) {
        this.auction = auction;
    }
}
