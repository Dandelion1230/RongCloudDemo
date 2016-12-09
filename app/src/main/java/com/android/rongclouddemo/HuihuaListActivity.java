package com.android.rongclouddemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import io.rong.imkit.RongIM;

public class HuihuaListActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huihua_list);
        if (RongIM.getInstance() == null || RongIM.getInstance().getRongIMClient() == null){
            Toast.makeText(HuihuaListActivity.this, "没有连接成功", Toast.LENGTH_SHORT).show();
        }
    }
}
