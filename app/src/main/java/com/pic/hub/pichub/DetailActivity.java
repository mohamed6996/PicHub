package com.pic.hub.pichub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pic.hub.pichub.fragments.DetailFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportFragmentManager().beginTransaction().add(R.id.detail_container, new DetailFragment()).commit();
    }
}
