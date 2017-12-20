package com.codegym.readnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codegym.readnews.R;
import com.codegym.readnews.model.NewsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Han on 12/16/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context mContext;
    private List<NewsModel.NewsBean> newsModelList;
    private OnItemClickListener onItemClickListener;


    public NewsAdapter(Context mContext, OnItemClickListener onItemClickListener) {
        this.mContext = mContext;
        this.newsModelList = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    public void setNewsModelList(List<NewsModel.NewsBean> newsModelList) {
        this.newsModelList = newsModelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NewsModel.NewsBean newsBean = newsModelList.get(position);
        holder.bindData(newsBean);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(newsBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsModelList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(NewsModel.NewsBean newsModel);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvatar;
        private TextView tvTitle;
        private Context mContext;

        public ViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }

        public void bindData(NewsModel.NewsBean newsBean) {
            String imgRegex = "(?i)<img[^>]+?src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
            Pattern p = Pattern.compile(imgRegex);
            Matcher m = p.matcher(newsBean.getDescription());
            String imgSrc = null;
            while(m.find()) {
                imgSrc = m.group(1);
            }
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.picture);
            Glide.with(mContext)
                    .load(imgSrc)
                    .apply(requestOptions)
                    .into(imgAvatar);
            tvTitle.setText(newsBean.getTitle());
        }
    }

}
