package com.example.lazar.domaci;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lazar.domaci.db.DatabaseHelper;
import com.example.lazar.domaci.db.UserDao;
import com.example.lazar.domaci.db.model.User;

import java.sql.SQLException;

public class UserActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        long  userId =  UserActivity.this.getIntent().getLongExtra("trenutni_user",-1);

        User user = findUserById(userId);

        TextView name= (TextView)findViewById(R.id.user_name);
        name.setText(String.valueOf(user.getName()));

        TextView email= (TextView)findViewById(R.id.user_email);
        email.setText(String.valueOf(user.getEmail()));

        TextView password= (TextView)findViewById(R.id.user_password);
        password.setText(String.valueOf(user.getPassword()));

        TextView address= (TextView)findViewById(R.id.user_address);
        address.setText(String.valueOf(user.getAddress()));

        TextView phone= (TextView)findViewById(R.id.user_phone);
        phone.setText(String.valueOf(user.getPhone()));

    }



    private User findUserById(long id){
        User user = new User();

        DatabaseHelper dh = new DatabaseHelper(UserActivity.this);

        try {
            UserDao userDao = new UserDao(dh.getConnectionSource());
            user = userDao.queryForId(id);



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


}
