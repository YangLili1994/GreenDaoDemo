package com.hzyanglili1.greendaodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hzyanglili1.greendaodemo.entity.User;

public class MainActivity extends AppCompatActivity {

    private DBManager dbManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = DBManager.getInstance(this);


        for (int i=0;i<20;i++){
            User user = new User();
            user.setName("name"+i);
            user.setAge(i);

            dbManager.insertUser(user);
        }


        for(User user : dbManager.queryUserlist()){
            Log.d("haha",user.toString());
        }

        User user = dbManager.queryUserById(10);
        if (user != null) {
            Log.d("haha", "查询得到的user： " + user.toString());
            dbManager.deleteUser(user);
        }

        for(User user0 : dbManager.queryUserlist()){
            Log.d("haha",user0.toString());
        }


        //dbManager.deleteUser();
    }
}
