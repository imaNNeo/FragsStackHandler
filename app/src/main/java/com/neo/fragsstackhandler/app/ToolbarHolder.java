
package com.neo.fragsstackhandler.app;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.neo.fragsstackhandler.R;

/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class ToolbarHolder {

    Toolbar mToolbar;
    ImageView ivBack,ivAdd;

    AppCompatActivity act;

    public void init(AppCompatActivity activity){
        mToolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        ivBack = mToolbar.findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.onBackPressed();
            }
        });
        ivAdd = mToolbar.findViewById(R.id.iv_add);
        activity.setSupportActionBar(mToolbar);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mToolbar.setContentInsetsAbsolute(0, 0);
        act = activity;
    }
}