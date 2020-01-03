package com.mycookyapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycookyapp.R;
import com.mycookyapp.adapters.RecipeAdapter;
import com.mycookyapp.data.DAO;
import com.mycookyapp.data.Recipe;

import java.util.List;
//import androidx.fragment.app.Fragment;

public class RecipeesFragment extends Fragment {

    private  RecipeClickListener listener;
    private  RecyclerView recycler;
    private DAO dao;

    public RecipeesFragment() {
        // Required empty public constructor
    }

    public RecipeesFragment(RecipeClickListener listener) {
        this.listener = listener;
    }

    public static RecipeesFragment newInstance(RecipeClickListener listener) {
        RecipeesFragment fragment = new RecipeesFragment(listener);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipees, container, false);

        recycler = (RecyclerView)view.findViewById(R.id.recycler);
        dao = DAO.getInstance(new DAO.DAOListener() {
            @Override
            public void onRecipesReady(List<Recipe> recipes) {
                loadRecycler(recipes);
            }

            @Override
            public void onRecipeDetailsReady(Recipe recipe) {

            }
        });

        List recipes = dao.getRecipes();
        if(recipes != null){
            loadRecycler(recipes);
        }

        return view;
    }

    private void loadRecycler(List recipes) {
        RecipeAdapter adapter = new RecipeAdapter(recipes, getContext(), new RecipeAdapter.RecipeClickListener() {
            @Override
            public void onClick(String id) {
                ((RecipeClickListener)getActivity()).onClick(id);
            }
        });
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public interface RecipeClickListener{
        public void onClick(String id);
    }
}
