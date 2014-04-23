package com.colombiana.ingsoftware;

import android.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.parse.ParseUser;


public class VisitadorActivity extends FragmentActivity implements ActionBar.TabListener {

    ViewPager viewPager;
    ActionBar actionBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitador);

        viewPager = (ViewPager) findViewById(R.id.pager);

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab1 = actionBar.newTab();
        tab1.setIcon(R.drawable.store_icon);
        tab1.setTabListener(this);

        ActionBar.Tab tab2 =  actionBar.newTab();
        tab2.setIcon(R.drawable.icon_producto);
        tab2.setTabListener(this);

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.visitador, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:
                ParseUser.logOut();
                logOut();
                Log.d("My", "Salio");
                return true;
            case R.id.perfil:
                perfil();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logOut() {
        Intent logout = new Intent(VisitadorActivity.this, LoginActivity.class);
        startActivity(logout);
        finish();
    }

    private void perfil()
    {
        Intent perfil = new Intent(VisitadorActivity.this, PerfilActivity.class);
        startActivity(perfil);
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }

    class MyAdapter extends FragmentPagerAdapter
    {
        public  MyAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if(position == 0)
            {
                fragment = new Tienda();
            }
            if(position == 1)
            {
                fragment = new Producto();
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
