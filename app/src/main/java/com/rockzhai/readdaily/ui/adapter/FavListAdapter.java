package com.rockzhai.readdaily.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.bean.Essay.EssayForDB;
import com.rockzhai.readdaily.ui.activity.EssayActivity;

import java.util.ArrayList;

/**
 * Created by rockzhai on 2017/5/4.
 * Contact Me : zhaidyan@gmail.com
 */

public class FavListAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<EssayForDB> mList;

    public FavListAdapter(Context context, ArrayList<EssayForDB> list) {
        this.context = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favessay_list, parent, false);
        return new EssayHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EssayHolder) {
            EssayHolder gankVideoViewHolder = (EssayHolder) holder;
            gankVideoViewHolder.bindItem(mList.get(position));
        } else if (holder instanceof EmptyViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * type = Empty
     */
    class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }



    class EssayHolder extends RecyclerView.ViewHolder {
        CardView card_Essay;
        TextView title;
        TextView author;
        TextView digest;
        ImageView iv_type_bg;

        public EssayHolder(View itemView) {
            super(itemView);
            card_Essay = (CardView) itemView.findViewById(R.id.card_fav);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            digest = (TextView) itemView.findViewById(R.id.tv_digest);
            author = (TextView) itemView.findViewById(R.id.tv_author);
            iv_type_bg = (ImageView) itemView.findViewById(R.id.iv_type_bg);
        }

        public void bindItem(EssayForDB essay) {

            iv_type_bg.setBackgroundResource(R.color.android);
            title.setText("我的收藏 : " + essay.getTitle());
            digest.setText(Html.fromHtml(essay.getDigest()));
            author.setText("via : " + essay.getAuthor());

             card_Essay.setOnClickListener(v -> context.startActivity(EssayActivity.newIntent(context,essay)));

        }
    }
}
