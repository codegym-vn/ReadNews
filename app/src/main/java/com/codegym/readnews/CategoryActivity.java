package com.codegym.readnews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codegym.readnews.adapter.CategoryAdapter;
import com.codegym.readnews.adapter.NewsAdapter;
import com.codegym.readnews.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements CategoryAdapter.OnItemClickListener{
    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        rcvCategory = findViewById(R.id.rcvCategory);
        rcvCategory.setLayoutManager(new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false));
        categoryList = new ArrayList<>();
        categoryList.add(new Category(R.drawable.world,"Thế giới"));
        categoryList.add(new Category(R.drawable.health,"Sức khỏe"));
        categoryList.add(new Category(R.drawable.startup,"Thời sự"));
        categoryList.add(new Category(R.drawable.sport,"Thể thao"));
        categoryList.add(new Category(R.drawable.family,"Gia đình"));
        categoryList.add(new Category(R.drawable.car,"Xe"));
        categoryAdapter = new CategoryAdapter(CategoryActivity.this,this);
        categoryAdapter.setCategoryList(categoryList);
        rcvCategory.setAdapter(categoryAdapter);
    }

    @Override
    public void onItemClick(Category category) {
        Intent intent = new Intent(CategoryActivity.this,MainActivity.class);
        intent.putExtra("Title", category.getTitle());
        startActivity(intent);
    }
}
