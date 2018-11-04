package com.shawn.fakewechat;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.LinkedList;
import java.util.List;
import com.shawn.fakewechat.adapter.ContactAdapter;
import com.shawn.fakewechat.bean.ContactShowInfo;
import com.shawn.fakewechat.utils.HelpUtils;
import com.shawn.fakewechat.utils.ServerActivity;
public class WechatActivity extends AppCompatActivity {
    public static final int TYPE_USER = 0x11;
    public static final int TYPE_SERVICE = 0X12;
    public static final int TYPE_SUBSCRIBE = 0x13;
    private int toolbarHeight, statusBarHeight;
    public String message;
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_main);
        Intent intent1=getIntent();
        message=intent1.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Log.i("zhy","收到"+message);

        HelpUtils.transparentNav(this);

        Toolbar bar = findViewById(R.id.activity_wechat_toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setTitle("");
        initData();

        bar.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        toolbarHeight = bar.getMeasuredHeight();
        statusBarHeight = HelpUtils.getStatusBarHeight(WechatActivity.this);

        Button button=(Button)findViewById(R.id.zhuxiao);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(WechatActivity.this,MainActivity.class);
                Toast.makeText(WechatActivity.this, "您已成功注销！", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });

    }

    private void initData() {
        ListView lv = findViewById(R.id.activity_wechat_lv);
        int[] headImgRes = {R.drawable.hdimg_3,  R.drawable.user_2,
                R.drawable.user_3, R.drawable.user_4};

        String[] usernames = {"fiona", "zhy", "肖箫", "唐小晓"};

        List<ContactShowInfo> infos = new LinkedList<>();
        int k=0;
        for (int i=0;i < headImgRes.length;i++) {
            if(!usernames[i].equals(message)){
                Log.i("zhy","no相等"+usernames[i]+"+"+i);
                infos.add(k, new ContactShowInfo(headImgRes[i], usernames[i]));
                k=k+1;
            }


        }
        ContactAdapter adapter = new ContactAdapter(this, R.layout.item_wechat_main, infos);
        lv.setAdapter(adapter);

        lv.setOnTouchListener(new View.OnTouchListener() {
            int preX, preY;
            boolean isSlip = false, isLongClick = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        preX = (int) event.getX();
                        preY = (int) event.getY();
                        mHandler.postDelayed(() -> {
                            isLongClick = true;
                            int x = (int) event.getX();
                            int y = (int) event.getY();
                            //延时500ms后，其Y的坐标加入了Toolbar和statusBar高度
                            int position = lv.pointToPosition(x, y - toolbarHeight - statusBarHeight);


                        }, 500);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        int nowX = (int) event.getX();
                        int nowY = (int) event.getY();

                        int movedX = Math.abs(nowX - preX);
                        int movedY = Math.abs(nowY - preY);
                        if (movedX > 50 || movedY > 50) {
                            isSlip = true;
                            mHandler.removeCallbacksAndMessages(null);
                            //处理滑动事件
                        }
                        break;


                    case MotionEvent.ACTION_UP:
                        mHandler.removeCallbacksAndMessages(null);
                        if (!isSlip && !isLongClick) {
                            //处理单击事件
                            int position = lv.pointToPosition(preX, preY);

                            Intent intent = new Intent(WechatActivity.this, ServerActivity.class);
                            intent.putExtra("name", usernames[position]);
                            intent.putExtra("profileId", headImgRes[position]);
                            startActivity(intent);
                        } else {
                            isSlip = false;
                            isLongClick = false;
                        }
                        break;
                }
                return false;
            }
        });
    }




        @Override
    public boolean onTouchEvent(MotionEvent event){
           finish();
           return true;
       }

}

