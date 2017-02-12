package com.example.user.xprocure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by user on 02/02/2017.
 */

public class pageFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static pageFragment newInstance(int page) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        pageFragment fragment = new pageFragment();
        fragment.setArguments(args);
        return fragment;
    }//b6:18:ef:15:9d:a2:43:41:d5:cf:11:b1:d5:e3:45:26
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getInt(ARG_PAGE);
    }
    @Override
    public View onCreateView (LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState ){
        View view =inflater.inflate(R.layout.fragementpage,container,false);

        return view;
    }
}
