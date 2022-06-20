package com.android.practicerecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.practicerecyclerview.databinding.ActivityMainBinding;
import com.android.practicerecyclerview.databinding.ItemBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("Item=" + i);
        }

        // 항목을 어떻게 배치할 건지 정하는 역할.
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        binding.recycleView.setAdapter(new MyAdaptor(list));
        // 항목을 꾸미는 역할
        binding.recycleView.addItemDecoration(new MyItemDecoration());

    }

    // 항목을 구현하기 위한 필요한 View들을 선언하고 획득한다.
    // 그리하여 그 뷰 객체들을 홀딩하고 있는 역할.
    // 예를 들면 항목이 이미지 뷰, 텍스트 뷰1, 텍스트 뷰2로 구성된다고 치면,
    // 그 뷰들을 선언하고 획득하여 객체들을 가지고 있는 역할.
    // 1) 변수들을 선언하고, findViewById로 뷰의 객체를 획득한다.
    // 2) binding 객체를 선언한다
    class MyViewHolder extends RecyclerView.ViewHolder {
        ItemBinding binding;

        MyViewHolder(ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    // 항목을 완성하는 역할. ViewHolder의 View을 이용해서 각각의 항목에
    // 데이터를 넣거나, 이벤트를 걸거나 해서 항목을 완성한다
    class MyAdaptor extends RecyclerView.Adapter<MyViewHolder> {
        private List<String> list;

        public MyAdaptor(List<String> list) {
            this.list = list;
        }

        // MyViewHolder 준비비
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGorup, int i) {
            ItemBinding binding = ItemBinding.inflate(LayoutInflater.from(viewGorup.getContext()), viewGorup, false);
            return new MyViewHolder(binding);
        }

        // 항목 구성: 항목이 x개면 x번 호출된다
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

    class MyItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int index = parent.getChildAdapterPosition(view) + 1;
            if (index % 3 == 0) {
                outRect.set(20, 20, 20, 60);
            } else {
                outRect.set(20, 20, 20, 20);
            }
            view.setBackgroundColor(0xFFECE9E9);
            ViewCompat.setElevation(view, 20.0f);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
            int width = parent.getWidth();
            int height = parent.getHeight();
            Drawable dr = ResourcesCompat.getDrawable(getResources(), R.drawable.android, null);
            int drWidth = dr.getIntrinsicWidth();
            int drHeight = dr.getIntrinsicHeight();
            int left = width / 2 - drWidth / 2;
            int top = height / 2 - drHeight / 2;
            c.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.android), left, top, null);
        }
    }
}




