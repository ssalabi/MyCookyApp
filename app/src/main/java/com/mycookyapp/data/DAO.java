package com.mycookyapp.data;


public class DAO {

    public DAO() {
    }

    public UserData getUserData(String id){
        return new UserData("aaa@gmail", "user0", "female", "0000$");
    }
}
