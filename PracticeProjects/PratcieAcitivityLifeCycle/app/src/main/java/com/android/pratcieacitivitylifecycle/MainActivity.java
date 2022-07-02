package com.android.pratcieacitivitylifecycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.pratcieacitivitylifecycle.databinding.ActivityMainBinding;
import com.android.pratcieacitivitylifecycle.databinding.ItemSubBinding;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> datas;
    MyLab2Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        datas=new ArrayList<>();

        datas.add("onCreate....");

        binding.lab2RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.lab2RecyclerView.addItemDecoration(new DividerItemDecoration(this, new LinearLayoutManager(this).getOrientation()));
        adapter = new MyLab2Adapter(datas);
        binding.lab2RecyclerView.setAdapter(adapter);

        binding.lab2BtnSub.setOnClickListener(view -> {
            Intent intent=new Intent(this, SubActivity.class);
            startActivity(intent);
        });
        binding.lab2BtnDialog.setOnClickListener(view -> {
            Intent intent=new Intent(this, DialogActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        datas.add("onResume....");
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();

        datas.add("onPause....");
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();

        datas.add("onStart....");
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();

        datas.add("onStop....");
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        datas.add("onRestart....");
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        datas.add("onDestory....");
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        datas.add("onSaveInstanceStatel....");
        // 항목이 나오게 하기 위해 목록 갱신
        adapter.notifyDataSetChanged();

        outState.putString("data1", "hello");
        outState.putInt("data2", 100);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        datas.add("onRestoreInstanceState...");
        // 항목이 나오게 하기 위해 목록 갱신
        adapter.notifyDataSetChanged();

        String data1 = savedInstanceState.getString("data1");
        int data2 = savedInstanceState.getInt("data2");

        Toast toast = Toast.makeText(this, data1 + ":" + data2, Toast.LENGTH_SHORT);
        toast.show();
    }




    private class MyLab2Adapter extends RecyclerView.Adapter<MyLab2ViewHolder> {
        private List<String> list;

        public MyLab2Adapter(List<String> list) {
            this.list = list;
        }

        @Override
        public MyLab2ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            ItemSubBinding binding = ItemSubBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            return new MyLab2ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(MyLab2ViewHolder viewHolder, int position) {
            String text = list.get(position);
            viewHolder.binding.subItemTextView.setText(text);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class MyLab2ViewHolder extends RecyclerView.ViewHolder {
        ItemSubBinding binding;
        public MyLab2ViewHolder(ItemSubBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}