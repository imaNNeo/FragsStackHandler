package com.neo.fragsstackhandler.app.frags.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neo.fragsstackhandler.R;
import com.neo.fragsstackhandler.fragsStackHandler.FragmentsHandler;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class MainFragPage2 extends Fragment {

    public FragmentsHandler mFragmentsHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentsHandler = new FragmentsHandler(getChildFragmentManager());
        mFragmentsHandler.setFragsContainerResId(R.id.child_fragment_container);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.frag_main_page2,container,false);
    }

}
