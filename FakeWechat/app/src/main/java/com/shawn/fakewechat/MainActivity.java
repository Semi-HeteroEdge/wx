package com.shawn.fakewechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE="com.my.asus.myapplication3.MESSAGE";
    String name1="zhy";
    String pwd1="zhy";
    String name2="fiona";
    String pwd2="fiona";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this,WechatActivity.class);
        EditText editText=findViewById(R.id.et_login_name);
        EditText password=findViewById(R.id.et_login_pwd);
        String message=editText.getText().toString();
        String pwd=password.getText().toString();

        if (!message.isEmpty()) {
            if(message.contentEquals(name1)&&pwd.contentEquals(pwd1)){
                Toast.makeText(getApplicationContext(), "连接服务器成功", Toast.LENGTH_SHORT).show();
                intent.putExtra(EXTRA_MESSAGE,message);
                startActivity(intent);
            }else if (message.contentEquals(name2)&&pwd.contentEquals(pwd2)){
                Toast.makeText(getApplicationContext(), "连接服务器成功", Toast.LENGTH_SHORT).show();
                intent.putExtra(EXTRA_MESSAGE,message);
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(MainActivity.this, "未输入用户名或密码", Toast.LENGTH_SHORT).show();
        }
    }
}