package com.codegym.readnews.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codegym.readnews.R;
import com.codegym.readnews.model.Category;
import com.codegym.readnews.model.NewsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Han on 12/16/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context mContext;
    private List<Category> categoryList;
    private OnItemClickListener onItemClickListener;


    public CategoryAdapter(Context mContext, OnItemClickListener onItemClickListener) {
        this.mContext = mContext;
        this.categoryList = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Category category = categoryList.get(position);
        holder.bindData(category);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(category);
            }
        });
    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Category category);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCategory;
        private TextView tvCategory;
        private Context mContext;

        public ViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            ivCategory = itemView.findViewById(R.id.ivCategory);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }

        public void bindData(Category category) {
            tvCategory.setText(category.getTitle());
            RequestOptions requestOptions = new RequestOptions()
                    .override(getScreenWidth()/2,getScreenWidth()/2)
                    .placeholder(R.drawable.picture);
            Glide.with(mContext)
                    .load(category.getUrlImage())
                    .apply(requestOptions)
                    .into(ivCategory);
        }
    }

}
