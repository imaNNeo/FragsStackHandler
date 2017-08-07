package com.neo.fragsstackhandler.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.neo.fragsstackhandler.R;
import com.neo.fragsstackhandler.app.frags.main.MainFragPage1;
import com.neo.fragsstackhandler.app.frags.main.MainFragPage2;
import com.neo.fragsstackhandler.app.frags.main.MainFragPage3;
import com.neo.fragsstackhandler.app.frags.main.MainFragPage4;
import com.neo.fragsstackhandler.app.frags.main.MainFragPage5;
import com.neo.fragsstackhandler.fragsStackHandler.FragmentsHandler;
import com.neo.fragsstackhandler.fragsStackHandler.MainFragmentsHandler;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private long lastBackPressed = 0;

    FooterNavHolder mFooterNavHolder;
    ToolbarHolder mToolbarHolder;
    public MainFragmentsHandler mMainFragsHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainFragsHandler = new MainFragmentsHandler(getSupportFragmentManager());
        mMainFragsHandler.setFragsContainerResId(R.id.fl_container);

        init();

        mMainFragsHandler.openMainFragPage1();
        mFooterNavHolder.setCurrentPos(FooterNavHolder.FOOTER_NAV_PAGE1);
    }

    private void init() {
        initToolbar();
        initFooter();
    }

    private void initToolbar() {
        mToolbarHolder = new ToolbarHolder();
        mToolbarHolder.init(this);
        mToolbarHolder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "Inner Fragment " + (getCurrentChildFragmentsHandler().fragManager.getBackStackEntryCount() + 1);
                int color = new Random().nextInt();
                getCurrentChildFragmentsHandler().openInnerFragment(title,color);
            }
        });

    }

    private void initFooter() {
        mFooterNavHolder = new FooterNavHolder();
        mFooterNavHolder.init(this);
        mFooterNavHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mFooterNavHolder.getCurrentPos()){
                    case FooterNavHolder.FOOTER_NAV_PAGE1:
                        mMainFragsHandler.openMainFragPage1();
                        break;

                    case FooterNavHolder.FOOTER_NAV_PAGE2:
                        mMainFragsHandler.openMainFragPage2();
                        break;

                    case FooterNavHolder.FOOTER_NAV_PAGE3:
                        mMainFragsHandler.openMainFragPage3();
                        break;

                    case FooterNavHolder.FOOTER_NAV_PAGE4:
                        mMainFragsHandler.openMainFragPage4();
                        break;

                    case FooterNavHolder.FOOTER_NAV_PAGE5:
                        mMainFragsHandler.openMainFragPage5();
                        break;

                }
            }
        });
    }

    public FragmentsHandler getCurrentChildFragmentsHandler(){
        FragmentsHandler childFragsHandler = null;
        Fragment currentFrag = mMainFragsHandler.getCurrentFragment();
        switch (mFooterNavHolder.getCurrentPos()){
            case FooterNavHolder.FOOTER_NAV_PAGE1 :
                try {
                    childFragsHandler = ((MainFragPage1)currentFrag).mFragmentsHandler;
                } catch (Exception e) {}
                break;

            case FooterNavHolder.FOOTER_NAV_PAGE2 :
                try {
                    childFragsHandler = ((MainFragPage2)currentFrag).mFragmentsHandler;
                } catch (Exception e) {}
                break;

            case FooterNavHolder.FOOTER_NAV_PAGE3  :
                try {
                    childFragsHandler = ((MainFragPage3)currentFrag).mFragmentsHandler;
                } catch (Exception e) {}
                break;

            case FooterNavHolder.FOOTER_NAV_PAGE4 :
                try {
                    childFragsHandler = ((MainFragPage4)currentFrag).mFragmentsHandler;
                } catch (Exception e) {}
                break;

            case FooterNavHolder.FOOTER_NAV_PAGE5 :
                try {
                    childFragsHandler = ((MainFragPage5)currentFrag).mFragmentsHandler;
                } catch (Exception e) {}
                break;
        }

        return childFragsHandler;
    }

    @Override
    public void onBackPressed() {

        boolean back = true;

        int currentStackCount = getCurrentChildFragmentsHandler().fragManager.getBackStackEntryCount();
        if(currentStackCount>0){
            getCurrentChildFragmentsHandler().fragManager.popBackStackImmediate();
            return;
        }

        if(mMainFragsHandler.getCount()>1){
            mMainFragsHandler.pop();
            refreshFooter();
            return;
        }

        if(System.currentTimeMillis() - lastBackPressed >= 2000) {
            Toast.makeText(this, "Press Back Again", Toast.LENGTH_SHORT).show();
            lastBackPressed = System.currentTimeMillis();
            return;
        }

        super.onBackPressed();
    }

    private void refreshFooter(){
        Fragment frag = mMainFragsHandler.getCurrentFragment();
        if(frag!=null) {
            if (frag instanceof  MainFragPage1) {
                mFooterNavHolder.setCurrentPos(FooterNavHolder.FOOTER_NAV_PAGE1);
            }else if (frag instanceof MainFragPage2) {
                mFooterNavHolder.setCurrentPos(FooterNavHolder.FOOTER_NAV_PAGE2);
            }else if (frag instanceof MainFragPage3) {
                mFooterNavHolder.setCurrentPos(FooterNavHolder.FOOTER_NAV_PAGE3);
            }else if (frag instanceof MainFragPage4) {
                mFooterNavHolder.setCurrentPos(FooterNavHolder.FOOTER_NAV_PAGE4);
            }else if (frag instanceof MainFragPage5) {
                mFooterNavHolder.setCurrentPos(FooterNavHolder.FOOTER_NAV_PAGE5);
            }
        }
    }
}