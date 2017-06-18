package com.example.lazar.domaci.db;

import com.example.lazar.domaci.db.model.Auction;
import com.example.lazar.domaci.db.model.Bid;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by Lazar on 6/6/2017.
 */

public class BidDao extends BaseDaoImpl<Bid,Long>{
    public BidDao(ConnectionSource connectionSource) throws SQLException {
        super(Bid.class);
        setConnectionSource(connectionSource);
        initialize();
    }
}
