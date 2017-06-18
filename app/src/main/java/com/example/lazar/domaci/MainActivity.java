package com.example.lazar.domaci;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.lazar.domaci.Init.Tool;
import com.example.lazar.domaci.adapters.ItemListAdapter;
import com.example.lazar.domaci.db.AuctionDao;
import com.example.lazar.domaci.db.DatabaseHelper;
import com.example.lazar.domaci.db.ItemDao;
import com.example.lazar.domaci.db.UserDao;
import com.example.lazar.domaci.db.model.Auction;
import com.example.lazar.domaci.db.model.Item;
import java.sql.SQLException;
import java.util.List;


public class MainActivity extends AppCompatActivity {



    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToogle;
    private List<Item> items;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ItemListAdapter adapter = new ItemListAdapter(MainActivity.this,sqlRead());
        listView = (ListView) findViewById(R.id.thelistView);

        listView.setAdapter(adapter);

        final long  userId =  MainActivity.this.getIntent().getLongExtra("trenutni_user",-1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, "Click Item " +position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                Item item= (Item) parent.getItemAtPosition(position);
                intent.putExtra("item_id", item.getId());
                intent.putExtra("trenutni_user",userId);
                startActivity(intent);
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);




    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToogle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return  true;

        } else if (id == R.id.exit) {
            finish();
            return true;
        }else  if (id == R.id.init_database){
            onInitDatabaseClick();

        }
        return super.onOptionsItemSelected(item);
    }


    public void clickSettings(MenuItem item) {
        startActivity(new Intent(this, SettingsActivity.class));


    }

    public void dugme_bid(View view) {



    }
    public List<Item> sqlRead(){

        long firstId = 0;


        DatabaseHelper dh = new DatabaseHelper(MainActivity.this);

        try {

            ItemDao itemDao = new ItemDao(dh.getConnectionSource());
            AuctionDao auctionDao = new AuctionDao(dh.getConnectionSource());

            items = itemDao.queryForAll();
            for (Item item : items){
                Log.i("Script",item.getName() + "   " + item.getDescription());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public void onInitDatabaseClick() {

        DatabaseHelper dh = new DatabaseHelper(MainActivity.this);

        try {
            ItemDao itemDao = new ItemDao(dh.getConnectionSource());
            AuctionDao auctionDao = new AuctionDao(dh.getConnectionSource());
            UserDao userDao = new UserDao(dh.getConnectionSource());

            for(Item item1 : Tool.items){
                int resoult = itemDao.create(item1);

                if(resoult == 1){
                    for(Auction auction : item1.getAuction()){

                        Log.i("Auction Error", auction.getEndDate() + "  " + auction.getStartPrice()+ " "+auction.getBid());
                        auctionDao.create(auction);
                        userDao.createIfNotExists(auction.getUser());

                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        Intent intent = new Intent(MainActivity.this,MainActivity.class);
        startActivity(intent);


    }

    public void clickUser(MenuItem item) {


        long  userId =  MainActivity.this.getIntent().getLongExtra("trenutni_user",-1);

        Intent intent = new Intent(MainActivity.this,UserActivity.class);
        intent.putExtra("trenutni_user",userId);
        startActivity(intent);
    }

    public void clickItem(MenuItem item) {
        Intent intent = new Intent(MainActivity.this,MainActivity.class);
        startActivity(intent);

    }

    public void clickLogOut(MenuItem item) {

        finish();
    }

    public void clickAuction(MenuItem item) {

        Intent intent = new Intent(MainActivity.this, ItemsActivity.class);
        startActivity(intent);
    }
}

