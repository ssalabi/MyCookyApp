package com.mycookyapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mycookyapp.R;
import com.mycookyapp.data.DAO;
import com.mycookyapp.data.Recipe;
import com.mycookyapp.data.UserData;

import java.util.List;


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

        return view;
    }


}
