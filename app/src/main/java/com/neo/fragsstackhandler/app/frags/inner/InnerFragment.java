package com.neo.fragsstackhandler.app.frags.inner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.neo.fragsstackhandler.R;

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class InnerFragment extends Fragment{
    private static final String KEY_TITLE = "keyTitle";
    private static final String KEY_BG_COLOR = "keyBgColor";

    public static InnerFragment getInstance(String title,int bgColor){
        InnerFragment innerFragment = new InnerFragment();
        Bundle b = new Bundle();
        b.putString(KEY_TITLE,title);
        b.putInt(KEY_BG_COLOR,bgColor);
        innerFragment.setArguments(b);
        return innerFragment;
    }




    int selectedBgColor;
    String selectedTitle;


    TextView tvTitle;
    RelativeLayout rlContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedTitle = getArguments().getString(KEY_TITLE);
        selectedBgColor = getArguments().getInt(KEY_BG_COLOR);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_inner,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rlContainer = view.findViewById(R.id.rl_container);
        tvTitle = view.findViewById(R.id.tv_title);

        rlContainer.setBackgroundColor(selectedBgColor);
        tvTitle.setText(selectedTitle);
    }
}
