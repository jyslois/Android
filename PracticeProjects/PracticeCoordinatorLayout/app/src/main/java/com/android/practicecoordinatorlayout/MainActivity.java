package com.android.practicecoordinatorlayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.practicecoordinatorlayout.databinding.ActivityMainBinding;
import com.android.practicecoordinatorlayout.databinding.ItemBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("Item=" + i);
        }
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new MyAdapter(list));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private List<String> list;

        public MyAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            ItemBinding binding = ItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            return new MyViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(MyViewHolder viewHolder, int position) {
            String text = list.get(position);
            viewHolder.binding.itemTextView.setText(text);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        ItemBinding binding;

        public MyViewHolder(ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}