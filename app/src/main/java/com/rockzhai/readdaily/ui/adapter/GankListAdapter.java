package com.rockzhai.readdaily.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.bean.gank.Gank;
import com.rockzhai.readdaily.ui.activity.GankActivity;
import com.rockzhai.readdaily.ui.activity.PictureActivity;

import java.util.List;

/**
 * Created by rockzhai on 2017/4/27.
 * Contact Me : zhaidyan@gmail.com
 */

public class GankListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Gank> mList;
    public GankListAdapter(Context context,List<Gank> list) {
        this.context = context;
        this.mList = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gank_meizhi,parent,false);
        return new GankMeizhiViewholder(rootView) ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GankMeizhiViewholder) {
            GankMeizhiViewholder gankMeizhiViewHolder = (GankMeizhiViewholder) holder;
            gankMeizhiViewHolder.bindItem(mList.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class GankMeizhiViewholder extends RecyclerView.ViewHolder {
        private CardView mCardView_meizhi;
        private ImageView iv_meizhi;
        private TextView tv_meizhi_title;

        public GankMeizhiViewholder(View itemView) {
            super(itemView);
            mCardView_meizhi = (CardView) itemView.findViewById(R.id.card_meizhi);
            iv_meizhi = (ImageView) itemView.findViewById(R.id.iv_meizhi);
            tv_meizhi_title = (TextView) itemView.findViewById(R.id.tv_meizhi_title);
        }

        public void bindItem(Gank meizhi) {
            tv_meizhi_title.setText(meizhi.getDesc());
            Glide.with(context).load(meizhi.getUrl()).centerCrop().into(iv_meizhi);

            // 图片点击事件
            iv_meizhi.setOnClickListener(V -> {
              Intent intent = PictureActivity.newIntent(context,meizhi.getUrl(),meizhi.getDesc());
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)context,iv_meizhi,PictureActivity.TRANSIT_PIC);

                // 5.0 +
                try {
                    ActivityCompat.startActivity((Activity)context,intent,optionsCompat.toBundle());
                }catch (Exception e) {
                    e.printStackTrace();
                    context.startActivity(intent);
                }

            });
            // card 点击事件
            mCardView_meizhi.setOnClickListener(v -> {
                Toast.makeText(context, "点击事件测试", Toast.LENGTH_SHORT).show();
                Intent intent = GankActivity.newIntent(context,meizhi.getPublishedAt().getTime());
                context.startActivity(intent);
            });
        }



    }
}
