package com.android.practiceintentactivityresultlauncher;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.practiceintentactivityresultlauncher.databinding.ActivityDetailBinding;
import com.android.practiceintentactivityresultlauncher.databinding.ActivityMainBinding;
import com.android.practiceintentactivityresultlauncher.databinding.ItemDetailBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    ArrayList<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 자신의 실행했더 인텐트 객체 얻기
        Intent intent = getIntent();
        // 그 인텐트 객체에 담긴 데이터 얻기
        String category = intent.getStringExtra("category");

        DBHelper helper=new DBHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select location from tb_data where category=?", new String[]{category});

        datas = new ArrayList<>();
        while (cursor.moveToNext()){
            datas.add(cursor.getString(0));
        }
        db.close();

        binding.detailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.detailRecyclerView.addItemDecoration(new DividerItemDecoration(this, new LinearLayoutManager(this).getOrientation()));
        binding.detailRecyclerView.setAdapter(new MySubAdapter(datas));
    }

    private class MySubAdapter extends RecyclerView.Adapter<MySubViewHolder> {
        private List<String> list;

        public MySubAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public MySubViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            ItemDetailBinding binding = ItemDetailBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            return new MySubViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(MySubViewHolder viewHolder, int position) {
            String text = list.get(position);
            viewHolder.binding.itemDetailView.setText(text);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class MySubViewHolder extends RecyclerView.ViewHolder {
        ItemDetailBinding binding;

        public MySubViewHolder(ItemDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            // 나열된 항목 중 하나가 선택되면 데이터를 포함해서 이전 화면으로 자동 전환 해주기
            binding.getRoot().setOnClickListener(view -> {
                Intent intent = getIntent();
                intent.putExtra("result", binding.itemDetailView.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            });
            
        }
    }

}