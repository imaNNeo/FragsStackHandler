package com.neo.fragsstackhandler.app;

import android.app.Activity;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.neo.fragsstackhandler.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class FooterNavHolder implements View.OnClickListener {

    public static final byte FOOTER_NAV_PAGE1 = 1;
    public static final byte FOOTER_NAV_PAGE2 = 2;
    public static final byte FOOTER_NAV_PAGE3 = 3;
    public static final byte FOOTER_NAV_PAGE4 = 4;
    public static final byte FOOTER_NAV_PAGE5 = 5;
    @IntDef({FOOTER_NAV_PAGE1, FOOTER_NAV_PAGE2, FOOTER_NAV_PAGE3, FOOTER_NAV_PAGE4, FOOTER_NAV_PAGE5})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FooterNavPos{}

    @FooterNavPos
    int currentSelected = -1;

    public TextView tvPage1,tvPage2,tvPage3,tvPage4,tvPage5;
    View.OnClickListener mOnClickListener;
    Activity act;
    public FooterNavHolder(){}
    public void init(Activity act){
        this.act = act;
        tvPage1 = act.findViewById(R.id.tv_page1);
        tvPage1.setOnClickListener(this);
        tvPage2 = act.findViewById(R.id.tv_page2);
        tvPage2.setOnClickListener(this);
        tvPage3 = act.findViewById(R.id.tv_page3);
        tvPage3.setOnClickListener(this);
        tvPage4 = act.findViewById(R.id.tv_page4);
        tvPage4.setOnClickListener(this);
        tvPage5 = act.findViewById(R.id.tv_page5);
        tvPage5.setOnClickListener(this);
    }

    public void setOnClickListener(View.OnClickListener listener){
        mOnClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        if(view==tvPage1){
            currentSelected = FOOTER_NAV_PAGE1;
        }else if(view==tvPage2){
            currentSelected = FOOTER_NAV_PAGE2;
        }else if(view==tvPage3){
            currentSelected = FOOTER_NAV_PAGE3;
        }else if(view==tvPage4){
            currentSelected = FOOTER_NAV_PAGE4;
        }else if(view==tvPage5){
            currentSelected = FOOTER_NAV_PAGE5;
        }

        refreshSelected();

        if(mOnClickListener!=null) mOnClickListener.onClick(view);
    }

    private void refreshSelected() {
        switch (currentSelected){
            case FOOTER_NAV_PAGE1:
                onPage1Selected();
                break;

            case FOOTER_NAV_PAGE2:
                onPage2Selected();
                break;

            case FOOTER_NAV_PAGE3:
                onPage3Selected();
                break;

            case FOOTER_NAV_PAGE4:
                onPage4Selected();
                break;

            case FOOTER_NAV_PAGE5:
                onPage5Selected();
                break;

        }
    }

    public void onPage1Selected(){
        setPage1Selected(true);
        setPage2Selected(false);
        setPage3Selected(false);
        setPage4Selected(false);
        setPage5Selected(false);
    }
    public void onPage2Selected(){
        setPage1Selected(false);
        setPage2Selected(true);
        setPage3Selected(false);
        setPage4Selected(false);
        setPage5Selected(false);
    }
    public void onPage3Selected(){
        setPage1Selected(false);
        setPage2Selected(false);
        setPage3Selected(true);
        setPage4Selected(false);
        setPage5Selected(false);
    }
    public void onPage4Selected(){
        setPage1Selected(false);
        setPage2Selected(false);
        setPage3Selected(false);
        setPage4Selected(true);
        setPage5Selected(false);
    }
    public void onPage5Selected(){
        setPage1Selected(false);
        setPage2Selected(false);
        setPage3Selected(false);
        setPage4Selected(false);
        setPage5Selected(true);
    }

    @FooterNavPos
    public int getCurrentPos(){
        return currentSelected;
    }
    public void setCurrentPos(@FooterNavPos int pos){
        currentSelected = pos;
        refreshSelected();
    }

    private void setPage1Selected(boolean selected){
        if(selected){
            tvPage1.setBackgroundColor(ContextCompat.getColor(act,R.color.footer_page1_color));
        }else{
            tvPage1.setBackgroundColor(ContextCompat.getColor(act,R.color.footer_default_color));
        }
    }
    private void setPage2Selected(boolean selected){
        if(selected){
            tvPage2.setBackgroundColor(ContextCompat.getColor(act,R.color.footer_page2_color));
        }else{
            tvPage2.setBackgroundColor(ContextCompat.getColor(act,R.color.footer_default_color));
        }
    }
    private void setPage3Selected(boolean selected){
        if(selected){
            tvPage3.setBackgroundColor(ContextCompat.getColor(act,R.color.footer_page3_color));
        }else{
            tvPage3.setBackgroundColor(ContextCompat.getColor(act,R.color.footer_default_color));
        }
    }
    private void setPage4Selected(boolean selected){
        if(selected){
            tvPage4.setBackgroundColor(ContextCompat.getColor(act,R.color.footer_page4_color));
        }else{
            tvPage4.setBackgroundColor(ContextCompat.getColor(act,R.color.footer_default_color));
        }
    }
    private void setPage5Selected(boolean selected){
        if(selected){
            tvPage5.setBackgroundColor(ContextCompat.getColor(act,R.color.footer_page5_color));
        }else{
            tvPage5.setBackgroundColor(ContextCompat.getColor(act,R.color.footer_default_color));
        }
    }
}