package com.example.lazar.domaci.db;

/**
 * Created by Lazar on 6/5/2017.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.lazar.domaci.db.model.Auction;
import com.example.lazar.domaci.db.model.Bid;
import com.example.lazar.domaci.db.model.Item;
import com.example.lazar.domaci.db.model.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME    = "domaci.db";

    private static final int DATABASE_VERSION = 1;



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource,Item.class);
            TableUtils.createTableIfNotExists(connectionSource,User.class);
            TableUtils.createTableIfNotExists(connectionSource,Auction.class);
            TableUtils.createTableIfNotExists(connectionSource,Bid.class);
        }catch (SQLException e){
           e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource,Item.class,true);
            TableUtils.dropTable(connectionSource,User.class,true);
            TableUtils.dropTable(connectionSource,Auction.class,true);
            TableUtils.dropTable(connectionSource,Bid.class,true);
            onCreate(database,connectionSource);
        }catch (SQLException e){

            e.printStackTrace();
        }
    }

    @Override
    public void close() {


        super.close();
    }
}
