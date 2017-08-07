package com.neo.fragsstackhandler.fragsStackHandler;

import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.neo.fragsstackhandler.R;
import com.neo.fragsstackhandler.app.frags.main.MainFragPage1;
import com.neo.fragsstackhandler.app.frags.main.MainFragPage2;
import com.neo.fragsstackhandler.app.frags.main.MainFragPage3;
import com.neo.fragsstackhandler.app.frags.main.MainFragPage4;
import com.neo.fragsstackhandler.app.frags.main.MainFragPage5;

import java.util.ArrayList;


/**
 * Created by iman.
 * iman.neofight@gmail.com
 */


public class MainFragmentsHandler {
    public FragmentManager fragManager;
    @IdRes
    int fragsContainerResId = 0;

    ArrayList<Fragment> mFragsStackList;
    ArrayList<Fragment> mFragsOnFragmentManager;

    public MainFragmentsHandler(FragmentManager fm){
        fragManager = fm;
        mFragsStackList = new ArrayList<>();
        mFragsOnFragmentManager = new ArrayList<>();
    }

    public String getCurrentFragTag() {
        if(mFragsStackList.size()>0)
            return mFragsStackList.get(mFragsStackList.size()-1).getTag();
        return "";
    }
    public Fragment getCurrentFragment(){
        if(mFragsStackList.size()>0)
            return mFragsStackList.get(mFragsStackList.size()-1);
        return null;
    }
    public boolean isFragmentExistsInStack(Fragment frag){
        if(frag==null)return false;
        if(mFragsStackList ==null)return false;
        for(Fragment f : mFragsStackList){
            if(f.equals(frag))
                return true;
        }
        return false;
    }
    public boolean isFragmentExistsInFragManager(Fragment frag){
        if(frag==null)return false;
        if(mFragsOnFragmentManager ==null)return false;
        for(Fragment f : mFragsOnFragmentManager){
            if(f.equals(frag))
                return true;
        }
        return false;
    }

    public void moveFragToFrontOfStack(Fragment target){
        if(target==null)return;
        if(mFragsStackList ==null)return;
        int fragPos = -1;
        for(int i = 0; i< mFragsStackList.size(); i++) {
            Fragment frag = mFragsStackList.get(i);
            if(frag.equals(target))
                fragPos = i;
        }

        target = mFragsStackList.get(fragPos);
        if(fragPos== mFragsStackList.size()-1){
        }else {
            for (int i = fragPos; i < mFragsStackList.size() - 1; i++) {
                mFragsStackList.set(i, mFragsStackList.get(i + 1));
            }
            mFragsStackList.set(mFragsStackList.size()-1,target);
        }
    }


    public void setFragsContainerResId(@IdRes int resId){
        fragsContainerResId = resId;
    }

    public int getCount(){
        return mFragsStackList.size();
    }
    public void pop(){
        Fragment currFrag = getCurrentFragment();
        mFragsStackList.remove(currFrag);

        String tag = currFrag.getTag();
        if(fragManager.findFragmentByTag(tag)!=null) {
            android.support.v4.app.FragmentTransaction ft = fragManager.beginTransaction();
            ft.hide(currFrag);
            ft.commit();
        }

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                hideAllFragsNonFront();
            }
        });
    }

    private void openFragmentsMain(Fragment frag, String tag, boolean withAnim){
        if(fragsContainerResId==0){
            Log.d("SS","fragsContainerResId==0");
            throw new RuntimeException("Dude please let me know your fragmentsContainerResId!!");
        }

        if(isFragmentExistsInStack(frag)){
            moveFragToFrontOfStack(frag);
        }else{
            if(mFragsStackList ==null)
                mFragsStackList = new ArrayList<>();
            mFragsStackList.add(frag);

            if(!isFragmentExistsInFragManager(frag)){
                android.support.v4.app.FragmentTransaction ft = fragManager.beginTransaction();
                if (withAnim)
                    ft.setCustomAnimations(
                            R.anim.frags_enter_right,
                            R.anim.frags_exit_right,
                            R.anim.frags_enter_right,
                            R.anim.frags_exit_right);
                ft.add(fragsContainerResId, frag, tag);
                mFragsOnFragmentManager.add(frag);
                ft.commit();
            }
        }

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                hideAllFragsNonFront();
            }
        });
    }

    private void hideAllFragsNonFront() {
        android.support.v4.app.FragmentTransaction ft = fragManager.beginTransaction();
        for(int i = 0; i< mFragsStackList.size()-1; i++) {
            Fragment fg = mFragsStackList.get(i);
            ft.hide(fg);
        }

        Fragment fg = mFragsStackList.get(mFragsStackList.size()-1);
        ft.show(fg);
        ft.commit();
    }


    public MainFragPage1 mainFragPage1;
    public void openMainFragPage1(){
        if(mainFragPage1 ==null) {
            mainFragPage1 = new MainFragPage1();
            mainFragPage1.setRetainInstance(true);
        }
        openFragmentsMain(mainFragPage1,MainFragPage1.class.getName(),false);
    }

    public MainFragPage2 mainFragPage2;
    public void openMainFragPage2(){
        if(mainFragPage2 ==null) {
            mainFragPage2 = new MainFragPage2();
            mainFragPage2.setRetainInstance(true);
        }
        openFragmentsMain(mainFragPage2,MainFragPage2.class.getName(),false);
    }

    public MainFragPage3 mainFragPage3;
    public void openMainFragPage3(){
        if(mainFragPage3 ==null) {
            mainFragPage3 = new MainFragPage3();
            mainFragPage3.setRetainInstance(true);
        }
        openFragmentsMain(mainFragPage3,MainFragPage3.class.getName(),false);
    }

    public MainFragPage4 mainFragPage4;
    public void openMainFragPage4(){
        if(mainFragPage4 ==null) {
            mainFragPage4 = new MainFragPage4();
            mainFragPage4.setRetainInstance(true);
        }
        openFragmentsMain(mainFragPage4,MainFragPage4.class.getName(),false);
    }

    public MainFragPage5 mainFragPage5;
    public void openMainFragPage5(){
        if(mainFragPage5 ==null) {
            mainFragPage5 = new MainFragPage5();
            mainFragPage5.setRetainInstance(true);
        }
        openFragmentsMain(mainFragPage5,MainFragPage5.class.getName(),false);
    }



}