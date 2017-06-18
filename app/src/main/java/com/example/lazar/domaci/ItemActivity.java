package com.example.lazar.domaci;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.lazar.domaci.adapters.ViewPagerAdapter;
import com.example.lazar.domaci.fragment.AuctionFragment;
import com.example.lazar.domaci.fragment.DetailsFragment;

/**
 * Created by Lazar on 5/7/2017.
 */

public class ItemActivity  extends AppCompatActivity{

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);

        tabLayout =(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new DetailsFragment(),"Detalji");
        viewPagerAdapter.addFragments(new AuctionFragment(),"Aukcije");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
