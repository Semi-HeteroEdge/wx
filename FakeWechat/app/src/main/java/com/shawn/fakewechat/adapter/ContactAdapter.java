package com.shawn.fakewechat.adapter;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.shawn.fakewechat.R;
import com.shawn.fakewechat.bean.ContactShowInfo;
import java.util.List;
public class ContactAdapter extends ArrayAdapter {
    private List<ContactShowInfo> contactInfos;
    private Context context;
    private int resource;
    public ContactAdapter(@NonNull Context context, @LayoutRes int resource, List<ContactShowInfo> contactInfos) {
        super(context, resource, contactInfos);
        this.contactInfos = contactInfos;
        this.context = context;
        this.resource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder vh;
        ContactShowInfo contactInfo = contactInfos.get(position);

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resource, parent, false);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
        //初始化item控件
        vh.headImg.setImageResource(contactInfo.getHeadImage());
        vh.username.setText(contactInfo.getUsername());

        return view;
    }

    class ViewHolder {
        private TextView username;
        private ImageView headImg;

        public ViewHolder(View v) {
            username = (TextView) v.findViewById(R.id.item_wechat_main_tv_username);

            headImg = (ImageView) v.findViewById(R.id.item_wechat_main_iv_headimg);
            
        }
    }


}
