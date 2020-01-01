package com.mycookyapp.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mycookyapp.R;
import com.mycookyapp.RecipeAdapter;
import com.mycookyapp.data.DAO;
import com.mycookyapp.data.UserData;

import java.util.List;
//import androidx.fragment.app.Fragment;

public class RecipeesFragment extends Fragment {

    private  RecyclerView recycler;
    private DAO dao;

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

        recycler = (RecyclerView)view.findViewById(R.id.recycler);
        dao = new DAO();
        List recipes = dao.getRecipes();
        RecipeAdapter adapter = new RecipeAdapter(recipes, getContext());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
