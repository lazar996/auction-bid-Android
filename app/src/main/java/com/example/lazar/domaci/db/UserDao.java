package com.example.lazar.domaci.db;

import com.example.lazar.domaci.db.model.Auction;
import com.example.lazar.domaci.db.model.User;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by Lazar on 6/6/2017.
 */

public class UserDao extends BaseDaoImpl<User,Long> {
    public UserDao(ConnectionSource connectionSource) throws SQLException {
        super(User.class);
        setConnectionSource(connectionSource);
        initialize();
    }
}
