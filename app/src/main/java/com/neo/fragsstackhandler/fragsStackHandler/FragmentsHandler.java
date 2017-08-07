
package com.neo.fragsstackhandler.fragsStackHandler;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.neo.fragsstackhandler.R;
import com.neo.fragsstackhandler.app.frags.inner.InnerFragment;


public class FragmentsHandler {
    public FragmentManager fragManager;
    @IdRes
    int fragsContainerResId = 0;

    public FragmentsHandler(FragmentManager fm){
        fragManager = fm;
    }

    public String getCurrentFragTag() {

        if (fragManager.getBackStackEntryCount() == 0) {
            return null;
        }

        String tag = fragManager.getBackStackEntryAt(fragManager.getBackStackEntryCount() - 1)
                .getName();

        return tag;
    }
    public Fragment getCurrentFragment(){
        return (Fragment) fragManager.findFragmentByTag(getCurrentFragTag());
    }


    public void setFragsContainerResId(@IdRes int resId){
        fragsContainerResId = resId;
    }

    private void openFragment(Fragment frag, String tag, boolean withAnim){
        if(fragsContainerResId==0){
            Log.d("SS","fragsContainerResId==0");
            return;
        }

        android.support.v4.app.FragmentTransaction ft = fragManager.beginTransaction();
        if(withAnim)
            ft.setCustomAnimations(
                    R.anim.frags_enter_right,
                    R.anim.frags_exit_right,
                    R.anim.frags_enter_right,
                    R.anim.frags_exit_right);
        ft.add(fragsContainerResId, frag, tag);
        ft.addToBackStack(tag);
        ft.commit();
    }

    private void openFragmentsMain(Fragment frag, String tag, boolean withAnim){
        if(fragsContainerResId==0){
            Log.d("SS","fragsContainerResId==0");
            return;
        }

        android.support.v4.app.FragmentTransaction ft = fragManager.beginTransaction();
        if(withAnim)
            ft.setCustomAnimations(
                    R.anim.frags_enter_right,
                    R.anim.frags_exit_right,
                    R.anim.frags_enter_right,
                    R.anim.frags_exit_right);
        ft.add(fragsContainerResId, frag, tag);
        ft.addToBackStack(tag);
        ft.commit();
    }


    public InnerFragment innerFragment;
    public void openInnerFragment(String title, int color){
        innerFragment = InnerFragment.getInstance(title,color);
        openFragment(innerFragment,InnerFragment.class.getName(),true);
    }

    public void removeFragment(Fragment frag){
        fragManager.beginTransaction().remove(frag).commit();
    }
    public void gotoPreviousFrag(){
        fragManager.popBackStack();
    }

    public void popAllFragments() {
        for(int i = 0; i < fragManager.getBackStackEntryCount(); ++i) {
            fragManager.popBackStack();
        }
    }

    public void popAllFragmentsTo(String tag) {
        for(int i = 0; i < fragManager.getBackStackEntryCount(); ++i) {
            FragmentManager.BackStackEntry be = fragManager.getBackStackEntryAt(i);
                fragManager.popBackStack();
            if(tag.equals(be.getName())) {
                return;
            }
        }
    }
}