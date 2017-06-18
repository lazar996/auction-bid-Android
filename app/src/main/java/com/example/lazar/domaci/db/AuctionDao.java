package com.example.lazar.domaci.db;

import com.example.lazar.domaci.db.model.Auction;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by Lazar on 6/6/2017.
 */

public class AuctionDao extends BaseDaoImpl<Auction,Long> {

    public AuctionDao(ConnectionSource connectionSource) throws SQLException {
        super(Auction.class);
        setConnectionSource(connectionSource);
        initialize();
    }
}
