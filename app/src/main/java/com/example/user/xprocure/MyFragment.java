package com.example.user.xprocure;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by user on 02/02/2017.
 */
public  class MyFragment extends Fragment {
    private TextView textView;

    public static MyFragment getInstance(int position) {
        MyFragment myFragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myFragment.setArguments(args);
        return myFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.my_fragment, container, false);
        textView = (TextView) layout.findViewById(R.id.position);
        Bundle bundle = getArguments();
        if (bundle != null) {
            textView.setText("The Page Selected is" + bundle.getInt ("position"));
        }


       RequestQueue requestQueue = VolleySingleton.getsInstance().getRequestQueue();

        StringRequest request=new StringRequest(Request.Method.GET, "http://www.google.com", new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(),"RESPONSE"+ response,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"ERROR"+ error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
        requestQueue.add(request);
        return layout;
    }
}
