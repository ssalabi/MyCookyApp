package com.mycookyapp.screens;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mycookyapp.R;
import com.mycookyapp.data.DAO;
import com.mycookyapp.data.UserData;
import com.mycookyapp.login.LoginActivity;
import com.mycookyapp.login.SharedPrefManager;
import com.mycookyapp.login.User;


public class AccountFragment extends Fragment {


    private TextView email;
    private TextView username;
    private TextView gender;
    private DAO dao;

    public AccountFragment() {
        // Required empty public constructor
    }


    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        email = view.findViewById(R.id.text_email);
        username = view.findViewById(R.id.text_user_name);
        gender = view.findViewById(R.id.text_gender);

        dao = DAO.getInstance();

        UserData userData = dao.getUserData("0");

        email.setText(userData.getEmail());
        username.setText(userData.getUserName());
        gender.setText(userData.getGender());

        //////////////////////////////////////////////////////////////////////////////////////////////

        //if the user is not logged in
        //starting the login activity
        if (!SharedPrefManager.getInstance(getContext()).isLoggedIn()) {
            getActivity().finish();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }





        //getting the current user
        User user = SharedPrefManager.getInstance(getContext()).getUser();

        //setting the values to the textviews
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        gender.setText(user.getGender());


        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        return view;
    }


}
