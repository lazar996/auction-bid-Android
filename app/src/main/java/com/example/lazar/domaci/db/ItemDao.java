package com.example.lazar.domaci.db;

import com.example.lazar.domaci.db.model.Auction;
import com.example.lazar.domaci.db.model.Item;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by Lazar on 6/6/2017.
 */

public class ItemDao extends BaseDaoImpl<Item,Long> {
    public ItemDao(ConnectionSource connectionSource) throws SQLException {
        super(Item.class);
        setConnectionSource(connectionSource);
        initialize();
    }
}
