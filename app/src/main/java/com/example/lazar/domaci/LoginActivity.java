package com.example.lazar.domaci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.lazar.domaci.db.DatabaseHelper;
import com.example.lazar.domaci.db.UserDao;
import com.example.lazar.domaci.db.model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private User user;
    private EditText username_;
    private EditText password_;
    private Button dugmeLogin;
    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_ = (EditText) findViewById(R.id.username);
        password_ = (EditText) findViewById(R.id.password);
        dugmeLogin = (Button) findViewById(R.id.button);
        users = getUsers();

    dugmeLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String username = username_.getText().toString();
            String password = password_.getText().toString();

            user = login(username, password);
            if(user != null){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("trenutni_user",user.getId());
                startActivity(intent);

            }else{

                recreate();
            }

        }
    });
}

    private List<User> getUsers(){
        DatabaseHelper dh = new DatabaseHelper(LoginActivity.this);

        try {
            UserDao userDao = new UserDao(dh.getConnectionSource());
            users = userDao.queryForAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return users;
    }

    private User login(String usern, String passwo){


        for(User user: users){

            if(user.getName().equals(usern)&& user.getPassword().equals(passwo)){

                return user;
            }
        }

        return null;
    }


    public void clickCancel(View view) {
        finish();
    }

    public void initDateBase(View view) {

        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);

    }
}


