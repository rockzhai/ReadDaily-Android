package com.rockzhai.readdaily.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.bean.gank.Gank;
import com.rockzhai.readdaily.ui.activity.GankWebViewActivity;

import java.util.List;

/**
 * Created by rockzhai on 2017/4/30.
 */
public class GankActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Gank> gankList;

    public GankActivityAdapter(Context context, List<Gank> gankList) {
        this.context = context;
        this.gankList = gankList;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = View.inflate(parent.getContext(), R.layout.item_gank_list, null);
        return new GankVideoViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GankVideoViewHolder) {
            GankVideoViewHolder gankVideoViewHolder = (GankVideoViewHolder) holder;
            gankVideoViewHolder.bindItem(gankList.get(position));
        } else if (holder instanceof EmptyViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return gankList.size();
    }

    /**
     * type = Empty
     */
    class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * type = Video
     */
    class GankVideoViewHolder extends RecyclerView.ViewHolder {

        CardView card_gank;
        ImageView iv_type;
        TextView tv_type;
        TextView tv_desc;
        TextView tv_who;
        ImageView iv_type_bg;

        public GankVideoViewHolder(View itemView) {
            super(itemView);
            // initview
            card_gank = (CardView) itemView.findViewById(R.id.card_gank);
            iv_type = (ImageView) itemView.findViewById(R.id.iv_type);
            iv_type_bg = (ImageView) itemView.findViewById(R.id.iv_type_bg);
            tv_desc = (TextView) itemView.findViewById(R.id.tv_desc);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type);
            tv_who = (TextView) itemView.findViewById(R.id.tv_who);
        }

        public void bindItem(Gank gank) {
            switch (gank.getType()) {
                case "Android":
                    Glide.with(context).load(R.drawable.android).centerCrop().into(iv_type);
                    iv_type_bg.setBackgroundResource(R.color.android);
                    break;
                case "iOS":
                    Glide.with(context).load(R.drawable.ios).centerCrop().into(iv_type);
                    iv_type_bg.setBackgroundResource(R.color.ios);
                    break;
                case "休息视频":
                    Glide.with(context).load(R.drawable.video).centerCrop().into(iv_type);
                    iv_type_bg.setBackgroundResource(R.color.休息视频);
                    break;
                case "前端":
                    Glide.with(context).load(R.drawable.web).centerCrop().into(iv_type);
                    iv_type_bg.setBackgroundResource(R.color.前端);
                    break;
                case "拓展资源":
                    Glide.with(context).load(R.drawable.tuozhan).centerCrop().into(iv_type);
                    iv_type_bg.setBackgroundResource(R.color.拓展资源);
                    break;
                case "瞎推荐":
                    Glide.with(context).load(R.drawable.tuijian).centerCrop().into(iv_type);
                    iv_type_bg.setBackgroundResource(R.color.瞎推荐);
                    break;
            }

            tv_type.setText("来自话题 : " + gank.getType());
            tv_desc.setText(gank.getDesc());
            tv_who.setText("via : " + gank.getWho());

            card_gank.setOnClickListener(v -> context.startActivity(GankWebViewActivity.newIntent(context, gank.getUrl())));

        }
    }
}
