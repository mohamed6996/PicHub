package com.pic.hub.pichub;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
import com.pic.hub.pichub.adapter.ViewPagerAdapter;
import com.pic.hub.pichub.fragments.CategoryFragment;
import com.pic.hub.pichub.fragments.EditorChoiceFragment;
import com.pic.hub.pichub.fragments.FavoriteFragment;
import com.pic.hub.pichub.fragments.PopularFragment;
import com.pic.hub.pichub.fragments.RecentFragment;

import io.fabric.sdk.android.Fabric;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ArrayList<Fragment> fragmentList;
    ArrayList<String> titleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        prepareFragments();

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //  viewPager.setOffscreenPageLimit(0);
        viewPager.setCurrentItem(1, true);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void prepareFragments() {
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        addData(new CategoryFragment(), "category");
        addData(new RecentFragment(), "recent");
        addData(new PopularFragment(), "popular");
        addData(new EditorChoiceFragment(), "editors choice");
        addData(new FavoriteFragment(), "favorite");

    }

    private void addData(Fragment fragment, String title) {
        fragmentList.add(fragment);
        titleList.add(title);
    }


}
