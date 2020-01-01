package com.mycookyapp.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mycookyapp.R;
import com.mycookyapp.data.DAO;
import com.mycookyapp.data.UserData;
//import androidx.fragment.app.Fragment;

public class RecipeesFragment extends Fragment {

    public RecipeesFragment() {
        // Required empty public constructor
    }

    public static RecipeesFragment newInstance() {
        RecipeesFragment fragment = new RecipeesFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipees, container, false);
        return view;
    }
}
