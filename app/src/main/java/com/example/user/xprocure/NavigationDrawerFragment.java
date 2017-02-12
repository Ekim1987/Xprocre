package com.example.user.xprocure;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements MikeAdapter.ClickListener {

    private RecyclerView recyclerView;

    public static final String PREf_FILE_NAME = "textpref";
    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    public MikeAdapter adapter;
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private View containerView;
    private boolean isDrawerOpened = false;

    private boolean mUserLearnerDrawer;
    private boolean mFromSavedInstanceState;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnerDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drwaerList);
        adapter = new MikeAdapter(getActivity(), getData());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(),"onClick"+position,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(),"onLongClick"+position,Toast.LENGTH_LONG).show();
            }
        }));
        return layout;
    }

    public static List<Information> getData() {
        List<Information> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_phone_android_black_24dp, R.drawable.ic_tablet_mac_black_24dp, R.drawable.ic_tablet_black_24dp, R.drawable.ic_watch_black_24dp};
        String[] titles = {"Smart Phone", "Apple Table", "LG Phone", "Smart Watch"};
        for (int i = 0; i < 100; i++) {
            Information current = new Information();
            current.itemId = icons[i % icons.length];
            current.title = titles[i % titles.length];
            data.add(current);


        }
        return data;
    }


    public void setup(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolBar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolBar, R.string.Opened_drawer, R.string.Drawer_Closed) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnerDrawer) {
                    mUserLearnerDrawer = true;
                    saveToPrefences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnerDrawer + "");

                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();


            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset < 0.5) {
                    toolBar.setAlpha((1 - slideOffset));
                }
            }

        };

        if (!mUserLearnerDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {

                mDrawerToggle.syncState();
            }
        });
    }

    private static void saveToPrefences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharePreferences = context.getSharedPreferences(PREf_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();

    }

    public static String readFromPreferences(Context context, String preferencesName, String defaultValue) {
        SharedPreferences sharedPrefences = context.getSharedPreferences(PREf_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPrefences.getString(preferencesName, defaultValue);

    }

    @Override
    public void itemClicked(View view, int position) {
        startActivity(new Intent(getActivity(), SubActivity.class));

    }
}

class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
    private GestureDetector gestureDetector;
    private ClickListener clickListener;

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
this.clickListener=clickListener;
        Log.d("MIKE", "constructor invoked");
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                Log.d("MIKE", "onSingleTapUp" + motionEvent);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                View child=recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());
                if(child!=null && clickListener!=null){
                    clickListener.onLongClick(child,recyclerView.getChildAdapterPosition(child));
                }
                Log.d("MIKE", "onLongPress" + motionEvent);
            }


        });
    }

        @Override
        public boolean onInterceptTouchEvent (RecyclerView rv, MotionEvent e){
            View child=rv.findChildViewUnder(e.getX(),e.getY());

            Log.d("MIKE", "onInterceptTouchEvent" +gestureDetector.onTouchEvent(e)+""+e);
            return false;
        }

        @Override
        public void onTouchEvent (RecyclerView rv, MotionEvent e){
            Log.d("MIKE", "onTouchEvent" + e);

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent ( boolean disallowIntercept){
            Log.d("MIKE", "onRequestDisallowInterceptTouchEvent");
        }


    }


